package com.gss.gevee.be.mouv.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.gss.gevee.be.core.base.DateTools;
import com.gss.gevee.be.core.base.GeveeBaseEntity;
import com.gss.gevee.be.core.enums.EnuActivite;
import com.gss.gevee.be.ref.entity.TabCli;
import com.gss.gevee.be.ref.entity.TabLieu;

@Entity
@Table(name="TAB_ORD")
public class TabOrd extends GeveeBaseEntity{

	private static final long serialVersionUID = 1L;
	
	public TabOrd(){
		
	}	
	
	//Numero de l'ordre de transport
	@Id
	@Column(name = "NUM_ORD_TRA")
	private String numOrdTra;
	
	//lieu d'enl�vement
	@ManyToOne
	@JoinColumn(name = "COD_CLI")
	private TabCli tabClient;
	
	//Activit�
	@Column(name = "ENU_ACT")
	private String enuAct;
	
	//date d'enl�vement
	@Column(name = "DAT_ENLEV")
	private String datEnlev;
	
	//date de dechargement
	@Column(name = "DAT_DECHA")
	private String datDecha;
	
	//N� Dossier
	@Column(name = "NUM_DOSS")
	private String numDoss;
	
	//lieu d'enl�vement
	@ManyToOne
	@JoinColumn(name = "COD_LIEU_ENLEV")
	private TabLieu tabLieuEnlev;
	
	//lieu de dechargement
	@ManyToOne
	@JoinColumn(name = "COD_LIEU_DECHA")
	private TabLieu tabLieuDecha;
	
	// Poids total
	@Column(name = "VAL_POI_TOT")
	private String valPoiTot;
	
	// nombre de colis total
	@Column(name = "VAL_COL_TOT")
	private BigDecimal valColTot;
	
	// Observations
	@Column(name = "LIB_OBS")
	private String libObs;
	
	@Column(name = "BOO_CLO")
	private BigDecimal booEstClo;
	
	/**
	 * Liste des conteneurs
	 */
	@Transient
	private List<TabCon> listCon = new ArrayList<TabCon>();
	
	
	@Override
	public String getEntityCode() {
		return "Ord";
	}

	@Override
	public Serializable getId() {
		return getNumOrdTra();
	}

	@Override
	public void setId(Serializable id) {
		setNumOrdTra((String)id);
		
	}

	@Override
	public void validateData() {
		
		try {
			if (this.getTabLieuEnlev() != null
					&& (this.getTabLieuEnlev().getCodLieu() == null || this
							.getTabLieuEnlev().getCodLieu().trim().isEmpty()))
				this.setTabLieuEnlev(null);
		} catch (Exception e) {
		}
		
		try {
			if (this.getTabLieuDecha() != null
					&& (this.getTabLieuDecha().getCodLieu() == null || this
							.getTabLieuDecha().getCodLieu().trim().isEmpty()))
				this.setTabLieuDecha(null);
		} catch (Exception e) {
		}
		
		try {
			if (this.getTabClient() != null
					&& (this.getTabClient().getCodCli() == null || this
							.getTabClient().getCodCli().trim().isEmpty()))
				this.setTabClient(null);
		} catch (Exception e) {
		}

		
	}

	@Override
	public void initData() {
		tabLieuEnlev = (tabLieuEnlev == null ? new TabLieu() : tabLieuEnlev);
		tabLieuDecha = (tabLieuDecha == null ? new TabLieu() : tabLieuDecha);
		tabClient = (tabClient == null ? new TabCli() : tabClient);
	}

	public void setNumOrdTra(String numOrdTra) {
		this.numOrdTra = numOrdTra;
	}

	public String getNumOrdTra() {
		return numOrdTra;
	}

	public void setEnuAct(String enuAct) {
		this.enuAct = enuAct;
	}

	public String getEnuAct() {
		return enuAct;
	}
	public String getLEnuAct() {
		EnuActivite v$enum = EnuActivite.getByValue(this.enuAct); 
		return (v$enum == null)? null: v$enum.getLibelle();
	}

	public void setDatEnlev(String datEnlev) {
		this.datEnlev = datEnlev;
	}

	public String getDatEnlev() {
		return datEnlev;
	}
	
	public Date getDateEnlev() {
		try {
			if (this.datEnlev != null && !this.datEnlev.isEmpty()) {
				return DateTools.getDateValue(this.datEnlev);
			}
		} catch (Exception e) {
			return null;
		}
		return null;
	}
	
	public void setDateEnlev(Date dateEnlev) {
		if (dateEnlev == null)
			this.datEnlev = null;
		else
			this.datEnlev = DateTools.formatDate(dateEnlev);
	}

	public void setDatDecha(String datDecha) {
		this.datDecha = datDecha;
	}

	public String getDatDecha() {
		return datDecha;
	}
	
	public Date getDateDecha() {
		try {
			if (this.datDecha != null && !this.datDecha.isEmpty()) {
				return DateTools.getDateValue(this.datDecha);
			}
		} catch (Exception e) {
			return null;
		}
		return null;
	}
	
	public void setDateDecha(Date dateDecha) {
		if (dateDecha == null)
			this.datDecha = null;
		else
			this.datDecha = DateTools.formatDate(dateDecha);
	}

	public void setNumDoss(String numDoss) {
		this.numDoss = numDoss;
	}

	public String getNumDoss() {
		return numDoss;
	}

	public void setTabLieuEnlev(TabLieu tabLieuEnlev) {
		this.tabLieuEnlev = tabLieuEnlev;
	}

	public TabLieu getTabLieuEnlev() {
		return tabLieuEnlev;
	}

	public void setTabLieuDecha(TabLieu tabLieuDecha) {
		this.tabLieuDecha = tabLieuDecha;
	}

	public TabLieu getTabLieuDecha() {
		return tabLieuDecha;
	}

	public void setValPoiTot(String valPoiTot) {
		this.valPoiTot = valPoiTot;
	}

	public String getValPoiTot() {
		return valPoiTot;
	}

	public void setValColTot(BigDecimal valColTot) {
		this.valColTot = valColTot;
	}

	public BigDecimal getValColTot() {
		return valColTot;
	}

	public void setLibObs(String libObs) {
		this.libObs = libObs;
	}

	public String getLibObs() {
		return libObs;
	}

	public void setListCon(List<TabCon> listCon) {
		this.listCon = listCon;
	}

	public List<TabCon> getListCon() {
		return listCon;
	}

	public void setTabClient(TabCli tabClient) {
		this.tabClient = tabClient;
	}

	public TabCli getTabClient() {
		return tabClient;
	}

	public void setBooEstClo(BigDecimal booEstClo) {
		this.booEstClo = booEstClo;
	}

	public BigDecimal getBooEstClo() {
		return booEstClo;
	}

}
