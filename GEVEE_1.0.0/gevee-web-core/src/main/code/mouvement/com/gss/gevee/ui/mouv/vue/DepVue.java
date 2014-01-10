package com.gss.gevee.ui.mouv.vue;

import com.gss.gevee.be.mouv.entity.TabDep;
import com.gss.gevee.ui.core.base.AbstractNavigationManager;
import com.gss.gevee.ui.core.base.GeveeVue;
import com.gss.gevee.ui.core.base.TableManager;

public class DepVue extends GeveeVue<TabDep>{
	
	public DepVue() {
		super();
		// Instance des propriétés génériques héritées  
		this.tableMgr = new TableManager();
		this.navigationMgr = new AbstractNavigationManager();
	}
	
	/**
	 * Retourne une nouvelle Instance de l'entité
	 * 
	 * @return
	 */
	public static TabDep getTabDep() {
		TabDep v$dep = new TabDep();
		// Création des instances entités liées utiles pour le binding avec les pages web
		v$dep.initData();
		return v$dep;
	}
	
	/**
	 * Retourne une nouvelle instance d'une entité utile pour la recherche des
	 * données;
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
