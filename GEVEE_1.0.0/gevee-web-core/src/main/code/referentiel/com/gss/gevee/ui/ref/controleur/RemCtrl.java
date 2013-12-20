package com.gss.gevee.ui.ref.controleur;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.gss.gevee.be.admin.entity.utilisateur.TabUsr;
import com.gss.gevee.be.core.exception.GeveeAppException;
import com.gss.gevee.be.core.svco.base.IBaseSvco;
import com.gss.gevee.be.ref.entity.TabRem;
import com.gss.gevee.ui.admin.util.AdminSvcoDeleguate;
import com.gss.gevee.ui.core.base.GeveeCtrl;
import com.gss.gevee.ui.core.base.ServiceLocatorException;
import com.gss.gevee.ui.core.base.Traitement;
import com.gss.gevee.ui.ref.util.RefSvcoDeleguate;
import com.gss.gevee.ui.ref.util.RefTrt;
import com.gss.gevee.ui.ref.vue.RemVue;

public class RemCtrl extends GeveeCtrl<TabRem, TabRem>{

	/**
	 * Nom du Bean managé par JSF dans le fichier de Configuration 
	 */
	private static String nomManagedBean = "remCtrl";
	
	public RemCtrl(){		
		defaultVue = new RemVue();		
	}
	@Override
	public List<Traitement> getListeTraitements() {
		String v$codeEntite = "Rem";

		System.out.println("RemCtrl.getListeTraitements() ici il vaut : "
				+ v$codeEntite);
		// Ensemble des traitements standards
		Map<String, Traitement> v$mapTrt = new TreeMap<String, Traitement>(
				RefTrt.getTrtStandards(v$codeEntite));
		listeTraitements = Traitement.getOrderedTrt(v$mapTrt);
		return listeTraitements;
	}

	@Override
	public Class<?> getMyClass() {
		return RemCtrl.class;
	}

	@Override
	public void buildListeTraitement() {
		if(getMapTraitements() == null){
			setMapTraitements(RefTrt.getTrtStandards("Rem")) ;
		}
	}

	@Override
	public IBaseSvco<TabRem> getEntitySvco() throws ServiceLocatorException {
		return RefSvcoDeleguate.getSvcoRem();
	}
	
	@Override
	public List<TabRem> rechercherParCritere(TabRem p$entity)
			throws GeveeAppException {
		try {
			super.setTimeOfLastSearch();
			return RefSvcoDeleguate.getSvcoRem().rechercherParCritere(p$entity);
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		}catch (GeveeAppException e) {
			GeveeAppException sdr = new GeveeAppException(e.getMessage());
			throw sdr;
		}
		return null;
	}
	
	public static void setNomManagedBean(String nomManagedBean) {
		RemCtrl.nomManagedBean = nomManagedBean;
	}
	public static String getNomManagedBean() {
		return nomManagedBean;
	}

}
