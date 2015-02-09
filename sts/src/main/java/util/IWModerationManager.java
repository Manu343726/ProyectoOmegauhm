package util;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;

import es.fdi.iw.model.File;
import es.fdi.iw.model.Moderation;
import es.fdi.iw.model.ModerationEvent;
import es.fdi.iw.model.Post;
import es.fdi.iw.model.Topic;
import es.fdi.iw.model.User;

public class IWModerationManager {
	
	//Java sucks, no tuples?
	public static class ModerationResult {
		private final String message;
		private final boolean successful;
		
		public ModerationResult(String message, boolean successful) {
			this.message = message;
			this.successful = successful;
		}
		
		public final boolean getSuccessful() {
			return successful;
		}
		
		public final String getMessage() {
			return message;
		}
	}
	
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
	
	public ModerationResult dimissEvent(Moderation m, User moderator) {
		if(moderator.getRole().equalsIgnoreCase("admin")) {
			manager.remove(m);
			
			return new ModerationResult("Event dimissed successfully", true);
		}
		else
			return new ModerationResult("You have no rights to perform this action", false);
	}
	
	public ModerationResult dimissEvent(long id, User moderator) {
		return dimissEvent(moderationByid(id), moderator);
	}
	
	public ModerationResult acceptModeration(IWFileManager fileManager, Moderation m, User admin) {
		ModerationResult result = null;
		
		if(admin.getRole().equalsIgnoreCase("admin")) {
			switch(m.getEvent()) {
			case DeleteFile:
				try {
					fileManager.trueDeleteFile(m.getFile(), admin); 
					
					result = new ModerationResult("File deleted successfully", true);
				} catch (IOException ex) {
					result = new ModerationResult("Error deleting file from server filesystem", false);
				}
			default:
				result = new ModerationResult("Done", true);
			}
		}
		else
			result = new ModerationResult("You have no rights to perform this action", false);
		
		if(result.getSuccessful())
			manager.remove(m);
		
		return result;
	}
	
	public ModerationResult acceptModeration(IWFileManager fileManager, long id, User admin) {
		return acceptModeration(fileManager, moderationByid(id), admin);
	}
	
	/**************************** QUEUE ****************************/
	/***************************************************************/
	
	@SuppressWarnings("unchecked")
	public List<Moderation> moderationQueue() {
		return (List<Moderation>) manager.createNamedQuery("moderationQueue").getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<Moderation> moderationByDate() {
		return (List<Moderation>) manager.createNamedQuery("moderationsByDate").getResultList();
	}
	
	public Moderation moderationByid(long id) {
		return (Moderation) manager.createNamedQuery("moderationById")
				            .setParameter("idParam", id)
				            .getSingleResult();
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
