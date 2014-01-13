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
import javax.persistence.Transient;

import com.gss.gevee.be.core.base.DateTools;
import com.gss.gevee.be.core.base.GeveeBaseEntity;

@Entity
@Table(name="TAB_DEP")
public class TabDep extends GeveeBaseEntity{
	
	private static final long serialVersionUID = 1L;
	
	public TabDep(){
		
	}	
	
	//Reference du déplacement
	@Id
	@Column(name = "COD_REF_DEP")
	private String codRefDep;
	
	//Conteneur
	@ManyToOne
	@JoinColumn(name = "COD_CON")
	private TabCon tabCon;
	
	@Column(name = "LIB_RECEP")
	private String libRecep;
	
	@Column(name = "LIB_ADD_RECEP")
	private String libAddRecep;
	
	@Column(name = "DAT_RECEP")
	private String datRecep;
	
	@Column(name = "VAL_CAU")
	private BigDecimal valCau;
	
	@Column(name = "VAL_DET")
	private BigDecimal valDet;
	
	//Retour caution = valCau - ValDet
	@Transient
	private BigDecimal retCau;
	
	@Column(name = "DAT_EST_RET")
	private String datEstRet;
	
	@Column(name = "DAT_EFF_RET")
	private String datEffRet;
	
	@Column(name = "DAT_EFF_ENLEV")
	private String datEffEnlev;
	
	@Column(name = "DAT_EFF_DECHA")
	private String datEffDecha;
	
	@Column(name = "DAT_REL_VID")
	private String datRelVid;
	
	@Column(name = "BOO_CLO")
	private BigDecimal booEstClo;
	
	@Column(name = "BOO_RECEP")
	private BigDecimal booEstRecep;
	
	@Column(name = "BOO_DEM")
	private BigDecimal booEstDem;
	
	@Column(name = "BOO_CAU")
	private BigDecimal booEstCau;
	
	@Column(name = "BOO_REL_VID")
	private BigDecimal booRelVid;
	
	@Column(name = "BOO_EST_DECHA")
	private BigDecimal booEstDecha;
	
	@Column(name = "BOO_EST_RET")
	private BigDecimal booEstRet;


	@Override
	public String getEntityCode() {
		return "Dep";
	}

	@Override
	public Serializable getId() {
		return getCodRefDep();
	}

	@Override
	public void setId(Serializable id) {
		setCodRefDep((String)id);
	}

	@Override
	public void validateData() {
		try {
			if (this.getTabCon() != null
					&& (this.getTabCon().getNumCon() == null || this
							.getTabCon().getNumCon().trim().isEmpty()))
				this.setTabCon(null);
		} catch (Exception e) {
		}
	}

	@Override
	public void initData() {
		tabCon = (tabCon == null ? new TabCon() : tabCon);
		tabCon.initData();
	}

	public void setCodRefDep(String codRefDep) {
		this.codRefDep = codRefDep;
	}

	public String getCodRefDep() {
		return codRefDep;
	}

	public void setTabCon(TabCon tabCon) {
		this.tabCon = tabCon;
	}

	public TabCon getTabCon() {
		return tabCon;
	}

	public void setLibRecep(String libRecep) {
		this.libRecep = libRecep;
	}

	public String getLibRecep() {
		return libRecep;
	}

	public void setLibAddRecep(String libAddRecep) {
		this.libAddRecep = libAddRecep;
	}

	public String getLibAddRecep() {
		return libAddRecep;
	}

	public void setValCau(BigDecimal valCau) {
		this.valCau = valCau;
	}

	public BigDecimal getValCau() {
		return valCau;
	}

	public void setValDet(BigDecimal valDet) {
		this.valDet = valDet;
	}

	public BigDecimal getValDet() {
		return valDet;
	}

	public void setRetCau(BigDecimal retCau) {
		this.retCau = retCau;
	}

	public BigDecimal getRetCau() {
		if(valCau != null && valDet != null) retCau = valCau.subtract(valDet);
		return retCau;
	}

	public void setDatEstRet(String datEstRet) {
		this.datEstRet = datEstRet;
	}

	public String getDatEstRet() {
		return datEstRet;
	}
	
