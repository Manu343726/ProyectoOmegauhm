package es.fdi.iw.model;

import javax.persistence.Transient;

public class Moderation {
	private ModerationEvent event;
	private ModerationCategory category;
	private File file;
	private Post post;
	
	private Moderation(){}
	
	public static Moderation createModeration(ModerationEvent event, File file, Post post) {
		Moderation m = new Moderation();
		
		m.event = event;
		m.file = file;
		m.post = post;
		
		switch(event) {
			case EditFile:  
				m.category = ModerationCategory.File;
			case EditPost:  
				m.category = ModerationCategory.Post;
			case NewFile:   
				m.category = ModerationCategory.File;
			case NewPost:   
				m.category = ModerationCategory.Post;
			case NewThread: 
				m.category = ModerationCategory.Thread;
		}
		
		return m;
	}

	public ModerationCategory getCategory() {
		return category;
	}

	public void setCategory(ModerationCategory category) {
		this.category = category;
	}
	
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}
	
	public ModerationEvent getEvent() {
		return event;
	}

	public void setEvent(ModerationEvent event) {
		this.event = event;
	}
	
	@Transient
	public Topic getThread() {
		if(category == ModerationCategory.File || category == ModerationCategory.Post) {
			return post.getThread();
		} else {
			return null;
		}
	}
}
