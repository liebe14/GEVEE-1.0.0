package com.gss.gevee.be.core.base;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressWarnings({"deprecation"})
public class DateTools {

	/***
	 * Format de date par défaut
	 */
	private static String DEFAULT_DATE_FORMAT = "yyyyMMddHHmmssSSSSSS";
	public static String DATEFORMAT_JJMMYYYY = "ddMMyyyy";
	public static String DATEFORMAT_YYYYMMJJ = "yyyyMMdd";
	
	
	/***
	 * Format de date courant
	 */
	private static String CURRENT_DATE_FORMAT = "yyyyMMddHHmmssSSSSSS";
	
	
	private static String CREATED_ENTITY_DEFAULT_DATE_VALUE = "19000101000000000000";     // DateTools.formatDate(new Date(0,00,01,00,00,00)) = 1900 01 01 00 00 00 000000	
	
	private static String ACTIVE_ENTITY_END_DATE_VALUE      = "99991231235959000000"; //DateTools.formatDate(new Date(9999-1900,11,31,23,59,59)); // 9999 12 31 23 59 59 000000
	
	
	//private static String CREATED_ENTITY_DEFAULT_DATE_VALUE = DateTools.formatDate(new Date(0,00,01,00,00,00));    // 1900 01 01 00 00 00 000000	
	//private static String ACTIVE_ENTITY_END_DATE_VALUE = DateTools.formatDate(new Date(9999-1900,11,31,23,59,59)); // 9999 12 31 23 59 59 000000
	
	/***
	 * Formate une date suivant le format de date courant
	 * @param date : Date à formater
	 * @return : Retourne la chaine de caractère représentant la date formatée
	 */
	public static String formatDate(Date date){
		SimpleDateFormat formater = new SimpleDateFormat(CURRENT_DATE_FORMAT);
		return formater.format(date);
	}
	
	public static void setCurrentDateFormat(String dateFormat){
		CURRENT_DATE_FORMAT = dateFormat;
	}
	
	public static String getCurrentDateFormat(){
		return CURRENT_DATE_FORMAT;
	}
	
	public static String getDefaultDateFormat(){
		return DEFAULT_DATE_FORMAT;
	}
	
	
	/****
	 * Créer une date avec les informations fournies
	 * @param year : L'année
	 * @param month : le mois (1...12) <br> (Janvier=1, Fevrier=2, ...)
	 * @param day : Le jour 
	 * @return Retourne une nouvelle instance de date avec (Heure=0h, Minute=0, Seconde=0)
	 */
	public static Date createDate(int year, int month, int day){		
		return createDate(year, month, day, 0, 0, 0);		
	}
	
	/****
	 * Créer une date avec les informations fournies
	 * @param year : L'année
	 * @param month : le mois (1...12) <br> (Janvier=1, Fevrier=2, ...)
	 * @param day : Le jour
	 * @param hour : L'heure
	 * @param minute : La minute
	 * @param second : La seconde
	 * @return Retourne une nouvelle instance de date
	 */
	public static Date createDate(int year, int month, int day, int hour, int minute, int second){		
		return new Date(year-1900, month-1, day, hour, minute, second);		
	}
	
	
	public static String formatDate(String format, Date date){
		SimpleDateFormat formt = new SimpleDateFormat(format);
		if(date == null)
			return null;
		
		return formt.format(date);
	}

	/***
	 * Récupere une date à partir d'une chaine de caractère au format courant
	 * @param p$strDate : Chaine de caractère représentant la date au format courant
	 * @return Date : Date construite à partie de la valeur fournie
	 * @throws ParseException
	 */
	public static Date getDateValue(String p$strDate) throws ParseException{
		Date date;
		SimpleDateFormat formater = new SimpleDateFormat(CURRENT_DATE_FORMAT);
		if(p$strDate == null || p$strDate.trim().isEmpty())
			return null;
		
		date = formater.parse(p$strDate);
		return date;
	}
	
	/***
	 * Récupere une date à partir d'une chaine de caractère 
	 * @param p$strDate : Chaine de caractère représentant la date
	 * @param format : Format de date sur lequel se trouve la chaine de caractère fournie
	 * @return Date : Date construite à partie de la valeur fournie 
	 * @throws ParseException
	 */
	public static Date getDateValue(String p$strDate, String format) throws ParseException{
		Date date;
		if(p$strDate == null || format == null || p$strDate.trim().isEmpty() || format.trim().isEmpty())
			return null;
		
		SimpleDateFormat formater = new SimpleDateFormat(format);
		date = formater.parse(p$strDate);
		return date;
	}

	public static String getActiveEntityEndDateValue(){
		return ACTIVE_ENTITY_END_DATE_VALUE;
	}
	
	public static String getCreatedEntityDefaultDateValue(){
		return CREATED_ENTITY_DEFAULT_DATE_VALUE;
	}
	
	/***
	 * Retourne la partie date d'une date formater suivant le format de date par défaut
	 * @param dateString : Date au format "yyyyMMddHHmmssSSSSSS"
	 * @return
	 */
	public static String getYear(String dateString){
		//yyyyMMddHHmmssSSSSSS
		return dateString.substring(0, 4);
	}
	
	/***
	 * Retourne la partie mois d'une date formater suivant le format de date par défaut
	 * @param dateString : Date au format "yyyyMMddHHmmssSSSSSS"
	 * @return
	 */
	public static String getMonth(String dateString){
		//yyyyMMddHHmmssSSSSSS
		return dateString.substring(4, 6);
	}
	
	/***
	 * Retourne la partie jour d'une date formater suivant le format de date par défaut
	 * @param dateString : Date au format "yyyyMMddHHmmssSSSSSS"
	 * @return
	 */
	public static String getDay(String dateString){
		//yyyyMMddHHmmssSSSSSS
		return dateString.substring(6, 8);
	}
}
