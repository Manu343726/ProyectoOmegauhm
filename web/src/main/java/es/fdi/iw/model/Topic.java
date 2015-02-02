package es.fdi.iw.model;

import java.util.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;
import javax.transaction.Transactional;

@Entity
@NamedQueries({
    @NamedQuery(name="topicById",
        query="select t from Topic t where t.id = :idParam"),
    @NamedQuery(name="topicsByDate",
        query="select t from Topic t order by t.started desc"),
    @NamedQuery(name="topicsByViews",
        query="select t from Topic t order by t.viewsCount desc")
})
public class Topic {
	static class NoQuestionOnTopicException extends Exception
	{
		public NoQuestionOnTopicException() {
			super("No question post on this thread");
		}
	}
	
	private long id;
	private String title;
	private String tags;
	private List<Post> posts;
	private int viewsCount;
	private Date started;
	
	public Topic() {}
	
	public static Topic createTopic(String title, Post question, String tags) {
		Topic t = new Topic();
		t.title = title;
		t.tags = tags;
		t.posts = new ArrayList<Post>();
		t.viewsCount = 0;
		
		question.setThread(t);
		question.setType(PostType.QUESTION);
		t.posts.add(question);
		
		t.started = question.getDate();
				
		
		return t;
	}
	
	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public int getViewsCount() {
		return viewsCount;
	}
	
	public void setViewsCount(int viewsCount) {
		this.viewsCount = viewsCount;
	}
	
	public void setTags(String tags)
	{
		this.tags = tags;
	}
	
	public String getTags()
	{
		return tags;
	}
	
	@OneToMany(targetEntity=Post.class,
			   fetch = FetchType.EAGER)
	public List<Post> getPosts()
	{
		return posts;
	}
	
	public void setPosts(List<Post> posts)
	{
		this.posts = posts;
	}
	
	public Date getStarted() {
		return started;
	}
	
	public void setStarted(Date started) {
		this.started = started;
	}
	
	public void addAnswer(Post post)
	{
		
		post.setThread(this);
		post.setType(PostType.ANSWER);
		posts.add(post);
	}
	
	@Transient
	public Post getQuestion()
	{
		return posts.get(0);
	}
	
	@Transient
	public int getAnswersCount()
	{
		return posts.size() - 1;
	}
	
	@Transient
	public String getUri()
	{
		return "topic/" + getId() + "/" + getTitle();
	}
}
