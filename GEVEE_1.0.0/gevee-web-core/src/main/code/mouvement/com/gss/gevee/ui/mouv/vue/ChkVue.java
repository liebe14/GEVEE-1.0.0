package com.gss.gevee.ui.mouv.vue;

import com.gss.gevee.be.mouv.entity.TabChk;
import com.gss.gevee.ui.core.base.AbstractNavigationManager;
import com.gss.gevee.ui.core.base.GeveeVue;
import com.gss.gevee.ui.core.base.TableManager;

public class ChkVue extends GeveeVue<TabChk>{
	
	public ChkVue() {
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
	public static TabChk getTabChk() {
		TabChk v$chk = new TabChk();
		// Création des instances entités liées utiles pour le binding avec les pages web
		v$chk.initData();
		return v$chk;
	}
	
	/**
	 * Retourne une nouvelle instance d'une entité utile pour la recherche des
	 * données;
	 * 
	 * @return
	 */
	public static TabChk getTabChkForSearch() {
		TabChk v$chk = getTabChk();
		return v$chk;
	}

	@Override
	public TabChk getNewEntity() {
		return getTabChk();
	}

	@Override
	public TabChk getEntityForSearch() {
		return getTabChkForSearch();
	}

}
