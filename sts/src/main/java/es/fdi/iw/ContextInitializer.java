package es.fdi.iw;


import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.web.context.ConfigurableWebApplicationContext;

public class ContextInitializer implements ApplicationContextInitializer<ConfigurableWebApplicationContext> {

    private static final Logger log = LoggerFactory.getLogger(ContextInitializer.class);

    private static final Properties props = new Properties();
    
    private static File baseFolder;
    
    public ContextInitializer() {
        log.debug("Got the constructor");
    }

    @Override
    public void initialize(ConfigurableWebApplicationContext ctx) {
    	/*try {
    		props.load(getClass().getResourceAsStream("/app.properties")); 
    		baseFolder = new File(props.getProperty("base"));
        	log.info("base folder is {}", baseFolder.getAbsolutePath());
        	if (!baseFolder.isDirectory()) {
        		if (baseFolder.exists()) {
        			log.error("{} exists and is not a directory -- cannot create", baseFolder);
        		} else if ( ! baseFolder.mkdirs()){
        			log.error("{} could not be created -- check permissions", baseFolder);        			
        		}
        	} else {
        		log.info("using already-existing base folder :-)");
        	}
        	baseFolder.mkdirs();
    	} catch (IOException ioe) {
    		log.error("Could not read properties file! No base folder!", ioe);
    	}
    	
    	log.info("read {} properties", props.size());*/
    }
    
    public static String getProperty(String key) {
    	return props.getProperty(key);
    }
    
    public static File getFolder(String name) {
    	File folder = new File(baseFolder, name);
    	if ( ! folder.exists()) folder.mkdirs();
    	return folder;
    }
    
    public static File getFile(String folder, String name) {
    	return new File(getFolder(folder), name);
    }
}