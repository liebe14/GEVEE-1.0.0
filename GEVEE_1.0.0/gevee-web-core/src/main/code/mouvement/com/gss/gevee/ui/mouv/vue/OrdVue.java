package com.gss.gevee.ui.mouv.vue;

import java.util.Map;

import com.gss.gevee.be.core.enums.EnuAco;
import com.gss.gevee.be.core.enums.EnuActivite;
import com.gss.gevee.be.core.enums.EnuTypCon;
import com.gss.gevee.be.core.enums.EnuTypMar;
import com.gss.gevee.be.mouv.entity.TabCon;
import com.gss.gevee.be.mouv.entity.TabOrd;
import com.gss.gevee.ui.core.base.AbstractNavigationManager;
import com.gss.gevee.ui.core.base.GeveeToolBox;
import com.gss.gevee.ui.core.base.GeveeVue;
import com.gss.gevee.ui.core.base.TableManager;

public class OrdVue extends GeveeVue<TabOrd>{
	
	/**
	 * Conteneur utilisé sur de l'édition d'un ordre de transport
	 */
	private TabCon conteneur;
	
	private  Map<String, Object> listeAconier;
	
	private  Map<String, Object> listeTypeCon;
	
	private  Map<String, Object> listeTypeMar;
	
	private  Map<String, Object> listeActivite;
	
	/**
	 * Gestionnaire (de la liste)des conteneurs
	 */
	private TableManager<TabCon> conteneurMgr = new TableManager<TabCon>();
	
	public OrdVue() {
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
	public static TabOrd getTabOrd() {
		TabOrd v$ord = new TabOrd();
		// Création des instances entités liées utiles pour le binding avec les pages web
		v$ord.initData();
		return v$ord;
	}
	
	/**
	 * Retourne une nouvelle instance d'une entité utile pour la recherche des
	 * données;
	 * 
	 * @return
	 */
	public static TabOrd getTabOrdForSearch() {

		TabOrd v$ord = getTabOrd();
		return v$ord;
	}
	
	@Override
	public TabOrd getNewEntity() {
		return getTabOrd();
	}

	@Override
	public TabOrd getEntityForSearch() {
		return getTabOrdForSearch();
	}
	
	public void setConteneur(TabCon conteneur) {
		this.conteneur = conteneur;
	}

	public TabCon getConteneur() {
		return conteneur;
	}

	public void setConteneurMgr(TableManager<TabCon> conteneurMgr) {
		this.conteneurMgr = conteneurMgr;
	}

	public TableManager<TabCon> getConteneurMgr() {
		return conteneurMgr;
	}

	public void setListeAconier(Map<String, Object> listeAconier) {
		this.listeAconier = listeAconier;
	}

	public Map<String, Object> getListeAconier() {
		if(listeAconier == null){
			listeAconier = GeveeToolBox.getComboData(EnuAco.getMaps());
			listeAconier.put("", "");
		}
		return listeAconier;
	}

	public void setListeTypeCon(Map<String, Object> listeTypeCon) {
		this.listeTypeCon = listeTypeCon;
	}

	public Map<String, Object> getListeTypeCon() {
		if(listeTypeCon == null){
			listeTypeCon = GeveeToolBox.getComboData(EnuTypCon.getMaps());
			listeTypeCon.put("", "");
		}
		return listeTypeCon;
	}

	public void setListeTypeMar(Map<String, Object> listeTypeMar) {
		this.listeTypeMar = listeTypeMar;
	}

	public Map<String, Object> getListeTypeMar() {
		if(listeTypeMar == null){
			listeTypeMar = GeveeToolBox.getComboData(EnuTypMar.getMaps());
			listeTypeMar.put("", "");
		}
		return listeTypeMar;
	}

	public void setListeActivite(Map<String, Object> listeActivite) {
		this.listeActivite = listeActivite;
	}

	public Map<String, Object> getListeActivite() {
		if(listeActivite == null){
			listeActivite = GeveeToolBox.getComboData(EnuActivite.getMaps());
			listeActivite.put("", "");
		}
		return listeActivite;
	}

	
}
