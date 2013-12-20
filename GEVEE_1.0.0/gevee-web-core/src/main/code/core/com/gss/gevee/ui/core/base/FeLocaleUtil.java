package com.gss.gevee.ui.core.base;


import java.util.Locale;
import java.util.ResourceBundle;

import javax.faces.context.FacesContext;


public class FeLocaleUtil{
	
	private static String DEFAULT_LOCALE_NAME = "com.gss.gevee.ui.messages.locale";
	
	private static String FRENCH_LOCALE_NAME = "com.gss.gevee.ui.messages.locale_fr";
	
	private static String ENGLISH_LOCALE_NAME = "com.gss.gevee.ui.messages.locale_en";
	
	private static FeLocaleUtil localeHelper = new FeLocaleUtil();
	
	
	///@Override
	public static Locale getCurrentLocale(){
		return FacesContext.getCurrentInstance().getViewRoot().getLocale();
		///return FacesContext.getCurrentInstance().getExternalContext().getRequestLocale();
	}
	
	///@Override
	public static String getLocalizedString(String key){
		Locale locale = null;
		String tmp = null;
		
		if(key==null)
			return "Void";
		
		try{
			locale = getCurrentLocale();
			if(locale==null)
				tmp = defaultLocaleResourceBundle.getString(key);
			if(locale.equals(Locale.FRENCH)){
				tmp = frLocaleResourceBundle.getString(key);
			}
			if (locale.equals(Locale.ENGLISH)) {
				tmp = enLocaleResourceBundle.getString(key);
			}
		}catch(Exception e){
			tmp = "Void";
			return tmp;
		}
		return tmp;
	}
	
	///@Override
	protected String getLocalizedString(String key, Locale locale){
		String tmp = null;
		System.out.println("FeLocaleUtil.getLocalizedString() je cherche la clé : "+key+" locale : "+locale);
		if(key==null)
			return "Void";
		
		try{
			if(locale==null)
				tmp = defaultLocaleResourceBundle.getString(key);
			if(locale.equals(Locale.FRENCH)){
				tmp = frLocaleResourceBundle.getString(key);
			}
			if (locale.equals(Locale.ENGLISH)) {
				tmp = enLocaleResourceBundle.getString(key);
			}
		}catch(Exception e){
			tmp = "Void";
			return tmp;
		}
		return tmp;
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
	
	///@Override
	public FeLocaleUtil getInstance() {
		if(localeHelper==null)
			localeHelper = new FeLocaleUtil();
		return localeHelper;
	}

	///@Override
	public ResourceBundle getDefaultLocaleResourceBundle() {
		return defaultLocaleResourceBundle;
	}

	///@Override
	public ResourceBundle getFrLocaleResourceBundle() {
		return frLocaleResourceBundle;
	}

	///@Override
	public ResourceBundle getEnLocaleResourceBundle() {
		return enLocaleResourceBundle;
	}
}
