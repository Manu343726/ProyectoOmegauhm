package es.fdi.iw;


import java.io.File;
import java.io.IOException;
import java.util.Properties;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.web.context.ConfigurableWebApplicationContext;

import util.IWFileManager;

public class ContextInitializer implements ApplicationContextInitializer<ConfigurableWebApplicationContext> {

    private static final Logger log = LoggerFactory.getLogger(ContextInitializer.class);

    private static final Properties props = new Properties();
    
    private static IWFileManager fileManager;
    
    public ContextInitializer() {
        log.debug("Got the constructor");
    }

    @Override
    public void initialize(ConfigurableWebApplicationContext ctx) 
    {
    	try 
    	{
    		props.load(getClass().getResourceAsStream("/app.properties")); 
    	} catch (IOException ioe) 
    	{
    		log.error("Could not read properties file! No base directory!", ioe);
    	}
    	
    	log.info("read {} properties", props.size());
    	
    }
    
    public static String getProperty(String key) {
    	return props.getProperty(key);
    }
    
    public static IWFileManager getFileManager(EntityManager manager)
    {
    	return new IWFileManager(getProperty("base"), manager);
    }
}