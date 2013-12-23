package com.gss.gevee.be.ref.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.gss.gevee.be.core.base.GeveeBaseEntity;

@Entity
@Table(name="TAB_LIEU")
public class TabLieu extends GeveeBaseEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public TabLieu(){
		
	}
	
	//Code lieu
	@Id
	@Column(name = "COD_LIEU")
	private String codLieu;

	//Libelle du lieu
	@Column(name = "LIB_LIEU")
	private String libLieu;

	
	public String getCodLieu() {
		return codLieu;
	}

	public void setCodLieu(String codLieu) {
		this.codLieu = codLieu;
	}

	public String getLibLieu() {
		return libLieu;
	}

	public void setLibLieu(String libLieu) {
		this.libLieu = libLieu;
	}

	@Override
	public String getEntityCode() {
		return "Lieu";
	}

	@Override
	public Serializable getId() {		
		 return getCodLieu();
	}

	@Override
	public void setId(Serializable id) {
		setCodLieu((String)id);
	}

	@Override
	public void validateData() {		
		
	}

	@Override
	public void initData() {	
		
	}

}
