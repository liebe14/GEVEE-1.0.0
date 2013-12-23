package com.gss.gevee.be.core.enums;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public enum EnuAco {
	MAERSK("2000", "Maersk"),

	MSC("2001", "MSC"),
	
	MOVIS("2002", "MOVIS"),
	
	SOCOMAR("2003", "SOCOMAR"),
	
	GETMA("2004", "Getma"),
	
	NECOTRANS("2005", "Necotrans"),
	
	TRANSIMEX("2006", "Transimex");
	

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
	private EnuAco(String value, String libelle) {
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
	public static EnuAco getByValue(String enumValue) {
		for (EnuAco enm : EnuAco.class
				.getEnumConstants()) {
			if (enm.getValue().equalsIgnoreCase(enumValue))
				return enm;
		}
		return null;
	}

	private static List<EnuAco> myList;
	private static Map<String, String> myMap;

	/***
	 * Retourne la liste des valeurs de l'énum dans un map (Map<Code,Libelle>)
	 * 
	 * @return
	 */
	public static Map<String, String> getMaps() {
		return myMap;
	}

	public static List<EnuAco> getListe() {
		return myList;
	}

	static {
		myList = new ArrayList<EnuAco>();
		myMap = new TreeMap<String, String>();
		myMap.put("", "");
		for (EnuAco enm : EnuAco.class
				.getEnumConstants()) {
			myList.add(enm);
			myMap.put(enm.getValue(), enm.getLibelle());
		}
	}

}
