package util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import es.fdi.iw.model.User;


public class IWFileManager 
{
	private EntityManager manager;
	private File baseDirectory;
	static final Logger log = LoggerFactory.getLogger(IWFileManager.class);
	
	public IWFileManager(String basePath)
	{
		baseDirectory = new File(basePath);
		log.info("base directory is {}", baseDirectory.getAbsolutePath());
		
		if (!baseDirectory.isDirectory())
    		if (baseDirectory.exists())
    			log.error("{} exists and is not a directory -- cannot create", baseDirectory);
    		else if (!baseDirectory.mkdirs())
    			log.error("{} could not be created -- check permissions", baseDirectory);        			
    	else
    		log.info("using already-existing base folder :-)");
	}
	
	public String getFilePath(es.fdi.iw.model.File file)
	{
		return Paths.get(baseDirectory.getAbsolutePath(), String.valueOf(file.getId())).toString();
	}
	
	public File getFile(es.fdi.iw.model.File file) throws IOException
	{
		File f = new File(getFilePath(file));
		
		f.createNewFile(); //See the docs, just returns false if the file already exists
		
		return f;
	}
	
	public void deleteFile(es.fdi.iw.model.File file) throws IOException
	{
		getFile(file).delete();
	}
	
	public es.fdi.iw.model.File uploadFile(MultipartFile load, String tags, User owner)
	{
		if (!load.isEmpty()) {
            try {
            	es.fdi.iw.model.File file = es.fdi.iw.model.File.createFile(load.getOriginalFilename(), tags, owner);
            			
                byte[] bytes = load.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(
                        		new FileOutputStream(getFile(file)));
                stream.write(bytes);
                stream.close();
                
                return file;
            } catch (Exception e) {
                return null;
            }
        } else {
            return null;
        }
	}
}
