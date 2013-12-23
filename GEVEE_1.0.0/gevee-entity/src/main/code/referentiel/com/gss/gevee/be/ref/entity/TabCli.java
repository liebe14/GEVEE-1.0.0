package com.gss.gevee.be.ref.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.gss.gevee.be.core.base.GeveeBaseEntity;

@Entity
@Table(name="TAB_CLI")
public class TabCli extends GeveeBaseEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public TabCli(){
		
	}
	
	//Code du client
	@Id
	@Column(name = "COD_CLI")
	private String codCli;

	//nom du client
	@Column(name = "LIB_NOM")
	private String libNom;
	
	//prenom du clien
	@Column(name = "LIB_PRE")
	private String libPre;
	
	//Téléphone
	@Column(name = "LIB_TEL")
	private String libTel;
	
	//Fax
	@Column(name = "LIB_FAX")
	private String libFax;
	
	//Localité
	@Column(name = "LIB_LOC")
	private String libLoc;
	
	//Adresse
	@Column(name = "LIB_ADR")
	private String libAdr;
	
	
	
	public String getCodCli() {
		return codCli;
	}

	public void setCodCli(String codCli) {
		this.codCli = codCli;
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

	public String getLibTel() {
		return libTel;
	}

	public void setLibTel(String libTel) {
		this.libTel = libTel;
	}

	public String getLibFax() {
		return libFax;
	}

	public void setLibFax(String libFax) {
		this.libFax = libFax;
	}

	public String getLibLoc() {
		return libLoc;
	}

	public void setLibLoc(String libLoc) {
		this.libLoc = libLoc;
	}

	public String getLibAdr() {
		return libAdr;
	}

	public void setLibAdr(String libAdr) {
		this.libAdr = libAdr;
	}

	@Override
	public String getEntityCode() {
		return "Cli";
	}

	@Override
	public Serializable getId() {		
		 return getCodCli();
	}

	@Override
	public void setId(Serializable id) {
		setCodCli((String)id);
	}

	@Override
	public void validateData() {		
		
	}

	@Override
	public void initData() {	
		
	}

}
