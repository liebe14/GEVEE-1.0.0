package com.gss.gevee.ui.mouv.vue;

import java.util.Map;

import com.gss.gevee.be.core.enums.EnuEtatCon;
import com.gss.gevee.be.core.enums.EnuSensMouv;
import com.gss.gevee.be.core.enums.EnuTypMouv;
import com.gss.gevee.be.mouv.entity.TabMouv;
import com.gss.gevee.ui.core.base.AbstractNavigationManager;
import com.gss.gevee.ui.core.base.GeveeToolBox;
import com.gss.gevee.ui.core.base.GeveeVue;
import com.gss.gevee.ui.core.base.TableManager;

public class MouvVue extends GeveeVue<TabMouv>{
	
	private  Map<String, Object> listeTypemouv;
	
	private  Map<String, Object> listeSensMouv;
	
	private  Map<String, Object> listeEtatCon;
	
	public MouvVue() {
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
	public static TabMouv getTabMouv() {
		TabMouv v$mouv = new TabMouv();
		// Création des instances entités liées utiles pour le binding avec les pages web
		v$mouv.initData();
		return v$mouv;
	}
	
	/**
	 * Retourne une nouvelle instance d'une entité utile pour la recherche des
	 * données;
	 * 
	 * @return
	 */
	public static TabMouv getTabMouvForSearch() {
		TabMouv v$mouv = getTabMouv();
		return v$mouv;
	}

	@Override
	public TabMouv getNewEntity() {
		return getTabMouv();
	}

	@Override
	public TabMouv getEntityForSearch() {
		return getTabMouvForSearch();
	}

	public void setListeTypemouv(Map<String, Object> listeTypemouv) {
		this.listeTypemouv = listeTypemouv;
	}

	public Map<String, Object> getListeTypemouv() {
		if(listeTypemouv == null){
			listeTypemouv = GeveeToolBox.getComboData(EnuTypMouv.getMaps());
			listeTypemouv.put("", "");
		}
		return listeTypemouv;
	}

	public void setListeSensMouv(Map<String, Object> listeSensMouv) {
		this.listeSensMouv = listeSensMouv;
	}

	public Map<String, Object> getListeSensMouv() {
		if(listeSensMouv == null){
			listeSensMouv = GeveeToolBox.getComboData(EnuSensMouv.getMaps());
			listeSensMouv.put("", "");
		}
		return listeSensMouv;
	}

	public void setListeEtatCon(Map<String, Object> listeEtatCon) {
		this.listeEtatCon = listeEtatCon;
	}

	public Map<String, Object> getListeEtatCon() {
		if(listeEtatCon == null){
			listeEtatCon = GeveeToolBox.getComboData(EnuEtatCon.getMaps());
			listeEtatCon.put("", "");
		}
		return listeEtatCon;
	}

}
