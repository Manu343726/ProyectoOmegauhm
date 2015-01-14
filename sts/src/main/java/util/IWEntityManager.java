package util;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import javassist.NotFoundException;
import es.fdi.iw.model.Post;
import es.fdi.iw.model.Topic;
import es.fdi.iw.model.User;

public class IWEntityManager {
	private EntityManager manager;
	
	public IWEntityManager(EntityManager manager)
	{
		this.manager = manager;
	}
	
	
	
	/************** CONSULTAS **************/
	/***************************************/
	
	//// TABLA USER
	
	public User userByLogin(String login)
	{
		return (User)manager.createNamedQuery("userByLogin")
                			.setParameter("loginParam", login)
        					.getSingleResult();
	}
	
	//// TABLA TOPIC
	
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
	
	@SuppressWarnings("unchecked")
	public List<Topic> topicsByOwner(String login)
	{
		return (List<Topic>)manager.createNamedQuery("topicsByOwner")
    						 	   .setParameter("login", login)
    						 	   .getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Topic> topicsByDate()
	{
		return (List<Topic>)manager.createNamedQuery("topicsByDate")
								   .getResultList();
	}
	
	
	
	/************** CREACIÓN DE ELEMENTOS **************/
	/***************************************************/
	
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
}
