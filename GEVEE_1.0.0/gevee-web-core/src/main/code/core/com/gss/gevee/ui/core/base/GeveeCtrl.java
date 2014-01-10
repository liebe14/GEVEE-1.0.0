package com.gss.gevee.ui.core.base;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.component.html.HtmlInputHidden;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.richfaces.component.html.ContextMenu;
import org.richfaces.component.html.HtmlToolBar;
import org.richfaces.json.JSONArray;
import org.richfaces.json.JSONObject;

import com.gss.gevee.be.core.base.BaseEntity;
import com.gss.gevee.be.core.base.BaseLogger;
import com.gss.gevee.be.core.exception.GeveeAppException;
import com.gss.gevee.be.core.svco.base.IBaseSvco;
import com.gss.gevee.be.util.EntFichier;

public abstract class GeveeCtrl <H extends BaseEntity, T extends H>{
	
	/***
	 * Vue par d�faut d'une entit� 
	 * Doit etre instanci�e par la classe concr�te 
	 */
	public GeveeVue<T> defaultVue;
	
	private String memoEntite;
	
	/**
	 * Op�ration sp�cifique � appliquer sur l'entite 
	 * (Annuler, Rejeter, Valider, ...)
	 * 
	 */
	protected String actionSpecifique;
	
	/**
	 * @return the listeTraitements
	 */
	public abstract List<Traitement> getListeTraitements();
	
	/**
	 * Liste des traitements possibles sur l'entit�
	 */	
	private  Map<String, Traitement> mapTraitements;
	
	/**
	 * Liste des traitements possibles sur l'entit�
	 */	
	protected  List<Traitement> listeTraitements;
	
	/**
	 * Barre d'outils des Actions de base  de l'IHM g�n�r�e par la Classe MenuFactory
	 */
	protected HtmlToolBar toolbar;
	
	/**
	 * Barre d'outils des Actions de base et Standards  de l'IHM g�n�r�e par la Classe MenuFactory
	 */	
	protected HtmlToolBar menu;
	
	/**
	 * Menu Contextuel  de l'IHM g�n�r�e par la Classe MenuFactory
	 */	
	protected ContextMenu  tableMenu;
	
	/**
	 * Champ cach� sur les pages web contenant le nom du fichier de la page
	 * Tr�s utile pour d�terminer sur de quel type formulaire il s'agit (LISTE, EDITION, DETAILS)	
	 */
	protected HtmlInputHidden hidden;
	
	/**
	 * Id de l'entit� � afficher sur le formulaire DETAILS
	 */
	private String idEntityToDisplay;
	
	/**
	 * Map utile pour la gestion de l'affichage des traitements niveau IHM
	 * Cl� :  Etat de l'Entit�
	 * Valeur :	Liste des traitements possible pour cet �tat
	 * Envoy� cot� Client via JSON
	 */
	protected Map<String, List<String>> etatListeTrt;
	
	/**
	 * Liste des codes des traitements utilisateurs
	 * valeur :  "code du traitement" +  "-"  + "Configuration de la visibilit� du traitement"
	 * Envoy� cot� Client via JSON
	 */
	protected List<String> listeTrtAut;
	/****
	 * Interface  G�n�rique SVCO des entit�s 
	 * Doit etre instanci�e par la classe concr�te 
	 * 
	 * 
	 */
	protected IBaseSvco<H> entitySvco;
	
	/**
	 * Date de la derni�re recherche
	 */
	protected Date dateLastSearch;
	
	/**
	 * Timeout � utiliser pour activer une nouvelle recherche lors de l'acc�s au formulaire liste
	 */
	private static  long timeoutRecherche;
	static{
		timeoutRecherche = Long.parseLong("20")  ;
	}
	
	/**
	 * Retourne le type Class de la Classe qui h�rite du G�n�ricControlleur
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
	
	/**
	 * @return the actionSpecifique
	 */
	public String getActionSpecifique() {
		return actionSpecifique;
	}


	/**
	 * @param actionSpecifique the actionSpecifique to set
	 */
	public void setActionSpecifique(String actionSpecifique) {
		this.actionSpecifique = actionSpecifique;
	}	
	
	public String getMemoEntite() {
		
		if(memoEntite == null){
			String v$nomControlleur = getMyClass().getSimpleName();		
			memoEntite = v$nomControlleur.substring(0, (v$nomControlleur.length()	- 4/*Taille du nom du controleur priv� du suffixe Ctrl*/ ));
		}
		return memoEntite;			
	}
	
