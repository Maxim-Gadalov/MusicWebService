package edu.gadalov.webservice.localebundle;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class LocaleBundle {
	private static final Logger LOG = LogManager.getLogger(LocaleBundle.class);
	private static final String BASE_BUNDLE = "property.content";
	
	private ResourceBundle  bundle;
	
	public LocaleBundle(Locale locale){
		try{
			bundle = ResourceBundle.getBundle(BASE_BUNDLE, locale);
		} catch(MissingResourceException e){
			LOG.error(e);
			throw new RuntimeException("Locale bundle not found");
		}		
	}
	public LocaleBundle(){
		try{
			bundle = ResourceBundle.getBundle(BASE_BUNDLE);
		} catch(MissingResourceException e){
			LOG.error(e);
			throw new RuntimeException("Locale bundle not found");
		}		
	}
	public String getValue(String key){
		//String value = new String();
		//value = bundle.getString(key);
		return bundle.getString(key);
	}

}
