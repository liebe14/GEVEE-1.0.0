package com.gss.gevee.be.core.enums;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public enum EnuTypCon {
	
	DRY_20("2000", "20' DRY"),

	DRY_40("2001", "40' DRY"),
	
	REEFER_20("2002", "20' REEFER"),
	
	REEFER_40("2003", "40' REEFER"),
	
	OPENTOP_20("2004", "20' OPEN TOP"),
	
	OPENTOP_40("2005", "40' OPEN TOP"),
	
	FLAT_20("2006", "20 ' FLAT"),
	
	FLAT_40("2007", "40 ' FLAT");

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
	private EnuTypCon(String value, String libelle) {
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
	public static EnuTypCon getByValue(String enumValue) {
		for (EnuTypCon enm : EnuTypCon.class
				.getEnumConstants()) {
			if (enm.getValue().equalsIgnoreCase(enumValue))
				return enm;
		}
		return null;
	}

	private static List<EnuTypCon> myList;
	private static Map<String, String> myMap;

	/***
	 * Retourne la liste des valeurs de l'�num dans un map (Map<Code,Libelle>)
	 * 
	 * @return
	 */
	public static Map<String, String> getMaps() {
		return myMap;
	}

	public static List<EnuTypCon> getListe() {
		return myList;
	}

	static {
		myList = new ArrayList<EnuTypCon>();
		myMap = new TreeMap<String, String>();
		myMap.put("", "");
		for (EnuTypCon enm : EnuTypCon.class
				.getEnumConstants()) {
			myList.add(enm);
			myMap.put(enm.getValue(), enm.getLibelle());
		}
	}

}
