package com.gss.gevee.ui.core.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.component.html.HtmlInputHidden;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import org.richfaces.component.html.ContextMenu;
import org.richfaces.component.html.HtmlToolBar;
import org.richfaces.json.JSONArray;
import org.richfaces.json.JSONObject;

import com.gss.gevee.be.core.base.BaseEntity;
import com.gss.gevee.be.core.base.BaseLogger;
import com.gss.gevee.be.core.exception.GeveeAppException;
import com.gss.gevee.be.core.svco.base.IBaseSvco;

public abstract class GeveeCtrl <H extends BaseEntity, T extends H>{
	
	/***
	 * Vue par défaut d'une entité 
	 * Doit etre instanciée par la classe concrète 
	 */
	public GeveeVue<T> defaultVue;
	
	private String memoEntite;
	
	/**
	 * @return the listeTraitements
	 */
	public abstract List<Traitement> getListeTraitements();
	
	/**
	 * Liste des traitements possibles sur l'entité
	 */	
	private  Map<String, Traitement> mapTraitements;
	
	/**
	 * Liste des traitements possibles sur l'entité
	 */	
	protected  List<Traitement> listeTraitements;
	
	/**
	 * Barre d'outils des Actions de base  de l'IHM générée par la Classe MenuFactory
	 */
	protected HtmlToolBar toolbar;
	
	/**
	 * Barre d'outils des Actions de base et Standards  de l'IHM générée par la Classe MenuFactory
	 */	
	protected HtmlToolBar menu;
	
	/**
	 * Menu Contextuel  de l'IHM générée par la Classe MenuFactory
	 */	
	protected ContextMenu  tableMenu;
	
	/**
	 * Champ caché sur les pages web contenant le nom du fichier de la page
	 * Très utile pour déterminer sur de quel type formulaire il s'agit (LISTE, EDITION, DETAILS)	
	 */
	protected HtmlInputHidden hidden;
	
	/**
	 * Id de l'entité à afficher sur le formulaire DETAILS
	 */
	private String idEntityToDisplay;
	
	/**
	 * Map utile pour la gestion de l'affichage des traitements niveau IHM
	 * Clé :  Etat de l'Entité
	 * Valeur :	Liste des traitements possible pour cet état
	 * Envoyé coté Client via JSON
	 */
	protected Map<String, List<String>> etatListeTrt;
	
	/**
	 * Liste des codes des traitements utilisateurs
	 * valeur :  "code du traitement" +  "-"  + "Configuration de la visibilité du traitement"
	 * Envoyé coté Client via JSON
	 */
	protected List<String> listeTrtAut;
	/****
	 * Interface  Générique SVCO des entités 
	 * Doit etre instanciée par la classe concrète 
	 * 
	 * 
	 */
	protected IBaseSvco<H> entitySvco;
	
	/**
	 * Date de la dernière recherche
	 */
	protected Date dateLastSearch;
	
	/**
	 * Timeout à utiliser pour activer une nouvelle recherche lors de l'accès au formulaire liste
	 */
	private static  long timeoutRecherche;
	static{
		timeoutRecherche = Long.parseLong("20")  ;
	}
	
	/**
	 * Retourne le type Class de la Classe qui hérite du GénéricControlleur
	 * 
	 * @return
	 */
	public  abstract Class<?> getMyClass();
	
	public GeveeCtrl(){		
		super();
	}
	
	public BaseLogger getLogger(){
		return BaseLogger.getLogger(getMyClass());
	}
	
	/**
	 * @return the defaultVue
	 */
	public GeveeVue<T> getDefaultVue() {
		return defaultVue;
	}


	/**
	 * @param defaultVue the defaultVue to set
	 */
	public void setDefaultVue(GeveeVue<T> defaultVue) {
		this.defaultVue = defaultVue;
	}
	
	public String getMemoEntite() {
		
		if(memoEntite == null){
			String v$nomControlleur = getMyClass().getSimpleName();		
			memoEntite = v$nomControlleur.substring(0, (v$nomControlleur.length()	- 4/*Taille du nom du controleur privé du suffixe Ctrl*/ ));
		}
		return memoEntite;			
	}
	
