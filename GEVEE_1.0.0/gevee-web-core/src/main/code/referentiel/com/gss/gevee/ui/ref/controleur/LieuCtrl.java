package com.gss.gevee.ui.ref.controleur;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.gss.gevee.be.core.exception.GeveeAppException;
import com.gss.gevee.be.core.svco.base.IBaseSvco;
import com.gss.gevee.be.ref.entity.TabLieu;
import com.gss.gevee.ui.core.base.GeveeCtrl;
import com.gss.gevee.ui.core.base.ServiceLocatorException;
import com.gss.gevee.ui.core.base.Traitement;
import com.gss.gevee.ui.ref.util.RefSvcoDeleguate;
import com.gss.gevee.ui.ref.util.RefTrt;
import com.gss.gevee.ui.ref.vue.LieuVue;

public class LieuCtrl extends GeveeCtrl<TabLieu, TabLieu>{
	
	/**
	 * Nom du Bean managé par JSF dans le fichier de Configuration 
	 */
	private static String nomManagedBean = "lieuCtrl";
	
	public LieuCtrl(){		
		defaultVue = new LieuVue();		
	}
	
	/**
	 * Retourne le nom du Bean Managé par JSF dans le Fichier de Configuration
	 * Utilile pour ne pas avoir a ecrire le nom des Beans en dur dans le Code
	 * @return
	 */
	public String getNomManagedBean(){
		return nomManagedBean;
	}	
	
	public IBaseSvco<TabLieu> getEntitySvco() throws ServiceLocatorException{	
		return RefSvcoDeleguate.getSvcoLieu();
	}
	
	public Class<LieuCtrl> getMyClass() {
		return LieuCtrl.class;
	}
	
	public String enregistrerModification(){
		try {
			getEntitySvco().modifier(defaultVue.getEntiteCourante());
		} catch (GeveeAppException e) {
			e.printStackTrace();
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		}
		return "LieuDetails";
	}

	@Override
	public List<Traitement> getListeTraitements() {
		String v$codeEntite = "Lieu";

		System.out.println("lieuCtrl.getListeTraitements() ici il vaut : "
				+ v$codeEntite);
		// Ensemble des traitements standards
		Map<String, Traitement> v$mapTrt = new TreeMap<String, Traitement>(
				RefTrt.getTrtStandards(v$codeEntite));
		listeTraitements = Traitement.getOrderedTrt(v$mapTrt);
		return listeTraitements;
	}

	@Override
	public  void  buildListeTraitement(){

		if(getMapTraitements() == null){
			setMapTraitements(RefTrt.getTrtStandards("Lieu")) ;
		}	
	}

	@Override
	public List<TabLieu> rechercherParCritere(TabLieu p$entity)
			throws GeveeAppException {
		try {
			super.setTimeOfLastSearch();
			return RefSvcoDeleguate.getSvcoLieu().rechercherTout(p$entity);
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		}catch (GeveeAppException e) {
			GeveeAppException sdr = new GeveeAppException(e.getMessage());
			throw sdr;
		}
		return null;
	}
	
}