	public Date getDateEstRet() {
		if (this.datEstRet == null || this.datEstRet.trim().isEmpty())
			return null;

		try {
			return DateTools.getDateValue(this.datEstRet);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void setDateEstRet(Date dateEstRet) {
		if (dateEstRet == null)
			this.datEstRet = null;
		else
			this.datEstRet = DateTools.formatDate(dateEstRet);
	}

	public void setDatEffRet(String datEffRet) {
		this.datEffRet = datEffRet;
	}

	public String getDatEffRet() {
		return datEffRet;
	}
	
	public Date getDateEffRet() {
		if (this.datEffRet == null || this.datEffRet.trim().isEmpty())
			return null;

		try {
			return DateTools.getDateValue(this.datEffRet);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void setDateEffRet(Date dateEffRet) {
		if (dateEffRet == null)
			this.datEffRet = null;
		else
			this.datEffRet = DateTools.formatDate(dateEffRet);
	}

	public void setDatEffEnlev(String datEffEnlev) {
		this.datEffEnlev = datEffEnlev;
	}

	public String getDatEffEnlev() {
		return datEffEnlev;
	}
	
	public Date getDateEffEnlev() {
		if (this.datEffEnlev == null || this.datEffEnlev.trim().isEmpty())
			return null;

		try {
			return DateTools.getDateValue(this.datEffEnlev);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void setDateEffEnlev(Date dateEffEnlev) {
		if (dateEffEnlev == null)
			this.datEffEnlev = null;
		else
			this.datEffEnlev = DateTools.formatDate(dateEffEnlev);
	}

	public void setDatEffDecha(String datEffDecha) {
		this.datEffDecha = datEffDecha;
	}

	public String getDatEffDecha() {
		return datEffDecha;
	}
	
	public Date getDateEffDecha() {
		if (this.datEffDecha == null || this.datEffDecha.trim().isEmpty())
			return null;

		try {
			return DateTools.getDateValue(this.datEffDecha);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void setDateEffDecha(Date dateEffDecha) {
		if (dateEffDecha == null)
			this.datEffDecha = null;
		else
			this.datEffDecha = DateTools.formatDate(dateEffDecha);
	}

	public void setDatRelVid(String datRelVid) {
		this.datRelVid = datRelVid;
	}

	public String getDatRelVid() {
		return datRelVid;
	}
	
	public Date getDateRelVid() {
		if (this.datRelVid == null || this.datRelVid.trim().isEmpty())
			return null;

		try {
			return DateTools.getDateValue(this.datRelVid);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void setDateRelVid(Date dateRelVid) {
		if (dateRelVid == null)
			this.datRelVid = null;
		else
			this.datRelVid = DateTools.formatDate(dateRelVid);
	}

	public void setBooEstClo(BigDecimal booEstClo) {
		this.booEstClo = booEstClo;
	}

	public BigDecimal getBooEstClo() {
		return booEstClo;
	}
	
	public boolean getBEstClo() {
		return (booEstClo != null && booEstClo.compareTo(BigDecimal.ONE) == 0);
	}

	public void setBEstClo(boolean bEstClo) {
		this.booEstClo = (bEstClo ? BigDecimal.ONE : BigDecimal.ZERO);
	}

	public void setBooEstRecep(BigDecimal booEstRecep) {
		this.booEstRecep = booEstRecep;
	}

	public BigDecimal getBooEstRecep() {
		return booEstRecep;
	}
	
	public boolean getBEstRecep() {
		return (booEstRecep != null && booEstRecep.compareTo(BigDecimal.ONE) == 0);
	}

	public void setBEstRecep(boolean bEstRecep) {
		this.booEstRecep = (bEstRecep ? BigDecimal.ONE : BigDecimal.ZERO);
	}

	public void setBooEstDem(BigDecimal booEstDem) {
		this.booEstDem = booEstDem;
	}

	public BigDecimal getBooEstDem() {
		return booEstDem;
	}
	
	public boolean getBEstDem() {
		return (booEstDem != null && booEstDem.compareTo(BigDecimal.ONE) == 0);
	}

	public void setBEstDem(boolean bEstDem) {
		this.booEstDem = (bEstDem ? BigDecimal.ONE : BigDecimal.ZERO);
	}

	public void setBooEstCau(BigDecimal booEstCau) {
		this.booEstCau = booEstCau;
	}

	public BigDecimal getBooEstCau() {
		return booEstCau;
	}
	
	public boolean getBEstCau() {
		return (booEstCau != null && booEstCau.compareTo(BigDecimal.ONE) == 0);
	}

	public void setBEstCau(boolean bEstCau) {
		this.booEstCau = (bEstCau ? BigDecimal.ONE : BigDecimal.ZERO);
	}

	public void setDatRecep(String datRecep) {
		this.datRecep = datRecep;
	}

	public String getDatRecep() {
		return datRecep;
	}
	
	public Date getDateRecep() {
		if (this.datRecep == null || this.datRecep.trim().isEmpty())
			return null;

		try {
			return DateTools.getDateValue(this.datRecep);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void setDateRecep(Date dateRecep) {
		if (dateRecep == null)
			this.datRecep = null;
		else
			this.datRecep = DateTools.formatDate(dateRecep);
	}

	public void setBooRelVid(BigDecimal booRelVid) {
		this.booRelVid = booRelVid;
	}

	public BigDecimal getBooRelVid() {
		return booRelVid;
	}
	
	public boolean getBRelVid() {
		return (booRelVid != null && booRelVid.compareTo(BigDecimal.ONE) == 0);
	}

	public void setBRelVid(boolean bRelVid) {
		this.booRelVid = (bRelVid ? BigDecimal.ONE : BigDecimal.ZERO);
	}

	public void setBooEstDecha(BigDecimal booEstDecha) {
		this.booEstDecha = booEstDecha;
	}

	public BigDecimal getBooEstDecha() {
		return booEstDecha;
	}
	
	public boolean getBEstDecha() {
		return (booEstDecha != null && booEstDecha.compareTo(BigDecimal.ONE) == 0);
	}

	public void setBEstDecha(boolean bEstDecha) {
		this.booEstDecha = (bEstDecha ? BigDecimal.ONE : BigDecimal.ZERO);
	}

	public void setBooEstRet(BigDecimal booEstRet) {
		this.booEstRet = booEstRet;
	}

	public BigDecimal getBooEstRet() {
		return booEstRet;
	}
	
	public boolean getBEstRet() {
		return (booEstRet != null && booEstRet.compareTo(BigDecimal.ONE) == 0);
	}

	public void setBEstRet(boolean bEstRet) {
		this.booEstRet = (bEstRet ? BigDecimal.ONE : BigDecimal.ZERO);
	}

}
