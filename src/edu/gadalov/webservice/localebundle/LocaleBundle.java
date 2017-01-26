package edu.gadalov.webservice.localebundle;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**Language resource bundle class.
 * @author Maxim Gadalov
 *
 */
public class LocaleBundle {
	private static final Logger LOG = LogManager.getLogger(LocaleBundle.class);
	private static final String BASE_BUNDLE = "property.content";
	
	private ResourceBundle  bundle;
	
	/**Create resource bundle object with Locale.
	 * @param locale - Locale @see {@link Locale#Locale(String, String)}
	 */
	public LocaleBundle(Locale locale){
		try{
			bundle = ResourceBundle.getBundle(BASE_BUNDLE, locale);
		} catch(MissingResourceException e){
			LOG.error(e);
			throw new RuntimeException("Locale bundle not found");
		}		
	}
	/**
	 * Create default resource bundle
	 */
	public LocaleBundle(){
		try{
			bundle = ResourceBundle.getBundle(BASE_BUNDLE);
		} catch(MissingResourceException e){
			LOG.error(e);
			throw new RuntimeException("Locale bundle not found");
		}		
	}
	/**Return located value.
	 * @param key - String key
	 * @return - String located value
	 */
	public String getValue(String key){
		return bundle.getString(key);
	}

}
