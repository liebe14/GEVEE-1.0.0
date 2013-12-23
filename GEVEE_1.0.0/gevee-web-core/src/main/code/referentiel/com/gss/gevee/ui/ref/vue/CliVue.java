package com.gss.gevee.ui.ref.vue;


import com.gss.gevee.be.core.base.BaseLogger;
import com.gss.gevee.be.ref.entity.TabCli;
import com.gss.gevee.ui.core.base.AbstractNavigationManager;
import com.gss.gevee.ui.core.base.GeveeVue;
import com.gss.gevee.ui.core.base.TableManager;

public class CliVue extends GeveeVue<TabCli>{
	
	
	
	public CliVue(){
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
	public static TabCli getTabCli (){		
		
		TabCli v$cli = new TabCli();
		v$cli.initData();
		return v$cli ;	
	}	
	
	/**
	 * Retourne une nouvelle instance d'une entit� utile pour la recherche des donn�es;  
	 * 
	 * @return
	 */
	public static TabCli getTabCliForSearch (){		
		
		TabCli v$cli = getTabCli();
		return v$cli ;	
	}	
	/**
	 * Retourne un Logger pour la Classe
	 * 
	 * @return
	 */
	public BaseLogger getLogger() {
		return BaseLogger.getLogger(CliVue.class);
	}
	
	
	public TabCli getNewEntity(){
		return getTabCli();
	}
	
	/***
	 * Retourne une nouvelle instance d'une entit� utile pour la recherche des donn�es;  
	 *  
	 * @return
	 */	
	public TabCli getEntityForSearch() {	
		return getTabCliForSearch();
	}	


}
