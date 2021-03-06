package com.gss.gevee.ui.core.base;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import com.gss.gevee.be.core.base.BaseLogger;

public class GeveeToolBox {
	
	/**
	 * Logger de la classe
	 * 
	 * @return
	 */
	private static BaseLogger getLogger(){
		return BaseLogger.getLogger(GeveeToolBox.class);
	}
	
	/**
	 * Retourne un ensemble d'�lements sous forme de Map<cl�, valeur> g�n�ralement issue d'une �num�ration de la BD
	 * 
	 * @param p$map
	 * @return
	 */
	public static Map<String, Object> getComboData(Map<String, String> p$map){
		
		// Map � retourner
		HashMap<String,Object> v$map = new HashMap<String,Object>();
			
		// Map � injecter dans le Combo Box
		TreeMap<String,String> v$comboList = new TreeMap<String,String>();
		
		if(p$map != null){
			// R�cup�ration de l'ensemble des Enum�rations et de leur Libell�
			for(String v$code: p$map.keySet()){
				// Map A afficher dans le combobox
				v$comboList.put(p$map.get(v$code),v$code); 
				// Map Utile pour retrouver le libelle de l'enumeration dans le tableau			
				v$map.put(v$code, p$map.get(v$code));
			}
		}
		
		v$map.put(CoreConstants.COMBO_BOX, v$comboList);
				
		return  v$map;				
	}		
	
	/**
	 * Retourne un ensemble d'�lements sous forme de Map<cl�, valeur> g�n�ralement issue d'une �num�ration de la BD
	 * Le map retourn�, notamment pour le combo, maintient l'ordre des �l�ments dans le map pass� en argument
	 * 
	 * @param p$map
	 * @return
	 */
	public static LinkedHashMap<String, Object> getComboDataStrictOrder(Map<String, String> p$map){
		
		// Map � retourner
		LinkedHashMap<String,Object> v$map = new LinkedHashMap<String,Object>();
			
		// Map � injecter dans le Combo Box
		LinkedHashMap<String,String> v$comboList = new LinkedHashMap<String,String>();
		
		if(p$map != null){
			// R�cup�ration de l'ensemble des Enum�rations et de leur Libell�
			for(String v$code: p$map.keySet()){
				// Map A afficher dans le combobox
				v$comboList.put(p$map.get(v$code),v$code); 
				// Map Utile pour retrouver le libelle de l'enumeration dans le tableau			
				v$map.put(v$code, p$map.get(v$code));
			}
			
		}
		
		v$map.put(CoreConstants.COMBO_BOX, v$comboList);
				
		return  v$map;				
	}		
	
	/**
	 * Sauvegarde la locale courante de l'application
	 * 
	 * @return
	 */
	public static void setCurrentLocale(Locale locale){
		FacesUtil.setApplicationMapValue(CoreConstants.CURRENT_LOCALE, locale);
	}
	
	/**
	 * Retourne la locale courante de l'application
	 * 
	 * @return
	 */
	public static Locale getCurrentLocale(){
		return (Locale) FacesUtil.getApplicationMapValue(CoreConstants.CURRENT_LOCALE);
	}
	
	/**
	 * Obtention du nom d'un bean manag� a partir du nom de la page
	 * 
	 * @param p$nomPage	:	Nom de la page
	 * @return
	 */
	public static String getManagedBeanName(String p$nomPage){
		
		String v$beanName =  null;
		
		if(p$nomPage == null || p$nomPage.trim().isEmpty())
			return v$beanName;
			
		int v$index = p$nomPage.lastIndexOf(CoreConstants.SUFFIXE_NVGT_LISTE);
		
		if(v$index == -1){
			v$index = p$nomPage.lastIndexOf(CoreConstants.SUFFIXE_NVGT_DETAILS);
			
			if(v$index == -1 ){
				v$index = p$nomPage.lastIndexOf(CoreConstants.SUFFIXE_NVGT_EDITION);
			}
		}
		
		if(v$index > 0){
			v$beanName = p$nomPage.substring(0, v$index);
			
			String v$string = v$beanName.substring(0, 1);
			v$beanName = v$beanName.replaceFirst(v$string, v$string.toLowerCase()).concat(CoreConstants.SUFFIXE_MANAGED_BEAN);			
		}
	
		return v$beanName;
	}

	
}
