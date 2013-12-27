package com.gss.gevee.ui.mouv.controleur;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.gss.gevee.be.core.base.BaseEntity;
import com.gss.gevee.be.core.exception.GeveeAppException;
import com.gss.gevee.be.core.svco.base.IBaseSvco;
import com.gss.gevee.be.mouv.entity.TabCon;
import com.gss.gevee.be.mouv.entity.TabOrd;
import com.gss.gevee.be.ref.entity.TabLieu;
import com.gss.gevee.ui.core.base.CoreConstants;
import com.gss.gevee.ui.core.base.FacesUtil;
import com.gss.gevee.ui.core.base.GeveeCtrl;
import com.gss.gevee.ui.core.base.GeveeToolBox;
import com.gss.gevee.ui.core.base.ServiceLocatorException;
import com.gss.gevee.ui.core.base.Traitement;
import com.gss.gevee.ui.mouv.util.MouvSvcoDelegaute;
import com.gss.gevee.ui.mouv.util.MouvTrt;
import com.gss.gevee.ui.mouv.vue.OrdVue;
import com.gss.gevee.ui.ref.util.RefSvcoDeleguate;

public class OrdCtrl extends GeveeCtrl<TabOrd, TabOrd>{
	
	/**
	 * Nom du Bean managé par JSF dans le fichier de Configuration 
	 */
	private static String nomManagedBean = "ordCtrl";
	
	private TabCon conteneur;
	
	private Map<String, TabCon> mapCon;
	
	public OrdCtrl(){		
		defaultVue = new OrdVue();	
		setConteneur(new TabCon());
		mapCon = new HashMap<String, TabCon>();
	}

	@Override
	public List<Traitement> getListeTraitements() {
		String v$codeEntite = "Ord";

		System.out.println("OrdCtrl.getListeTraitements() ici il vaut : "
				+ v$codeEntite);
		// Ensemble des traitements standards
		Map<String, Traitement> v$mapTrt = new TreeMap<String, Traitement>(
				MouvTrt.getTrtStandards(v$codeEntite));
		listeTraitements = Traitement.getOrderedTrt(v$mapTrt);
		return listeTraitements;
	}

	@Override
	public Class<?> getMyClass() {
		return OrdCtrl.class;
	}

	@Override
	public void buildListeTraitement() {
		if(getMapTraitements() == null){
			setMapTraitements(MouvTrt.getTrtStandards("Ord")) ;
		}
	}

	@Override
	public List<TabOrd> rechercherParCritere(TabOrd p$entity)
			throws GeveeAppException {
		try {
			super.setTimeOfLastSearch();
			return MouvSvcoDelegaute.getSvcoOrd().rechercherParCritere(p$entity);
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		}catch (GeveeAppException e) {
			GeveeAppException sdr = new GeveeAppException(e.getMessage());
			throw sdr;
		}
		return null;
	}

	@Override
	public IBaseSvco<TabOrd> getEntitySvco() throws ServiceLocatorException {
		return MouvSvcoDelegaute.getSvcoOrd();
	}

	public static String getNomManagedBean() {
		return nomManagedBean;
	}
	
	public void setSelectedEntity(BaseEntity p$entite) {

		// Nom de la propriété à mettre à jour pour
		String v$propriete = defaultVue.getNavigationMgr().getSelectionPropertyName();

		if (v$propriete.equals("tabLieuEnlev")) {
			TabLieu v$entite = (TabLieu) p$entite;
			defaultVue.getEntiteCourante().setTabLieuEnlev(v$entite);
		}
	
		if (v$propriete.equals("tabLieuDecha")) {

			TabLieu v$entite = (TabLieu) p$entite;
			defaultVue.getEntiteCourante().setTabLieuDecha(v$entite);
		}
	}
		
