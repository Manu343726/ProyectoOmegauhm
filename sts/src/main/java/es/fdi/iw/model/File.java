package es.fdi.iw.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Transient;

@Entity
@NamedQueries({
    @NamedQuery(name="FileById",
        query="select f from File f where f.id = :idParam"),
    @NamedQuery(name="filesByDate",
    	query="select f from File f order by f.date desc")
})
public class File {
	
	private long id;
	private String name;
	private Date date;
	private String tags;
	private User owner;

	public File() {
	}
	
	public static File createFile(String name, String tags, User owner) {
		File f = new File();
		f.name = name;
		f.date = new Date(); //RTFM, is initialized to the time of allocation
		f.tags = tags;
		f.owner = owner;
	
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

	public void setDate(Date d){
		this.date=d;
	}
	
	public Date getDate(){
		return this.date;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTags() {
		return this.tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}
	
	@ManyToOne(targetEntity=User.class)
	public User getOwner() {
		return owner;
	}
	
	public void setOwner(User owner) {
		this.owner = owner;
	}
	
	@Transient 
	public String getTimeStamp(){
		return new SimpleDateFormat("EEE, d MMM yyyy HH:mm").format(date);
	}
}