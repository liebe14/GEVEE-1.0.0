package com.gss.gevee.ui.ref.controleur;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.gss.gevee.be.core.exception.GeveeAppException;
import com.gss.gevee.be.core.svco.base.IBaseSvco;
import com.gss.gevee.be.ref.entity.TabTrac;
import com.gss.gevee.ui.core.base.GeveeCtrl;
import com.gss.gevee.ui.core.base.ServiceLocatorException;
import com.gss.gevee.ui.core.base.Traitement;
import com.gss.gevee.ui.ref.util.RefSvcoDeleguate;
import com.gss.gevee.ui.ref.util.RefTrt;
import com.gss.gevee.ui.ref.vue.TracVue;

public class TracCtrl extends GeveeCtrl<TabTrac, TabTrac>{
	
	/**
	 * Nom du Bean managé par JSF dans le fichier de Configuration 
	 */
	private static String nomManagedBean = "tracCtrl";
	
	public TracCtrl(){		
		defaultVue = new TracVue();		
	}
	
	@Override
	public IBaseSvco<TabTrac> getEntitySvco() throws ServiceLocatorException {
		return RefSvcoDeleguate.getSvcoTrac();
	}

	@Override
	public List<Traitement> getListeTraitements() {
		String v$codeEntite = "Trac";

		System.out.println("TracCtrl.getListeTraitements() ici il vaut : "
				+ v$codeEntite);
		// Ensemble des traitements standards
		Map<String, Traitement> v$mapTrt = new TreeMap<String, Traitement>(
				RefTrt.getTrtStandards(v$codeEntite));
		listeTraitements = Traitement.getOrderedTrt(v$mapTrt);
		return listeTraitements;
	}

	@Override
	public Class<?> getMyClass() {
		return TracCtrl.class;
	}

	@Override
	public void buildListeTraitement() {
		if(getMapTraitements() == null){
			setMapTraitements(RefTrt.getTrtStandards("Trac")) ;
		}
	}

	@Override
	public List<TabTrac> rechercherParCritere(TabTrac p$entity)
			throws GeveeAppException {
		try {
			super.setTimeOfLastSearch();
			return RefSvcoDeleguate.getSvcoTrac().rechercherParCritere(p$entity);
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		}catch (GeveeAppException e) {
			GeveeAppException sdr = new GeveeAppException(e.getMessage());
			throw sdr;
		}
		return null;
	}


	public static String getNomManagedBean() {
		return nomManagedBean;
	}
	
}
