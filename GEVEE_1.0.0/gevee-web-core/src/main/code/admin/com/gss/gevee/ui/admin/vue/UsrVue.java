package com.gss.gevee.ui.admin.vue;

import com.gss.gevee.be.admin.entity.utilisateur.TabUsr;
import com.gss.gevee.be.core.base.BaseLogger;
import com.gss.gevee.ui.core.base.AbstractNavigationManager;
import com.gss.gevee.ui.core.base.GeveeVue;
import com.gss.gevee.ui.core.base.TableManager;

public class UsrVue extends GeveeVue<TabUsr>{
	
	
	/**
	 * Propriété permant de gérer l'exactitude du mot de passe saisi par l'utilisateur 
	 */
	String password;
	
	public UsrVue(){
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
	public static TabUsr getTabUsr (){		
		
		TabUsr v$user = new TabUsr();
		v$user.initData();
		return v$user ;	
	}	
	
	/**
	 * Retourne une nouvelle instance d'une entité utile pour la recherche des données;  
	 * 
	 * @return
	 */
	public static TabUsr getTabUsrForSearch (){		
		
		TabUsr v$user = getTabUsr();
		return v$user ;	
	}	
	/**
	 * Retourne un Logger pour la Classe
	 * 
	 * @return
	 */
	public BaseLogger getLogger() {
		return BaseLogger.getLogger(UsrVue.class);
	}
	
	
	public TabUsr getNewEntity(){
		return getTabUsr();
	}
	
	/***
	 * Retourne une nouvelle instance d'une entité utile pour la recherche des données;  
	 *  
	 * @return
	 */	
	public TabUsr getEntityForSearch() {	
		return getTabUsrForSearch();
	}	
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password2 the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}
