package com.gss.gevee.ui.ref.vue;


import com.gss.gevee.be.core.base.BaseLogger;
import com.gss.gevee.be.ref.entity.TabLieu;
import com.gss.gevee.ui.core.base.AbstractNavigationManager;
import com.gss.gevee.ui.core.base.GeveeVue;
import com.gss.gevee.ui.core.base.TableManager;

public class LieuVue extends GeveeVue<TabLieu>{
	
	
	
	public LieuVue(){
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
	public static TabLieu getTabLieu (){		
		
		TabLieu v$lieu = new TabLieu();
		v$lieu.initData();
		return v$lieu ;	
	}	
	
	/**
	 * Retourne une nouvelle instance d'une entité utile pour la recherche des données;  
	 * 
	 * @return
	 */
	public static TabLieu getTabLieuForSearch (){		
		
		TabLieu v$lieu = getTabLieu();
		return v$lieu ;	
	}	
	/**
	 * Retourne un Logger pour la Classe
	 * 
	 * @return
	 */
	public BaseLogger getLogger() {
		return BaseLogger.getLogger(LieuVue.class);
	}
	
	
	public TabLieu getNewEntity(){
		return getTabLieu();
	}
	
	/***
	 * Retourne une nouvelle instance d'une entité utile pour la recherche des données;  
	 *  
	 * @return
	 */	
	public TabLieu getEntityForSearch() {	
		return getTabLieuForSearch();
	}	


}
