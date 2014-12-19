package es.fdi.iw.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
public class Topic {
	private long id;
	private String title;
	private String tags;
	
	public Topic() {}
	
	public static Topic createTopic(String title, String tags) {
		Topic t = new Topic();
		t.title = title;
		t.tags = tags;
		
		return t;
	}
	
	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setTags(String tags)
	{
		this.tags = tags;
	}
	
	public String getTags()
	{
		return tags;
	}
}
