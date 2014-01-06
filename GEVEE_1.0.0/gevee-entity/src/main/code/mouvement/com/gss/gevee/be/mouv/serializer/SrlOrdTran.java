package com.gss.gevee.be.mouv.serializer;

import java.io.Serializable;
import java.util.ArrayList;

public class SrlOrdTran implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Constructeur par defaut
	 */
	public SrlOrdTran() {
		
	}
	/**
	 * Liste des �l�ments de l'�tat
	 */
	ArrayList<SrlOrdTranElt> srlOrdTran;
	
	/**
	 * Accesseur en lecture de la liste des �l�ments de l'�tat
	 */
	public ArrayList<SrlOrdTranElt> getSrlOrdTran() {
		return srlOrdTran;
	}

	/**
	 * Accesseur en modification de la liste des �l�ments de l'�tat
	 */
	public void setSrlOrdTran(ArrayList<SrlOrdTranElt> srlOrdTran) {
		this.srlOrdTran = srlOrdTran;
	}
	
	/**
	 * Ajoute un �l�ment dans la liste des �l�ments de l'�tat
	 */
	public void addElement(SrlOrdTranElt p$elt) {

		if (srlOrdTran == null)
			srlOrdTran = new ArrayList<SrlOrdTranElt>();
		srlOrdTran.add(p$elt);
		}
	
	/**
	 * Initialise les donn�es de l'objet
	 */
	public void initData() {
		srlOrdTran = new ArrayList<SrlOrdTranElt>();
	}


}
