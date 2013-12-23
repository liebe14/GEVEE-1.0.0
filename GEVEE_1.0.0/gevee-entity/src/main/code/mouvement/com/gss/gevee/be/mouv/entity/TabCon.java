package com.gss.gevee.be.mouv.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Id;

import com.gss.gevee.be.core.base.GeveeBaseEntity;

public class TabCon extends GeveeBaseEntity{
	
	private static final long serialVersionUID = 1L;
	
	public TabCon(){
		
	}
	//Code conteneur = numCon + numOrd
	@Id
	@Column(name = "COD_CON")
	private String codCon;
	
	//Numero conteneur /N°ordre
	@Column(name = "NUM_CON")
	private String numCon;
	
	//Numero Plomb1
	@Column(name = "NUM_PLO1")
	private String numPlo1;
	
	//Numero Plomb2
	@Column(name = "NUM_PLO2")
	private String numPlo2;
	
	//Type Conteneur
	@Column(name = "ENU_TYP_CON")
	private String enuTypCon;

	//Marchandise
	@Column(name = "ENU_TYP_MAR")
	private String enuTypMar;
	
	// Poids 
	@Column(name = "VAL_POI")
	private BigDecimal valPoi;
	
	// Volume 
	@Column(name = "VAL_VOL")
	private BigDecimal valVol;

	//Aconier
	@Column(name = "ENU_ACO")
	private String enuAco;
	
	//Marchandise
	@Column(name = "NUM_COM")
	private String numCom;



	@Override
	public String getEntityCode() {
		return "Con";
	}

	@Override
	public Serializable getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setId(Serializable id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void validateData() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub
		
	}	

}
