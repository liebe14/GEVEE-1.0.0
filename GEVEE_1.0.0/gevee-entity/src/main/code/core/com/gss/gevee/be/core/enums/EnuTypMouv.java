package com.gss.gevee.be.core.enums;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public enum EnuTypMouv {
	
	TRANSFERT("01", "Transfert"),
	
	LIVRAISON("02", "Livraison"),
	
	RETOUR_VIDE("03", "Retour vide"),
	
	RESTITUTION_VIDE("04", "Restitution Vide"),
	
	DOUBLE_RELEVAGE("05", "Double relevage"),

	POSITIONNEMENT("06", "Positionnement");

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
	private EnuTypMouv(String value, String libelle) {
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
	public static EnuTypMouv getByValue(String enumValue) {
		for (EnuTypMouv enm : EnuTypMouv.class
				.getEnumConstants()) {
			if (enm.getValue().equalsIgnoreCase(enumValue))
				return enm;
		}
		return null;
	}

	private static List<EnuTypMouv> myList;
	private static Map<String, String> myMap;

	/***
	 * Retourne la liste des valeurs de l'�num dans un map (Map<Code,Libelle>)
	 * 
	 * @return
	 */
	public static Map<String, String> getMaps() {
		return myMap;
	}

	public static List<EnuTypMouv> getListe() {
		return myList;
	}

	static {
		myList = new ArrayList<EnuTypMouv>();
		myMap = new TreeMap<String, String>();
		myMap.put("", "");
		for (EnuTypMouv enm : EnuTypMouv.class
				.getEnumConstants()) {
			myList.add(enm);
			myMap.put(enm.getValue(), enm.getLibelle());
		}
	}

}
