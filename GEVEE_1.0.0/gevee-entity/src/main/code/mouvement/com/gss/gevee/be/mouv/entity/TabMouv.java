package com.gss.gevee.be.mouv.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.gss.gevee.be.core.base.DateTools;
import com.gss.gevee.be.core.base.GeveeBaseEntity;
import com.gss.gevee.be.core.enums.EnuEtatCon;
import com.gss.gevee.be.core.enums.EnuSensMouv;
import com.gss.gevee.be.core.enums.EnuTypMouv;
import com.gss.gevee.be.ref.entity.TabChau;
import com.gss.gevee.be.ref.entity.TabLieu;
import com.gss.gevee.be.ref.entity.TabRem;
import com.gss.gevee.be.ref.entity.TabTrac;

@Entity
@Table(name="TAB_MOUV")
public class TabMouv extends GeveeBaseEntity{
	
	private static final long serialVersionUID = 1L;

	public TabMouv(){
		
	}
	
	//Reference du mouvement
	@Id
	@Column(name = "COD_REF_MOUV")
	private String codRefMouv;
	
	//Reference du déplacement
	@ManyToOne
	@JoinColumn(name = "COD_REF_DEP")
	private TabDep tabDep;
	
	@Column(name = "ENU_SENS_MOUV")
	private String enuSensMouv;
	
	@Column(name = "ENU_TYP_MOUV")
	private String enuTypMouv;
	
	@Column(name = "ENU_ETAT_CON")
	private String enuEtatCon;
	
	@ManyToOne
	@JoinColumn(name = "COD_LIEU_DEPAR")
	private TabLieu tabLieuDepar;
	
	@ManyToOne
	@JoinColumn(name = "COD_LIEU_ARRIV")
	private TabLieu tabLieuArriv;
	
	@ManyToOne
	@JoinColumn(name = "COD_CHAU")
	private TabChau tabChau;
	
	@ManyToOne
	@JoinColumn(name = "COD_TRAC")
	private TabTrac tabTrac;
	
	@ManyToOne
	@JoinColumn(name = "COD_REM")
	private TabRem tabRem;
	
	@Column(name = "DAT_DEPAR")
	private String datDepar;
	
	@Column(name = "DAT_ARRIV")
	private String datArriv;
	
	@Column(name = "HEUR_DEPAR")
	private String heurDepar;
	
	@Column(name = "HEUR_ARRIV")
	private String heurArriv;
	
	//Position du mouvement : 1, 2, 3 etc...
	@Column(name = "POS_MOUV")
	private Integer posMouv;
	
	@Column(name = "BOO_VAL")
	private BigDecimal booEstVal;

	@Override
	public String getEntityCode() {
		return "Mouv";
	}

	@Override
	public Serializable getId() {
		return getCodRefMouv();
	}

	@Override
	public void setId(Serializable id) {
		setCodRefMouv((String)id);
	}

	@Override
	public void validateData() {
		
		try {
			if (this.getTabDep() != null
					&& (this.getTabDep().getCodRefDep() == null || this
							.getTabDep().getCodRefDep().trim().isEmpty()))
				this.setTabDep(null);
		} catch (Exception e) {
		}
		
		try {
			if (this.getTabChau() != null
					&& (this.getTabChau().getMatChau() == null || this
							.getTabChau().getMatChau().trim().isEmpty()))
				this.setTabChau(null);
		} catch (Exception e) {
		}
		
		try {
			if (this.getTabLieuArriv() != null
					&& (this.getTabLieuArriv().getCodLieu() == null || this
							.getTabLieuArriv().getCodLieu().trim().isEmpty()))
				this.setTabLieuArriv(null);
		} catch (Exception e) {
		}
		
		try {
			if (this.getTabLieuDepar() != null
					&& (this.getTabLieuDepar().getCodLieu() == null || this
							.getTabLieuDepar().getCodLieu().trim().isEmpty()))
				this.setTabLieuDepar(null);
		} catch (Exception e) {
		}
		
		try {
			if (this.getTabRem() != null
					&& (this.getTabRem().getNumParc() == null || this
							.getTabRem().getNumParc().trim().isEmpty()))
				this.setTabRem(null);
		} catch (Exception e) {
		}
		
		try {
			if (this.getTabTrac() != null
					&& (this.getTabTrac().getNumParc() == null || this
							.getTabTrac().getNumParc().trim().isEmpty()))
				this.setTabTrac(null);
		} catch (Exception e) {
		}

	}

	@Override
	public void initData() {
		
		tabChau = (tabChau == null ? new TabChau() : tabChau);
		tabDep = (tabDep == null ? new TabDep() : tabDep);
		tabLieuArriv = (tabLieuArriv == null ? new TabLieu() : tabLieuArriv);
		tabLieuDepar = (tabLieuDepar == null ? new TabLieu() : tabLieuDepar);
		tabRem = (tabRem == null ? new TabRem() : tabRem);
		tabTrac = (tabTrac == null ? new TabTrac() : tabTrac);
		
	}