	/**
	 * Retourne le nom du Bean Managé par JSF dans le Fichier de Configuration
	 * Utilile pour ne pas avoir a ecrire le nom des Beans en dur dans le Code
	 * @return
	 */
	public String getNomManagedBean2(){
		
		String v$string = getMemoEntite().substring(0, 1);
		String 	v$nomManagedBean = getMemoEntite().replaceFirst(v$string, v$string.toLowerCase())+"Ctrl";
		return v$nomManagedBean;
	}	
	
	/**
	 * @return the tableMenu
	 */
	public ContextMenu getTableMenu() {
		// Génération du Menu Contextuel utilisateur
		tableMenu = new MenuFactory(getHidden().getValue().toString(), getNomManagedBean2(), getListeTraitements()).getContextMenu();
				
		return tableMenu;

	}

	/**
	 * @param tableMenu the tableMenu to set
	 */
	public void setTableMenu(ContextMenu tableMenu) {
		this.tableMenu = tableMenu;
	}	
	
	/**
	 * @return the hidden
	 */
	public HtmlInputHidden getHidden() {
		return hidden;
	}

	/**
	 * @param hidden the hidden to set
	 */
	public void setHidden(HtmlInputHidden hidden) {
		// Le try-catch est nécéssaire à cause de la gestion générique de la page introduite dans les nouveaux templates
			// Template Formulaire Liste, Edition, Details
			// Etant donné que le <ui:param name='nomPage' value='#{managedBean.memoEntite}XXXX' est évalué a la création de la page par le gestionnaire de facelet
			// Des exceptions peuvent survenir lors de la phase RESTORE_VIEW pour des traitements ne créeant pas la page
		try{
			
			this.hidden = hidden;	
			
		}	
		catch(Exception e){
		} 
		
	}
	
	/**
	 * @return the toolbar
	 */
	public HtmlToolBar getToolbar() {
		// Génération de la Barre d'outils des Actions de Base		
		 toolbar = new MenuFactory(getHidden().getValue().toString(), getNomManagedBean2(), getListeTraitements()).getToolbar();
		 
		 return toolbar;
	}

	/**
	 * @param toolbar the toolbar to set
	 */
	public void setToolbar(HtmlToolBar toolbar) {
		this.toolbar = toolbar;
	}
	
	/**
	 * @return the mapTraitements
	 */		
	public Map<String, Traitement> getMapTraitements() {					
		return mapTraitements;
	}	
	
	public void setMapTraitements(Map<String, Traitement> mapTraitements){
		this.mapTraitements = mapTraitements;
	}
	
	/**
	 * Construit la liste des traitements de l'entité
	 */
	public abstract void  buildListeTraitement();
	
	/**
	 * @param listeTraitements the listeTraitements to set
	 */
	public void setListeTraitements(List<Traitement> listeTraitements) {
		this.listeTraitements = listeTraitements;
	}
	
	public void reinitialiser(ActionEvent evt){
		/*Réinitialisation de l'entité de recherche*/
		defaultVue.setEntiteRecherche(defaultVue.getEntityForSearch());	
	}
	
	/**
	 * Pas de Pagination
	 */
	private static  int pasPagination;
	static{
		pasPagination = 200  ;
	}
	/**
	 * Gère la pagination pour une recherche 
	 * 
	 * @param evt
	 */
	public void  paginer(ActionEvent evt){
								
		String typeRecherche = defaultVue.getTypeRecherche();
		
		int v$page = defaultVue.getTableMgr().getPage();
				
		T v$entiteRecherche = defaultVue.getEntiteRecherche();

		List<T> v$liste = new ArrayList<T>();
		
		try{
								
			// Définition de l'offset pour la pagination
			int offset = (v$page * pasPagination);
			
			v$entiteRecherche.setOffset(offset + 1);
//			v$entiteRecherche.setMaxRow(pasPagination);	
			
			
			if(typeRecherche.equals("ID")){
			
				v$liste = rechercherParCritere(v$entiteRecherche);			
			}
			
			else if(typeRecherche.equals("CRITERE")){
									
				v$liste = rechercherParCritere(v$entiteRecherche);
			}
			
			else if(typeRecherche.equals("TOUT")) {
								
				v$liste = rechercherTout(v$entiteRecherche);
			}				
				
			// Mise  à jour de  la liste de recherche ==> Mise à jour du modèle automatiquement
			defaultVue.getTableMgr().setListeRecherche(v$liste);
			
			// Si la liste de recherche est vide, précisez que la recherche n'a retourné aucun résultat
			if(v$liste == null || v$liste.size() == 0){
				FacesUtil.addWarnMessage("", "Aucun élément trouvé");
			}
			
		}
		catch (GeveeAppException e) {	
			e.printStackTrace();
			FacesUtil.addWarnMessage("TRAITEMENT_ALL_ECHEC", e.getMessage());			
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		}						
	}	
	
