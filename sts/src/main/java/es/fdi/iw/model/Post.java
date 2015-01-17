package es.fdi.iw.model;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
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
    @NamedQuery(name="postById",
        query="select p from Post p where p.id = :idParam")
})
public class Post {
	private long id;
	
	@Lob
	@Column(columnDefinition = "blob") //Deja de truncarme el texto cabrón NO FUNCIONA
	private String text;
	private List<Vote> votes;
	private Date date;
	private User owner;
	private Topic thread;
	private PostType type; //true is a question, false is an answer
	
	public Post() {}
	
	public static Post createPost(String text, User owner) {
		Post p = new Post();
		p.text = text;
		p.owner = owner;
		p.thread = null;
		p.votes = new ArrayList<Vote>();
		
		p.date = new Date();
		
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

	@Column(length=4096)
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public void setVotes(List<Vote> votes){
		this.votes = votes;
	}
	
	@OneToMany(targetEntity=Vote.class,
			   fetch = FetchType.EAGER)
	public List<Vote> getVotes()
	{
		return votes;
	}
	
	@Enumerated(EnumType.STRING)
	public PostType getType()
	{
		return type;
	}
	
	public void setType(PostType type)
	{
		this.type = type;
	}
	
	public Date getDate(){
		return date;
	}
	
	public void setDate(Date date){
		this.date = date;
	}
	
	@Transient
	public String getUri()
	{
		return getThread().getURI() + "/#" + getId();
	}
	
	@Transient
	public int getVotesCount(){
		int count = 0;
		
		for(Vote v : votes){
			if(v.getSign())
				count++;
			else
				count--;
		}
		
		return count;
	}
	
	@Transient 
	public String getTimeStamp(){
		return new SimpleDateFormat("EEE, d MMM yyyy HH:mm").format(date);
	}
}