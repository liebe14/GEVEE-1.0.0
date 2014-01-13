package com.gss.gevee.ui.mouv.controleur;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.gss.gevee.be.core.base.BaseEntity;
import com.gss.gevee.be.core.exception.GeveeAppException;
import com.gss.gevee.be.core.svco.base.IBaseSvco;
import com.gss.gevee.be.mouv.entity.TabChk;
import com.gss.gevee.be.mouv.entity.TabDep;
import com.gss.gevee.be.mouv.entity.TabMouv;
import com.gss.gevee.be.ref.entity.TabChau;
import com.gss.gevee.be.ref.entity.TabLieu;
import com.gss.gevee.be.ref.entity.TabRem;
import com.gss.gevee.be.ref.entity.TabTrac;
import com.gss.gevee.ui.core.base.FacesUtil;
import com.gss.gevee.ui.core.base.GeveeCtrl;
import com.gss.gevee.ui.core.base.GeveeToolBox;
import com.gss.gevee.ui.core.base.ServiceLocatorException;
import com.gss.gevee.ui.core.base.Traitement;
import com.gss.gevee.ui.mouv.util.MouvSvcoDelegaute;
import com.gss.gevee.ui.mouv.util.MouvTrt;
import com.gss.gevee.ui.mouv.vue.MouvVue;
import com.gss.gevee.ui.ref.util.RefSvcoDeleguate;



public class MouvCtrl extends GeveeCtrl<TabMouv, TabMouv>{
	
	/**
	 * Nom du Bean managé par JSF dans le fichier de Configuration 
	 */
	private static String nomManagedBean = "mouvCtrl";
	
	public MouvCtrl(){		
		defaultVue = new MouvVue();	
	}


	@Override
	public List<Traitement> getListeTraitements() {
		String v$codeEntite = "Mouv";

		System.out.println("MouvCtrl.getListeTraitements() ici il vaut : "
				+ v$codeEntite);
		// Ensemble des traitements standards
		Map<String, Traitement> v$mapTrt = new TreeMap<String, Traitement>(
				MouvTrt.getTrtStandards(v$codeEntite));
		
		v$mapTrt.remove(Traitement.getKey(v$codeEntite, MouvTrt.AJOUTER));
		v$mapTrt.remove(Traitement.getKey(v$codeEntite, MouvTrt.COPIER));
		
		// Valider
		v$mapTrt.put(MouvTrt.VALIDER_MOUV.getKey(), new Traitement(MouvTrt.VALIDER_MOUV));
		
		// Enregistrer check point
		Traitement v$traitement = new Traitement(
				MouvTrt.ENREG_CHKPT);
		v$traitement.setModalType(Traitement.MODAL_SPECIAL);
		v$traitement.setMethode("enregistrerChk");
		v$mapTrt.put(v$traitement.getKey(), v$traitement);
		
		listeTraitements = Traitement.getOrderedTrt(v$mapTrt);
		return listeTraitements;
	}

	@Override
	public Class<?> getMyClass() {
		return MouvCtrl.class;
	}

	@Override
	public void buildListeTraitement() {
		if(getMapTraitements() == null){
			setMapTraitements(MouvTrt.getTrtStandards("Mouv")) ;
		}
	}

	@Override
	public List<TabMouv> rechercherParCritere(TabMouv p$entity)
			throws GeveeAppException {
		try {
			super.setTimeOfLastSearch();
			return MouvSvcoDelegaute.getSvcoMouv().rechercherParCritere(p$entity);
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		}catch (GeveeAppException e) {
			GeveeAppException sdr = new GeveeAppException(e.getMessage());
			throw sdr;
		}
		return null;
	}

	@Override
	public IBaseSvco<TabMouv> getEntitySvco() throws ServiceLocatorException {
		return MouvSvcoDelegaute.getSvcoMouv();
	}

	public static String getNomManagedBean() {
		return nomManagedBean;
	}
	
