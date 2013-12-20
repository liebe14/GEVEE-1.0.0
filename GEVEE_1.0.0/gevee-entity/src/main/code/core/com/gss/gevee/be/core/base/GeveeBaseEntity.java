package com.gss.gevee.be.core.base;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.gss.gevee.be.core.enums.EnuEtat;

@MappedSuperclass
public abstract class GeveeBaseEntity extends BaseEntity{

	private static final long serialVersionUID = 1L;
	
	@Column(name = "ETAT_ENT")
	private String etatEnt;
	
	@Column(name = "COD_EXE_FIS")
	private String codExeFis;
	
	@Column(name = "BOO_ACT")
	private BigDecimal booAct;
	
	/**
	 * Constructeur par défaut
	 */
	public GeveeBaseEntity(){
		
	}
	public void setCodExeFis(String codExeFis) {
		this.codExeFis = codExeFis;
	}

	public String getCodExeFis() {
		return codExeFis;
	}
	
	public void setBooAct(BigDecimal booAct) {
		this.booAct = booAct;
	}

	public BigDecimal getBooAct() {
		return booAct;
	}
	
	public String getSpecialId(){
		return (this.getId()==null ? "null" : this.getId().toString()) + "-null-null";
	}

	public void setEtatEnt(String etatEnt) {
		this.etatEnt = etatEnt;
	}

	public String getEtatEnt() {
		return etatEnt;
	}
	
	public String getLEtatEnt() {
		EnuEtat v$enum = EnuEtat.getByValue(this.etatEnt); 
		return (v$enum == null)? null: v$enum.getLibelle();
	}
	
}
