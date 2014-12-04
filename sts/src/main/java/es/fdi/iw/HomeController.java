package es.fdi.iw;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import es.fdi.iw.model.Topic;
import es.fdi.iw.model.Post;
import es.fdi.iw.model.User;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@PersistenceContext
	private EntityManager entityManager;
	
	/**
	 * Intercepts login requests generated by the header; then continues to load normal page
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@Transactional
	public String login(HttpServletRequest request, Model model, HttpSession session) {
		String formLogin = request.getParameter("login");
		String formPass = request.getParameter("pass");
		String formSource = request.getParameter("source");
		logger.info("Login attempt from '{}' while visiting '{}'", formLogin, formSource);
		
		// validate request
		if (formLogin == null || formLogin.length() < 4 || formPass == null || formPass.length() < 4) {
			model.addAttribute("loginError", "usuarios y contraseñas: 4 caracteres mínimo");
		} else {
			User u = null;
			try {
				u = (User)entityManager.createNamedQuery("userByLogin")
					.setParameter("loginParam", formLogin).getSingleResult();
				if (u.isPassValid(formPass)) {
					logger.info("pass was valid");				
					session.setAttribute("user", u);
				} else {
					logger.info("pass was NOT valid");
					model.addAttribute("loginError", "error en usuario o contraseña");
				}
			} catch (NoResultException nre) {
				if (formPass.length() == 4) {
					// UGLY: register new users if they do not exist and pass is 4 chars long
					logger.info("no-such-user; creating user {}", formLogin);				
					User user = User.createUser(formLogin, formPass, "user");
					entityManager.persist(user);				
					session.setAttribute("user", user);
				} else {
					logger.info("no such login: {}", formLogin);
				}
				model.addAttribute("loginError", "error en usuario o contraseña");
			}
		}
		
		// redirects to view from which login was requested
		//return "redirect:" + formSource;
		return "home";
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

	/**
	 * Uploads a photo for a user
	 * @param id of user 
	 * @param photo to upload
	 * @return
	 */
	@RequestMapping(value="/user", method=RequestMethod.POST)
    public @ResponseBody String handleFileUpload(@RequestParam("photo") MultipartFile photo,
    		@RequestParam("id") String id){
        if (!photo.isEmpty()) {
            try {
                byte[] bytes = photo.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(
                        		new FileOutputStream(ContextInitializer.getFile("user", id)));
                stream.write(bytes);
                stream.close();
                return "You successfully uploaded " + id + 
                		" into " + ContextInitializer.getFile("user", id).getAbsolutePath() + "!";
            } catch (Exception e) {
                return "You failed to upload " + id + " => " + e.getMessage();
            }
        } else {
            return "You failed to upload a photo for " + id + " because the file was empty.";
        }
    }

	/**
	 * Displays user details
	 */
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public String user(HttpSession session, HttpServletRequest request) {		
		return "user";
	}	
	
	/**
	 * Returns a users' photo
	 * @param id id of user to get photo from
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value="/user/photo", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
	public byte[] userPhoto(@RequestParam("id") String id) throws IOException {
	    File f = ContextInitializer.getFile("user", id);
	    InputStream in = null;
	    if (f.exists()) {
	    	in = new BufferedInputStream(new FileInputStream(f));
	    } else {
	    	in = new BufferedInputStream(
	    			this.getClass().getClassLoader().getResourceAsStream("unknown-user.jpg"));
	    }
	    
	    return IOUtils.toByteArray(in);
	}
	
	/**
	 * Toggles debug mode
	 */
	@RequestMapping(value = "/debug", method = RequestMethod.GET)
	public String debug(HttpSession session, HttpServletRequest request) {
		String formDebug = request.getParameter("debug");
		logger.info("Setting debug to {}", formDebug);
		session.setAttribute("debug", 
				"true".equals(formDebug) ? "true" : "false");
		return "redirect:/";
	}

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String empty(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate);
		model.addAttribute("pageTitle", "Bienvenido a IW");
		
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
	 * A not-very-dynamic view that shows an "about us".
	 */
	@RequestMapping(value = "/about", method = RequestMethod.GET)
	@Transactional
	public String about(Locale locale, Model model) {
		logger.info("User is looking up 'about us'");
		List<User> us = entityManager.createQuery("select u from User u").getResultList();
		System.err.println(us.size());
		model.addAttribute("users", us);
		model.addAttribute("pageTitle", "IW: Quienes somos");
		return "about";
	}
	
	/**
	 * 
	 */
	@RequestMapping(value = "/repository", method = RequestMethod.GET)
	public String repository(Locale locale, Model model) {
		return "repository";
	}
	
	/**
	 * 
	 */
	@RequestMapping(value = "/forum", method = RequestMethod.GET)
	public String forum(Locale locale, Model model) {
		logger.info("ENTRANDO AL FORO");
		List<Topic> ts = entityManager.createQuery("select t from Topic t").getResultList();
		System.err.println(ts.size());
		model.addAttribute("threads", ts);
		return "forum";
	}
	
	/**
	 * 
	 */
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String admin(Locale locale, Model model) {
		return "admin";
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
	@RequestMapping(value = "/publication", method = RequestMethod.GET)
	public String publication(Locale locale, Model model) {
		return "publication";
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
	public String newpost(HttpServletRequest request, Model model, HttpSession session) {
		String formText = request.getParameter("text");
		String formTitle = request.getParameter("title");
		logger.info(formTitle);
		logger.info(formText);
		
		User user = (User)session.getAttribute("user");
		
		logger.info("Usuario {}", user.toString());
		
		Topic topic = Topic.createTopic(formTitle);
		entityManager.persist(topic);
		
		Post post = Post.createPost(formText, user, topic);
		entityManager.persist(post);
		
		/*try {
			post = (Post)entityManager.createNamedQuery("postByOwner")
					.setParameter("ownerParam", user).getSingleResult();
			
			logger.info("Post {}", post.toString());
		}catch (NoResultException nre) {
			logger.info("No encuentro el post");
		}*/
		
		return "forum";
	}
	
}