	@SuppressWarnings("unchecked")
	@Override
	public String gotoRelatedEntity() {
			
		// Determine vers quelle page ou Formulaire l'on doit se diriger
		String v$navigation = super.gotoRelatedEntity();
		/*
		 * Recuperation du controleur 
		 * NB: 
		 * 	1-Cette méthode suppose que le controleur est bel et bien dans le Scope Session
		 * 	2-Par ailleurs il devrait normalement déja existé du fait du passage de paramètres dans la page web
		 */
		GeveeCtrl<BaseEntity, BaseEntity> v$controleur  =  (GeveeCtrl<BaseEntity, BaseEntity>) FacesUtil.getSessionMapValue(GeveeToolBox.getManagedBeanName(v$navigation));

		return v$navigation;
	}

	public void setConteneur(TabCon conteneur) {
		this.conteneur = conteneur;
	}

	public TabCon getConteneur() {
		return conteneur;
	}	
		
	public String ajouterConteneur(){
		
		if(mapCon.containsKey(conteneur.getNumCon())){
			String msg = "Le conteneur N° "+ conteneur.getNumCon()+" fait déjà parti de cet ordre de transport";
			FacesUtil.addWarnMessage(msg, msg);
		}else{
			System.out.println("OrdCtrl.ajouterConteneur() conteneur : "+conteneur.getNumCon());
			((OrdVue)getDefaultVue()).getConteneurMgr().add(conteneur);
			System.out.println("OrdCtrl.ajouterConteneur() taille : "+((OrdVue)defaultVue).getConteneurMgr().getListeRecherche().size());
			mapCon.put(conteneur.getNumCon(), conteneur);
		}
		
		initialiserConteneur();
		return null;
	}

	public String supprimerConteneur() {
        
		OrdVue v$vue = (OrdVue) defaultVue;
		TabCon conteneur =  v$vue.getConteneurMgr().getEntiteSelectionne();
		System.out.println("je suis ici"+conteneur.getNumCon());
		v$vue.getConteneurMgr().remove(conteneur);
		mapCon.remove(conteneur.getNumCon());
		return null;
	}
	
	
	
	public void initialiserConteneur(){
		  conteneur =new TabCon();
	}
	
	public String effacerpiece(){
		initialiserConteneur();
		return "ok";
	}

	/**
	 * Permet de naviguer vers le formulaire de Consultaton afin de consulter  les informations relatives à une entité 
	 * 
	 * @return
	 */
	public String afficher(){
		
		String v$navigation = null;
		try {	
		// L'entité selectionné devient l'objet courant; Cela suppose que le Contexte de page est Liste
		defaultVue.setEntiteCourante(defaultVue.getTableMgr().getEntiteSelectionne());
		
		// Par simple Prudence, on dira si l'entite existe
		if(defaultVue.getEntiteCourante() != null){
			
			// Mise à jour de la navigation : Vers le formulaire de Details
			v$navigation =  getMemoEntite().concat(CoreConstants.SUFFIXE_NVGT_DETAILS);
			
			// MAJ de l'ID à display
			setIdEntityToDisplay(defaultVue.getEntiteCourante().getId());
		}
		
		// Recherche des conteneurs de l'ordre
		List<TabCon> liste = MouvSvcoDelegaute.getSvcoCon().rechercherParNumOrd(defaultVue.getEntiteCourante().getNumOrdTra());
		((OrdVue)defaultVue).getConteneurMgr().clear();
		((OrdVue)defaultVue).getConteneurMgr().add(liste);
		
		// Mise en cohérence des IMH
		coherenceIHM();
		
	}	
	catch (Exception e) {	
		e.printStackTrace();
		//Message utilisateur
		FacesUtil.addWarnMessage("TRAITEMENT_ALL_ECHEC", "TRAITEMENT_ALL_ECHEC_INCONNU");
		getLogger().error(e.getMessage(), e);
	}		
		// Retour à la page adéquate
		return v$navigation;
	}
	
