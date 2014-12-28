package es.fdi.iw.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
@NamedQueries({
    @NamedQuery(name="postById",
        query="select p from Post p where p.id = :idParam")
})
public class Post {
	private long id;
	private String text;
	private int upVotes;
	private int downVotes;
	private Date date;
	private User owner;
	
	@ManyToOne
	private Topic thread;
	
	@Enumerated(EnumType.STRING)
	private PostType type; //true is a question, false is an answer
	
	public Post() {}
	
	public static Post createPost(String text, User owner) {
		Post p = new Post();
		p.text = text;
		p.owner = owner;
		p.thread = null;
		p.upVotes = 0;
		p.downVotes = 0;
		
		return p;
	}
	
	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}	
	
	@ManyToOne(targetEntity=User.class)
	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}
	
	@ManyToOne(targetEntity=Topic.class)
	public Topic getThread() {
		return thread;
	}
	
	public void setThread(Topic thread) {
		this.thread = thread;
	}

	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public int getUpVotes() {
		return upVotes;
	}
	
	public void setUpVotes(int upVotes) {
		this.upVotes = upVotes;
	}
	
	public int getDownVotes() {
		return downVotes;
	}
	
	public void setDownVotes(int upVotes) {
		this.downVotes = upVotes;
	}
	
	@Transient
	public int getVotes()
	{
		return getUpVotes() - getDownVotes();
	}
	
	public PostType getType()
	{
		return type;
	}
	
	public void setType(PostType type)
	{
		this.type = type;
	}
}