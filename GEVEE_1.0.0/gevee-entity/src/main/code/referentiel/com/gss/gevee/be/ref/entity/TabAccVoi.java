package com.gss.gevee.be.ref.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.gss.gevee.be.core.base.DateTools;
import com.gss.gevee.be.core.base.GeveeBaseEntity;
import com.gss.gevee.be.core.enums.EnuMarque;

@MappedSuperclass
public abstract class TabAccVoi extends GeveeBaseEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	public TabAccVoi(){
		
	}
	//numero du parc : code de l'entité
	@Id
	@Column(name = "NUM_PARC")
	private String numParc;
	
	//numero d'immatriculation : doit étre unique
	@Column(name = "NUM_MAT")
	private String numMat;
	
	@Column(name = "DAT_MIS_CIR")
	private String datMisCir;
	
	@Column(name = "ENU_MAR")
	private String enuMar;
	
	@Column(name = "NUM_CHA")
	private String numCha;
	
	@Column(name = "NBR_ESS")
	private Integer nbrEss;
	
	@Column(name = "LIB_ETA_FON")
	private String libEtaFon;

	
	@Override
	public void validateData() {
		
	}

	@Override
	public void initData() {
		// TODO Auto-generated method stub
		
	}

	public void setNumParc(String numParc) {
		this.numParc = numParc;
	}

	public String getNumParc() {
		return numParc;
	}

	public void setNumMat(String numMat) {
		this.numMat = numMat;
	}

	public String getNumMat() {
		return numMat;
	}
	
	public String getEnuMar() {
		return enuMar;
	}
	public void setEnuMar(String enuMar) {
		this.enuMar = enuMar;
	}
	public String getLEnuMar() {
		EnuMarque v$enum = EnuMarque.getByValue(this.enuMar); 
		return (v$enum == null)? null: v$enum.getLibelle();
	}

	public void setDatMisCir(String datMisCir) {
		this.datMisCir = datMisCir;
	}

	public String getDatMisCir() {
		return datMisCir;
	}
	
	public Date getDateMisCir() {
		try {
			if (this.datMisCir != null && !this.datMisCir.isEmpty()) {
				return DateTools.getDateValue(this.datMisCir);
			}
		} catch (Exception e) {
			return null;
		}
		return null;
	}
	
	public void setDateMisCir(Date dateMisCir) {
		if (dateMisCir == null)
			this.datMisCir = null;
		else
			this.datMisCir = DateTools.formatDate(dateMisCir);
	}

	public void setNumCha(String numCha) {
		this.numCha = numCha;
	}

	public String getNumCha() {
		return numCha;
	}

	public void setNbrEss(Integer nbrEss) {
		this.nbrEss = nbrEss;
	}

	public Integer getNbrEss() {
		return nbrEss;
	}

	public void setLibEtaFon(String libEtaFon) {
		this.libEtaFon = libEtaFon;
	}

	public String getLibEtaFon() {
		return libEtaFon;
	}

}