	/**
	 * Méthode appelée par le composant suggestionBox pour obtenir son Contenu
	 */
	public List<TabLieu>  autoCompleteLieuEnlev(Object suggest){ 
		
		String pref = (String)suggest;
		ArrayList<TabLieu> result = new ArrayList<TabLieu>();
		
		TabLieu tabLieu = new TabLieu();
		List<TabLieu> v$liste = null;
		try {
			v$liste = RefSvcoDeleguate.getSvcoLieu().rechercherTout(tabLieu);
		} catch (ServiceLocatorException e) {
			getLogger().error("Erreur Récupération serrvice SvcoLieu", e);
		} catch (GeveeAppException e) {
			getLogger().error("Erreur lors de l'exécution du  SvcoLieu.rechercherTout !"  , e);
		}
		
		v$liste = (v$liste != null) ? v$liste : new ArrayList<TabLieu>();
		for(TabLieu item : v$liste){
        	if ((item.getLibLieu() != null && item.getLibLieu().toLowerCase().indexOf(pref.toLowerCase()) == 0) || "".equals(pref))
            {
                result.add(item);
            }
        }
      
		return result;
	}
	
	/**
	 * Méthode appelée par le composant suggestionBox pour obtenir son Contenu
	 */
	public List<TabLieu>  autoCompleteLieuDecha(Object suggest){ 
		
		String pref = (String)suggest;
		ArrayList<TabLieu> result = new ArrayList<TabLieu>();
		
		TabLieu tabLieu = new TabLieu();
		List<TabLieu> v$liste = null;
		try {
			v$liste = RefSvcoDeleguate.getSvcoLieu().rechercherTout(tabLieu);
		} catch (ServiceLocatorException e) {
			getLogger().error("Erreur Récupération serrvice SvcoLieu", e);
		} catch (GeveeAppException e) {
			getLogger().error("Erreur lors de l'exécution du  SvcoLieu.rechercherTout !"  , e);
		}
		
		v$liste = (v$liste != null) ? v$liste : new ArrayList<TabLieu>();
		for(TabLieu item : v$liste){
        	if ((item.getLibLieu() != null && item.getLibLieu().toLowerCase().indexOf(pref.toLowerCase()) == 0) || "".equals(pref))
            {
                result.add(item);
            }
        }
      
		return result;
	}
	
	/***
	 * Enregistre la création ou la modification d'une entité 
	 * 
	 * @return
	 */
	
