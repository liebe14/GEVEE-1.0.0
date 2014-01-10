package com.gss.gevee.be.core.enums;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public enum EnuEtat {


	CREE("2000", "Cr�e"),
	
	VALIDE("2002", "Valid�"),
	
	DEMARRE("2003", "D�marr�"),
	
	CLOTURE("2004", "Cl�tut�"),
	
	LIVRE("2005", "Livr�"),
	
	RECEPTIONNE("2006", "R�ceptionn�"),

	SUPP("2001", "Supprim�");

	/**
	 * Valeur de l'�num�ration
	 */
	private final String value;
	/**
	 * libell� de la valeur
	 */
	private final String libelle;
	
	/**
	 * Construit un objet �tant donn�e une valeur et un libell�
	 * @param value : la valeur
	 * @param libelle : le libell�
	 */
	private EnuEtat(String value, String libelle) {
		this.value = value;
		this.libelle = libelle;

	}
	/**
	 * Retourne la valeur de l'�num�ration
	 * @return value : la valeur retourn�e
	 */
	public String getValue() {
		return this.value;
	}
	/**
	 * Retourne le libell� de l'�num�ration
	 * @return libelle : le libell�
	 */
	public String getLibelle() {
		return this.libelle;
	}
	/**
	 * retourne une �num�ration en fonction de sa valeur
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
	 * Retourne la liste des valeurs de l'�num dans un map (Map<Code,Libelle>)
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
