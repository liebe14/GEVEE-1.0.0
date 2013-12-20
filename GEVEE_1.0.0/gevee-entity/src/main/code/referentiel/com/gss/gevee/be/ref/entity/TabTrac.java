package com.gss.gevee.be.ref.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.gss.gevee.be.core.enums.EnuFamilleTracteur;

@Entity
@Table(name="TAB_TRAC")
public class TabTrac extends TabAccVoi{
	
	private static final long serialVersionUID = 1L;
	
	@Override
	public String getEntityCode() {
		return "Trac";
	}
	
	//numero du parc : code de l'entité
	@Id
	@Column(name = "NUM_PARC")
	private String numParc;
	
	//Capacité
	@Column(name = "LIB_CAP")
	private String libCap;
	
	@Column(name = "ENU_FAM")
	private String enuFam;
	
	public String getNumParc() {
		return numParc;
	}

	public void setNumParc(String numParc) {
		this.numParc = numParc;
	}

	public void setLibCap(String libCap) {
		this.libCap = libCap;
	}

	public String getLibCap() {
		return libCap;
	}
	
	public void setEnuFam(String enuFam) {
		this.enuFam = enuFam;
	}

	public String getEnuFam() {
		return enuFam;
	}
	public String getLEnuFam() {
		EnuFamilleTracteur v$enum = EnuFamilleTracteur.getByValue(this.enuFam); 
		return (v$enum == null)? null: v$enum.getLibelle();
	}
	
	@Override
	public Serializable getId() {
		return getNumParc();
	}

	@Override
	public void setId(Serializable id) {
		setNumParc((String)id);
	}



}