	/**
	 * Retourne le nom du Bean Manag� par JSF dans le Fichier de Configuration
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
		// G�n�ration du Menu Contextuel utilisateur
		tableMenu = new MenuFactory(getHidden().getValue().toString(), getNomManagedBean2(), getListeTraitements()).getContextMenu();
				
		return tableMenu;

	}

	/**
	 * @param tableMenu the tableMenu to set
	 */
	public void setTableMenu(ContextMenu tableMenu) {
		System.out.println("Je suis dans setTableMenu......");
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
		// Le try-catch est n�c�ssaire � cause de la gestion g�n�rique de la page introduite dans les nouveaux templates
			// Template Formulaire Liste, Edition, Details
			// Etant donn� que le <ui:param name='nomPage' value='#{managedBean.memoEntite}XXXX' est �valu� a la cr�ation de la page par le gestionnaire de facelet
			// Des exceptions peuvent survenir lors de la phase RESTORE_VIEW pour des traitements ne cr�eant pas la page
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
		// G�n�ration de la Barre d'outils des Actions de Base		
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
	 * Construit la liste des traitements de l'entit�
	 */
	public abstract void  buildListeTraitement();
	
	/**
	 * @param listeTraitements the listeTraitements to set
	 */
	public void setListeTraitements(List<Traitement> listeTraitements) {
		this.listeTraitements = listeTraitements;
	}
	
	public void reinitialiser(ActionEvent evt){
		/*R�initialisation de l'entit� de recherche*/
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
	 * G�re la pagination pour une recherche 
	 * 
	 * @param evt
	 */
	public void  paginer(ActionEvent evt){
								
		String typeRecherche = defaultVue.getTypeRecherche();
		
		int v$page = defaultVue.getTableMgr().getPage();
				
		T v$entiteRecherche = defaultVue.getEntiteRecherche();

		List<T> v$liste = new ArrayList<T>();
		
		try{
								
			// D�finition de l'offset pour la pagination
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
				
			// Mise  � jour de  la liste de recherche ==> Mise � jour du mod�le automatiquement
			defaultVue.getTableMgr().setListeRecherche(v$liste);
			
			// Si la liste de recherche est vide, pr�cisez que la recherche n'a retourn� aucun r�sultat
			if(v$liste == null || v$liste.size() == 0){
				FacesUtil.addWarnMessage("", "Aucun �l�ment trouv�");
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
	 * Ferme le formulaire d'entr�e : G�n�ralement le formulaire Liste 
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
			
			// D�sactivation du mode selection 
			defaultVue.getNavigationMgr().setEnSelection(false);
			
			// Raz de la r�gle de navigation permettant de retourner � la page d'origine
			defaultVue.getNavigationMgr().setSelectionSource(null);
			
			
		}
		else if(defaultVue.getNavigationMgr().getRelatedSource() != null){
			
			// L'on  retourne vers le formulaire d'origine 
			v$navigation = defaultVue.getNavigationMgr().getRelatedSource();

			// Raz de la r�gle de navigation permettant de retourner � la page d'origine
			defaultVue.getNavigationMgr().setRelatedSource(null);

		}
		else {
			
			// TODO A ce niveau nous ne sommes ni en selection ni en navigation 
			//Dans ce cas l'action � r�aliser est de fermer le formulaire et de rentrer � la page d'acceuil
			//FacesUtil.addWarnMessage(Constantes.ID_BARRE_MSG,"Fermer  : ", "Retour a la page d'acceuil ");
			v$navigation = "AcceuilDeBase";
		}		
		
		// Retour � la page ad�quate	
		return v$navigation;	
	}	
	
	/**
	 * @return the menu
	 */
	public HtmlToolBar getMenu() {
						
		// G�n�ration du Menu Actions de Base et Sp�cifiques
		menu = new MenuFactory(getHidden().getValue().toString(), getNomManagedBean2(), getListeTraitements()).getMenu();
		
		// Injection du Map Etat Liste des traitements 
		FacesUtil.setSessionMapValue("mapEtatCodeTrt", getJsonMapEtatTrt());
		
		// Injection du Sp�cial Id de l'entit� courante 
		//FacesUtil.setSessionMapValue("specialKey", defaultVue.getSpecialKey(v$page));
		
		return menu;			
	}
	
	private String getJsonMapEtatTrt(){
		
		Map v$mapEtatCodeTrt  = new HashMap();
		
		// Il faut d'abord convertir les structures de donn�es en Json avant de les ajouter dans le grand map
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
				 *  Concat�nation du code du traitement � la Configuration de la visibilit�
				 *  Utilis� niveau Client pour l'affichage des traitements 
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
	 * M�thode de mise � jour du tableau du formulaire liste en effectuant une recherche sous certaines conditions 
	 * 
	 * 
	 * @return : 
	 */		
	public boolean isTimeoutElapsed(){
		
		// R�cup�ration de la date � cet instant pr�cis
		Date now = new Date();
		if(getDateLastSearch() != null){
			long difference = now.getTime() - getDateLastSearch().getTime();
			// Si le temps �coul� depuis la derni�re recherche est inf�rieur au timeout, alors aucune recherche n'est � faire
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
			
			// D�sactivation de la pagination
			defaultVue.getEntiteRecherche().setOffset(-1);
			defaultVue.getEntiteRecherche().setMaxRow(-1);
			
			// Nombre total d'�l�m�nts de la requete de Recherche
			//defaultVue.getTableMgr().setTotalRecherche(getEntitySvco().compterParCritere(defaultVue.getEntiteRecherche())); PAS CORRECT
			long v$total = getEntitySvco().rechercherTout(defaultVue.getEntiteRecherche()).size();
			// D�finition de la plage pour la pagination
			defaultVue.getEntiteRecherche().setOffset(1);
			defaultVue.getEntiteRecherche().setMaxRow(pasPagination);
			
			//defaultVue.setListePagination(ToolBox.getListePagination(defaultVue.getTableMgr().getTotalRecherche(), pasPagination));	PAS CORRECT
			List<SelectItem> v$pagination = getListePagination(v$total, pasPagination);
			
			/*
			 * TODO Modification Temporaire � r�viser
			 * La recherchepar code  = recherche par crit�re
			 */				
			if(typeRecherche.equals("ID")){
								
				v$liste = rechercherParCritere(defaultVue.getEntiteRecherche());			
			}
			
			else if(typeRecherche.equals("CRITERE")){
				v$liste = rechercherParCritere(defaultVue.getEntiteRecherche());
			}
			else if(typeRecherche.equals("TOUT")) {
								
				v$liste = rechercherTout(defaultVue.getEntiteRecherche());
				// R�initialisation de la zone de recherche
				reinitialiser(evt);
			}
			
			// Mise  � jour de  la liste de recherche ==> Mise � jour du mod�le automatiquement
			defaultVue.getTableMgr().setListeRecherche(v$liste);
			
			// Mise � jour des informations de pagination dans le Gestionnaire de table
			defaultVue.getTableMgr().setTotalRecherche(v$total);
			defaultVue.setListePagination(v$pagination);
			//defaultVue.getTableMgr().setListeRecherche(v$liste);
			
			// Si la liste de recherche est vide, pr�cisez que la recherche n'a retourn� aucun r�sultat
			if(v$liste == null || v$liste.size() == 0){
				FacesUtil.addWarnMessage("", "Aucun �l�ment trouv�");
			}
						
			// Mise � jour de la date de la derni�re recherche
			setTimeOfLastSearch();
		}
				
		catch (GeveeAppException e) {			
			FacesUtil.addWarnMessage("Echec lors de l'ex�cution du traitement",
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
	 * Retourne le nom de la page d'acceuil d'une entit� 
	 * En G�n�ral le formulaire Liste
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
	 * Enregistre la cr�ation ou la modification d'une entit� 
	 * 
	 * @return
	 */
	@SuppressWarnings("finally")
	public String enregistrer() {
		
		// Determine vers quelle page ou Formulaire l'on doit se diriger
		String v$navigation = null;
		try {
			
			// Si nous sommes en Cr�ation sur le Formulaire d'Edition
			if(! defaultVue.getNavigationMgr().getEnModification()){
													
				// Consommation de l'EJB distant pour la cr�ation
				defaultVue.setEntiteCourante((T) getEntitySvco().creer(defaultVue.getEntiteCourante()));
								
				// Raffra�chissement de l'entit� courante pour des besoins de mises � jour des entit�s li�s s'il y'en a				
				defaultVue.setEntiteCourante((T) getEntitySvco().rechercher(defaultVue.getEntiteCourante(), defaultVue.getEntiteCourante().getId()));
							
				// MAJ de la liste de Recherche
				defaultVue.getTableMgr().add(defaultVue.getEntiteCourante());					
				
				// Apr�s un enregistrement nous retournons toujours en consultation 
				v$navigation = getMemoEntite().concat(CoreConstants.SUFFIXE_NVGT_DETAILS);
								
				FacesUtil.addInfoMessage("","CREATION_SUCCESS");
											
			}
			
			// Par contre Si nous sommes en modification sur le Formulaire d'Edition
			else{
													
				// Consommation de l'EJB distant pour la cr�ation 
				defaultVue.setEntiteCourante((T) getEntitySvco().modifier(defaultVue.getEntiteCourante()));
				
				// Raffra�chissement de l'entit� courante pour des besoins de mises � jour des entit�s li�s s'il y'en a				
				defaultVue.setEntiteCourante((T) getEntitySvco().rechercher(defaultVue.getEntiteCourante(), defaultVue.getEntiteCourante().getId()));
			
				// MAJ de la liste de Recherche
				defaultVue.getTableMgr().replace(defaultVue.getEntiteTemporaire(), defaultVue.getEntiteCourante());
				
				
				// Apr�s un enregistrement nous retournons toujours en consultation 
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
			
			// Retour � la page ad�quate	
			return v$navigation;
		}	
		
	}
	
	/**
	 * Permet de naviguer vers le formulaire d'Edition afin de creer une nouvelle entit�
	 * 
	 * @return
	 */
	public String ajouter(){
		
		
		// Determine vers quelle page ou formulaire l'on doit se diriger
		String v$navigation = null;
		
		// Mise � jour de l'entit� courante selon le contexte du Formulaire 
		if(defaultVue.getNavigationMgr().isFromListe())
			defaultVue.setEntiteCourante(defaultVue.getTableMgr().getEntiteSelectionne());		
		
		// Sauvegarde de l'entit� courante 
		defaultVue.setEntiteTemporaire(defaultVue.getEntiteCourante());
		
		// L'entit� courante devient vierge afin qu'aucunes donn�es ne s'affichent sur l'interface graphique 
		defaultVue.setEntiteCourante(defaultVue.getNewEntity());
		
		// Mise � jour du Contexte : En cr�ation 
		defaultVue.getNavigationMgr().setEnModification(false);
		
		// Mise � jour de la navigation : Vers le formulaire d'Edition
		v$navigation =  getMemoEntite().concat("Edition");
		
		// Retour � la page ad�quate
		return v$navigation;
	}	
	
	/**
	 * Permet de naviguer vers le formulaire de Consultaton afin de consulter  les informations relatives � une entit� 
	 * 
	 * @return
	 */
	public String afficher(){
		
		String v$navigation = null;
			
		// L'entit� selectionn� devient l'objet courant; Cela suppose que le Contexte de page est Liste
		defaultVue.setEntiteCourante(defaultVue.getTableMgr().getEntiteSelectionne());
		
		// Par simple Prudence, on dira si l'entite existe
		if(defaultVue.getEntiteCourante() != null){
			
			// Mise � jour de la navigation : Vers le formulaire de Details
			v$navigation =  getMemoEntite().concat(CoreConstants.SUFFIXE_NVGT_DETAILS);
			
			// MAJ de l'ID � display
			setIdEntityToDisplay(defaultVue.getEntiteCourante().getId());
		}
		
		
		// Retour � la page ad�quate
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
	 * Permet la selection d'une entit� qui sera renseign� dans une autre 
	 * 
	 * @return
	 */
	
	public String selectionner(){
				
		// Determine vers quelle page ou formulaire l'on doit se diriger
		String v$navigation = null;
		
		
		// L'entit� selectionn� devient l'objet courant; Cela suppose que le Contexte de page est Liste
		if(defaultVue.getNavigationMgr().isFromListe())
			defaultVue.setEntiteCourante(defaultVue.getTableMgr().getEntiteSelectionne());
		
				
		// Par prudence l'on dira si nous sommes en Selection 
		if(defaultVue.getNavigationMgr().getEnSelection()){
			
			// R�cup�ration du Controlleur de la source
			// Cette m�thode suppose que le controleur est dans le scope session
			GeveeCtrl<H,T> v$controlleur  =  (GeveeCtrl<H,T>) FacesUtil.getSessionMapValue(defaultVue.getNavigationMgr().getSelectionBeanCtrlName());
						 			
			// S�l�ection multiple uniquement possible sur le formulaire liste
			 if(AbstractTableManager.SelectionModeEnum.MULTI.getValue().equals(this.getDefaultVue().getTableMgr().getSelectionMode())){
				 				 			
					// Execution de la m�thode effectuant le matching des valeurs 
				 v$controlleur.setSelectedEntity(defaultVue.getTableMgr().getEntiteSelectionnes());
				 
				 	// Apr�s la s�lection multiple l'on revient en s�lection simple
				 passerEnSelectionSimple();

			 }
			 
			 // S�lection unique
			 else {
				 			 				 
					// Execution de la m�thode effectuant le matching des valeurs 
				 v$controlleur.setSelectedEntity(defaultVue.getEntiteCourante());
				 				 
			 }
			 
			// L'on  retourne vers le formulaire d'origine 
			v$navigation = defaultVue.getNavigationMgr().getSelectionSource();
			
			// D�sactivation du mode selection 
			defaultVue.getNavigationMgr().setEnSelection(false);	
			
			//D�sactivation du Filtre SBR si jamais ce dernier avait �t� activ�
			defaultVue.getEntiteRecherche().setApplyFilter(false);
			
		}
			
		// Retour � la page ad�quate
		return v$navigation;
		
	}	
	
	public   void setSelectedEntity(List<? extends BaseEntity> p$selectionListe){
	}
	
	public   void setSelectedEntity(BaseEntity p$entite){
	}
	
	/**
	 * Mettre le formulaire Liste en mode S�lection simple
	 */
	public void  passerEnSelectionSimple(){
				
		if(! AbstractTableManager.SelectionModeEnum.SINGLE.getValue().equals(defaultVue.getTableMgr().getSelectionMode())){
			
			// MAJ du tableau en mode s�lection simple
			defaultVue.getTableMgr().setSelectionMode(AbstractTableManager.SelectionModeEnum.SINGLE.getValue());

			// R�initialisation de la liste des traitements
			setListeTraitements(null);	
		}
	
	}
	
	/***
	 * R�alise une copie de l'entit� en r�initialisant les champs qu'il faut
	 * @return
	 */
	public BaseEntity copie(){
		return null;		
	}
	
	/***
	 * Annule la modification ou la cr�ation d'une entit� 
	 * 
	 * @return
	 */
	public String annulerEdition(){
		
		// Determine vers quelle page ou Formulaire l'on doit se diriger
		String v$navigation = null;
		
		// Restauration de l'entit� temporairement sauvegard�
		defaultVue.setEntiteCourante(defaultVue.getEntiteTemporaire());
		
		// Si nous sommes parti du Formulaire Details vers le Formulaire d'Edition
		if(defaultVue.getNavigationMgr().isFromDetails()){
			
			// Alors nous retournons vers le formulaire Details
			v$navigation = getMemoEntite().concat(CoreConstants.SUFFIXE_NVGT_DETAILS);
			
			//Mise en Coh�rence des IHMs avant de retourner sur le formulaire de Consultation
		}	
		
		// Sinon nous retournons vers le formulaire Liste 
		else {
			
			// Alors nous retournons vers le formulaire Liste
			v$navigation = getMemoEntite().concat(CoreConstants.SUFFIXE_NVGT_LISTE);
			
		}
		
		// Retour � la page ad�quate	
		return v$navigation;
	}
	
	/***
	 * Terminer la consultation des informations sur une entit� 
	 * 
	 * @return
	 */
	public String terminer(){
		
		// Determine vers quelle page ou Formulaire l'on doit se diriger
		String v$navigation = null;
		
		// Suite � l'action terminer  du formulaire Details l'on retourne sur le Formulaire Liste
		v$navigation = getNamePageListe();

		// Raffra�chissement des donn�es de la page
		// refreshData(this); N'est plus utile gr�ce au raffra�chissement automatique int�gr�e sur les pages
		
		// Retour � la page ad�quate	
		return v$navigation;
	}	
	
	/****
	 * Recherche et affiche en consultation l'element suivant relativement � l'element courant en consultation 
	 * 
	 * @param evt	: Ev�nement g�n�r� par l'objet graphique 
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
			
			// Mise � jour de l'entit� courant 
			defaultVue.setEntiteCourante(defaultVue.getTableMgr().getEntityByIndex(index));			

		}	
		
	}	
	

	/****
	 * Recherche et affiche en consultation l'element precedent relativement � l'element courant en consultation 
	 * 
	 * @param evt	: Ev�nement g�n�r� par l'objet graphique 
	 */	
	public void  precedent(ActionEvent evt){
				
		// Taille de la liste de Recherche
		int v$taille = defaultVue.getTableMgr().getListeRecherche().size();
		
		// S'il y'a au moins 1 element dans le tableau	et que l'element courant est contenu dans le tableau
		if(v$taille > 1 && defaultVue.getTableMgr().contains(defaultVue.getEntiteCourante())){
			
			//Alors Recuperer son index 
			int index = defaultVue.getTableMgr().getIndexOfEntity(defaultVue.getEntiteCourante());
			
			
			//Calcul de l'index de l'entit� precedente 
			index = (index > 0) ? (index -1)%v$taille : (v$taille-1);
			
			// Mise � jour de l'entit� courant 
			defaultVue.setEntiteCourante(defaultVue.getTableMgr().getEntityByIndex(index));			
			
		}				

	}
	
	/****
	 * Recherche et affiche en consultation le premier element de la liste relativement � l'element courant en consultation 
	 * 
	 * @param evt	: Ev�nement g�n�r� par l'objet graphique 
	 */	
	public void  premier(ActionEvent evt){
			
		// Taille de la liste de Recherche
		int v$taille = defaultVue.getTableMgr().getListeRecherche().size();
		
		// S'il y'a au moins 1 element dans le tableau	et que l'element courant est contenu dans le tableau
		if(v$taille > 1 && defaultVue.getTableMgr().contains(defaultVue.getEntiteCourante())){
			
			
			//Calcul de l'index du premier element de la liste 
			int v$index = 0;
			
			// Mise � jour de l'entit� courant 
			defaultVue.setEntiteCourante(defaultVue.getTableMgr().getEntityByIndex(v$index));			

		}				

	}
	
	/****
	 * Recherche et affiche en consultation le dernier element de la liste relativement � l'element courant en consultation 
	 * 
	 * @param evt	: Ev�nement g�n�r� par l'objet graphique 
	 */	
	public void  dernier(ActionEvent evt){
		
		// Les m�thodes suivant & precedent peuvent etre jumel�es en une m�thode 
		
		// Taille de la liste de Recherche
		int v$taille = defaultVue.getTableMgr().getListeRecherche().size();
		
		// S'il y'a au moins 1 element dans le tableau	et que l'element courant est contenu dans le tableau
		if(v$taille > 1 && defaultVue.getTableMgr().contains(defaultVue.getEntiteCourante())){
			
			
			//Calcul de l'index du dernier element de la liste 
			int v$index = v$taille -1;
			
			// Mise � jour de l'entit� courant 
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
			
			// L'entit� selectionn� devient l'objet courant; Cela suppose que le Contexte de page est Liste
			if(defaultVue.getNavigationMgr().isFromListe())
				defaultVue.setEntiteCourante(defaultVue.getTableMgr().getEntiteSelectionne());
						
			// Consommation de l'EJB distant pour la suppression
			getEntitySvco().supprimer(defaultVue.getEntiteCourante());
						
			// MAJ de la liste de Recherche
			defaultVue.getTableMgr().remove(defaultVue.getEntiteCourante());
			
			
			// Si nous sommes en Consultation ==> sur le formulaire Details
			// Donc l'entit� courante est d�ja connue
			if(defaultVue.getNavigationMgr().isFromDetails()){
														
				// Entre Temps nous naviguons vers le formulaire Liste 		
					// Mise � jour de la navigation : Vers le formulaire Liste
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
			
			// Retour � la page ad�quate	
			return v$navigation;
		}
										
	}
	
	/**
	 * Permet de Naviguer vers le formulaire d'Edition afin de pouvoir modifier une entit� 
	 * 	
	 * @return
	 */
	public String modifier(){
		
		
		// Determine vers quelle page ou formulaire l'on doit se diriger
		String v$navigation = null;
			
		// Mise � jour du Contexte : En Modification 
		defaultVue.getNavigationMgr().setEnModification(true);
		
		// Mise � jour de l'entit� courante selon le contexte du Formulaire 
		if(defaultVue.getNavigationMgr().isFromListe())
			defaultVue.setEntiteCourante(defaultVue.getTableMgr().getEntiteSelectionne());
		
		
		// Sauvegarde de l'entit� courante 
		defaultVue.setEntiteTemporaire(defaultVue.getEntiteCourante());
		
		// Clone de l'Entit� courante avant Modification
		defaultVue.setEntiteCourante(defaultVue.clone(defaultVue.getEntiteCourante()));
		
							
		// Si nous sommes en Consultation ==> sur le formulaire Details
		// Donc l'entit� courante est d�ja connue
		if(defaultVue.getNavigationMgr().isFromDetails()){
									
			// Mise � jour de la navigation : Vers le formulaire d'Edition
			v$navigation =  getMemoEntite().concat(CoreConstants.SUFFIXE_NVGT_EDITION);			
		}
		
		// Par contre si nous sommes sur le formulaire Liste 
		else if(defaultVue.getNavigationMgr().isFromListe()){
						
			// Par simple Prudence, on dit si l'entite existe
			if(defaultVue.getEntiteCourante() != null){
												
				// Mise � jour de la navigation : Vers le formulaire d'Edition
				v$navigation =  getMemoEntite().concat(CoreConstants.SUFFIXE_NVGT_EDITION);				
			}
		}
		
		// Retour � la page ad�quate
		return v$navigation;
	}	
	/**
	 * Permet de naviguer vers les entit�s li�s d'une entit� donn�e 
	 * C'est une implementation de la logique de relation Merise (1 � 1, 1 � n, ...) entre les entit�s
	 * Exemple: Pour 1 Ministere l'on pourrait avoir la liste de ses Sections ou alors pour une Section voir le Ministere correspondant
	 * 
	 * @return
	 */
	public String gotoRelatedEntity() {
			
		/*
		 * L'impl�mentation ci dessous doit �tre ex�cut� dans chaque red�finiton de la m�thode
		 */		 
										
		// Mise � jour de l'entit� courante selon le contexte du Formulaire 
		defaultVue.setEntiteCouranteOfPageContext();
		
		// Determine vers quelle page ou Formulaire l'on doit se diriger
		String v$navigation = defaultVue.getNavigationMgr().getRelatedDestination();

		return v$navigation;
				
	}	
	
	/**
	 * Ensemble de traitements de permettant de mettre les IHMs dans l'�tat ad�quat par rapport � l'entit� courante
	 * Ces traitements sont � priori communs aux actions  AJOUT, MODIFICATION, COPIE, AFFICHAGE
	 * 
	 */
	public void coherenceIHM(){
		
	}
	
	/***
	 * M�thode g�n�rique devant la plupart du temps etre red�finie par la classe concr�te 
	 * Utiliser par tous les boutons ou lien permettant de faire la s�lection 
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public  String makeSelection(){
				
		/**
		 * Cette impl�mentation est basique 
		 * Il sera souvent probable de mettre le formulaire de destination dans un contexte particulier 
		 * Dans ce cas cette m�thode devra etre red�finie 	
		 */		
		
		// Determine vers quelle page ou Formulaire l'on doit se diriger
		String v$navigation = null;
				
		// R�cup�ration du formulaire de destination 
		v$navigation = defaultVue.getNavigationMgr().getSelectionDestination();
		
		
		/*
		 * R�cuperation du controleur 
		 * NB: 
		 * 	1-Cette m�thode suppose que le controleur est bel et bien dans le Scope Session
		 * 	2-Par ailleurs il devrait normalement d�ja exist� du fait du passage de param�tres dans la page web
		 */
		GeveeCtrl<BaseEntity, BaseEntity> v$controleur  =    (GeveeCtrl<BaseEntity, BaseEntity>) FacesUtil.getSessionMapValue(GeveeToolBox.getManagedBeanName(v$navigation));
		
		
		// Raffra�chissement des donn�es de la page
		refreshData(v$controleur);
							
		// Retour � la page ad�quate	
		return v$navigation;			
		
	}
	
	/**
	 * 	
	 * Raffra�chie les donn�es d'un formulaire � partir de l'entit� de recherche 
	 * @param p$controleur	: Contoleur dont l'on veut raffra�chir les donn�es
	 */
	@SuppressWarnings("unchecked")
	public void refreshData(GeveeCtrl p$controleur){
		
		try {			
			p$controleur.naviguer(p$controleur.getDefaultVue().getEntiteRecherche());	
		} 
		catch (Exception e) {
			getLogger().error(e.getMessage(), e);
		}
	}
	
	/***
	 * Effectue une recherche par crit�re pour la navigation vers cette entit�
	 * Cette impl�mentation int�gre la pagination 
	 * M�thode appel� par des controleurs d�sireux de naviguer vers cette entit� 
	 * 
	 * @param p$critere : Crit�re de recherche
	 * @throws CframeException 
	 * @throws ServiceLocatorException 
	 */		
	public void  naviguer(T p$critere) throws ServiceLocatorException, Exception {
					
		List<T> v$liste = new ArrayList<T>();
		
//		try{

			//defaultVue.getTableMgr().setTotalRecherche(0); PAS CORRECT
			
			// D�sactivation de la pagination
			p$critere.setOffset(-1);
			p$critere.setMaxRow(-1);
			
			// Nombre total d'�l�m�nts de la requete de Recherche
			//defaultVue.getTableMgr().setTotalRecherche(getEntitySvco().compterParCritere(defaultVue.getEntiteRecherche())); PAS CORRECT
			long v$total = getEntitySvco().rechercherParCritere(p$critere).size();
			
			// D�finition de la plage pour la pagination
			p$critere.setOffset(1);
			p$critere.setMaxRow(pasPagination);
			
			// Mise � jour de la liste de pagination 
			//defaultVue.setListePagination(ToolBox.getListePagination(defaultVue.getTableMgr().getTotalRecherche(), pasPagination));	PAS CORRECT
			List<SelectItem> v$pagination = getListePagination(v$total, pasPagination);
							
			v$liste = rechercherParCritere(p$critere);
			
			// MAJ du crit�re de recherche
			defaultVue.setEntiteRecherche(p$critere);
			
			// Mise  � jour du Gestionnaire de table
			defaultVue.getTableMgr().setTotalRecherche(v$total);
			defaultVue.setListePagination(v$pagination);
			defaultVue.getTableMgr().setListeRecherche(v$liste);
			
			// Mise � jour de la date de la derni�re recherche
			setTimeOfLastSearch();
			
	}	
	
	/**
	 * Permet de cr�er le r�pertoire temporaires dans lequel stock� le fichier � pr�visualiser
	 * 
	 * @param p$fichier		: Entit� fichier � g�n�rer pour pr�visualisation
	 * @param fileExtension	: Extension du fichier � g�n�rer
	 * @return
	 * @throws Exception 
	 */
	protected  String preview(EntFichier p$fichier, String fileExtension) throws Exception{
		
		// Determine vers quelle page ou formulaire l'on doit se diriger
		String v$navigation = null;
		
		String generatedFileName = "";
		
		// Si le fichier existe 
		if(p$fichier != null){
			
			//R�cup�ration de l'ID de la session
			String sessionId = ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false)).getId();
			
			//Creation du repertoire Reports devant contenir tous les �tats
			String reportsPath = ((ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext())			
									.getRealPath(File.separator+"Reports");	// R�pertoire temporaire de cr�ation des fichiers
			
			File reportsDir = new File(reportsPath);
			if(reportsDir.mkdir()) {
				//System.out.println("le repertoire des etats a bien �t� cr��");
			}
			else {
				//System.out.println("le repertoire des etats existe peut-�tre d�j�, il n'a pas �t� cr��");
				//throw new Exception("Echec de la Pr�visualisation de fichier =====> Impossible de cr�er le repertoire temporaire Reports");
			
			}
			
			//Cr�ation dans le repertoire Reports, du repertoire temporaire correspondant � la session courante 
			String sessionDirPath = reportsPath+File.separator+sessionId;
			File v$sessionDir = new File(sessionDirPath);	
			if(v$sessionDir.mkdir()) {
				//System.out.println("le repertoire de session "+sessionDirPath+" a bien �t� cr��");
			}
			else {
				//System.out.println("le repertoire de session "+sessionDirPath+" existe peut-�tre d�j�, il n'a pas �t� cr��");	
				//throw new Exception("Echec de la Pr�visualisation de fichier =====> Impossible de cr�er le repertoire temporaire correspondant � la session courante de l'utilisateur");
			}
			
			v$sessionDir.deleteOnExit();
			
			//Cr�ation du fichier temporaire qui permettra d'obtenir le nom du fichier (temporaire) � donner � l'�tat
			File generatedFile;
			try {
				String extension = "."+fileExtension;
				generatedFile = File.createTempFile("Eta", extension,  v$sessionDir);
				generatedFile.deleteOnExit();
				generatedFileName = generatedFile.getName();
				//String generatedFileAbsPath = generatedFile.getAbsolutePath(); //chemin absolu, pourra �tre utilis� � souhait
							
				p$fichier.setUri(sessionDirPath+File.separator+generatedFileName);
			} 
			catch (IOException e) {
				throw e;
			}			
									
			// Download du Fichier		
			try {
			 
				 //String path = EntFichier.createTempFile(v$fichier);
				 String path = EntFichier.writeFile(p$fichier);
				 
				 // Chemin relatif (par rapport � la position de la ViewDocument) du fichier pdf g�n�r�
				 path = "../../Reports/"+sessionId+"/"+generatedFileName;
				 
				 //System.out.println("chemin du fichier g�n�r� : "+path);
				 
				 // Injection du chemin relatif du fichier g�n�r� dans le scope session
				 FacesUtil.setSessionMapValue("fileToPreviewPath", path);

				 // Injection de la r�gle de navigation connduisant � la page � partir de laquelle se fait la pr�visualisation
				 String v$nomPage = getHidden().getValue().toString();
				 if(v$nomPage.contains(CoreConstants.SUFFIXE_NVGT_EDITION))
					 v$nomPage = v$nomPage.replace(CoreConstants.SUFFIXE_NVGT_EDITION, CoreConstants.SUFFIXE_NVGT_DETAILS);
				 FacesUtil.setSessionMapValue("pageBeforePreview", v$nomPage);
				 
				 // Navigation vers la page de pr�visualisation du document
				 v$navigation = "ViewDocument";
				 
				 return v$navigation;
			 } 
			 catch (GeveeAppException e) {
				throw e;
			 } 
			
		}
		else{
			throw new Exception("generer ordre transport");
		}

		
	}	


}
