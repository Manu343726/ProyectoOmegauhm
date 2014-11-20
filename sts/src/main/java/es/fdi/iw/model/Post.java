package es.fdi.iw.model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.persistence.Column;
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
    @NamedQuery(name="postById",
        query="select u from User u where u.login = :loginParam") //CAMBIAR
})
public class Post {	
	private long id;
	private String text;
	private User owner;
	private int upVotes;
	private int downVotes;
	private Date date;

	public Post() {}
	
	public static Post createPost(String text, User owner) {
		Post p = new Post();
		p.text = text;
		p.owner = owner; // Ojo que aqui no comprobamos si el usuario está en la 
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

	public String getText() {
		return this.text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public User getOwner() {
		return this.owner;
	}
	
	public void setOwner(User owner) {
		this.owner = owner;
	}
	
	public int getUpVotes() {
		return this.upVotes;
	}
	
	public void setUpVotes(int upVotes) {
		this.upVotes = upVotes;
	}
	
	public int getDownVotes() {
		return this.downVotes;
	}
	
	public void setDownVotes(int downVotes) {
		this.downVotes = downVotes;
	}

	
	
	// CAMBIAR APARTIR DE AQUIIIIIIIIIIIII !!!!!!!!!!!!!!
	/////////////////////////////////////////////////////
	
	
	@OneToMany(targetEntity=Book.class)
	@JoinColumn(name="owner_id") // <-- this avoids creating an extra User_Book table
	public List<Book> getOwnedBooks() {
		return ownedBooks;
	}

	public void setOwnedBooks(List<Book> ownedBooks) {
		this.ownedBooks = ownedBooks;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String toString() {
		return "" + id + " " + login + " " + hashedAndSalted + " " + salt;
	}
}
