package com.gss.gevee.ui.ref.vue;


import com.gss.gevee.be.core.base.BaseLogger;
import com.gss.gevee.be.ref.entity.TabChau;
import com.gss.gevee.ui.core.base.AbstractNavigationManager;
import com.gss.gevee.ui.core.base.GeveeVue;
import com.gss.gevee.ui.core.base.TableManager;

public class ChauVue extends GeveeVue<TabChau>{
	
	
	
	public ChauVue(){
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
	public static TabChau getTabChau (){		
		
		TabChau v$chau = new TabChau();
		v$chau.initData();
		return v$chau ;	
	}	
	
	/**
	 * Retourne une nouvelle instance d'une entité utile pour la recherche des données;  
	 * 
	 * @return
	 */
	public static TabChau getTabChauForSearch (){		
		
		TabChau v$chau = getTabChau();
		return v$chau ;	
	}	
	/**
	 * Retourne un Logger pour la Classe
	 * 
	 * @return
	 */
	public BaseLogger getLogger() {
		return BaseLogger.getLogger(ChauVue.class);
	}
	
	
	public TabChau getNewEntity(){
		return getTabChau();
	}
	
	/***
	 * Retourne une nouvelle instance d'une entité utile pour la recherche des données;  
	 *  
	 * @return
	 */	
	public TabChau getEntityForSearch() {	
		return getTabChauForSearch();
	}	


}
