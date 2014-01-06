package com.gss.gevee.ui.mouv.controleur;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.gss.gevee.be.core.exception.GeveeAppException;
import com.gss.gevee.be.core.svco.base.IBaseSvco;
import com.gss.gevee.be.mouv.entity.TabCon;
import com.gss.gevee.ui.core.base.GeveeCtrl;
import com.gss.gevee.ui.core.base.ServiceLocatorException;
import com.gss.gevee.ui.core.base.Traitement;
import com.gss.gevee.ui.mouv.util.MouvSvcoDelegaute;
import com.gss.gevee.ui.mouv.util.MouvTrt;
import com.gss.gevee.ui.mouv.vue.ConVue;

public class ConCtrl extends GeveeCtrl<TabCon, TabCon>{
	
	/**
	 * Nom du Bean managé par JSF dans le fichier de Configuration 
	 */
	private static String nomManagedBean = "conCtrl";
	
	public ConCtrl(){
		defaultVue = new ConVue();
	}

	@Override
	public List<Traitement> getListeTraitements() {
		String v$codeEntite = "Con";

		System.out.println("ConCtrl.getListeTraitements() ici il vaut : "
				+ v$codeEntite);
		// Ensemble des traitements standards
		Map<String, Traitement> v$mapTrt = new TreeMap<String, Traitement>(
				MouvTrt.getTrtStandards(v$codeEntite));
		//Retrait du traitement ajouter de la liste des traitement standard
		v$mapTrt.remove(Traitement.getKey(v$codeEntite, MouvTrt.AJOUTER));
		v$mapTrt.remove(Traitement.getKey(v$codeEntite, MouvTrt.AFFICHER));
		v$mapTrt.remove(Traitement.getKey(v$codeEntite, MouvTrt.COPIER));
		v$mapTrt.remove(Traitement.getKey(v$codeEntite, MouvTrt.MODIFIER));
		v$mapTrt.remove(Traitement.getKey(v$codeEntite, MouvTrt.SUPPRIMER));
		
		listeTraitements = Traitement.getOrderedTrt(v$mapTrt);
		return listeTraitements;
//		return new ArrayList<Traitement>();
	}

	@Override
	public Class<?> getMyClass() {
		return ConCtrl.class;
	}

	@Override
	public void buildListeTraitement() {
		if(getMapTraitements() == null){
			setMapTraitements(MouvTrt.getTrtStandards("Con")) ;
		}		
	}

	@Override
	public List<TabCon> rechercherParCritere(TabCon p$entity)
			throws GeveeAppException {
		try {
			super.setTimeOfLastSearch();
			return MouvSvcoDelegaute.getSvcoCon().rechercherParCritere(p$entity);
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		}catch (GeveeAppException e) {
			GeveeAppException sdr = new GeveeAppException(e.getMessage());
			throw sdr;
		}
		return null;
	}

	@Override
	public IBaseSvco<TabCon> getEntitySvco() throws ServiceLocatorException {
		return MouvSvcoDelegaute.getSvcoCon();
	}

	public static void setNomManagedBean(String nomManagedBean) {
		ConCtrl.nomManagedBean = nomManagedBean;
	}

	public static String getNomManagedBean() {
		return nomManagedBean;
	}

}