	public void setSelectedEntity(BaseEntity p$entite) {

		// Nom de la propriété à mettre à jour pour
		String v$propriete = defaultVue.getNavigationMgr().getSelectionPropertyName();

		if (v$propriete.equals("tabDep")) {
			TabDep v$entite = (TabDep) p$entite;
			defaultVue.getEntiteCourante().setTabDep(v$entite);
		}
		
		if (v$propriete.equals("tabChau")) {
			TabChau v$entite = (TabChau) p$entite;
			defaultVue.getEntiteCourante().setTabChau(v$entite);
		}
		
		if (v$propriete.equals("tabLieuDepar")) {
			TabLieu v$entite = (TabLieu) p$entite;
			defaultVue.getEntiteCourante().setTabLieuDepar(v$entite);
		}
		
		if (v$propriete.equals("tabLieuArriv")) {
			TabLieu v$entite = (TabLieu) p$entite;
			defaultVue.getEntiteCourante().setTabLieuArriv(v$entite);
		}
		
		if (v$propriete.equals("tabTrac")) {
			TabTrac v$entite = (TabTrac) p$entite;
			defaultVue.getEntiteCourante().setTabTrac(v$entite);
		}
		
		if (v$propriete.equals("tabRem")) {
			TabRem v$entite = (TabRem) p$entite;
			defaultVue.getEntiteCourante().setTabRem(v$entite);
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
	
	/**
	 * Méthode appelée par le composant suggestionBox pour obtenir son Contenu
	 */
	public List<TabDep>  autoCompleteRefDep(Object suggest){ 
		
		String pref = (String)suggest;
		ArrayList<TabDep> result = new ArrayList<TabDep>();
		
		TabDep tabDep = new TabDep();
		List<TabDep> v$liste = null;
		try {
			v$liste = MouvSvcoDelegaute.getSvcoDep().rechercherTout(tabDep);
		} catch (ServiceLocatorException e) {
			getLogger().error("Erreur Récupération serrvice SvcoDep", e);
		} catch (GeveeAppException e) {
			getLogger().error("Erreur lors de l'exécution du  SvcoDep.rechercherTout !"  , e);
		}
		
		v$liste = (v$liste != null) ? v$liste : new ArrayList<TabDep>();
		for(TabDep item : v$liste){
        	if ((item.getCodRefDep() != null && item.getCodRefDep().toLowerCase().indexOf(pref.toLowerCase()) == 0) || "".equals(pref))
            {
                result.add(item);
            }
        }
      
		return result;
	}
	
	/**
	 * Méthode appelée par le composant suggestionBox pour obtenir son Contenu
	 */
	public List<TabLieu>  autoCompleteLieuDep(Object suggest){ 
		
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
	public List<TabLieu>  autoCompleteLieuArriv(Object suggest){ 
		
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
	public List<TabChau>  autoCompleteChau(Object suggest){ 
		
		String pref = (String)suggest;
		ArrayList<TabChau> result = new ArrayList<TabChau>();
		
		TabChau tabChau = new TabChau();
		List<TabChau> v$liste = null;
		try {
			v$liste = RefSvcoDeleguate.getSvcoChau().rechercherTout(tabChau);
		} catch (ServiceLocatorException e) {
			getLogger().error("Erreur Récupération serrvice SvcoChau", e);
		} catch (GeveeAppException e) {
			getLogger().error("Erreur lors de l'exécution du  SvcoChau.rechercherTout !"  , e);
		}
		
		v$liste = (v$liste != null) ? v$liste : new ArrayList<TabChau>();
		for(TabChau item : v$liste){
        	if ((item.getMatChau() != null && item.getMatChau().toLowerCase().indexOf(pref.toLowerCase()) == 0) || "".equals(pref))
            {
                result.add(item);
            }
        }
      
		return result;
	}
	
	@SuppressWarnings({ "finally", "unchecked" })
	public String enregistrerChk() {
		
		String v$navigation = null;
		
		try {
			// Mise à jour de l'entité courante selon le contexte du Formulaire
			if (defaultVue.getNavigationMgr().isFromListe())
				defaultVue.setEntiteCourante(defaultVue.getTableMgr()
						.getEntiteSelectionne());

			// Sauvegarde de l'entité avant traitement specifique
			defaultVue.setEntiteTemporaire(defaultVue.getEntiteCourante());
			
			if(!defaultVue.getEntiteCourante().getBEstVal()){
				FacesUtil.addInfoMessage("", "Impossible d'enregistrer le chekpoint : bien vouloir valider le mouvement");
				return null;
			}
			
			ChkCtrl chkCtrl = (ChkCtrl) FacesUtil
			.getSessionMapValue(new ChkCtrl().getNomManagedBean2());

			if (chkCtrl == null) {
				chkCtrl = new ChkCtrl();
				
				FacesUtil.setSessionMapValue(chkCtrl.getNomManagedBean2(),
						chkCtrl);
			}
			
			TabMouv mouv = getDefaultVue().getEntiteCourante();
			
			TabChk chk = new TabChk();
			chk.setTabMouv(mouv);		
			chkCtrl.getDefaultVue().setEntiteCourante(chk);
			chkCtrl.getDefaultVue().getNavigationMgr().setEnModification(false);
			
		} catch (Exception e) {
			v$navigation = null;
			e.printStackTrace();
		}
		return "ChkEdition";

	}
	
	@SuppressWarnings("finally")
	public String valider() {

		// Determine vers quelle page ou Formulaire l'on doit se diriger
		String v$navigation = null;

		// Message d'information
		String v$msgDetails = "Le mouvement du conteneur n° ";

		try {

			// Mise à jour de l'entité courante selon le contexte du Formulaire
			if (defaultVue.getNavigationMgr().isFromListe())
				defaultVue.setEntiteCourante(defaultVue.getTableMgr()
						.getEntiteSelectionne());
			
			// Sauvegarde de l'entité avant traitement specifique
			defaultVue.setEntiteTemporaire(defaultVue.getEntiteCourante());

			// Consommation de l'EJB distant selon l'operation spécifique car
			// l'entite courante est connue
			defaultVue.setEntiteCourante(MouvSvcoDelegaute.getSvcoMouv()
					.valider(defaultVue.getEntiteCourante()));
			
			v$msgDetails += defaultVue.getEntiteCourante().getTabDep().getTabCon().getNumCon() + " du " + defaultVue.getEntiteCourante().getTabLieuDepar().getLibLieu() +
			" vers "+ defaultVue.getEntiteCourante().getTabLieuArriv().getLibLieu()+" a été validé";

			// L'on remplace l'ancienne entité de la liste par la nouvelle issue
			// du résultat du traitement spécifiques
			defaultVue.getTableMgr().replace(defaultVue.getEntiteTemporaire(),
					defaultVue.getEntiteCourante());

			// Si nous sommes en Consultation ==> sur le formulaire Details
			if (defaultVue.getNavigationMgr().isFromDetails()) {
				// Traitements particuliers
			}

			// Par contre si nous sommes sur le formulaire Liste
			else if (defaultVue.getNavigationMgr().isFromListe()) {
				// Traitements particuliers
			}
			defaultVue.getTableMgr().updateDataModel();
			FacesUtil.addInfoMessage("", v$msgDetails);

		} catch (Exception e) {
			e.printStackTrace();
			// Aucune navigation possible
			v$navigation = null;
			getLogger().error(e.getMessage(), e);
		} finally {
			// Retour à la page adéquate
			return v$navigation;
		}
	}

}
