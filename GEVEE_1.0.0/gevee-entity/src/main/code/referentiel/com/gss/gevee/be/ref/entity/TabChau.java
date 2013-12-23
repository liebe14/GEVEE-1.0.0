package com.gss.gevee.be.ref.entity;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.gss.gevee.be.core.base.DateTools;
import com.gss.gevee.be.core.base.GeveeBaseEntity;

@Entity
@Table(name="TAB_CHAU")
public class TabChau extends GeveeBaseEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public TabChau(){
		
	}
	
	//Matricule Chauffeur
	@Id
	@Column(name = "MAT_CHAU")
	private String matChau;

	//nom du Chauffeur
	@Column(name = "LIB_NOM")
	private String libNom;
	
	//prenom du Chauffeur
	@Column(name = "LIB_PRE")
	private String libPre;
	
	//Numero permit
	@Column(name = "NUM_PERM")
	private String numPerm;
	
	//Date Validité permit
	@Column(name = "DAT_VAL_PERM")
	private String datValPerm;
	
	//Information
	@Column(name = "LIB_INF")
	private String libInf;
	
	//Téléphone
	@Column(name = "LIB_TEL")
	private String libTel;
	
	//CNI
	@Column(name = "NUM_CNI")
	private String numCNI;
	
	//date delivrance CNI
	@Column(name = "DAT_DEL_CNI")
	private String datDelCNI;
	
	//date de validité CNI
	@Column(name = "DAT_VAL_CNI")
	private String datValCNI;
	
	
	public String getMatChau() {
		return matChau;
	}

	public void setMatChau(String matChau) {
		this.matChau = matChau;
	}

	public String getLibNom() {
		return libNom;
	}

	public void setLibNom(String libNom) {
		this.libNom = libNom;
	}

	public String getLibPre() {
		return libPre;
	}

	public void setLibPre(String libPre) {
		this.libPre = libPre;
	}

	public String getNumPerm() {
		return numPerm;
	}

	public void setNumPerm(String numPerm) {
		this.numPerm = numPerm;
	}

	public String getDatValPerm() {
		return datValPerm;
	}

	public void setDatValPerm(String datValPerm) {
		this.datValPerm = datValPerm;
	}

	public String getLibInf() {
		return libInf;
	}

	public void setLibInf(String libInf) {
		this.libInf = libInf;
	}

	public String getLibTel() {
		return libTel;
	}

	public void setLibTel(String libTel) {
		this.libTel = libTel;
	}

	public String getNumCNI() {
		return numCNI;
	}

	public void setNumCNI(String numCNI) {
		this.numCNI = numCNI;
	}

	public String getDatDelCNI() {
		return datDelCNI;
	}

	public void setDatDelCNI(String datDelCNI) {
		this.datDelCNI = datDelCNI;
	}

	public String getDatValCNI() {
		return datValCNI;
	}

	public void setDatValCNI(String datValCNI) {
		this.datValCNI = datValCNI;
	}


	public Date getDateValidePermis() {
		try {
			if (this.datValPerm != null && !this.datValPerm.isEmpty()) {
				return DateTools.getDateValue(this.datValPerm);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void setDateValidePermis(Date dateValidePermis) {
		if (dateValidePermis == null)
			this.datValPerm = null;
		else
			this.datValPerm = DateTools.formatDate(dateValidePermis);
	}
	
	/***
	 * 
	 * @return Retourne true si la date la date de validité du permis  est
	 *         atteinte et false sinon
	 */
	public boolean getValidePermis() {
		if (this.datValPerm != null && !this.datValPerm.trim().isEmpty()) {
			String todayDate = DateTools.formatDate(new Date());
			int nbre = todayDate.compareTo(this.datValPerm);
			return (nbre > 0);
		}
		return false;
	}	


	public Date getDateDelivranceCNI() {
		try {
			if (this.datDelCNI != null && !this.datDelCNI.isEmpty()) {
				return DateTools.getDateValue(this.datDelCNI);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void setDateDelivranceCNI(Date dateDelivranceCNI) {
		if (dateDelivranceCNI == null)
			this.datDelCNI = null;
		else
			this.datDelCNI = DateTools.formatDate(dateDelivranceCNI);
	}
	

	public Date getDateValideCNI() {
		try {
			if (this.datValCNI != null && !this.datValCNI.isEmpty()) {
				return DateTools.getDateValue(this.datValCNI);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void setDateValideCNI(Date dateValideCNI) {
		if (dateValideCNI == null)
			this.datValCNI = null;
		else
			this.datValCNI = DateTools.formatDate(dateValideCNI);
	}
	
	/***
	 * 
	 * @return Retourne true si la date la date de validité de la CNI  est
	 *         atteinte et false sinon
	 */
	public boolean getValideCNI() {
		if (this.datValCNI != null && !this.datValCNI.trim().isEmpty()) {
			String todayDate = DateTools.formatDate(new Date());
			int nbre = todayDate.compareTo(this.datValCNI);
			return (nbre > 0);
		}
		return false;
	}	

	@Override
	public String getEntityCode() {
		return "Chau";
	}

	@Override
	public Serializable getId() {		
		 return getMatChau();
	}

	@Override
	public void setId(Serializable id) {
		setMatChau((String)id);
	}

	@Override
	public void validateData() {		
		
	}

	@Override
	public void initData() {	
		
	}

}