	@SuppressWarnings("finally")
	@Override
	public String enregistrer(){
		
		// Determine vers quelle page ou Formulaire l'on doit se diriger
		String v$navigation = null;
				
		getLogger().debug("DEBUG méthode enregistrer!");
		
		try {
			
			// Si nous sommes en Création sur le Formulaire d'Edition
			if(! defaultVue.getNavigationMgr().getEnModification()){
				// Set la liste des conteneurs de l'ordre de transport
				defaultVue.getEntiteCourante().setListCon(((OrdVue)defaultVue).getConteneurMgr().getListeRecherche());
				// Consommation de l'EJB distant pour la création
				defaultVue.setEntiteCourante((TabOrd) getEntitySvco().creer(defaultVue.getEntiteCourante()));
								
				// Raffraîchissement de l'entité courante pour des besoins de mises à jour des entités liés s'il y'en a				
				defaultVue.setEntiteCourante((TabOrd) getEntitySvco().rechercher(defaultVue.getEntiteCourante(), defaultVue.getEntiteCourante().getId()));
				
				getLogger().debug("DEBUG rechercher");
				
				// MAJ de la liste de Recherche
				defaultVue.getTableMgr().add(defaultVue.getEntiteCourante());					
				
				// Après un enregistrement nous retournons toujours en consultation 
				v$navigation = getMemoEntite().concat(CoreConstants.SUFFIXE_NVGT_DETAILS);
				
				FacesUtil.addInfoMessage("","CREATION_SUCCESS");
											
			}
			
			// Par contre Si nous sommes en modification sur le Formulaire d'Edition
			else{
				//Récupére la liste de tous les conteneurs en modification
				List<TabCon> listCon = ((OrdVue)defaultVue).getConteneurMgr().getListeRecherche();
				//Charge la liste dans un map
				Map<String, TabCon> mapConMod = new HashMap<String, TabCon>();
				for(TabCon conCurrent : listCon){
					mapConMod.put(conCurrent.getNumCon(), conCurrent);
				}
				// Recherche des conteneurs de l'ordre déjà exixtant
				List<TabCon> liste = MouvSvcoDelegaute.getSvcoCon().rechercherParNumOrd(defaultVue.getEntiteCourante().getNumOrdTra());
				//On parcour la liste des conteneurs exixtant et on les retire du map
				for(TabCon conToRemove : liste){
					if(mapConMod.containsKey(conToRemove.getNumCon())){
						mapConMod.remove(conToRemove.getNumCon());
					}
				}
				List<TabCon> newCon = new ArrayList<TabCon>(mapConMod.values());
				defaultVue.getEntiteCourante().setListCon(newCon);
				
				// Set la liste des conteneurs de l'ordre de transport
//				defaultVue.getEntiteCourante().setListCon(((OrdVue)defaultVue).getConteneurMgr().getListeRecherche());
				
				// Consommation de l'EJB distant pour la création 
				defaultVue.setEntiteCourante((TabOrd) getEntitySvco().modifier(defaultVue.getEntiteCourante()));
				
				// Raffraîchissement de l'entité courante pour des besoins de mises à jour des entités liés s'il y'en a				
				defaultVue.setEntiteCourante((TabOrd) getEntitySvco().rechercher(defaultVue.getEntiteCourante(), defaultVue.getEntiteCourante().getId()));
			
				// MAJ de la liste de Recherche
				defaultVue.getTableMgr().replace(defaultVue.getEntiteTemporaire(), defaultVue.getEntiteCourante());
				
				
				// Après un enregistrement nous retournons toujours en consultation 
				v$navigation = getMemoEntite().concat(CoreConstants.SUFFIXE_NVGT_DETAILS);
						
				FacesUtil.addInfoMessage("", "MODIFICATION_SUCCESS");
			
			}	
			// Recherche des conteneurs de l'ordre
			List<TabCon> liste = MouvSvcoDelegaute.getSvcoCon().rechercherParNumOrd(defaultVue.getEntiteCourante().getNumOrdTra());
			getLogger().debug("DEBUG enregistrer : liste = " + liste.size());
			((OrdVue)defaultVue).getConteneurMgr().clear();
			((OrdVue)defaultVue).getConteneurMgr().add(liste);
			
			mapCon.clear();

			// Coherence IHM avant affichage du formulaire de consultation
//			coherenceIHM();
		}	
		
		catch (Exception e) {	
			
			e.printStackTrace();
			// Aucune navigation possible
			v$navigation = null;
			
			//Message utilisateur
			FacesUtil.addWarnMessage("TRAITEMENT_ALL_ECHEC", "TRAITEMENT_ALL_ECHEC_INCONNU");
			getLogger().error(e.getMessage(), e);
		}		
				
		finally{
			
			// Retour à la page adéquate	
			return v$navigation;
		}	
		
	}	
	
	/**
	 * Permet de naviguer vers le formulaire d'Edition afin de creer une nouvelle entité
	 * 
	 * @return
	 */
	public String ajouter(){
		
		((OrdVue)defaultVue).getConteneurMgr().clear();
		mapCon.clear();
		
		// Determine vers quelle page ou formulaire l'on doit se diriger
		String v$navigation = null;
		
		// Mise à jour de l'entité courante selon le contexte du Formulaire 
		if(defaultVue.getNavigationMgr().isFromListe())
			defaultVue.setEntiteCourante(defaultVue.getTableMgr().getEntiteSelectionne());		
		
		// Sauvegarde de l'entité courante 
		defaultVue.setEntiteTemporaire(defaultVue.getEntiteCourante());
		
		// L'entité courante devient vierge afin qu'aucunes données ne s'affichent sur l'interface graphique 
		defaultVue.setEntiteCourante(defaultVue.getNewEntity());
		
		// Mise à jour du Contexte : En création 
		defaultVue.getNavigationMgr().setEnModification(false);
		
		// Mise à jour de la navigation : Vers le formulaire d'Edition
		v$navigation =  getMemoEntite().concat("Edition");
		
		// Retour à la page adéquate
		return v$navigation;
	}	
	
