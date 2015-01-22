package util;

import javax.persistence.EntityManager;

import es.fdi.iw.model.File;
import es.fdi.iw.model.Moderation;
import es.fdi.iw.model.ModerationEvent;
import es.fdi.iw.model.Post;
import es.fdi.iw.model.Topic;

public class IWModerationManager {
	private EntityManager manager;
	
	private IWModerationManager(EntityManager manager) {
		this.manager = manager;
	}
	
	public static IWModerationManager get(EntityManager manager) {
		return new IWModerationManager(manager);
	}
	
	private Moderation createModeration(ModerationEvent event, Post post, File file) {
		Moderation m = Moderation.createModeration(event, file, post);
		
		manager.persist(m);
		
		return m;
	}
	
	/**************************** TOPIC ****************************/
	/***************************************************************/
	
	public Moderation moderateNewTopic(Topic thread) {
		return createModeration(ModerationEvent.NewThread, thread.getQuestion(), null);
	}
	
	/**************************** POST ****************************/
	/**************************************************************/
	
	public Moderation moderateNewAnswer(Post post) {
		return createModeration(ModerationEvent.NewPost, post, null);
	}
	
	/**************************** FILE ****************************/
	/**************************************************************/
	
	public Moderation moderateNewFile(File file) {
		return createModeration(ModerationEvent.NewFile, null, file);
	}
	
	public Moderation moderateDeleteFile(File file) {
		return createModeration(ModerationEvent.DeleteFile, null, file);
	}
}
