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
	 * Retourne un ensemble d'élements sous forme de Map<clé, valeur> généralement issue d'une énumération de la BD
	 * 
	 * @param p$map
	 * @return
	 */
	public static Map<String, Object> getComboData(Map<String, String> p$map){
		
		// Map à retourner
		HashMap<String,Object> v$map = new HashMap<String,Object>();
			
		// Map à injecter dans le Combo Box
		TreeMap<String,String> v$comboList = new TreeMap<String,String>();
		
		if(p$map != null){
			// Récupération de l'ensemble des Enumérations et de leur Libellé
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
	 * Retourne un ensemble d'élements sous forme de Map<clé, valeur> généralement issue d'une énumération de la BD
	 * Le map retourné, notamment pour le combo, maintient l'ordre des éléments dans le map passé en argument
	 * 
	 * @param p$map
	 * @return
	 */
	public static LinkedHashMap<String, Object> getComboDataStrictOrder(Map<String, String> p$map){
		
		// Map à retourner
		LinkedHashMap<String,Object> v$map = new LinkedHashMap<String,Object>();
			
		// Map à injecter dans le Combo Box
		LinkedHashMap<String,String> v$comboList = new LinkedHashMap<String,String>();
		
		if(p$map != null){
			// Récupération de l'ensemble des Enumérations et de leur Libellé
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
}
