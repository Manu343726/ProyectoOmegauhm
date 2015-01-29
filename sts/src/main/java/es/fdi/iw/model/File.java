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
        query="select u from User u where u.login = :loginParam") //CAMBIAR
})
public class File {
	
	private long id;
	private String name;
	private Date date;
	private String tags;
	private boolean deletePending; //File deletion event is on moderation queue, an user has no direct rights to delete a file

	public File() {
	}
	
	public static File createFile(String name, String tags) {
		File f = new File();
		f.name = name;
		f.date = new Date(); //RTFM, is initialized to the time of allocation
		f.tags = tags;
		f.deletePending = false;
	
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
	
	public boolean getDeletePending() {
		return deletePending;
	}

	public void setDeletePending(boolean deletePending) {
		this.deletePending = deletePending;
	}
	
	@Transient
	public String getUri()
	{
		return "file/download/id/" + getId();
	}
	
	@Transient 
	public String getTimeStamp(){
		return new SimpleDateFormat("EEE, d MMM yyyy HH:mm").format(date);
	}
}