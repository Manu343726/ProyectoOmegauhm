package es.fdi.iw.model;

import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({
    @NamedQuery(name="postByOwner",
        query="select p from Post p where p.owner = :ownerParam")
})
public class Post {
	private long id;
	private String text;
	private int upVotes;
	private int downVotes;
	private Date date;
	private User owner;
	private Hilo thread;
	
	public Post() {}
	
	public static Post createPost(String text, User owner, Hilo thread) {
		Post p = new Post();
		p.text = text;
		p.owner = owner;
		p.thread = thread;
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
	
	@ManyToOne(targetEntity=Hilo.class)
	public Hilo getThread() {
		return thread;
	}
	
	public void setThread(Hilo thread) {
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
	
	
}