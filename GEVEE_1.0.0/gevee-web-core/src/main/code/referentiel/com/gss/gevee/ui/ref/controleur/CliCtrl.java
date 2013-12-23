package com.gss.gevee.ui.ref.controleur;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.gss.gevee.be.core.exception.GeveeAppException;
import com.gss.gevee.be.core.svco.base.IBaseSvco;
import com.gss.gevee.be.ref.entity.TabCli;
import com.gss.gevee.ui.core.base.GeveeCtrl;
import com.gss.gevee.ui.core.base.ServiceLocatorException;
import com.gss.gevee.ui.core.base.Traitement;
import com.gss.gevee.ui.ref.util.RefSvcoDeleguate;
import com.gss.gevee.ui.ref.util.RefTrt;
import com.gss.gevee.ui.ref.vue.CliVue;

public class CliCtrl extends GeveeCtrl<TabCli, TabCli>{
	
	/**
	 * Nom du Bean managé par JSF dans le fichier de Configuration 
	 */
	private static String nomManagedBean = "cliCtrl";
	
	public CliCtrl(){		
		defaultVue = new CliVue();		
	}
	
	/**
	 * Retourne le nom du Bean Managé par JSF dans le Fichier de Configuration
	 * Utilile pour ne pas avoir a ecrire le nom des Beans en dur dans le Code
	 * @return
	 */
	public String getNomManagedBean(){
		return nomManagedBean;
	}	
	
	public IBaseSvco<TabCli> getEntitySvco() throws ServiceLocatorException{	
		return RefSvcoDeleguate.getSvcoCli();
	}
	
	public Class<CliCtrl> getMyClass() {
		return CliCtrl.class;
	}
	
	public String enregistrerModification(){
		try {
			getEntitySvco().modifier(defaultVue.getEntiteCourante());
		} catch (GeveeAppException e) {
			e.printStackTrace();
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		}
		return "CliDetails";
	}

	@Override
	public List<Traitement> getListeTraitements() {
		String v$codeEntite = "Cli";

		System.out.println("cliCtrl.getListeTraitements() ici il vaut : "
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
			setMapTraitements(RefTrt.getTrtStandards("Cli")) ;
		}	
	}

	@Override
	public List<TabCli> rechercherParCritere(TabCli p$entity)
			throws GeveeAppException {
		try {
			super.setTimeOfLastSearch();
			return RefSvcoDeleguate.getSvcoCli().rechercherTout(p$entity);
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		}catch (GeveeAppException e) {
			GeveeAppException sdr = new GeveeAppException(e.getMessage());
			throw sdr;
		}
		return null;
	}
	
}
