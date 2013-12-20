package com.gss.gevee.be.util.locale;

import java.util.Locale;
import java.util.ResourceBundle;

public class LocaleUtil {
	
	private static Locale FRENCH_LOCALE = Locale.FRENCH;
	private static Locale ENGLISH_LOCALE = Locale.ENGLISH;
	
	private static String DEFAULT_LOCALE_NAME = "";
	
	private static String FRENCH_LOCALE_NAME = "";
	
	private static String ENGLISH_LOCALE_NAME = "";
	
	private static LocaleUtil localeHelper = new LocaleUtil();
	
	
	private static Locale current;
	
	static{
		if(current==null)
			current = FRENCH_LOCALE;
	}
	public static Locale getCurrentLocale(){
		return current;
	}
	
	public static void setCurrentLocale(Locale locale){
		current = locale;
	}
	
	private static ResourceBundle defaultLocaleResourceBundle;
	static{
		defaultLocaleResourceBundle = ResourceBundle.getBundle(DEFAULT_LOCALE_NAME);
	}
	
	private static ResourceBundle frLocaleResourceBundle;
	static{
		frLocaleResourceBundle = ResourceBundle.getBundle(FRENCH_LOCALE_NAME);
	}
	
	private static ResourceBundle enLocaleResourceBundle;
	static{
		enLocaleResourceBundle = ResourceBundle.getBundle(ENGLISH_LOCALE_NAME);
	}
	

	public ResourceBundle getDefaultLocaleResourceBundle() {
		return defaultLocaleResourceBundle;
	}

	public ResourceBundle getFrLocaleResourceBundle() {
		return frLocaleResourceBundle;
	}

	public ResourceBundle getEnLocaleResourceBundle() {
		return enLocaleResourceBundle;
	}
	
	public static String getLocalizedString(String key){
		Locale p$locale = null;
		String tmp = null;
		
		if(key==null)
			return "Void";
		
		try{
			p$locale = getCurrentLocale();
			if(p$locale==null)
				tmp = defaultLocaleResourceBundle.getString(key);
			if(p$locale.equals(Locale.FRENCH)){
				tmp = frLocaleResourceBundle.getString(key);
			}
			if (p$locale.equals(Locale.ENGLISH)) {
				tmp = enLocaleResourceBundle.getString(key);
			}
		}catch(Exception e){
			e.printStackTrace();
			tmp = "Void";
			return tmp;
		}
		return tmp;
	}
	
	///@Override
	public LocaleUtil getInstance() {
		if(localeHelper==null)
			localeHelper = new LocaleUtil();
		return localeHelper;
	}
	
}
