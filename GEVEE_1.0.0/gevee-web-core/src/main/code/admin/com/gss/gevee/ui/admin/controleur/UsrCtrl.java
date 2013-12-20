package com.gss.gevee.ui.admin.controleur;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.gss.gevee.be.admin.entity.utilisateur.TabUsr;
import com.gss.gevee.be.core.exception.GeveeAppException;
import com.gss.gevee.be.core.svco.base.IBaseSvco;
import com.gss.gevee.ui.admin.util.AdminSvcoDeleguate;
import com.gss.gevee.ui.admin.util.AdminTrt;
import com.gss.gevee.ui.admin.vue.UsrVue;
import com.gss.gevee.ui.core.base.GeveeCtrl;
import com.gss.gevee.ui.core.base.ServiceLocatorException;
import com.gss.gevee.ui.core.base.Traitement;

public class UsrCtrl extends GeveeCtrl<TabUsr, TabUsr>{
	
	/**
	 * Nom du Bean managé par JSF dans le fichier de Configuration 
	 */
	private static String nomManagedBean = "usrCtrl";
	
	public UsrCtrl(){		
		defaultVue = new UsrVue();		
	}
	
	/**
	 * Retourne le nom du Bean Managé par JSF dans le Fichier de Configuration
	 * Utilile pour ne pas avoir a ecrire le nom des Beans en dur dans le Code
	 * @return
	 */
	public String getNomManagedBean(){
		return nomManagedBean;
	}	
	
	public IBaseSvco<TabUsr> getEntitySvco() throws ServiceLocatorException{	
		return AdminSvcoDeleguate.getSvcoUsr();
	}
	
	public Class<UsrCtrl> getMyClass() {
		return UsrCtrl.class;
	}
	
	public String enregistrerModification(){
		try {
			getEntitySvco().modifier(defaultVue.getEntiteCourante());
		} catch (GeveeAppException e) {
			e.printStackTrace();
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		}
		return "UsrDetails";
	}

	@Override
	public List<Traitement> getListeTraitements() {
		String v$codeEntite = "Usr";

		System.out.println("usrCtrl.getListeTraitements() ici il vaut : "
				+ v$codeEntite);
		// Ensemble des traitements standards
		Map<String, Traitement> v$mapTrt = new TreeMap<String, Traitement>(
				AdminTrt.getTrtStandards(v$codeEntite));
		listeTraitements = Traitement.getOrderedTrt(v$mapTrt);
		return listeTraitements;
	}

	@Override
	public  void  buildListeTraitement(){

		if(getMapTraitements() == null){
			setMapTraitements(AdminTrt.getTrtStandards("Usr")) ;
		}	
	}

	@Override
	public List<TabUsr> rechercherParCritere(TabUsr p$entity)
			throws GeveeAppException {
		try {
			super.setTimeOfLastSearch();
			return AdminSvcoDeleguate.getSvcoUsr().rechercherParCritere(p$entity);
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		}catch (GeveeAppException e) {
			GeveeAppException sdr = new GeveeAppException(e.getMessage());
			throw sdr;
		}
		return null;
	}
	
}
