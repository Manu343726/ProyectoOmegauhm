package es.fdi.iw.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.persistence.Entity;
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
    @NamedQuery(name="topicByTitle",
        query="select t from Topic t where t.title = :title")
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
	private Post question;
	private List<Post> answers;
	
	private int answersCount;
	
	public Topic() {}
	
	public static Topic createTopic(String title, Post question, String tags) {
		Topic t = new Topic();
		t.title = title;
		t.question = question;
		t.question.setType(PostType.QUESTION);
		t.question.setThread(t);
		t.tags = tags;
		t.answers = new ArrayList<Post>();
		t.answersCount = 0;
		
		question.setThread(t);
		
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
	
	public void setTags(String tags)
	{
		this.tags = tags;
	}
	
	public String getTags()
	{
		return tags;
	}
	
	@OneToMany(targetEntity=Post.class,
			   mappedBy="thread")//La relación pertenece al post
	public List<Post> getAnswers()
	{
		return answers;
	}
	
	public void setAnswers(List<Post> posts)
	{
		this.answers = posts;
	}
	
	@OneToOne
	public Post getQuestion()
	{
		return question;
	}
	
	public void setQuestion(Post question)
	{
		this.question = question;
	}
	
	public void addAnswer(User user, String answer)
	{
		Post post = Post.createPost(answer, user);
		post.setThread(this);
		post.setType(PostType.ANSWER);
		answers.add(post);
		answersCount++;
	}
	
	@Transient
	public int getAnswerscount()
	{
		return answersCount;
	}
	
	@Transient
	public String getURI()
	{
		return "topic/" + getId() + "/" + getTitle();
	}
}
