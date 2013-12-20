package com.gss.gevee.ui.ref.vue;

import java.util.Map;

import com.gss.gevee.be.core.enums.EnuFamilleRemorque;
import com.gss.gevee.be.core.enums.EnuMarque;
import com.gss.gevee.be.core.enums.EnuModele;
import com.gss.gevee.be.core.enums.EnuTypeRemorque;
import com.gss.gevee.be.ref.entity.TabRem;
import com.gss.gevee.ui.core.base.AbstractNavigationManager;
import com.gss.gevee.ui.core.base.GeveeToolBox;
import com.gss.gevee.ui.core.base.GeveeVue;
import com.gss.gevee.ui.core.base.TableManager;

public class RemVue extends GeveeVue<TabRem>{
	
	/**
	 * Liste type remorque
	 */
	private static Map<String, Object> listeTypeRemorque;
	
	/**
	 * Liste modele
	 */
	private static Map<String, Object> listeModele;
	
	private static Map<String, Object> listeFamille;
	
	/**
	 * Liste marque
	 */
	private static Map<String, Object> listeMarque;
	
	public RemVue(){
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
	public static TabRem getTabRem (){		
		
		TabRem v$rem = new TabRem();
		v$rem.initData();
		return v$rem ;	
	}	
	
	/**
	 * Retourne une nouvelle instance d'une entité utile pour la recherche des données;  
	 * 
	 * @return
	 */
	public static TabRem getTabRemForSearch (){		
		
		TabRem v$rem = getTabRem();
		return v$rem ;	
	}	

	@Override
	public TabRem getNewEntity() {
		return getTabRem();
	}

	@Override
	public TabRem getEntityForSearch() {
		return getTabRemForSearch();
	}

	public void setListeTypeRemorque(Map<String, Object> listeTypeRemorque) {
		RemVue.listeTypeRemorque = listeTypeRemorque;
	}

	public Map<String, Object> getListeTypeRemorque() {
		if(listeTypeRemorque == null){
			listeTypeRemorque = GeveeToolBox.getComboData(EnuTypeRemorque.getMaps());
			listeTypeRemorque.put("", "");
		}
		return listeTypeRemorque;
	}

	public Map<String, Object> getListeModele() {
		if(listeModele == null){
			listeModele = GeveeToolBox.getComboData(EnuModele.getMaps());
			listeModele.put("", "");
		}
		return listeModele;
	}
	
	public Map<String, Object> getListeFamille() {
		if(listeFamille == null){
			listeFamille = GeveeToolBox.getComboData(EnuFamilleRemorque.getMaps());
			listeFamille.put("", "");
		}
		return listeFamille;
	}
	
	public Map<String, Object> getListeMarque() {
		if(listeMarque == null){
			listeMarque = GeveeToolBox.getComboData(EnuMarque.getMaps());
			listeMarque.put("", "");
		}
		return listeMarque;
	}

}
