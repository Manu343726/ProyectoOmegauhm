package util;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import javassist.NotFoundException;
import es.fdi.iw.model.File;
import es.fdi.iw.model.Moderation;
import es.fdi.iw.model.ModerationEvent;
import es.fdi.iw.model.Post;
import es.fdi.iw.model.PostType;
import es.fdi.iw.model.Topic;
import es.fdi.iw.model.User;
import es.fdi.iw.model.Vote;

public class IWEntityManager {
	private EntityManager manager;

	private IWEntityManager(EntityManager manager) {
		this.manager = manager;
	}
	
	public static IWEntityManager get(EntityManager manager) {
		return new IWEntityManager(manager);
	}
	
	/**************************** USER ****************************/
	/**************************************************************/

	public User userByLogin(String login) {
		return (User) manager.createNamedQuery("userByLogin")
							 .setParameter("loginParam", login)
							 .getSingleResult();
	}

	public User newUser(String login, String email, String password) {
		User user = User.createUser(login, password, "admin", email);
		manager.persist(user);

		return user;
	}

	/**************************** POST ****************************/
	/**************************************************************/

	public Post postById(long id) {
		return (Post) manager.createNamedQuery("postById")
				.setParameter("idParam", id).getSingleResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Post> postsByUserAndTopic(long userId, long topicId) {
		return (List<Post>) manager.createNamedQuery("postsByUserAndTopic")
							 	   .setParameter("userIdParam", userId)
							 	   .setParameter("topicIdParam", topicId)
							       .getResultList();
	}
	
	public boolean userHasAnswer(long userId, long topicId) {
		List<Post> userPosts = postsByUserAndTopic(userId, topicId);
		
		if(userPosts.isEmpty())
			return false;
		else if(userPosts.size() == 1)
			return userPosts.get(0).getType() == PostType.ANSWER;
		else
			return true; //Si hay dos al menos uno es una respuesta
	}

	public Vote votePost(Post post, User user, boolean positive) {
		
		List<Vote> votes = votesByUserAndPost(user.getId(), post.getId());
		
		if(votes.isEmpty() && !post.getOwner().equals(user)) {
			Vote vote = Vote.createVote(user, post, positive);
			
			manager.persist(vote);
			
			return vote;
		}
		else
			return null;
			
	}
	
	public Vote votePost(long postId, User user, boolean positive){
		return votePost(postById(postId), user, positive);
	}

	public Post answerQuestion(Topic topic, String text, User user) {
		if(!userHasAnswer(user.getId(), topic.getId())) {
			Post post = Post.createPost(text, user);
			
			topic.addAnswer(post);
	
			manager.persist(post);
			manager.persist(topic);
			
			IWModerationManager.get(manager).moderateNewAnswer(post);
	
			return post;
		}
		else
			return null;
	}
	
	public Post answerQuestion(Topic topic, String text, String login) {
		return answerQuestion(topic, text, userByLogin(login));
	}

	public Post answerQuestion(long topic_id, String text, User user) {
		return answerQuestion(topicById(topic_id), text, user);
	}
	
	

	/**************************** TOPIC ****************************/
	/***************************************************************/

	public Topic topicById(long id) {
		return (Topic) manager.createNamedQuery("topicById")
				.setParameter("idParam", id).getSingleResult();
	}

	@SuppressWarnings("unchecked")
	public List<Topic> topicsByDate() {
		return (List<Topic>) manager.createNamedQuery("topicsByDate")
				.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Topic> topicsByViews() {
		return (List<Topic>) manager.createNamedQuery("topicsByViews")
				.getResultList();
	}

	public Topic newTopic(User user, String title, String text, String tags) {
		Post question = Post.createPost(text, user);
		Topic thread = Topic.createTopic(title, question, tags);

		manager.persist(question);
		manager.persist(thread);
		
		IWModerationManager.get(manager).moderateNewTopic(thread);

		return thread;
	}

	public Topic newTopic(String login, String title, String text, String tags) {
		return newTopic(userByLogin(login), title, text, tags);
	}
	
	
	/**************************** VOTE ****************************/
	/***************************************************************/
	@SuppressWarnings("unchecked")
	public List<Vote> votesByUserAndPost(long userId, long postId) {
		return (List<Vote>) manager.createNamedQuery("votesByUserAndPost")
							 	   .setParameter("userIdParam", userId)
							 	   .setParameter("postIdParam", postId)
							       .getResultList();
	}
	
	/**************************** VOTE ****************************/
	/***************************************************************/
	public File fileById(long id) {
		return (File)manager.createNamedQuery("fileById")
			                .setParameter("idParam", id)
 	             		    .getSingleResult();
	}
}
