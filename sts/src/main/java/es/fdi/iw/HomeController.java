package es.fdi.iw;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.activation.MimetypesFileTypeMap;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import util.IWEntityManager;
import util.IWFileManager;
import util.IWModerationManager;
import es.fdi.iw.model.File;
import es.fdi.iw.model.Moderation;
import es.fdi.iw.model.Topic;
import es.fdi.iw.model.Post;
import es.fdi.iw.model.User;
import es.fdi.iw.model.Vote;

import java.io.FileInputStream;
import java.io.BufferedInputStream;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory
			.getLogger(HomeController.class);

	@PersistenceContext
	private EntityManager entityManager;

	public boolean isLogged(HttpSession session) {
		return session.getAttribute("user") != null;
	}

	/**
	 * Intercepts login requests generated by the header; then continues to load
	 * normal page
	 */
	@RequestMapping(value = "/signinUser", method = RequestMethod.POST)
	@Transactional
	public String signin(HttpServletRequest request, Model model,
			HttpSession session) {
		String formLogin = request.getParameter("login");
		String formPass = request.getParameter("pass");
		logger.info("Sign in attempt from '{}'", formLogin);

		User u = null;
		try {
			
			u = IWEntityManager.get(entityManager).userByLogin(formLogin);

			if (u.isPassValid(formPass)) {
				logger.info("pass was valid");
				session.setAttribute("user", u);
			} else {
				logger.info("pass was NOT valid");
				model.addAttribute("signinError",
						"error en usuario o contrase�a");
			}
		} catch (NoResultException nre) {
			logger.info("no-such-user with login {}", formLogin);
		}

		return "redirect:/";
	}

	@RequestMapping(value = "/joinUser", method = RequestMethod.POST)
	@Transactional
	public String join(HttpServletRequest request, Model model,
			HttpSession session) {
		String formLogin = request.getParameter("login");
		String formEmail = request.getParameter("email");
		String formPass = request.getParameter("pass");
		logger.info("Register attempt from '{}'", formLogin);

		IWEntityManager manager = IWEntityManager.get(entityManager);

		User u = null;

		try {
			u = manager.userByLogin(formLogin);

			logger.info("user {} already exists", formLogin);
		} catch (NoResultException nre) {
			logger.info("no-such-user; creating user {}", formLogin);
			User user = manager.newUser(formLogin, formEmail, formPass);
			session.setAttribute("user", user);
		}

		return "redirect:/";
	}

	/**
	 * Logout (also returns to home view).
	 */
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		logger.info("User '{}' logged out", session.getAttribute("user"));
		session.invalidate();
		return "redirect:/";
	}

	@RequestMapping(value = "/file/fileload", method = RequestMethod.POST)
	@Transactional
	public String handleFileUpload(
			@RequestParam("file") MultipartFile load,
			@RequestParam("tags") String tags,
			HttpSession session) {
		es.fdi.iw.model.File file = ContextInitializer.getFileManager(entityManager)
				.uploadFile(load, tags, (User)session.getAttribute("user"));

		if (file != null) {
			entityManager.persist(file);
		}
		
		logger.info("El fichero se ha creado correctamente");

		return "redirect:/repository";
	}
	
	
	
	/*@RequestMapping(value = "/file/download/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	@ResponseBody
	public FileSystemResource getFile(@PathVariable("id") long id) throws IOException {
		File f = (File) entityManager.createNamedQuery("fileById")
				 .setParameter("idParam", id)
 				 .getSingleResult(); 
		
	    return new FileSystemResource(ContextInitializer.getFileManager().getFile(f));
		
	}*/
	
	/*@RequestMapping(value = "/file/download/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public void getFile(
	    @PathVariable("id") long id, 
	    HttpServletResponse response) throws IOException {

	      // get your file as InputStream
		  File file = (File) entityManager.createNamedQuery("fileById")
					 				 .setParameter("idParam", id)
					 				 .getSingleResult(); 
		  java.io.File f = ContextInitializer.getFileManager().getFile(file);
		  
	      InputStream in = null;
	      in = new BufferedInputStream(new FileInputStream(f));
	      
	      // copy it to response's OutputStream
	      org.apache.commons.io.IOUtils.copy(in, response.getOutputStream());
	      response.setHeader("Content-Disposition", "attachment; filename=" + file.getName());
	      response.flushBuffer();
	}*/
	
	@ResponseBody
	@RequestMapping(value = "/file/download/{id}", method = RequestMethod.GET)
	public byte[] getFile(@PathVariable("id") long id, HttpServletResponse response) throws IOException {
	 
		  File file = IWEntityManager.get(entityManager).fileById(id);
		  
		  java.io.File f = ContextInitializer.getFileManager(entityManager).getFile(file);
		  
		  InputStream in = new BufferedInputStream(new FileInputStream(f));
		  
		  response.setHeader("Content-Disposition", "attachment; filename=" + file.getName()); 
		  
		  String type = Files.probeContentType(f.toPath());
		  if (type != null) {
		     	response.setContentType(type);
		  }
		  
	      return IOUtils.toByteArray(in);
	}

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	@Transactional
	public String empty(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
				DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);
		model.addAttribute("pageTitle", "Bienvenido a IW");

		IWEntityManager manager = IWEntityManager.get(entityManager);
		
		// Mocking
		if (manager.topicsByDate().size() <= 0) {
			
			int threads = 3;
			int answers_per_thread = 3;

			logger.info("Mocking up DB...");

			for (int i = 0; i < threads; ++i) {
				Topic topic = manager.newTopic("user", "Título pregunta " + i,
						"Texto pregunta " + i, "tag1 tag2 tag3");

				for (int j = 0; j < answers_per_thread; ++j)
					manager.answerQuestion(topic, "Texto respuesta " + i + "."
							+ j, "admin");
			}
			
			
		}

		List<Topic> threads = manager.topicsByDate();
		System.err.println(threads.size());
		model.addAttribute("threads", threads);
		
		@SuppressWarnings("unchecked")
		List<File> filesOrderedByDate = (List<File>) entityManager.createNamedQuery("filesByDate")
					  .getResultList();

		model.addAttribute("filesOrderedByDate", filesOrderedByDate);

		return "home";
	}

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String index(Locale locale, Model model) {
		return empty(locale, model);
	}

	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/repository", method = RequestMethod.GET)
	public String repository(Locale locale, Model model) {
		logger.info("ENTRANDO AL REPO");
		List<File> filesOrderedByDate = (List<File>) entityManager.createNamedQuery("filesByDate")
			       												  .getResultList();
		
		model.addAttribute("filesOrderedByDate", filesOrderedByDate);
		
		return "repository";
	}

	/**
	 * 
	 */
	@RequestMapping(value = "/forum", method = RequestMethod.GET)
	public String forum(Locale locale, Model model) {
		logger.info("ENTRANDO AL FORO");
		IWEntityManager manager = IWEntityManager.get(entityManager); 
		List<Topic> threadsOrderedByDate = manager.topicsByDate();
		List<Topic> threadsOrderedByViews = manager.topicsByViews();
		
		model.addAttribute("threadsOrderedByDate", threadsOrderedByDate);
		model.addAttribute("threadsOrderedByViews", threadsOrderedByViews);

		return "forum";
	}

	/**
	 * 
	 */
	@RequestMapping(value = "/moderation", method = RequestMethod.GET)
	@Transactional
	public String moderation(Locale locale, Model model) {
		model.addAttribute("moderationQueue", 
				           IWModerationManager.get(entityManager).moderationByDate());
		
		return "moderation";
	}

	@RequestMapping(value = "/file/select", method = RequestMethod.GET)
	public String fileSelect(Locale locale, Model model) {
		return "fileload";
	}

	/**
	 * 
	 */
	@RequestMapping(value = "/signin", method = RequestMethod.GET)
	public String signin(Locale locale, Model model) {
		return "signin";
	}

	/**
	 * 
	 */
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join(Locale locale, Model model) {
		return "join";
	}

	/**
	 * 
	 */
	@RequestMapping(value = "/publication", method = RequestMethod.GET)
	public String publication(Locale locale, Model model, HttpSession session) {
		if (isLogged(session))
			return "publication";
		else
			return "redirect:/";
	}
	
	/**
	 * 
	 */
	@RequestMapping(value = "/answer", method = RequestMethod.GET)
	public String answer(Locale locale, Model model, HttpSession session) {
		if (isLogged(session))
			return "answer";
		else
			return "redirect:/";
	}

	/**
	 * 
	 */
	@RequestMapping(value = "/resources/leeme", method = RequestMethod.GET)
	public String leeme(Locale locale, Model model) {
		return "LEEME";
	}

	@Transactional
	@RequestMapping(value = "/newpost", method = RequestMethod.POST)
	public String newpost(HttpServletRequest request, Model model,
			HttpSession session) {
		String formText = request.getParameter("text");
		String formTitle = request.getParameter("title");
		String formTags = request.getParameter("tags");

		logger.info(formTitle);
		logger.info(formText);
		logger.info(formTags);

		
		User user = (User) session.getAttribute("user");

		if (user == null)
			return "404";
		
		IWEntityManager.get(entityManager).newTopic(user, formTitle, formText, formTags);

		return "redirect:/forum";
	}
	
	@Transactional
	@RequestMapping(value = "/newanswer", method = RequestMethod.POST)
	public String newanswer(HttpServletRequest request, Model model,
			HttpSession session) {
		String formText = request.getParameter("text");
		
		User user = (User) session.getAttribute("user");

		if (user == null)
			return "404";
		
		//IWEntityManager.get(entityManager).answerQuestion(long topic_id, formText, user);

		return "redirect:/forum";
	}

	/**
	 * Opens topic
	 */
	@Transactional
	@RequestMapping(value = "/topic/{id}/{title}", method = RequestMethod.GET)
	public String topic(@PathVariable("id") long id,
			@PathVariable("title") String title, HttpSession session,
			HttpServletRequest request) {
		
		Topic topic = IWEntityManager.get(entityManager).topicById(id);

		topic.setViewsCount(topic.getViewsCount() + 1);

		session.setAttribute("topic", topic);

		// Workaround to hibernate lazy initialization issue
		session.setAttribute("topic_question", topic.getQuestion());
		session.setAttribute("topic_posts", topic.getPosts());
		session.setAttribute("topic_asker", topic.getQuestion().getOwner());

		// Some logging for logging purposes and to force lazy initialization of
		// answers list
		for (Post answer : topic.getPosts())
			logger.info("Post [id=" + answer.getId() + ",owner="
					+ answer.getOwner().getLogin() + ",points="
					+ answer.getVotes() + "]");

		return "topic";
	}

	/**
	 * Votes on post
	 */
	@Transactional
	@RequestMapping(value = "/vote/{id}/{value}", method = RequestMethod.GET)
	public String vote(@PathVariable("id") long id,
			@PathVariable("value") int value, HttpSession session,
			HttpServletRequest request) {

		if (!this.isLogged(session))
			return "401"; // go fuck yourself
		
		Post post = IWEntityManager.get(entityManager).postById(id);

		Vote vote = IWEntityManager.get(entityManager).votePost(id, (User)session.getAttribute("user"), value >= 0);
		
		System.err.println(vote);
		
		return "redirect:/" + post.getUri();
	}
	
	@Transactional
	@RequestMapping(value = "/moderation/dimiss/{id}", method = RequestMethod.GET)
	public String dimissModerationEvent(@PathVariable("id") long id, HttpSession session,
			HttpServletRequest request) {
		
		if(isLogged(session)) {		
			IWModerationManager.ModerationResult  result = IWModerationManager.get(entityManager)
					.dimissEvent(id, (User)session.getAttribute("user"));
			
			session.setAttribute("moderationResult", result);
			session.setAttribute("moderationQueue", 
			           IWModerationManager.get(entityManager).moderationQueue());
	
			return "moderation";
		}
		else
			return "redirect:/login";
	}
	
	@Transactional
	@RequestMapping(value = "/moderation/accept/{id}", method = RequestMethod.GET)
	public String acceptModerationEvent(@PathVariable("id") long id, HttpSession session,
			HttpServletRequest request) {
		
		if(isLogged(session)) {
			IWModerationManager.ModerationResult result = IWModerationManager.get(entityManager)
					.acceptModeration(ContextInitializer.getFileManager(entityManager),
	            		         	  id, (User)session.getAttribute("user"));
			
			session.setAttribute("moderationResult", result);
			session.setAttribute("moderationQueue", 
			           IWModerationManager.get(entityManager).moderationQueue());
	
			return "redirect:/moderation";	
		}
		else
			return "redirect:/login";
	}
}
