package es.fdi.iw.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Post {
	private long id;
	private String text;
	private int upVotes;
	private int downVotes;
	private Date date;
	private User owner;

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