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
import com.gss.gevee.be.ref.entity.TabLieu;

@Entity
@Table(name="TAB_CHK")
public class TabChk extends GeveeBaseEntity{
	
	private static final long serialVersionUID = 1L;

	public TabChk(){
		
	}
	
	//Reference du mouvement
	@Id
	@Column(name = "COD_REF_CHK")
	private String codRefChk;
	
	//Reference du mouvement
	@ManyToOne
	@JoinColumn(name = "COD_REF_MOUV")
	private TabMouv tabMouv;
	
	//Reference du mouvement
	@ManyToOne
	@JoinColumn(name = "COD_LIEU")
	private TabLieu tabLieu;
	
	@Column(name = "DAT_CHK")
	private String datChk;
	
	@Column(name = "BOO_VAL")
	private BigDecimal booEstVal;

	@Override
	public String getEntityCode() {
		return "Chk";
	}

	@Override
	public Serializable getId() {
		return getCodRefChk();
	}

	@Override
	public void setId(Serializable id) {
		setCodRefChk((String)id);
	}

	@Override
	public void validateData() {
		
		try {
			if (this.getTabLieu() != null
					&& (this.getTabLieu().getCodLieu() == null || this
							.getTabLieu().getCodLieu().trim().isEmpty()))
				this.setTabLieu(null);
		} catch (Exception e) {
		}
		
		try {
			if (this.getTabMouv() != null
					&& (this.getTabMouv().getCodRefMouv() == null || this
							.getTabMouv().getCodRefMouv().trim().isEmpty()))
				this.setTabMouv(null);
		} catch (Exception e) {
		}
		
	}

	@Override
	public void initData() {
		
		tabLieu = (tabLieu == null ? new TabLieu() : tabLieu);
		tabMouv = (tabMouv == null ? new TabMouv() : tabMouv);
	}

	public void setCodRefChk(String codRefChk) {
		this.codRefChk = codRefChk;
	}

	public String getCodRefChk() {
		return codRefChk;
	}

	public void setTabMouv(TabMouv tabMouv) {
		this.tabMouv = tabMouv;
	}

	public TabMouv getTabMouv() {
		return tabMouv;
	}

	public void setTabLieu(TabLieu tabLieu) {
		this.tabLieu = tabLieu;
	}

	public TabLieu getTabLieu() {
		return tabLieu;
	}

	public void setDatChk(String datChk) {
		this.datChk = datChk;
	}

	public String getDatChk() {
		return datChk;
	}
	
	public Date getDateChk() {
		if (this.datChk == null || this.datChk.trim().isEmpty())
			return null;

		try {
			return DateTools.getDateValue(this.datChk);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void setDateChk(Date dateChk) {
		if (dateChk == null)
			this.datChk = null;
		else
			this.datChk = DateTools.formatDate(dateChk);
	}

	public void setBooEstVal(BigDecimal booEstVal) {
		this.booEstVal = booEstVal;
	}

	public BigDecimal getBooEstVal() {
		return booEstVal;
	}

}
