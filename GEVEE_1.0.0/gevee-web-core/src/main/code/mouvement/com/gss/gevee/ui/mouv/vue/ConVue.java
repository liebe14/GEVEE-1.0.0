package com.gss.gevee.ui.mouv.vue;

import com.gss.gevee.be.mouv.entity.TabCon;
import com.gss.gevee.ui.core.base.AbstractNavigationManager;
import com.gss.gevee.ui.core.base.GeveeVue;
import com.gss.gevee.ui.core.base.TableManager;

public class ConVue extends GeveeVue<TabCon>{
	
	public ConVue() {
		super();
		// Instance des propri�t�s g�n�riques h�rit�es  
		this.tableMgr = new TableManager();
		this.navigationMgr = new AbstractNavigationManager();
	}
	
	/**
	 * Retourne une nouvelle Instance de l'entit�
	 * 
	 * @return
	 */
	public static TabCon getTabCon() {
		TabCon v$con = new TabCon();
		// Cr�ation des instances entit�s li�es utiles pour le binding avec les pages web
		v$con.initData();
		return v$con;
	}
	
	/**
	 * Retourne une nouvelle instance d'une entit� utile pour la recherche des
	 * donn�es;
	 * 
	 * @return
	 */
	public static TabCon getTabConForSearch() {

		TabCon v$con = getTabCon();
		return v$con;
	}

	@Override
	public TabCon getNewEntity() {
		return getTabCon();
	}

	@Override
	public TabCon getEntityForSearch() {
		return getTabConForSearch();
	}

}
