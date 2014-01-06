package com.gss.gevee.be.mouv.serializer;

import com.gss.gevee.be.core.base.DateTools;
import com.gss.gevee.be.mouv.entity.TabCon;
import com.gss.gevee.be.mouv.entity.TabOrd;

public class SrlOrdTranElt {
	
	/**
	 * Valeur à l'erreur
	 */
	private static String ERROR_VALUE = "";
	
	private String numOrd;
	private String numDos;
	private String enuAct;
	private String valPoiTot;
	private String nbrCol;
	private String lieuEnlev;
	private String lieuDecha;
	private String datEnlev;
	private String datDecha;
	private String libObs;
	private String numCon;
	private String enuTypCon;
	private String enuAco;
	private String numPlo1;
	private String numPlo2;
	private String valPoi;
	private String valVol;
	private String enuMar;
	private String numCom;
	private String client;
	
	public SrlOrdTranElt() {
		
	}
	
	public SrlOrdTranElt(TabOrd ordtrans, TabCon con){
		
		try {
			this.numOrd = ordtrans.getNumOrdTra();
		}catch (Exception e) {
			this.numOrd=ERROR_VALUE ;
		}
		try {
			this.numDos = ordtrans.getNumDoss();
		}catch (Exception e) {
			this.numDos=ERROR_VALUE ;
		}
		try {
			this.enuAct = ordtrans.getLEnuAct();
		}catch (Exception e) {
			this.enuAct=ERROR_VALUE ;
		}
		try {
			this.valPoiTot = ordtrans.getValPoiTot();
		}catch (Exception e) {
			this.valPoiTot=ERROR_VALUE ;
		}
		try {
			this.nbrCol = ordtrans.getValColTot().toString();
		}catch (Exception e) {
			this.nbrCol=ERROR_VALUE ;
		}
		try {
			this.lieuEnlev = ordtrans.getTabLieuEnlev().getLibLieu();
		}catch (Exception e) {
			this.lieuEnlev=ERROR_VALUE ;
		}
		try {
			this.lieuDecha = ordtrans.getTabLieuDecha().getLibLieu();
		}catch (Exception e) {
			this.lieuDecha=ERROR_VALUE ;
		}
		try {
			this.datEnlev = DateTools.formatDate("dd/MM/yyyy", DateTools
							.getDateValue(ordtrans.getDatEnlev()));
		} catch (Exception e) {
			this.datEnlev = ERROR_VALUE;
		}
		try {
			this.datDecha = DateTools.formatDate("dd/MM/yyyy", DateTools
							.getDateValue(ordtrans.getDatDecha()));
		} catch (Exception e) {
			this.datDecha = ERROR_VALUE;
		}
		try {
			this.libObs = ordtrans.getLibObs();
		}catch (Exception e) {
			this.libObs=ERROR_VALUE ;
		}
		try {
			this.numCon = con.getNumCon();
		}catch (Exception e) {
			this.numCon=ERROR_VALUE ;
		}
		try {
			this.enuTypCon = con.getLEnuTypCon();
		}catch (Exception e) {
			this.enuTypCon=ERROR_VALUE ;
		}
		try {
			this.enuAco = con.getLEnuAco();
		}catch (Exception e) {
			this.enuAco=ERROR_VALUE ;
		}
		try {
			this.numPlo1 = con.getNumPlo1();
		}catch (Exception e) {
			this.numPlo1=ERROR_VALUE ;
		}
		try {
			this.numPlo2 = con.getNumPlo2();
		}catch (Exception e) {
			this.numPlo2=ERROR_VALUE ;
		}
		try {
			this.valPoi = con.getValPoi();
		}catch (Exception e) {
			this.valPoi=ERROR_VALUE ;
		}
		try {
			this.valVol = con.getValVol().toString();
		}catch (Exception e) {
			this.valVol=ERROR_VALUE ;
		}
		try {
			this.enuMar = con.getLEnuTypMar();
		}catch (Exception e) {
			this.enuMar=ERROR_VALUE ;
		}
		try {
			this.numCom = con.getNumCom();
		}catch (Exception e) {
			this.numCom=ERROR_VALUE ;
		}
		try {
			this.setClient(ordtrans.getTabClient().getLibNom());
		}catch (Exception e) {
			this.numOrd=ERROR_VALUE ;
		}
		
		
	}

	public String getNumOrd() {
		return numOrd;
	}
	public void setNumOrd(String numOrd) {
		this.numOrd = numOrd;
	}
	public String getNumDos() {
		return numDos;
	}
	public void setNumDos(String numDos) {
		this.numDos = numDos;
	}
	public String getEnuAct() {
		return enuAct;
	}
	public void setEnuAct(String enuAct) {
		this.enuAct = enuAct;
	}
	public String getValPoiTot() {
		return valPoiTot;
	}
	public void setValPoiTot(String valPoiTot) {
		this.valPoiTot = valPoiTot;
	}
	public String getNbrCol() {
		return nbrCol;
	}
	public void setNbrCol(String nbrCol) {
		this.nbrCol = nbrCol;
	}
	public String getLieuEnlev() {
		return lieuEnlev;
	}
	public void setLieuEnlev(String lieuEnlev) {
		this.lieuEnlev = lieuEnlev;
	}
	public String getLieuDecha() {
		return lieuDecha;
	}
	public void setLieuDecha(String lieuDecha) {
		this.lieuDecha = lieuDecha;
	}
	public String getDatEnlev() {
		return datEnlev;
	}
	public void setDatEnlev(String datEnlev) {
		this.datEnlev = datEnlev;
	}
	public String getDatDecha() {
		return datDecha;
	}
	public void setDatDecha(String datDecha) {
		this.datDecha = datDecha;
	}
	public String getLibObs() {
		return libObs;
	}
	public void setLibObs(String libObs) {
		this.libObs = libObs;
	}
	public String getNumCon() {
		return numCon;
	}
	public void setNumCon(String numCon) {
		this.numCon = numCon;
	}
	public String getEnuTypCon() {
		return enuTypCon;
	}
	public void setEnuTypCon(String enuTypCon) {
		this.enuTypCon = enuTypCon;
	}
	public String getEnuAco() {
		return enuAco;
	}
	public void setEnuAco(String enuAco) {
		this.enuAco = enuAco;
	}
	public String getNumPlo1() {
		return numPlo1;
	}
	public void setNumPlo1(String numPlo1) {
		this.numPlo1 = numPlo1;
	}
	public String getNumPlo2() {
		return numPlo2;
	}
	public void setNumPlo2(String numPlo2) {
		this.numPlo2 = numPlo2;
	}
	public String getValPoi() {
		return valPoi;
	}
	public void setValPoi(String valPoi) {
		this.valPoi = valPoi;
	}
	public String getValVol() {
		return valVol;
	}
	public void setValVol(String valVol) {
		this.valVol = valVol;
	}
	public String getEnuMar() {
		return enuMar;
	}
	public void setEnuMar(String enuMar) {
		this.enuMar = enuMar;
	}
	public String getNumCom() {
		return numCom;
	}
	public void setNumCom(String numCom) {
		this.numCom = numCom;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getClient() {
		return client;
	}

}
