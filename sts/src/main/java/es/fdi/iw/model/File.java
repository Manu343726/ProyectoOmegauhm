package es.fdi.iw.model;
//pene
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({
    @NamedQuery(name="FileById",
        query="select u from User u where u.login = :loginParam") //CAMBIAR
})
public class File {
	
	private long id;
	private String Name;
	private String route;
	private User owner;
	private int upVotes;
	private int downVotes;
	private Date date;
	private String subject;

	public File() {
	}
	
	public static File createPost(String text, User owner) {
		File f = new File();
		f.Name = text;
		f.owner = owner; // Ojo que aqui no comprobamos si el usuario está en la bd
	
		return f;
	}

	
	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public void setDate(Date d){
		this.date=d;
	}
	
	public Date getDate(){
		return this.date;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}




	// CAMBIAR APARTIR DE AQUIIIIIIIIIIIII !!!!!!!!!!!!!!
	/////////////////////////////////////////////////////
	
	
	

	public String toString() {
		return "" + id + " ";
	}
}