	public void setCodRefMouv(String codRefMouv) {
		this.codRefMouv = codRefMouv;
	}

	public String getCodRefMouv() {
		return codRefMouv;
	}

	public void setTabDep(TabDep tabDep) {
		this.tabDep = tabDep;
	}

	public TabDep getTabDep() {
		return tabDep;
	}

	public void setEnuSensMouv(String enuSensMouv) {
		this.enuSensMouv = enuSensMouv;
	}

	public String getEnuSensMouv() {
		return enuSensMouv;
	}
	
	public String getLEnuSensMouv() {
		EnuSensMouv v$enum = EnuSensMouv.getByValue(this.enuSensMouv); 
		return (v$enum == null)? null: v$enum.getLibelle();
	}

	public void setEnuTypMouv(String enuTypMouv) {
		this.enuTypMouv = enuTypMouv;
	}

	public String getEnuTypMouv() {
		return enuTypMouv;
	}
	
	public String getLEnuTypMouv() {
		EnuTypMouv v$enum = EnuTypMouv.getByValue(this.enuTypMouv); 
		return (v$enum == null)? null: v$enum.getLibelle();
	}

	public void setEnuEtatCon(String enuEtatCon) {
		this.enuEtatCon = enuEtatCon;
	}

	public String getEnuEtatCon() {
		return enuEtatCon;
	}
	
	public String getLEnuEtatCon() {
		EnuEtatCon v$enum = EnuEtatCon.getByValue(this.enuEtatCon); 
		return (v$enum == null)? null: v$enum.getLibelle();
	}

	public void setTabLieuDepar(TabLieu tabLieuDepar) {
		this.tabLieuDepar = tabLieuDepar;
	}

	public TabLieu getTabLieuDepar() {
		return tabLieuDepar;
	}

	public void setTabLieuArriv(TabLieu tabLieuArriv) {
		this.tabLieuArriv = tabLieuArriv;
	}

	public TabLieu getTabLieuArriv() {
		return tabLieuArriv;
	}

	public void setTabChau(TabChau tabChau) {
		this.tabChau = tabChau;
	}

	public TabChau getTabChau() {
		return tabChau;
	}

	public void setTabTrac(TabTrac tabTrac) {
		this.tabTrac = tabTrac;
	}

	public TabTrac getTabTrac() {
		return tabTrac;
	}

	public void setTabRem(TabRem tabRem) {
		this.tabRem = tabRem;
	}

	public TabRem getTabRem() {
		return tabRem;
	}

	public void setDatDepar(String datDepar) {
		this.datDepar = datDepar;
	}

	public String getDatDepar() {
		return datDepar;
	}
	
	public Date getDateDepar() {
		if (this.datDepar == null || this.datDepar.trim().isEmpty())
			return null;

		try {
			return DateTools.getDateValue(this.datDepar);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void setDateDepar(Date dateDepar) {
		if (dateDepar == null)
			this.datDepar = null;
		else
			this.datDepar = DateTools.formatDate(dateDepar);
	}

	public void setDatArriv(String datArriv) {
		this.datArriv = datArriv;
	}

	public String getDatArriv() {
		return datArriv;
	}
	
	public Date getDateArriv() {
		if (this.datArriv == null || this.datArriv.trim().isEmpty())
			return null;

		try {
			return DateTools.getDateValue(this.datArriv);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void setDateArriv(Date dateArriv) {
		if (dateArriv == null)
			this.datArriv = null;
		else
			this.datArriv = DateTools.formatDate(dateArriv);
	}

	public void setHeurDepar(String heurDepar) {
		this.heurDepar = heurDepar;
	}

	public String getHeurDepar() {
		return heurDepar;
	}

	public void setHeurArriv(String heurArriv) {
		this.heurArriv = heurArriv;
	}

	public String getHeurArriv() {
		return heurArriv;
	}

	public void setPosMouv(Integer posMouv) {
		this.posMouv = posMouv;
	}

	public Integer getPosMouv() {
		return posMouv;
	}

	public void setBooEstVal(BigDecimal booEstVal) {
		this.booEstVal = booEstVal;
	}

	public BigDecimal getBooEstVal() {
		return booEstVal;
	}
	
	public boolean getBEstVal() {
		return (booEstVal != null && booEstVal.compareTo(BigDecimal.ONE) == 0);
	}

	public void setBEstRecep(boolean bEstVal) {
		this.booEstVal = (bEstVal ? BigDecimal.ONE : BigDecimal.ZERO);
	}

}
