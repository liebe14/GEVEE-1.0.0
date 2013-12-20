package com.gss.gevee.ui.ref.vue;

import java.util.Map;

import com.gss.gevee.be.core.enums.EnuFamilleTracteur;
import com.gss.gevee.be.core.enums.EnuMarque;
import com.gss.gevee.be.core.enums.EnuModele;
import com.gss.gevee.be.ref.entity.TabTrac;
import com.gss.gevee.ui.core.base.AbstractNavigationManager;
import com.gss.gevee.ui.core.base.GeveeToolBox;
import com.gss.gevee.ui.core.base.GeveeVue;
import com.gss.gevee.ui.core.base.TableManager;

public class TracVue extends GeveeVue<TabTrac>{
	
	/**
	 * Liste modele
	 */
	private static Map<String, Object> listeModele;
	
	private static Map<String, Object> listeFamille;
	
	/**
	 * Liste marque
	 */
	private static Map<String, Object> listeMarque;
	
	public TracVue(){
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
	public static TabTrac getTabTrac (){		
		
		TabTrac v$trac = new TabTrac();
		v$trac.initData();
		return v$trac ;	
	}	
	
	/**
	 * Retourne une nouvelle instance d'une entité utile pour la recherche des données;  
	 * 
	 * @return
	 */
	public static TabTrac getTabTracForSearch (){		
		
		TabTrac v$trac = getTabTrac();
		return v$trac ;	
	}	

	@Override
	public TabTrac getNewEntity() {
		return getTabTrac();
	}

	@Override
	public TabTrac getEntityForSearch() {
		return getTabTracForSearch();
	}
	
	public Map<String, Object> getListeModele() {
		if(listeModele == null){
			listeModele = GeveeToolBox.getComboData(EnuModele.getMaps());
			listeModele.put("", "");
		}
		return listeModele;
	}

	public Map<String, Object> getListeMarque() {
		if(listeMarque == null){
			listeMarque = GeveeToolBox.getComboData(EnuMarque.getMaps());
			listeMarque.put("", "");
		}
		return listeMarque;
	}

	public void setListeMarque(Map<String, Object> listeMarque) {
		TracVue.listeMarque = listeMarque;
	}

	public void setListeModele(Map<String, Object> listeModele) {
		TracVue.listeModele = listeModele;
	}
	
	public Map<String, Object> getListeFamille() {
		if(listeFamille == null){
			listeFamille = GeveeToolBox.getComboData(EnuFamilleTracteur.getMaps());
			listeFamille.put("", "");
		}
		return listeFamille;
	}

}
