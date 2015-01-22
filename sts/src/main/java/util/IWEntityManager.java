package util;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import javassist.NotFoundException;
import es.fdi.iw.model.Post;
import es.fdi.iw.model.Topic;
import es.fdi.iw.model.User;
import es.fdi.iw.model.Vote;

public class IWEntityManager {
	private EntityManager manager;

	public IWEntityManager(EntityManager manager) {
		this.manager = manager;
	}
	
	/**************************** USER ****************************/
	/**************************************************************/

	public User userByLogin(String login) {
		return (User) manager.createNamedQuery("userByLogin")
							 .setParameter("loginParam", login)
							 .getSingleResult();
	}

	public User newUser(String login, String email, String password) {
		User user = User.createUser(login, password, "user", email);
		manager.persist(user);

		return user;
	}

	/**************************** POST ****************************/
	/**************************************************************/

	public Post postById(long id) {
		return (Post) manager.createNamedQuery("postById")
				.setParameter("idParam", id).getSingleResult();
	}

	public Vote votePost(Post post, User user, boolean positive) {
		
		try {
			Vote v = null;
			v = voteByUserAndPost(user.getId(), post.getId());	
		}
		catch (NoResultException nre) {
			if(post.getOwner() != user){
				Vote vote = Vote.createVote(user, post, positive);
				
				manager.persist(vote);
				
				return vote;
			} 
			else
				return null;
		}
		return null;
			
	}
	
	public Vote votePost(long postId, User user, boolean positive){
		return votePost(postById(postId), user, positive);
	}

	public Post answerQuestion(Topic topic, String text, String login) {
		User user = userByLogin(login);
		Post post = Post.createPost(text, user);
		
		topic.addAnswer(post);

		manager.persist(post);
		manager.persist(topic);

		return post;
	}

	public Post answerQuestion(long topic_id, String text, String login) {
		return answerQuestion(topicById(topic_id), text, login);
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

		return thread;
	}

	public Topic newTopic(String login, String title, String text, String tags) {
		return newTopic(userByLogin(login), title, text, tags);
	}
	
	
	/**************************** VOTE ****************************/
	/***************************************************************/
	public Vote voteByUserAndPost(long userId, long postId) {
		return (Vote) manager.createNamedQuery("voteByUserAndPost")
							 .setParameter("userIdParam", userId)
							 .setParameter("postIdParam", postId)
							 .getSingleResult();
	}

}
