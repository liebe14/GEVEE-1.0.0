package com.gss.gevee.ui.mouv.controleur;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.gss.gevee.be.core.base.BaseEntity;
import com.gss.gevee.be.core.exception.GeveeAppException;
import com.gss.gevee.be.core.svco.base.IBaseSvco;
import com.gss.gevee.be.mouv.entity.TabChk;
import com.gss.gevee.be.mouv.entity.TabMouv;
import com.gss.gevee.be.ref.entity.TabLieu;
import com.gss.gevee.ui.core.base.FacesUtil;
import com.gss.gevee.ui.core.base.GeveeCtrl;
import com.gss.gevee.ui.core.base.ServiceLocatorException;
import com.gss.gevee.ui.core.base.Traitement;
import com.gss.gevee.ui.mouv.util.MouvSvcoDelegaute;
import com.gss.gevee.ui.mouv.util.MouvTrt;
import com.gss.gevee.ui.mouv.vue.ChkVue;
import com.gss.gevee.ui.ref.util.RefSvcoDeleguate;

public class ChkCtrl extends GeveeCtrl<TabChk, TabChk>{
	
	/**
	 * Nom du Bean managé par JSF dans le fichier de Configuration 
	 */
	private static String nomManagedBean = "chkCtrl";
	
	public ChkCtrl(){		
		defaultVue = new ChkVue();	
	}
	
	@Override
	public List<Traitement> getListeTraitements() {
		String v$codeEntite = "Chk";

		System.out.println("ChkCtrl.getListeTraitements() ici il vaut : "
				+ v$codeEntite);
		// Ensemble des traitements standards
		Map<String, Traitement> v$mapTrt = new TreeMap<String, Traitement>(
				MouvTrt.getTrtStandards(v$codeEntite));
		
		v$mapTrt.remove(Traitement.getKey(v$codeEntite, MouvTrt.AJOUTER));
		v$mapTrt.remove(Traitement.getKey(v$codeEntite, MouvTrt.COPIER));
		v$mapTrt.remove(Traitement.getKey(v$codeEntite, MouvTrt.SELECTIONNER));
		
		// Valider
		v$mapTrt.put(MouvTrt.VALIDER_MOUV.getKey(), new Traitement(MouvTrt.VALIDER_MOUV));
		
		listeTraitements = Traitement.getOrderedTrt(v$mapTrt);
		
		return listeTraitements;
	}

	@Override
	public Class<?> getMyClass() {
		return ChkCtrl.class;
	}
	
	@Override
	public void buildListeTraitement() {
		if(getMapTraitements() == null){
			setMapTraitements(MouvTrt.getTrtStandards("Chk")) ;
		}
	}

	@Override
	public List<TabChk> rechercherParCritere(TabChk p$entity)
			throws GeveeAppException {
		try {
			super.setTimeOfLastSearch();
			return MouvSvcoDelegaute.getSvcoChk().rechercherParCritere(p$entity);
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		}catch (GeveeAppException e) {
			GeveeAppException sdr = new GeveeAppException(e.getMessage());
			throw sdr;
		}
		return null;
	}

	@Override
	public IBaseSvco<TabChk> getEntitySvco() throws ServiceLocatorException {
		return MouvSvcoDelegaute.getSvcoChk();
	}
	
	public static String getNomManagedBean() {
		return nomManagedBean;
	}
	
	public void setSelectedEntity(BaseEntity p$entite) {

		// Nom de la propriété à mettre à jour pour
		String v$propriete = defaultVue.getNavigationMgr().getSelectionPropertyName();

		if (v$propriete.equals("tabMouv")) {
			TabMouv v$entite = (TabMouv) p$entite;
			defaultVue.getEntiteCourante().setTabMouv(v$entite);
		}
		
		if (v$propriete.equals("tabLieu")) {
			TabLieu v$entite = (TabLieu) p$entite;
			defaultVue.getEntiteCourante().setTabLieu(v$entite);
		}
		
	}
	
	@SuppressWarnings("finally")
	public String valider() {

		// Determine vers quelle page ou Formulaire l'on doit se diriger
		String v$navigation = null;

		// Message d'information
		String v$msgDetails = "L'arrêt du mouvement n° ";

		try {

			// Sauvegarde de l'entité avant traitement specifique
			defaultVue.setEntiteTemporaire(defaultVue.getEntiteCourante());

			// Consommation de l'EJB distant selon l'operation spécifique car
			// l'entite courante est connue
			defaultVue.setEntiteCourante(MouvSvcoDelegaute.getSvcoChk()
					.valider(defaultVue.getTableMgr().getEntiteSelectionne()));
			
			v$msgDetails += defaultVue.getEntiteCourante().getTabMouv().getCodRefMouv() + " au lieu dit " + defaultVue.getEntiteCourante().getTabLieu().getLibLieu() +
			" a été validé";

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
	
	/**
	 * Méthode appelée par le composant suggestionBox pour obtenir son Contenu
	 */
	public List<TabLieu>  autoCompleteLieu(Object suggest){ 
		
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
	public List<TabMouv>  autoCompleteMouv(Object suggest){ 
		
		String pref = (String)suggest;
		ArrayList<TabMouv> result = new ArrayList<TabMouv>();
		
		TabMouv tabMouv = new TabMouv();
		List<TabMouv> v$liste = null;
		try {
			v$liste =MouvSvcoDelegaute.getSvcoMouv().rechercherTout(tabMouv);
		} catch (ServiceLocatorException e) {
			getLogger().error("Erreur Récupération serrvice SvcoLieu", e);
		} catch (GeveeAppException e) {
			getLogger().error("Erreur lors de l'exécution du  SvcoLieu.rechercherTout !"  , e);
		}
		
		v$liste = (v$liste != null) ? v$liste : new ArrayList<TabMouv>();
		for(TabMouv item : v$liste){
        	if ((item.getCodRefMouv() != null && item.getCodRefMouv().toLowerCase().indexOf(pref.toLowerCase()) == 0) || "".equals(pref))
            {
                result.add(item);
            }
        }
      
		return result;
	}
}
