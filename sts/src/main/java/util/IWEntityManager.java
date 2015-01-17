package util;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import javassist.NotFoundException;
import es.fdi.iw.model.Post;
import es.fdi.iw.model.Topic;
import es.fdi.iw.model.User;
import es.fdi.iw.model.Vote;

public class IWEntityManager {
	private EntityManager manager;
	
	public IWEntityManager(EntityManager manager)
	{
		this.manager = manager;
	}
	
	public User userByLogin(String login)
	{
		return (User)manager.createNamedQuery("userByLogin")
                			.setParameter("loginParam", login)
        					.getSingleResult();
	}
	
	public Topic topicByTitle(String title)
	{
		return (Topic)manager.createNamedQuery("topicByTitle")
    						 .setParameter("title", title)
						 	 .getSingleResult();
	}
	
	public Topic topicById(long id)
	{
		return (Topic)manager.createNamedQuery("topicById")
    						 .setParameter("id", id)
						 	 .getSingleResult();
	}
	
	public Post postById(long id)
	{
		return (Post)manager.createNamedQuery("postById")
                			.setParameter("idParam", id)
        					.getSingleResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Topic> topicsByOwner(String login)
	{
		return (List<Topic>)manager.createNamedQuery("topicsByOwner")
    						 	   .setParameter("login", login)
    						 	   .getResultList();
	}
	
	public Topic newTopic(User user, String title, String text, String tags)
	{
		Post question = Post.createPost(text, user);
		Topic thread = Topic.createTopic(title, question, tags);
		
		manager.persist(question);
		manager.persist(thread);
		
		return thread;
	}
	
	public Topic newTopic(String login, String title, String text, String tags)
	{
		return newTopic(userByLogin(login), title, text, tags);
	}
	
	public User newUser(String login, String email, String password)
	{
		User user = User.createUser(login, password, "user", email);
		manager.persist(user);
		
		return user;
	}
	
	public Post newPost(Topic topic, String text, String login)
	{
		User user = userByLogin(login);
		Post post = Post.createPost(text, user);
		
		post.setOwner(user);
		post.setThread(topic);
		
		manager.persist(post);
		manager.persist(topic);
		
		return post;
	}
	
	public Post newPost(String topic_title, String text, String login)
	{
		return newPost(topicByTitle(topic_title), text, login);
	}
	
	public Post newPost(long topic_id, String text, String login)
	{
		return newPost(topicById(topic_id), text, login);
	}
	
	public Vote votePost(Post post, User user, boolean positive){
		if(post.getOwner() != user){
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
}
