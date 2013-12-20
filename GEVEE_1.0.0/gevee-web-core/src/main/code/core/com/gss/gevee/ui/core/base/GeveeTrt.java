package com.gss.gevee.ui.core.base;

import java.util.Map;
import java.util.TreeMap;

public class GeveeTrt extends BasicTrt{
	
	/**
	 * Retourne la liste des traitements standards 
	 * 
	 * @param p$codeEntite	: Code de l'entité
	 * @return
	 */
	public static Map<String, Traitement> getTrtStandards() {
		return 	getTrtStandards("");
	}
	
	/**
	 * Retourne la liste des traitements standards pour une entité donnée
	 * 
	 * @param p$codeEntite	: Code de l'entité
	 * @return
	 */
	public static Map<String, Traitement> getTrtStandards(String p$codeEntite) {
		
		Traitement v$traitement;
		Map<String,Traitement> trtStandards = new TreeMap<String, Traitement>();
		
		v$traitement = new Traitement(Traitement.getCodeTrt(p$codeEntite, GeveeTrt.AJOUTER.getCode()), GeveeTrt.AJOUTER);
		trtStandards.put(v$traitement.getKey(), v$traitement);
		
		
		v$traitement = new Traitement(Traitement.getCodeTrt(p$codeEntite, GeveeTrt.AFFICHER.getCode()), GeveeTrt.AFFICHER);		
		trtStandards.put(v$traitement.getKey(), v$traitement);
	
		v$traitement = new Traitement(Traitement.getCodeTrt(p$codeEntite, GeveeTrt.COPIER.getCode()), GeveeTrt.COPIER);				
		trtStandards.put(v$traitement.getKey(), v$traitement);

		v$traitement = new Traitement(Traitement.getCodeTrt(p$codeEntite, GeveeTrt.MODIFIER.getCode()), GeveeTrt.MODIFIER);				
		trtStandards.put(v$traitement.getKey(), v$traitement);

		v$traitement = new Traitement(Traitement.getCodeTrt(p$codeEntite, GeveeTrt.SELECTIONNER.getCode()), GeveeTrt.SELECTIONNER);				
		trtStandards.put(v$traitement.getKey(), v$traitement);

		v$traitement = new Traitement(Traitement.getCodeTrt(p$codeEntite, GeveeTrt.SUPPRIMER.getCode()), GeveeTrt.SUPPRIMER);				
		trtStandards.put(v$traitement.getKey(), v$traitement);
		
		
		
		return trtStandards;
		
	}

}
