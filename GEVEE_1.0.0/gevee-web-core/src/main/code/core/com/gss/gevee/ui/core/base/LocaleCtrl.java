package com.gss.gevee.ui.core.base;


import java.util.Locale;


public class LocaleCtrl {
	private static Locale FRENCH_LOCALE = Locale.FRENCH;
	private static Locale ENGLISH_LOCALE = Locale.ENGLISH;
	
	private String actual = "fr";
	
	public LocaleCtrl(){
		///actual = "fr";
		///ToolBox.setCurrentLocale(FRENCH_LOCALE);
		FeLocaleUtil helper = (FeLocaleUtil) new FeLocaleUtil().getInstance();
		helper.getInstance();
	}

	public String getActual() {
		return actual;
	}

	public void setActual(String actual) {
		this.actual = actual;
		if(actual.equals("fr")){
			GeveeToolBox.setCurrentLocale(FRENCH_LOCALE);
		}
		if(actual.equals("en")){
			GeveeToolBox.setCurrentLocale(ENGLISH_LOCALE);
		}
	}
	
	public static void main(String[] args) throws Exception {
		String[] list = Locale.getISOLanguages();
		/*for(int i=0; i<list.length; ++i){
			System.out.println(list[i]);
		}*/
		Locale loc = new Locale("fr");
		System.out.println(loc);
	}
	
	
}
