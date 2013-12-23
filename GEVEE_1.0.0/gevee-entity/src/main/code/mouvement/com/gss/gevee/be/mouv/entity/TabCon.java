package com.gss.gevee.be.mouv.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.gss.gevee.be.core.base.GeveeBaseEntity;
import com.gss.gevee.be.core.enums.EnuAco;
import com.gss.gevee.be.core.enums.EnuTypCon;
import com.gss.gevee.be.core.enums.EnuTypMar;

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
	private String valPoi;
	
	// Volume 
	@Column(name = "VAL_VOL")
	private BigDecimal valVol;

	//Aconier
	@Column(name = "ENU_ACO")
	private String enuAco;
	
	//Marchandise
	@Column(name = "NUM_COM")
	private String numCom;
	
	//L'ordre de transport du conteneur
	@ManyToOne
	@JoinColumn(name = "NUM_ORD_TRA")
	private TabOrd tabOrdTran;



	@Override
	public String getEntityCode() {
		return "Con";
	}

	@Override
	public Serializable getId() {
		return getCodCon();
	}

	@Override
	public void setId(Serializable id) {
		setCodCon((String)id);
		
	}

	@Override
	public void validateData() {
		try {
			if (this.getTabOrdTran() != null
					&& (this.getTabOrdTran().getNumOrdTra() == null || this
							.getTabOrdTran().getNumOrdTra().trim().isEmpty()))
				this.setTabOrdTran(null);
		} catch (Exception e) {
		}
		
	}

	@Override
	public void initData() {
		tabOrdTran = (tabOrdTran == null ? new TabOrd() : tabOrdTran);		
	}

	public void setCodCon(String codCon) {
		this.codCon = codCon;
	}

	public String getCodCon() {
		return codCon;
	}

	public void setNumCon(String numCon) {
		this.numCon = numCon;
	}

	public String getNumCon() {
		return numCon;
	}

	public void setNumPlo1(String numPlo1) {
		this.numPlo1 = numPlo1;
	}

	public String getNumPlo1() {
		return numPlo1;
	}

	public void setNumPlo2(String numPlo2) {
		this.numPlo2 = numPlo2;
	}

	public String getNumPlo2() {
		return numPlo2;
	}

	public void setEnuTypCon(String enuTypCon) {
		this.enuTypCon = enuTypCon;
	}

	public String getEnuTypCon() {
		return enuTypCon;
	}	
	
	public String getLEnuTypCon() {
		EnuTypCon v$enum = EnuTypCon.getByValue(this.enuTypCon); 
		return (v$enum == null)? null: v$enum.getLibelle();
	}

	public void setEnuTypMar(String enuTypMar) {
		this.enuTypMar = enuTypMar;
	}

	public String getEnuTypMar() {
		return enuTypMar;
	}
	
	public String getLEnuTypMar() {
		EnuTypMar v$enum = EnuTypMar.getByValue(this.enuTypMar); 
		return (v$enum == null)? null: v$enum.getLibelle();
	}

	public void setValPoi(String valPoi) {
		this.valPoi = valPoi;
	}

	public String getValPoi() {
		return valPoi;
	}

	public void setValVol(BigDecimal valVol) {
		this.valVol = valVol;
	}

	public BigDecimal getValVol() {
		return valVol;
	}

	public void setEnuAco(String enuAco) {
		this.enuAco = enuAco;
	}

	public String getEnuAco() {
		return enuAco;
	}
	
	public String getLEnuAco() {
		EnuAco v$enum = EnuAco.getByValue(this.enuAco); 
		return (v$enum == null)? null: v$enum.getLibelle();
	}

	public void setNumCom(String numCom) {
		this.numCom = numCom;
	}

	public String getNumCom() {
		return numCom;
	}

	public void setTabOrdTran(TabOrd tabOrdTran) {
		this.tabOrdTran = tabOrdTran;
	}

	public TabOrd getTabOrdTran() {
		return tabOrdTran;
	}

}
