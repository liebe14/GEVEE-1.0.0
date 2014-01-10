package com.gss.gevee.be.core.enums;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public enum EnuEtat {


	CREE("2000", "Crée"),
	
	VALIDE("2002", "Validé"),
	
	DEMARRE("2003", "Démarré"),
	
	CLOTURE("2004", "Clôtuté"),
	
	LIVRE("2005", "Livré"),
	
	RECEPTIONNE("2006", "Réceptionné"),

	SUPP("2001", "Supprimé");

	/**
	 * Valeur de l'énumération
	 */
	private final String value;
	/**
	 * libellé de la valeur
	 */
	private final String libelle;
	
	/**
	 * Construit un objet étant donnée une valeur et un libellé
	 * @param value : la valeur
	 * @param libelle : le libellé
	 */
	private EnuEtat(String value, String libelle) {
		this.value = value;
		this.libelle = libelle;

	}
	/**
	 * Retourne la valeur de l'énumération
	 * @return value : la valeur retournée
	 */
	public String getValue() {
		return this.value;
	}
	/**
	 * Retourne le libellé de l'énumération
	 * @return libelle : le libellé
	 */
	public String getLibelle() {
		return this.libelle;
	}
	/**
	 * retourne une énumération en fonction de sa valeur
	 * @param enumValue
	 * @return
	 */
	public static EnuEtat getByValue(String enumValue) {
		for (EnuEtat enm : EnuEtat.class
				.getEnumConstants()) {
			if (enm.getValue().equalsIgnoreCase(enumValue))
				return enm;
		}
		return null;
	}

	private static List<EnuEtat> myList;
	private static Map<String, String> myMap;

	/***
	 * Retourne la liste des valeurs de l'énum dans un map (Map<Code,Libelle>)
	 * 
	 * @return
	 */
	public static Map<String, String> getMaps() {
		return myMap;
	}

	public static List<EnuEtat> getListe() {
		return myList;
	}

	static {
		myList = new ArrayList<EnuEtat>();
		myMap = new TreeMap<String, String>();
		myMap.put("", "");
		for (EnuEtat enm : EnuEtat.class
				.getEnumConstants()) {
			myList.add(enm);
			myMap.put(enm.getValue(), enm.getLibelle());
		}
	}
	
}