	/***
	 * Annule la modification ou la création d'une entité 
	 * 
	 * @return
	 */
	public String annulerEdition(){
		try {
			mapCon.clear();
			// Determine vers quelle page ou Formulaire l'on doit se diriger
			String v$navigation = null;
			
			// Restauration de l'entité temporairement sauvegardé
			defaultVue.setEntiteCourante(defaultVue.getEntiteTemporaire());
			
			// Si nous sommes parti du Formulaire Details vers le Formulaire d'Edition
			if(defaultVue.getNavigationMgr().isFromDetails()){
				
				// Alors nous retournons vers le formulaire Details
				v$navigation = getMemoEntite().concat(CoreConstants.SUFFIXE_NVGT_DETAILS);
				// Recherche des conteneurs de l'ordre
				List<TabCon>	liste = MouvSvcoDelegaute.getSvcoCon().rechercherParNumOrd(defaultVue.getEntiteCourante().getNumOrdTra());
				getLogger().debug("DEBUG enregistrer : liste = " + liste.size());
				((OrdVue)defaultVue).getConteneurMgr().clear();
				((OrdVue)defaultVue).getConteneurMgr().add(liste);
				
				//Mise en Cohérence des IHMs avant de retourner sur le formulaire de Consultation
			}	
			
			// Sinon nous retournons vers le formulaire Liste 
			else {
				
				// Alors nous retournons vers le formulaire Liste
				v$navigation = getMemoEntite().concat(CoreConstants.SUFFIXE_NVGT_LISTE);
				
			}
			
			// Retour à la page adéquate	
			return v$navigation;
		
		} catch (GeveeAppException e) {
			e.printStackTrace();
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Permet de Naviguer vers le formulaire d'Edition afin de pouvoir modifier une entité 
	 * 	
	 * @return
	 */
	public String modifier(){
		
		mapCon.clear();
		// Determine vers quelle page ou formulaire l'on doit se diriger
		String v$navigation = null;
			
		// Mise à jour du Contexte : En Modification 
		defaultVue.getNavigationMgr().setEnModification(true);
		
		// Mise à jour de l'entité courante selon le contexte du Formulaire 
		if(defaultVue.getNavigationMgr().isFromListe())
			defaultVue.setEntiteCourante(defaultVue.getTableMgr().getEntiteSelectionne());
		
		
		// Sauvegarde de l'entité courante 
		defaultVue.setEntiteTemporaire(defaultVue.getEntiteCourante());
		
		// Clone de l'Entité courante avant Modification
		defaultVue.setEntiteCourante(defaultVue.clone(defaultVue.getEntiteCourante()));
		
							
		// Si nous sommes en Consultation ==> sur le formulaire Details
		// Donc l'entité courante est déja connue
		if(defaultVue.getNavigationMgr().isFromDetails()){
									
			// Mise à jour de la navigation : Vers le formulaire d'Edition
			v$navigation =  getMemoEntite().concat(CoreConstants.SUFFIXE_NVGT_EDITION);			
		}
		
		// Par contre si nous sommes sur le formulaire Liste 
		else if(defaultVue.getNavigationMgr().isFromListe()){
						
			// Par simple Prudence, on dit si l'entite existe
			if(defaultVue.getEntiteCourante() != null){
												
				// Mise à jour de la navigation : Vers le formulaire d'Edition
				v$navigation =  getMemoEntite().concat(CoreConstants.SUFFIXE_NVGT_EDITION);				
			}
		}
		// Recherche des conteneurs de l'ordre
		try {
			List<TabCon> liste = MouvSvcoDelegaute.getSvcoCon().rechercherParNumOrd(defaultVue.getEntiteCourante().getNumOrdTra());
			getLogger().debug("DEBUG enregistrer : liste = " + liste.size());
			((OrdVue)defaultVue).getConteneurMgr().clear();
			((OrdVue)defaultVue).getConteneurMgr().add(liste);
		} catch (GeveeAppException e) {
			e.printStackTrace();
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		}
		
		
		// Retour à la page adéquate
		return v$navigation;
	}	
	

}
