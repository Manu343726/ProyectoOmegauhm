package es.fdi.iw.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Transient;

@Entity
@NamedQueries({
    @NamedQuery(name="moderationQueue",
        query="select m from Moderation m"),
    @NamedQuery(name="moderationById",
        query="select m from Moderation m where m.id = :idParam"),
    @NamedQuery(name="moderationsByDate",
    	query="select m from Moderation m order by m.date desc")
})
public class Moderation {
	private long id;
	private ModerationEvent event;
	private ModerationCategory category;
	private File file;
	private Post post;
	private Date date;
	
	public Moderation(){}
	
	public static Moderation createModeration(ModerationEvent event, File file, Post post) {
		Moderation m = new Moderation();
		
		m.event = event;
		m.file = file;
		m.post = post;
		
		switch(event) {
			case EditFile:  
				m.category = ModerationCategory.File; break;
			case EditPost:  
				m.category = ModerationCategory.Post; break;
			case NewFile:   
				m.category = ModerationCategory.File; break;
			case NewPost:   
				m.category = ModerationCategory.Post; break;
			case NewThread: 
				m.category = ModerationCategory.Thread; break;
			case DeleteFile:
				m.category = ModerationCategory.File; break;
		}
		
		m.date = new Date();
		
		return m;
	}
	
	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Enumerated(EnumType.STRING)
	public ModerationCategory getCategory() {
		return category;
	}

	public void setCategory(ModerationCategory category) {
		this.category = category;
	}
	
	@ManyToOne(targetEntity=File.class)
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	@ManyToOne(targetEntity=Post.class)
	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}
	
	public void setDate(Date d){
		this.date=d;
	}
	
	public Date getDate(){
		return this.date;
	}
	
	@Enumerated(EnumType.STRING)
	public ModerationEvent getEvent() {
		return event;
	}

	public void setEvent(ModerationEvent event) {
		this.event = event;
	}
	
	@Transient
	public Topic getThread() {
		if(category == ModerationCategory.Thread || category == ModerationCategory.Post) {
			return post.getThread();
		} else {
			return null;
		}
	}
	
	@Transient 
	public String getTimeStamp(){
		return new SimpleDateFormat("EEE, d MMM yyyy HH:mm").format(date);
	}
}
