package com.gss.gevee.be.ref.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.gss.gevee.be.core.enums.EnuFamilleRemorque;

@Entity
@Table(name="TAB_REM")
public class TabRem extends TabAccVoi{
	
	private static final long serialVersionUID = 1L;
	
	@Override
	public String getEntityCode() {
		return "Rem";
	}

	//numero du parc : code de l'entité
	@Id
	@Column(name = "NUM_PARC")
	private String numParc;
	
	@Column(name = "ENU_FAM")
	private String enuFam;
	
	public String getNumParc() {
		return numParc;
	}

	public void setNumParc(String numParc) {
		this.numParc = numParc;
	}
	
	public void setEnuFam(String enuFam) {
		this.enuFam = enuFam;
	}

	public String getEnuFam() {
		return enuFam;
	}
	
	public String getLEnuFam() {
		EnuFamilleRemorque v$enum = EnuFamilleRemorque.getByValue(this.enuFam); 
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
