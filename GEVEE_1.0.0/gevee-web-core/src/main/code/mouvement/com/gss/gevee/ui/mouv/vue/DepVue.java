package com.gss.gevee.ui.mouv.vue;

import com.gss.gevee.be.mouv.entity.TabDep;
import com.gss.gevee.ui.core.base.AbstractNavigationManager;
import com.gss.gevee.ui.core.base.GeveeVue;
import com.gss.gevee.ui.core.base.TableManager;

public class DepVue extends GeveeVue<TabDep>{
	
	public DepVue() {
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
	public static TabDep getTabDep() {
		TabDep v$dep = new TabDep();
		// Cr�ation des instances entit�s li�es utiles pour le binding avec les pages web
		v$dep.initData();
		return v$dep;
	}
	
	/**
	 * Retourne une nouvelle instance d'une entit� utile pour la recherche des
	 * donn�es;
	 * 
	 * @return
	 */
	public static TabDep getTabDepForSearch() {
		TabDep v$dep = getTabDep();
		return v$dep;
	}

	@Override
	public TabDep getNewEntity() {
		return getTabDep();
	}

	@Override
	public TabDep getEntityForSearch() {
		return getTabDepForSearch();
	}

}