	public abstract List<T>  rechercherParCritere(T p$entity) throws GeveeAppException;
	
	public List<T>  rechercherTout(T p$entity) throws GeveeAppException, ServiceLocatorException{
		return getEntitySvco().rechercherTout(p$entity);
	}
	
	/***
	 * Ferme le formulaire d'entrée : Généralement le formulaire Liste 
	 * 
	 * @return
	 */	
	public String fermer(){
		
		// Determine vers quelle page ou Formulaire l'on doit se diriger
		String v$navigation = null;
		
		/***
		 * TODO A ameliorer  
		 */
				
		if(defaultVue.getNavigationMgr().getEnSelection()){
			
			// L'on  retourne vers le formulaire d'origine 
			v$navigation = defaultVue.getNavigationMgr().getSelectionSource();
			
			// Désactivation du mode selection 
			defaultVue.getNavigationMgr().setEnSelection(false);
			
			// Raz de la règle de navigation permettant de retourner à la page d'origine
			defaultVue.getNavigationMgr().setSelectionSource(null);
			
			
		}
		else if(defaultVue.getNavigationMgr().getRelatedSource() != null){
			
			// L'on  retourne vers le formulaire d'origine 
			v$navigation = defaultVue.getNavigationMgr().getRelatedSource();

			// Raz de la règle de navigation permettant de retourner à la page d'origine
			defaultVue.getNavigationMgr().setRelatedSource(null);

		}
		else {
			
			// TODO A ce niveau nous ne sommes ni en selection ni en navigation 
			//Dans ce cas l'action à réaliser est de fermer le formulaire et de rentrer à la page d'acceuil
			//FacesUtil.addWarnMessage(Constantes.ID_BARRE_MSG,"Fermer  : ", "Retour a la page d'acceuil ");
			v$navigation = "AcceuilDeBase";
		}		
		
		// Retour à la page adéquate	
		return v$navigation;	
	}	
	
	/**
	 * @return the menu
	 */
	public HtmlToolBar getMenu() {
						
		// Génération du Menu Actions de Base et Spécifiques
		menu = new MenuFactory(getHidden().getValue().toString(), getNomManagedBean2(), getListeTraitements()).getMenu();
		
		// Injection du Map Etat Liste des traitements 
		FacesUtil.setSessionMapValue("mapEtatCodeTrt", getJsonMapEtatTrt());
		
		// Injection du Spécial Id de l'entité courante 
		//FacesUtil.setSessionMapValue("specialKey", defaultVue.getSpecialKey(v$page));
		
		return menu;			
	}
	
	private String getJsonMapEtatTrt(){
		
		Map v$mapEtatCodeTrt  = new HashMap();
		
		// Il faut d'abord convertir les structures de données en Json avant de les ajouter dans le grand map
		JSONObject v$mapJsonEtatListeTrt = new JSONObject(getEtatListeTrt());
		JSONArray v$jsonListeTrtAut = new JSONArray(getListeTrtAut());
				
		v$mapEtatCodeTrt.put("mapEtatTrt", v$mapJsonEtatListeTrt);
		v$mapEtatCodeTrt.put("codeTrt", v$jsonListeTrtAut);
		
		JSONObject v$mapJson = new JSONObject(v$mapEtatCodeTrt);
		String v$json = v$mapJson.toString();
						
		return v$json;
	}

