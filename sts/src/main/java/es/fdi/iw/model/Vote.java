package es.fdi.iw.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
@NamedQueries({
	@NamedQuery(name="voteByUserAndPost",
			query="select v from Vote v join v.user u join v.post p where u.id = :userIdParam and p.id = :postIdParam")
})
public class Vote {
	
	private long id;
	private User user;
	private Post post;
	private boolean sign; // true = positivo  false = negativo
	
	public Vote() {}
	
	public static Vote createVote(User user, Post post, boolean sign) {
		Vote v = new Vote();
		v.user = user;
		v.post = post;
		v.sign = sign;
		
		return v;
	}
	
	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}	
	
	@ManyToOne
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	@ManyToOne
	public Post getPost() {
		return post;
	}
	
	public void setPost(Post post) {
		this.post = post;
	}
	
	public boolean getSign() {
		return sign;
	}
	
	public void setSign(boolean sign) {
		this.sign = sign;
	}
}
