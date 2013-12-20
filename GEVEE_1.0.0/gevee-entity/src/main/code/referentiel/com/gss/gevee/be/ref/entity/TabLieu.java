package com.gss.gevee.be.ref.entity;

import javax.persistence.Column;
import javax.persistence.Id;

public class TabLieu {
	
	//Numero de l'ordre de transport
	@Id
	@Column(name = "NUM_ORD_TRA")
	private String codLieu;

	public void setCodLieu(String codLieu) {
		this.codLieu = codLieu;
	}

	public String getCodLieu() {
		return codLieu;
	}

}