	/**
	 * @param menu the menu to set
	 */
	public void setMenu(HtmlToolBar menu) {
						
		this.menu = menu;
	}
	
	/**
	 * @return the etatListeTrt
	 */
	public  Map<String, List<String>> getEtatListeTrt() {
				
		return etatListeTrt;

	}


	/**
	 * @param etatListeTrt the etatListeTrt to set
	 */
	public void setEtatListeTrt(Map<String, List<String>> etatListeTrt) {
		this.etatListeTrt = etatListeTrt;
	}
	
	/**
	 * @return the listeTrtAut
	 */
	public List<String> getListeTrtAut() {
		
		if(listeTrtAut == null){
			listeTrtAut = new ArrayList<String>();
			for(Traitement t: getListeTraitements()){
				/*
				 *  Concaténation du code du traitement à la Configuration de la visibilité
				 *  Utilisé niveau Client pour l'affichage des traitements 
				 */			
				listeTrtAut.add(t.getCode()+"-"+t.getConfigVisibilite());
			}
		}
		return listeTrtAut;
	}


	/**
	 * @param listeTrtAut the listeTrtAut to set
	 */
	public void setListeTrtAut(List<String> listeTrtAut) {
		this.listeTrtAut = listeTrtAut;
	}
	
	/***
	 * Méthode de mise à jour du tableau du formulaire liste en effectuant une recherche sous certaines conditions 
	 * 
	 * 
	 * @return : 
	 */		
	public boolean isTimeoutElapsed(){
		
		// Récupération de la date à cet instant précis
		Date now = new Date();
		if(getDateLastSearch() != null){
			long difference = now.getTime() - getDateLastSearch().getTime();
			// Si le temps écoulé depuis la dernière recherche est inférieur au timeout, alors aucune recherche n'est à faire
			if(difference < timeoutRecherche){
				return false;
			}
		}
		
		return true;
		
				
	}
	
	/**
	 * @return the dateLastSearch
	 */
	public Date getDateLastSearch() {
		return dateLastSearch;
	}

	/**
	 * @param dateLastSearch the dateLastSearch to set
	 */
	public void setDateLastSearch(Date dateLastSearch) {
		this.dateLastSearch = dateLastSearch;
	}
	
	public void setTimeOfLastSearch() {
		
		setDateLastSearch(new Date());
	}
	
	public abstract IBaseSvco<H> getEntitySvco() throws ServiceLocatorException;
	
