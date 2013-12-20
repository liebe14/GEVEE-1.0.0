package com.gss.gevee.ui.core.base;





/**
 * Cette classe encapsule la gestion de la logique de navigation entre les formulaires
 * Exemples :
 * 	-> Selection de données
 *  -> Retour à la page adéquate après création, consultation d'une entité
 * 
 */
public class AbstractNavigationManager {
	
	/**
	 * Règle de navigation permettant de retourner à la page qui est à l'origine de la selection 
	 * Exemple : Pour l'entité Ministère, si l'on est en création ou modification et qu'on selectionne la Localité 
	 * 	alors selectionSource = MnrEdition pour le navigationManager de Localité 
	 */
	private String selectionSource;
	
	/***
	 * Règle de navigation permettant determinant vers quel formulaire aller pour sélectionner un element
	 * Exemple : Pour l'entité Ministère, si l'on souhaite selectionner la localité, 
	 * 	selectionDestination = LocListe pour le navigationManager de Ministere
	 * 
	 */
	private String selectionDestination;
	
	/***
	 * Nom du controleur à l'origine de la selection 
	 * Exemple : Pour l'entité Ministère, selectionBeanCtrlName = mnrCtrl
	 */
	private String selectionBeanCtrlName;
	
	/**
	 * Nom de la propriété a mettre à jour lors de la sélection 
	 * Exemple : Dans l'entité ministère la propriété peut etre l'entité Localité (tblLoc)
	 */
	private String selectionPropertyName;
	
	/**
	 * Règle de navigation  determinant vers quel formulaire retourner après navigation vers des entités reliés
	 * Exemple : Pour l'entité Ministère, l'on pourrait consulter les Sections relatedSource = MnrListe (Si nous sommes partis du formulaire liste) 
	 */
	private String relatedSource;
	
	/**
	 * Règle de navigation  determinant vers quel formulaire aller pour consulter les entités liés 
	 * Exemple : Pour l'entité Ministère, l'on pourrait consulter les Sections relatedDestination = SctListe
	 */
	private String relatedDestination;
	
	/**
	 * Determine si l'on se trouve en selection 
	 */
	private boolean enSelection = false;
	
	/**
	 * Determine si l'on se trouve en creation (false) ou modification (true) sur le formulaire d'Edition
	 */
	private boolean enModification = false;
	
	/**
	 * Determine si l'on se trouve en creation (enModification = false) et en copie 
	 * NB :  En copie, nous sommes en création, mais en création nous ne sommes pas forcement en copie
	 */
	private boolean enCopie = false;
	

	/**
	 * Determine dans quel contexte une action a été déclenchée : A partir du formulaire Liste ou Details
	 * 	Utile pour gérer les règles de navigation 
	 */
	private String pageContexte;
	
	
	/**
	 * Constructeur par défaut
	 */
	public AbstractNavigationManager() {
		super();
		//  Auto-generated constructor stub
	}

	
	/**
	 * @return the selectionSource
	 */
	public String getSelectionSource() {
		return selectionSource;
	}


	/**
	 * @param selectionSource the selectionSource to set
	 */
	public void setSelectionSource(String selectionSource) {
		this.selectionSource = selectionSource;
	}

	/**
	 * @return the selectionDestination
	 */
	public String getSelectionDestination() {
		return selectionDestination;
	}

	/**
	 * @param selectionDestination the selectionDestination to set
	 */
	public void setSelectionDestination(String selectionDestination) {
		this.selectionDestination = selectionDestination;
	}
	
	
	/**
	 * @return the relatedSource
	 */
	public String getRelatedSource() {
		return relatedSource;
	}

	/**
	 * @param relatedSource the relatedSource to set
	 */
	public void setRelatedSource(String relatedSource) {
		this.relatedSource = relatedSource;
	}



	/**
	 * @return the relatedDestination
	 */
	public String getRelatedDestination() {
		return relatedDestination;
	}



	/**
	 * @param relatedDestination the relatedDestination to set
	 */
	public void setRelatedDestination(String relatedDestination) {
		this.relatedDestination = relatedDestination;
	}



	/**
	 * @return the selectionBeanCtrlName
	 */
	public String getSelectionBeanCtrlName() {
		return selectionBeanCtrlName;
	}


	/**
	 * @param selectionBeanCtrlName the selectionBeanCtrlName to set
	 */
	public void setSelectionBeanCtrlName(String selectionBeanCtrlName) {
		this.selectionBeanCtrlName = selectionBeanCtrlName;
	}
	

	/**
	 * @return the selectionPropertyName
	 */
	public String getSelectionPropertyName() {
		return selectionPropertyName;
	}


	/**
	 * @param selectionPropertyName the selectionPropertyName to set
	 */
	public void setSelectionPropertyName(String selectionPropertyName) {
		this.selectionPropertyName = selectionPropertyName;
	}

	/**
	 * @return the enSelection
	 */
	public boolean getEnSelection() {
		return enSelection;
	}



	/**
	 * @param enSelection the enSelection to set
	 */
	public void setEnSelection(boolean enSelection) {
		this.enSelection = enSelection;
	}

	/**
	 * @param enSelection the enSelection to set
	 */
	public void setEnSelection(String enSelection) {
		this.enSelection = Boolean.parseBoolean(enSelection);
	}



	/**
	 * @return the enModification
	 */
	public boolean getEnModification() {
		return enModification;
	}


	/**
	 * @param enCreation the enModification to set
	 */
	public void setEnModification(boolean enModification) {
		
		// Si nous sommes en modification dc pas en création
		if(enModification){
			// alors nous ne sommes pas en copie
			this.enCopie = false;
		}
			
		this.enModification = enModification;
	}


	/**
	 * @param enCopie the enCopie to set
	 */
	public void setEnCopie(boolean enCopie) {
		
		// Si nous sommes en copie
		if(enCopie){
			// alors nous sommes en création
			setEnModification(false);
		}
		this.enCopie = enCopie;
	}


	/**
	 * @return the enCopie
	 */
	public boolean getEnCopie() {
		return enCopie;
	}


	/**
	 * @return the pageContexte
	 */
	public String getPageContexte() {
		return pageContexte;
	}


	/**
	 * @param pageContexte the pageContexte to set
	 */
	public void setPageContexte(String pageContexte) {		
		this.pageContexte = pageContexte;
	}
	
	public boolean isFromListe(){
		//return getPageContexte().equals(CoreConstants.CONTEXTE_LISTE);
		return CoreConstants.CONTEXTE_LISTE.equals(getPageContexte());
	}
	
	public boolean isFromDetails(){	
		//return getPageContexte().equals(CoreConstants.CONTEXTE_DETAILS);
		return CoreConstants.CONTEXTE_DETAILS.equals(getPageContexte());
	}
	
	public boolean isFromEdiion(){	
		//return getPageContexte().equals(CoreConstants.CONTEXTE_DETAILS);
		return CoreConstants.CONTEXTE_EDITION.equals(getPageContexte());
	}	
	
}
