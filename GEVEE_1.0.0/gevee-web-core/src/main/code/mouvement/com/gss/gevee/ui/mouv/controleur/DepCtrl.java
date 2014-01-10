package com.gss.gevee.ui.mouv.controleur;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.richfaces.component.html.ContextMenu;

import com.gss.gevee.be.core.base.BaseEntity;
import com.gss.gevee.be.core.enums.EnuEtat;
import com.gss.gevee.be.core.exception.GeveeAppException;
import com.gss.gevee.be.core.svco.base.IBaseSvco;
import com.gss.gevee.be.mouv.entity.TabCon;
import com.gss.gevee.be.mouv.entity.TabDep;
import com.gss.gevee.be.mouv.entity.TabMouv;
import com.gss.gevee.be.mouv.entity.TabOrd;
import com.gss.gevee.ui.core.base.FacesUtil;
import com.gss.gevee.ui.core.base.GeveeCtrl;
import com.gss.gevee.ui.core.base.GeveeToolBox;
import com.gss.gevee.ui.core.base.MenuFactory;
import com.gss.gevee.ui.core.base.ServiceLocatorException;
import com.gss.gevee.ui.core.base.Traitement;
import com.gss.gevee.ui.mouv.util.MouvSvcoDelegaute;
import com.gss.gevee.ui.mouv.util.MouvTrt;
import com.gss.gevee.ui.mouv.vue.DepVue;

public class DepCtrl extends GeveeCtrl<TabDep, TabDep>{
	
	/**
	 * Nom du Bean managé par JSF dans le fichier de Configuration 
	 */
	private static String nomManagedBean = "depCtrl";
	
	public DepCtrl(){		
		defaultVue = new DepVue();	
	}


	@Override
	public List<Traitement> getListeTraitements() {
		String v$codeEntite = "Dep";

		System.out.println("DepCtrl.getListeTraitements() ici il vaut : "
				+ v$codeEntite);
		// Ensemble des traitements standards
		Map<String, Traitement> v$mapTrt = new TreeMap<String, Traitement>(
				MouvTrt.getTrtStandards(v$codeEntite));
		
		// Demarrer
		v$mapTrt.put(MouvTrt.DEMARRER_DEP.getKey(), new Traitement(MouvTrt.DEMARRER_DEP));
		
        // CLOTURER
		v$mapTrt.put(MouvTrt.CLOTURER_DEP.getKey(), new Traitement(MouvTrt.CLOTURER_DEP));
		
		// Receptionner
		Traitement v$traitementTabDep = new Traitement(
				MouvTrt.RECEPTIONNER);
		v$traitementTabDep.setModalType(Traitement.MODAL_SPECIAL);
		v$traitementTabDep.setModalPanel("mpnl_receptionner"); 
		v$traitementTabDep.setMethode("beforeReceptionner");
		v$mapTrt.put(v$traitementTabDep.getKey(), v$traitementTabDep);
		
		// Enregistrer mouvement
		Traitement v$traitement = new Traitement(
				MouvTrt.ENREG_MOUV);
		v$traitement.setModalType(Traitement.MODAL_SPECIAL);
		v$traitement.setMethode("enregistrerMouv");
		v$mapTrt.put(v$traitement.getKey(), v$traitement);
		
		listeTraitements = Traitement.getOrderedTrt(v$mapTrt);
		return listeTraitements;
	}

	@Override
	public Class<?> getMyClass() {
		return DepCtrl.class;
	}

	@Override
	public void buildListeTraitement() {
		if(getMapTraitements() == null){
			setMapTraitements(MouvTrt.getTrtStandards("Dep")) ;
		}
	}

	@Override
	public List<TabDep> rechercherParCritere(TabDep p$entity)
			throws GeveeAppException {
		try {
			super.setTimeOfLastSearch();
			return MouvSvcoDelegaute.getSvcoDep().rechercherParCritere(p$entity);
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		}catch (GeveeAppException e) {
			GeveeAppException sdr = new GeveeAppException(e.getMessage());
			throw sdr;
		}
		return null;
	}

	@Override
	public IBaseSvco<TabDep> getEntitySvco() throws ServiceLocatorException {
		return MouvSvcoDelegaute.getSvcoDep();
	}

	public static String getNomManagedBean() {
		return nomManagedBean;
	}
	
