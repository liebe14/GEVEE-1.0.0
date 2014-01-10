package com.gss.gevee.ui.mouv.controleur;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import com.gss.gevee.be.core.exception.GeveeAppException;
import com.gss.gevee.be.core.svco.base.IBaseSvco;
import com.gss.gevee.be.mouv.entity.TabCon;
import com.gss.gevee.ui.core.base.FacesUtil;
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
	
	public void  rechercher(ActionEvent evt) {
		
		System.out.println("je suis dans rechercher override");
		// Selon le type de recherche	
		//String typeRecherche = FacesUtil.getActionAttribute(evt, "typeDeRecherche");
		String typeRecherche = "CRITERE";
		
		List<TabCon> v$liste = new ArrayList<TabCon>();
		
		try{
			
			// Désactivation de la pagination
			defaultVue.getEntiteRecherche().setOffset(-1);
			defaultVue.getEntiteRecherche().setMaxRow(-1);
			
			// Nombre total d'éléménts de la requete de Recherche
			//defaultVue.getTableMgr().setTotalRecherche(getEntitySvco().compterParCritere(defaultVue.getEntiteRecherche())); PAS CORRECT
			long v$total = getEntitySvco().rechercherTout(defaultVue.getEntiteRecherche()).size();
			// Définition de la plage pour la pagination
			defaultVue.getEntiteRecherche().setOffset(1);
			defaultVue.getEntiteRecherche().setMaxRow(200);
			
			//defaultVue.setListePagination(ToolBox.getListePagination(defaultVue.getTableMgr().getTotalRecherche(), pasPagination));	PAS CORRECT
			List<SelectItem> v$pagination = getListePagination(v$total, 200);
			/*
			 * TODO Modification Temporaire à réviser
			 * La recherchepar code  = recherche par critère
			 */				
			if(typeRecherche.equals("ID")){
								
				v$liste = rechercherParCritere(defaultVue.getEntiteRecherche());			
			}
			
			else if(typeRecherche.equals("CRITERE")){
				v$liste = rechercherParCritere(defaultVue.getEntiteRecherche());
			}
			else if(typeRecherche.equals("TOUT")) {
								
				v$liste = rechercherTout(defaultVue.getEntiteRecherche());
				// Réinitialisation de la zone de recherche
				reinitialiser(evt);
			}
			
			// Mise  à jour de  la liste de recherche ==> Mise à jour du modèle automatiquement
			defaultVue.getTableMgr().setListeRecherche(v$liste);
			
			// Mise à jour des informations de pagination dans le Gestionnaire de table
			defaultVue.getTableMgr().setTotalRecherche(v$total);
			defaultVue.setListePagination(v$pagination);
			//defaultVue.getTableMgr().setListeRecherche(v$liste);
			
			// Si la liste de recherche est vide, précisez que la recherche n'a retourné aucun résultat
			if(v$liste == null || v$liste.size() == 0){
				FacesUtil.addWarnMessage("", "Aucun élément trouvé");
			}
			
			if(defaultVue.getEntiteRecherche().getEtatEnt() != null){
				defaultVue.getEntiteRecherche().setEtatEnt(null);
			}
						
			// Mise à jour de la date de la dernière recherche
			setTimeOfLastSearch();
		}
				
		catch (GeveeAppException e) {			
			FacesUtil.addWarnMessage("Echec lors de l'exécution du traitement",
									 e.getMessage());
		}catch (Exception e) {		
			e.printStackTrace();
			FacesUtil.addWarnMessage("TRAITEMENT_ALL_ECHEC_INCONNU",
	 				 e.getMessage());	
		}	
	}

}