	public void  rechercher(ActionEvent evt) {
		// Selon le type de recherche	
		//String typeRecherche = FacesUtil.getActionAttribute(evt, "typeDeRecherche");
		String typeRecherche = defaultVue.getTypeRecherche();
		
		List<T> v$liste = new ArrayList<T>();
		
		try{
			
			// Désactivation de la pagination
			defaultVue.getEntiteRecherche().setOffset(-1);
			defaultVue.getEntiteRecherche().setMaxRow(-1);
			
			// Nombre total d'éléménts de la requete de Recherche
			//defaultVue.getTableMgr().setTotalRecherche(getEntitySvco().compterParCritere(defaultVue.getEntiteRecherche())); PAS CORRECT
			long v$total = getEntitySvco().rechercherTout(defaultVue.getEntiteRecherche()).size();
			// Définition de la plage pour la pagination
			defaultVue.getEntiteRecherche().setOffset(1);
			defaultVue.getEntiteRecherche().setMaxRow(pasPagination);
			
			//defaultVue.setListePagination(ToolBox.getListePagination(defaultVue.getTableMgr().getTotalRecherche(), pasPagination));	PAS CORRECT
			List<SelectItem> v$pagination = getListePagination(v$total, pasPagination);
			
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
	
	/**
	 * 
	 * 
	 * @param p$totalRecherche
	 * @param p$pasPagination
	 * @return
	 */
	public static List<SelectItem> getListePagination(long p$totalRecherche, int p$pasPagination){
		
		String v$libelle;
		SelectItem v$item;
		
		List<SelectItem> v$listePagination = new ArrayList<SelectItem>();
		
		long v$pages = (p$totalRecherche / p$pasPagination) + (((p$totalRecherche % p$pasPagination) == 0 )? 0:1); 
		
		for(long i=0; i< (v$pages-1); i++){
			v$libelle = "" + ((i*p$pasPagination)+1) + " - " + ((i+1)*p$pasPagination);
			v$item = new SelectItem(""+i,v$libelle);		
			v$listePagination.add(v$item);
		}
		
		long v$lastPage = v$pages - 1;
		
		if(v$lastPage >= 0 ){
			v$libelle = "" + ((v$lastPage*p$pasPagination)+1) + " - " + p$totalRecherche;
			v$item = new SelectItem(""+v$lastPage,v$libelle);		
			v$listePagination.add(v$item);
		}
		
		return v$listePagination;
	}	
	
	public String openHomePage(){
		
		String v$navigation = getHomePage();
		
		return v$navigation;
	}
	
	/**
	 * Retourne le nom de la page d'acceuil d'une entité 
	 * En Général le formulaire Liste
	 * @return
	 */
	public String getHomePage(){
		return getNamePageListe();
	}
	
	/**
	 * Retourne le nom du formulaire LISTE
	 * @return
	 */
	public String getNamePageListe(){
		return  getMemoEntite().concat("Liste");
	}
	/***
	 * Enregistre la création ou la modification d'une entité 
	 * 
	 * @return
	 */
	@SuppressWarnings("finally")
	public String enregistrer() {
		
		// Determine vers quelle page ou Formulaire l'on doit se diriger
		String v$navigation = null;
		try {
			
			// Si nous sommes en Création sur le Formulaire d'Edition
			if(! defaultVue.getNavigationMgr().getEnModification()){
													
				// Consommation de l'EJB distant pour la création
				defaultVue.setEntiteCourante((T) getEntitySvco().creer(defaultVue.getEntiteCourante()));
								
				// Raffraîchissement de l'entité courante pour des besoins de mises à jour des entités liés s'il y'en a				
				defaultVue.setEntiteCourante((T) getEntitySvco().rechercher(defaultVue.getEntiteCourante(), defaultVue.getEntiteCourante().getId()));
							
				// MAJ de la liste de Recherche
				defaultVue.getTableMgr().add(defaultVue.getEntiteCourante());					
				
				// Après un enregistrement nous retournons toujours en consultation 
				v$navigation = getMemoEntite().concat(CoreConstants.SUFFIXE_NVGT_DETAILS);
								
				FacesUtil.addInfoMessage("","CREATION_SUCCESS");
				///FacesUtil.addInfoMessage("",BundleEnum.MSG_TRT_CREATION_SUCCES.getValeur());
											
			}
			
			// Par contre Si nous sommes en modification sur le Formulaire d'Edition
			else{
													
				// Consommation de l'EJB distant pour la création 
				defaultVue.setEntiteCourante((T) getEntitySvco().modifier(defaultVue.getEntiteCourante()));
				
				// Raffraîchissement de l'entité courante pour des besoins de mises à jour des entités liés s'il y'en a				
				defaultVue.setEntiteCourante((T) getEntitySvco().rechercher(defaultVue.getEntiteCourante(), defaultVue.getEntiteCourante().getId()));
			
				// MAJ de la liste de Recherche
				defaultVue.getTableMgr().replace(defaultVue.getEntiteTemporaire(), defaultVue.getEntiteCourante());
				
				
				// Après un enregistrement nous retournons toujours en consultation 
				v$navigation = getMemoEntite().concat(CoreConstants.SUFFIXE_NVGT_DETAILS);
						
				FacesUtil.addInfoMessage("", "MODIFICATION_SUCCESS");
			
			}	
						
			// Coherence IHM avant affichage du formulaire de consultation
		}	
		
		catch (Exception e) {			
			// Aucune navigation possible
			v$navigation = null;
			
			//Message utilisateur
			FacesUtil.addWarnMessage("TRAITEMENT_ALL_ECHEC_INCONNU",
					 				 e.getMessage());
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
	
	/**
	 * Permet de naviguer vers le formulaire de Consultaton afin de consulter  les informations relatives à une entité 
	 * 
	 * @return
	 */
	public String afficher(){
		
		String v$navigation = null;
			
		// L'entité selectionné devient l'objet courant; Cela suppose que le Contexte de page est Liste
		defaultVue.setEntiteCourante(defaultVue.getTableMgr().getEntiteSelectionne());
		
		// Par simple Prudence, on dira si l'entite existe
		if(defaultVue.getEntiteCourante() != null){
			
			// Mise à jour de la navigation : Vers le formulaire de Details
			v$navigation =  getMemoEntite().concat(CoreConstants.SUFFIXE_NVGT_DETAILS);
			
			// MAJ de l'ID à display
			setIdEntityToDisplay(defaultVue.getEntiteCourante().getId());
		}
		
		
		// Retour à la page adéquate
		return v$navigation;
	}	
	
	public void setIdEntityToDisplay(String idEntityToDisplay) {
		this.idEntityToDisplay = idEntityToDisplay;
	}
	
	public void setIdEntityToDisplay(Serializable idEntityToDisplay) {
		if(idEntityToDisplay == null ) this.idEntityToDisplay = null;
		else if(idEntityToDisplay instanceof String){
			this.idEntityToDisplay = (String) idEntityToDisplay;
		}
	}	

	public String getIdEntityToDisplay() {
		return idEntityToDisplay;
	}
	
	/***
	 * Permet la selection d'une entité qui sera renseigné dans une autre 
	 * 
	 * @return
	 */
	
	public String selectionner(){
				
		// Determine vers quelle page ou formulaire l'on doit se diriger
		String v$navigation = null;
		
		
		// L'entité selectionné devient l'objet courant; Cela suppose que le Contexte de page est Liste
		if(defaultVue.getNavigationMgr().isFromListe())
			defaultVue.setEntiteCourante(defaultVue.getTableMgr().getEntiteSelectionne());
		
				
		// Par prudence l'on dira si nous sommes en Selection 
		if(defaultVue.getNavigationMgr().getEnSelection()){
			
			// Récupération du Controlleur de la source
			// Cette méthode suppose que le controleur est dans le scope session
			GeveeCtrl<H,T> v$controlleur  =  (GeveeCtrl<H,T>) FacesUtil.getSessionMapValue(defaultVue.getNavigationMgr().getSelectionBeanCtrlName());
						 			
			// Séléection multiple uniquement possible sur le formulaire liste
			 if(AbstractTableManager.SelectionModeEnum.MULTI.getValue().equals(this.getDefaultVue().getTableMgr().getSelectionMode())){
				 				 			
					// Execution de la méthode effectuant le matching des valeurs 
				 v$controlleur.setSelectedEntity(defaultVue.getTableMgr().getEntiteSelectionnes());
				 
				 	// Après la sélection multiple l'on revient en sélection simple
				 passerEnSelectionSimple();

			 }
			 
			 // Sélection unique
			 else {
				 			 				 
					// Execution de la méthode effectuant le matching des valeurs 
				 v$controlleur.setSelectedEntity(defaultVue.getEntiteCourante());
				 				 
			 }
			 
			// L'on  retourne vers le formulaire d'origine 
			v$navigation = defaultVue.getNavigationMgr().getSelectionSource();
			
			// Désactivation du mode selection 
			defaultVue.getNavigationMgr().setEnSelection(false);	
			
			//Désactivation du Filtre SBR si jamais ce dernier avait été activé
			defaultVue.getEntiteRecherche().setApplyFilter(false);
			
		}
			
		// Retour à la page adéquate
		return v$navigation;
		
	}	
	
	public   void setSelectedEntity(List<? extends BaseEntity> p$selectionListe){
	}
	
	public   void setSelectedEntity(BaseEntity p$entite){
	}
	
	/**
	 * Mettre le formulaire Liste en mode Sélection simple
	 */
	public void  passerEnSelectionSimple(){
				
		if(! AbstractTableManager.SelectionModeEnum.SINGLE.getValue().equals(defaultVue.getTableMgr().getSelectionMode())){
			
			// MAJ du tableau en mode sélection simple
			defaultVue.getTableMgr().setSelectionMode(AbstractTableManager.SelectionModeEnum.SINGLE.getValue());

			// Réinitialisation de la liste des traitements
			setListeTraitements(null);	
		}
	
	}
	
	/***
	 * Réalise une copie de l'entité en réinitialisant les champs qu'il faut
	 * @return
	 */
	public BaseEntity copie(){
		return null;		
	}
	
	/***
	 * Annule la modification ou la création d'une entité 
	 * 
	 * @return
	 */
	public String annulerEdition(){
		
		// Determine vers quelle page ou Formulaire l'on doit se diriger
		String v$navigation = null;
		
		// Restauration de l'entité temporairement sauvegardé
		defaultVue.setEntiteCourante(defaultVue.getEntiteTemporaire());
		
		// Si nous sommes parti du Formulaire Details vers le Formulaire d'Edition
		if(defaultVue.getNavigationMgr().isFromDetails()){
			
			// Alors nous retournons vers le formulaire Details
			v$navigation = getMemoEntite().concat(CoreConstants.SUFFIXE_NVGT_DETAILS);
			
			//Mise en Cohérence des IHMs avant de retourner sur le formulaire de Consultation
		}	
		
		// Sinon nous retournons vers le formulaire Liste 
		else {
			
			// Alors nous retournons vers le formulaire Liste
			v$navigation = getMemoEntite().concat(CoreConstants.SUFFIXE_NVGT_LISTE);
			
		}
		
		// Retour à la page adéquate	
		return v$navigation;
	}
	
	/***
	 * Terminer la consultation des informations sur une entité 
	 * 
	 * @return
	 */
	public String terminer(){
		
		// Determine vers quelle page ou Formulaire l'on doit se diriger
		String v$navigation = null;
		
		// Suite à l'action terminer  du formulaire Details l'on retourne sur le Formulaire Liste
		v$navigation = getNamePageListe();

		// Raffraîchissement des données de la page
		// refreshData(this); N'est plus utile grâce au raffraîchissement automatique intégrée sur les pages
		
		// Retour à la page adéquate	
		return v$navigation;
	}	
	
	/****
	 * Recherche et affiche en consultation l'element suivant relativement à l'element courant en consultation 
	 * 
	 * @param evt	: Evènement généré par l'objet graphique 
	 */	
	public void suivant(ActionEvent evt){
				
		// Taille de la liste de Recherche
		int v$taille = defaultVue.getTableMgr().getListeRecherche().size();
		
		// S'il y'a au moins 1 element dans le tableau	et que l'element courant est contenu dans le tableau
		if(v$taille > 1 && defaultVue.getTableMgr().contains(defaultVue.getEntiteCourante())){
			
			//Alors Recuperer son index 
			int index = defaultVue.getTableMgr().getIndexOfEntity(defaultVue.getEntiteCourante());

			//Calcul de l'index de l'entite suivante 
			index = (index+1)%(v$taille);
			
			// Mise à jour de l'entité courant 
			defaultVue.setEntiteCourante(defaultVue.getTableMgr().getEntityByIndex(index));			

		}	
		
	}	
	

	/****
	 * Recherche et affiche en consultation l'element precedent relativement à l'element courant en consultation 
	 * 
	 * @param evt	: Evènement généré par l'objet graphique 
	 */	
	public void  precedent(ActionEvent evt){
				
		// Taille de la liste de Recherche
		int v$taille = defaultVue.getTableMgr().getListeRecherche().size();
		
		// S'il y'a au moins 1 element dans le tableau	et que l'element courant est contenu dans le tableau
		if(v$taille > 1 && defaultVue.getTableMgr().contains(defaultVue.getEntiteCourante())){
			
			//Alors Recuperer son index 
			int index = defaultVue.getTableMgr().getIndexOfEntity(defaultVue.getEntiteCourante());
			
			
			//Calcul de l'index de l'entité precedente 
			index = (index > 0) ? (index -1)%v$taille : (v$taille-1);
			
			// Mise à jour de l'entité courant 
			defaultVue.setEntiteCourante(defaultVue.getTableMgr().getEntityByIndex(index));			
			
		}				

	}
	
	/****
	 * Recherche et affiche en consultation le premier element de la liste relativement à l'element courant en consultation 
	 * 
	 * @param evt	: Evènement généré par l'objet graphique 
	 */	
	public void  premier(ActionEvent evt){
			
		// Taille de la liste de Recherche
		int v$taille = defaultVue.getTableMgr().getListeRecherche().size();
		
		// S'il y'a au moins 1 element dans le tableau	et que l'element courant est contenu dans le tableau
		if(v$taille > 1 && defaultVue.getTableMgr().contains(defaultVue.getEntiteCourante())){
			
			
			//Calcul de l'index du premier element de la liste 
			int v$index = 0;
			
			// Mise à jour de l'entité courant 
			defaultVue.setEntiteCourante(defaultVue.getTableMgr().getEntityByIndex(v$index));			

		}				

	}
	
	/****
	 * Recherche et affiche en consultation le dernier element de la liste relativement à l'element courant en consultation 
	 * 
	 * @param evt	: Evènement généré par l'objet graphique 
	 */	
	public void  dernier(ActionEvent evt){
		
		// Les méthodes suivant & precedent peuvent etre jumelées en une méthode 
		
		// Taille de la liste de Recherche
		int v$taille = defaultVue.getTableMgr().getListeRecherche().size();
		
		// S'il y'a au moins 1 element dans le tableau	et que l'element courant est contenu dans le tableau
		if(v$taille > 1 && defaultVue.getTableMgr().contains(defaultVue.getEntiteCourante())){
			
			
			//Calcul de l'index du dernier element de la liste 
			int v$index = v$taille -1;
			
			// Mise à jour de l'entité courant 
			defaultVue.setEntiteCourante(defaultVue.getTableMgr().getEntityByIndex(v$index));			
			
		}				

	}	
	
	/***
	 * Supprime une entite 
	 * 
	 * @return
	 */
	@SuppressWarnings("finally")
	public String supprimer(){
				
		// Determine vers quelle page ou formulaire l'on doit se diriger
		String v$navigation = null;
	
		try{
			
			// L'entité selectionné devient l'objet courant; Cela suppose que le Contexte de page est Liste
			if(defaultVue.getNavigationMgr().isFromListe())
				defaultVue.setEntiteCourante(defaultVue.getTableMgr().getEntiteSelectionne());
						
			// Consommation de l'EJB distant pour la suppression
			getEntitySvco().supprimer(defaultVue.getEntiteCourante());
						
			// MAJ de la liste de Recherche
			defaultVue.getTableMgr().remove(defaultVue.getEntiteCourante());
			
			
			// Si nous sommes en Consultation ==> sur le formulaire Details
			// Donc l'entité courante est déja connue
			if(defaultVue.getNavigationMgr().isFromDetails()){
														
				// Entre Temps nous naviguons vers le formulaire Liste 		
					// Mise à jour de la navigation : Vers le formulaire Liste
				v$navigation =  getMemoEntite().concat(CoreConstants.SUFFIXE_NVGT_LISTE);			
			}
						
			FacesUtil.addInfoMessage("","Suppression reussie");
			
		}	
		catch (Exception e) {			
			// Aucune navigation possible
			v$navigation = null;
			
			//Message utilisateur
			FacesUtil.addWarnMessage("TRAITEMENT_ALL_ECHEC_INCONNU", e.getMessage());
			getLogger().error(e.getMessage(), e);
		}		finally{
			
			// Retour à la page adéquate	
			return v$navigation;
		}
										
	}
	
	/**
	 * Permet de Naviguer vers le formulaire d'Edition afin de pouvoir modifier une entité 
	 * 	
	 * @return
	 */
	public String modifier(){
		
		
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
		
		// Retour à la page adéquate
		return v$navigation;
	}	
	
}