	public void setSelectedEntity(BaseEntity p$entite) {

		// Nom de la propriété à mettre à jour pour
		String v$propriete = defaultVue.getNavigationMgr().getSelectionPropertyName();

		if (v$propriete.equals("tabCon")) {
			TabCon v$entite = (TabCon) p$entite;
			defaultVue.getEntiteCourante().setTabCon(v$entite);
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
	public List<TabOrd>  autoCompleteOrd(Object suggest){ 
		
		String pref = (String)suggest;
		ArrayList<TabOrd> result = new ArrayList<TabOrd>();
		
		TabOrd tabOrd = new TabOrd();
		List<TabOrd> v$liste = null;
		try {
			v$liste = MouvSvcoDelegaute.getSvcoOrd().rechercherTout(tabOrd);
		} catch (ServiceLocatorException e) {
			getLogger().error("Erreur Récupération serrvice SvcoOrd", e);
		} catch (GeveeAppException e) {
			getLogger().error("Erreur lors de l'exécution du  SvcoOrd.rechercherTout !"  , e);
		}
		
		v$liste = (v$liste != null) ? v$liste : new ArrayList<TabOrd>();
		for(TabOrd item : v$liste){
        	if ((item.getNumOrdTra() != null && item.getNumOrdTra().toLowerCase().indexOf(pref.toLowerCase()) == 0) || "".equals(pref))
            {
                result.add(item);
            }
        }
      
		return result;
	}
	
	public List<TabCon> getAllConEtatCree(){
		try {
			return MouvSvcoDelegaute.getSvcoDep().rechercherConParEtatEnt(EnuEtat.CREE.getValue());
		} catch (GeveeAppException e) {
			e.printStackTrace();
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		}
		return null; 
	}
	
	@SuppressWarnings({ "finally", "unchecked" })
	public String enregistrerMouv() {
		
		String v$navigation = null;
		
		try {
			// Mise à jour de l'entité courante selon le contexte du Formulaire
			if (defaultVue.getNavigationMgr().isFromListe())
				defaultVue.setEntiteCourante(defaultVue.getTableMgr()
						.getEntiteSelectionne());

			// Sauvegarde de l'entité avant traitement specifique
			defaultVue.setEntiteTemporaire(defaultVue.getEntiteCourante());
			
			if(!defaultVue.getEntiteCourante().getBEstDem()){
				FacesUtil.addInfoMessage("", "Impossible d'enregistrer le mouvement : bien vouloir démarrer le déplacement");
				return null;
			}
			
			MouvCtrl mouvCtrl = (MouvCtrl) FacesUtil
			.getSessionMapValue(new MouvCtrl().getNomManagedBean2());

			if (mouvCtrl == null) {
				mouvCtrl = new MouvCtrl();
				
				FacesUtil.setSessionMapValue(mouvCtrl.getNomManagedBean2(),
						mouvCtrl);
			}
			
			TabDep dep = getDefaultVue().getEntiteCourante();
			
			TabMouv mouv = new TabMouv();
			mouv.setTabDep(dep);		
			mouvCtrl.getDefaultVue().setEntiteCourante(mouv);
			mouvCtrl.getDefaultVue().getNavigationMgr().setEnModification(false);
			
		} catch (Exception e) {
			v$navigation = null;
			e.printStackTrace();
		}
		return "MouvEdition";
//		finally {
//			// Retour à la page adéquate
//			return v$navigation;
//		}
	}
	
	
	public String beforeReceptionner() {
		return null;
	}
	
	@SuppressWarnings("finally")
	public String receptionner() {

		// Determine vers quelle page ou Formulaire l'on doit se diriger
		String v$navigation = null;

		// Message d'information
		String v$msgDetails = "La réception du conteneur n° ";

		try {

			// Sauvegarde de l'entité avant traitement specifique
			defaultVue.setEntiteTemporaire(defaultVue.getEntiteCourante());
			
			if(!defaultVue.getEntiteCourante().getBEstDem()){
				FacesUtil.addInfoMessage("", "Impossible de receptionner : bien vouloir démarrer le déplacement");
				return null;
			}
			
			// Consommation de l'EJB distant selon l'operation spécifique car
			// l'entite courante est connue
			defaultVue.setEntiteCourante(MouvSvcoDelegaute.getSvcoDep()
					.receptionner(defaultVue.getEntiteCourante()));
			
			v$msgDetails += defaultVue.getEntiteCourante().getTabCon().getNumCon() + " a été effectuée";

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
	
	@SuppressWarnings("finally")
	public String demarrer() {

		// Determine vers quelle page ou Formulaire l'on doit se diriger
		String v$navigation = null;

		// Message d'information
		String v$msgDetails = "Le déplacement du conteneur n°";

		try {

			// Sauvegarde de l'entité avant traitement specifique
			defaultVue.setEntiteTemporaire(defaultVue.getEntiteCourante());

			// Consommation de l'EJB distant selon l'operation spécifique car
			// l'entite courante est connue
//			defaultVue.setEntiteCourante(MouvSvcoDelegaute.getSvcoDep()
//					.demarrer(defaultVue.getTableMgr().getEntiteSelectionne()));
			defaultVue.setEntiteCourante(MouvSvcoDelegaute.getSvcoDep()
					.demarrer(defaultVue.getEntiteCourante()));
			
			v$msgDetails += defaultVue.getEntiteCourante().getTabCon().getNumCon() + " est démarré";

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
