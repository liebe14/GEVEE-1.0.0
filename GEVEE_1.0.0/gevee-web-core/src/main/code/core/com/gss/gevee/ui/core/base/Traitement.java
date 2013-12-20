package com.gss.gevee.ui.core.base;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author lkamhoua
 *
 */
public class Traitement {
	
	/**
	 * Type de navigation
	 */
	public enum EnmTypeNavigation{
		VERS_FORMULAIRE_LISTE,
		
		VERS_FORMULAIRE_DETAIL
	}
	
	/**
	 * Type de modal panel
	 */
	public enum EnmTypeModalPanel{
		AUCUN(MODAL_NO),
		CONFIRMATION(MODAL_SIMPLE),
		MOTIF(MODAL_MOTIF),
		IMPRESSION("mpnl_impression"),
		FICHIER(MODAL_FILE),
		PARTICULIER(MODAL_SPECIAL);
		
		private final String modalId;
		
		private EnmTypeModalPanel(String modalId){
			this.modalId  = modalId;
		}
		
		public String getModalId(){
			return modalId;
		}
	}
	
	public static final String PROGRESS_NO = null;
	public static final String PROGRESS_SIMPLE = "stus_actions";
	public static final String PROGRESS_SPECIAL = "";
	
	@Deprecated
	public static final String STANDARD = "B";
	@Deprecated
	public static final String SPECIFIQUE = "S";
	@Deprecated
	public static final String NAVIGATION = "N";
	
	@Deprecated
	public static final String MODAL_NO = "0";
	@Deprecated
	public static final String MODAL_SIMPLE = "1";
	@Deprecated
	public static final String MODAL_MOTIF = "mpnl_motif";	// Code prédéfini du modal panel standard de gestion des motifs
	@Deprecated
	public static final String MODAL_FILE = "mpnl_file";	// Code prédéfini du modal panel standard d'upload de fichiers
	@Deprecated
	public static final String MODAL_SPECIAL = "4";
	
	
	public static final String RERENDER_NO = null;
	public static final String RERENDER_MAIN_PANEL = "pnel_main,actionForm";
	//public static final String RERENDER_MODAL_FILE = "mpnl_file";
	public static  		String RERENDER_MODAL_PANEL = "CODE_MODAL_PANEL";		// doit être égal à la propriété modalPanel (Mis implicitement à jour lors du setModalPanel)
	public static final String RERENDER_TABLE = "pnel_table,actionForm";

	
	public static final String METHODE_AUCUNE = null;
	public static final String METHODE_ACTION_SPCF = "executerActionSpecifique";
	public static final String METHODE_SET_MODAL = "avantExecutionModal";
	public static final String METHODE_NAVIGATION = "gotoRelatedEntity";
	
	public static final int IHM_LISTE = 0;
	public static final int IHM_CONTEXT = 1;
	public static final int IHM_DETAILS = 2;
	public static final int IHM_EDITION = 3;
	
	/**
	 * 
	 */
	public static final String SEPERATEUR_1 = "-";
	public static final String SEPERATEUR_3 = "_";
	

	/*
	 * Traitement(Type, Code, Libelle, Commentaire, methode, configIHM [LCDE] , 
	 * visibilité ['tenir compte du droit de l'utilisateur(1) ou pas(0)',
	 * 			   'tenir compte de état entité(1) ou pas(0)',
	 * 			   'nécessite qu'un élément soit sélectionné(2) ou nécessite que la liste de résultats ne soit pas vide(1) ou ne tient pas compte de ça(0)'] 
	 * modalType, modalMessage, progressBar, raccourci clavier, index, reRender, image) 
	 */	
	
	
	private  String type;
	private  String codeEntite;
	private  String realCodeTraitement;
	private  String code;
	private  String libelle = "";
	private  String description = "";
	private  String methode;
	private  boolean[] configIHM = new boolean[]{true,true,true,false};
	private  String configVisibilite = "112";
	private  String modalType = Traitement.MODAL_SIMPLE;
	private  String modalMsg = "";
	private  String progressBar = Traitement.PROGRESS_SIMPLE;
	private  String clavier = "";
	private  String index = "";
	private  String reRender = RERENDER_MAIN_PANEL;
	private  String image = "";
	

	
	private String ctrlDestination;	// Nom du controlleur de la destination dans le cas ou le traitement est une navigation
		
	private String rendered;
	private boolean renderedBool = true;
	
	private String modalPanel;
	
	
	public Traitement() {

	}	
	
	/**
	 * Contructeur des traitements standards & spécifiques
	 * 
	 * @param type
	 * @param code
	 * @param libelle
	 * @param description
	 * @param methode
	 * @param configIHM
	 * @param configVisibilite
	 * @param modalType
	 * @param modalMsg
	 * @param progressBar
	 * @param clavier
	 * @param index
	 * @param reRender
	 * @param image
	 */
	@Deprecated
	public Traitement(String type, String code, String libelle, String description, String methode, boolean[] configIHM, String configVisibilite, String modalType, String modalMsg ,
			String progressBar, String clavier, String index, String reRender, String image){
				
		setType(type);
		setCode(code);
		setLibelle(libelle);
		setDescription(description);
		setMethode(methode);
		setConfigIHM(configIHM);
		setConfigVisibilite(configVisibilite);
		setModalType(modalType);
		setModalMsg(modalMsg);
		setProgressBar(progressBar);
		setClavier(clavier);
		setIndex(index);
		setReRender(reRender);
		setImage(image);

	}
	
	
	/**
	 * Contructeur des traitements standards & spécifiques
	 * 
	 * @param type
	 * @param codeEntite
	 * @param codeTraitement
	 * @param libelle
	 * @param description
	 * @param methode
	 * @param configIHM
	 * @param configVisibilite
	 * @param modalType
	 * @param modalMsg
	 * @param progressBar
	 * @param clavier
	 * @param index
	 * @param reRender
	 * @param image
	 */
	public Traitement(String type, String codeEntite, String realCodeTraitement, String libelle, String description, String methode, boolean[] configIHM, String configVisibilite, String modalType, String modalMsg ,
			String progressBar, String clavier, String index, String reRender, String image){
				
		setType(type);
		setCodeEntite(codeEntite);
		setRealCodeTraitement(realCodeTraitement);
		setLibelle(libelle);
		setDescription(description);
		setMethode(methode);
		setConfigIHM(configIHM);
		setConfigVisibilite(configVisibilite);
		setModalType(modalType);
		setModalMsg(modalMsg);
		setProgressBar(progressBar);
		setClavier(clavier);
		setIndex(index);
		setReRender(reRender);
		setImage(image);
		
		setCode(codeEntite, realCodeTraitement);
	}	
	
	
	/**
	 * Contructeur pour les traitements de navigation
	 * 
	 * @param type
	 * @param code
	 * @param libelle
	 * @param description
	 * @param methode
	 * @param configIHM
	 * @param ctrlDestination
	 * @param progressBar
	 * @param clavier
	 * @param index
	 */
	@Deprecated
	public Traitement(String type, String codeEntite, String libelle, String description, String methode, boolean[] configIHM, 
			String ctrlDestination, String progressBar, String clavier, String index){
							
		setType(type);
		setCodeEntite(codeEntite);
		setLibelle(libelle);
		setDescription(description);
		setMethode(methode);
		setConfigIHM(configIHM);
		setCtrlDestination(ctrlDestination);
		setProgressBar(progressBar);
		setClavier(clavier);
		setIndex(index);
		
		setModalType(MODAL_NO);
		setConfigVisibilite("002");	
	}	
	
	/**
	 * Contructeur pour les traitements de navigation
	 * 
	 * @param type
	 * @param codeEntite
	 * @param libelle
	 * @param description
	 * @param methode
	 * @param configIHM
	 * @param progressBar
	 * @param clavier
	 * @param index
	 */
//	@Deprecated
//	public Traitement(String type, String codeEntite, String libelle, String description, String methode, boolean[] configIHM, 
//			String progressBar, String clavier, String index){
//				
//		setType(type);
//		setCodeEntite(codeEntite);
//		setLibelle(libelle);
//		setDescription(description);
//		setMethode(methode);
//		setConfigIHM(configIHM);
//		setProgressBar(progressBar);
//		setClavier(clavier);
//		setIndex(index);
//		
//		setCtrlDestination(ToolBox.getManagedBeanNameFromEntityCode(codeEntite));
//		
//		setModalType(MODAL_NO);
//		setConfigVisibilite("002");
//	}		

	
	/**
	 * Création d'un traitement de navigation basique sur une entité
	 * 
	 * @param codeEntite
	 * @param libelle
	 * @param description
	 * @return
	 */
	public static Traitement getTraitementNavigation(String codeEntite, String libelleFormulaireListe, String libelleFormulaireDetail, String description){
		 
		Traitement v$traitement = new Traitement();
		
		v$traitement.setType(NAVIGATION);
		
		v$traitement.setCodeEntite(codeEntite);		
		v$traitement.setLibelle(libelleFormulaireListe + BasicTrt.SEPERATEUR_2 + libelleFormulaireDetail);	// Compatibilité ascendante
		v$traitement.setDescription(description);
		v$traitement.setMethode(METHODE_NAVIGATION);

		v$traitement.setCtrlDestination(getManagedBeanNameFromEntityCode(codeEntite));
		v$traitement.setModalType(MODAL_NO);
		v$traitement.setConfigVisibilite("002");
				
		return v$traitement;
	}
	
	/**
	 * Obtention du nom d'un bean managé a partir du code réel de l'entité
	 * 
	 * @param p$entityCode	:	Code réel de l'entité
	 * @return
	 */
	public static String getManagedBeanNameFromEntityCode(String p$entityCode){
		
		String v$beanName =  null;
		
		if ((p$entityCode != null) && (! p$entityCode.trim().isEmpty())){
			String v$string = p$entityCode.substring(0, 1);
			v$beanName = p$entityCode.replaceFirst(v$string, v$string.toLowerCase()).concat("Ctrl");						
		}
				
		return v$beanName;
	}
	
		
	/**
	 * Contructeur d'un traitement à partir d'un objet traitement
	 * Utile pour éviter les références d'objet
	 * 
	 * @param traitement	: Objet traitement
	 */
	public Traitement(Traitement traitement) {
		
		setType(traitement.getType());
		setCodeEntite(traitement.getCodeEntite());
		setRealCodeTraitement(traitement.getRealCodeTraitement());
		setLibelle(traitement.getLibelle());
		setDescription(traitement.getDescription());
		setMethode(traitement.getMethode());
		setConfigIHM(traitement.getConfigIHM());
		setConfigVisibilite(traitement.getConfigVisibilite());
		setModalType(traitement.getModalType());
		setModalMsg(traitement.getModalMsg());
		setModalPanel(traitement.getModalPanel());
		setProgressBar(traitement.getProgressBar());
		setClavier(traitement.getClavier());
		setIndex(traitement.getIndex());
		setReRender(traitement.getReRender());
		setImage(traitement.getImage());
		setCtrlDestination(traitement.getCtrlDestination());

		setCode(traitement.getCode());

	}
	
	/**
	 * Construction d'un traitement avec définition de son code réel
	 * Cette méthode ne devrait plus être implémentée
	 * 
	 * @param codeTrt		: code réel du traitement
	 * @param traitement	: Objet traitement
	 */
	@Deprecated 
	public Traitement(String codeTrt, Traitement traitement) {
	
		this(traitement);
	
		// Mise à jour du code du traitement en fonction de la page
		setCode(codeTrt);	
		
		// Mise à jour du libellé a afficher du traitement en fonction de la page
		setLibelle();
	}	

	/**
	 * Création d'un traitement de navigation
	 * 
	 * @param traitement
	 * @param enmTypeNavigation
	 */
	public Traitement(Traitement traitement, EnmTypeNavigation enmTypeNavigation) {
		
		this(traitement);
				
		// MAJ du Code du Traitement
		if(EnmTypeNavigation.VERS_FORMULAIRE_DETAIL.equals(enmTypeNavigation)){
			this.setCode(this.naviguerVersFormulaireDetails());			
		}	
		else if(EnmTypeNavigation.VERS_FORMULAIRE_LISTE.equals(enmTypeNavigation)){
			this.setCode(this.naviguerVersFormulaireListe());					
		}
		
		// MAJ du libelle
		setLibelle();
		
	}	
	
	
	/**
	 * Création d'un traitement pour une entité
	 * 
	 * @param traitement
	 * @param codeEntite	
	 */
	public Traitement(Traitement traitement, String codeEntite) {
		
		this(traitement);
	
		this.setCodeEntite(codeEntite);
		
		// MAJ DU CODE DU TRAITEMENT
		this.setCode(this.codeEntite, this.realCodeTraitement);
	}	

	/**
	 * Création d'un traitement pour une entité
	 * 
	 * @param traitement
	 * @param codeEntite
	 * @param methode
	 */
	public Traitement(Traitement traitement, String codeEntite, String methode) {
		
		this(traitement, codeEntite);
	
		this.setMethode(methode);

	}
	
	/**
	 * Création d'un traitement avec modal panel particulier pour une entité pour une entité
	 * 
	 * @param traitement
	 * @param codeEntite
	 * @param methode
	 * @param modalId
	 */
	public Traitement(Traitement traitement, String codeEntite, String methode, String modalId) {
		
		this(traitement, codeEntite, methode);

		this.setModalType(EnmTypeModalPanel.PARTICULIER);
		this.setModalPanel(modalId);
	}
	
	/**
	 * Création d'un traitement pour une entité
	 * 
	 * @param traitement
	 * @param codeEntite
	 * @param methode
	 * @param modalType
	 */
	public Traitement(Traitement traitement, String codeEntite, String methode, EnmTypeModalPanel modalType){
		
		this(traitement, codeEntite, methode);
		this.setModalType(modalType);
	}

	/**
	 * Création d'un traitement pour une entité
	 * 
	 * @param traitement
	 * @param codeEntite
	 * @param methode
	 * @param modalType
	 * @param modalId
	 */
	public Traitement(Traitement traitement, String codeEntite, String methode, EnmTypeModalPanel modalType, String modalId){
		
		this(traitement, codeEntite, methode);
		this.setModalType(modalType);
		this.setModalPanel(modalId);
	}
	
	
	/**
	 * Création d'un traitement spécifique normal (orchestré, sans modal panel)
	 * 
	 * @param realCodeTraitement
	 * @param libelle
	 * @param description
	 * @param methode
	 * @param modalMsg
	 * @param clavier
	 * @param image
	 * @return
	 */
	public static Traitement getTraitementSpecifique(String realCodeTraitement, String libelle, String description, String methode, String modalMsg ,
				 String clavier, String image){
		 
		Traitement v$traitement = new Traitement();
		
		v$traitement.setType(SPECIFIQUE);
		v$traitement.setRealCodeTraitement(realCodeTraitement);
		v$traitement.setLibelle(libelle);
		v$traitement.setDescription(description);
		v$traitement.setMethode(methode);
		v$traitement.setModalMsg(modalMsg);
		v$traitement.setClavier(clavier);
		v$traitement.setImage(image);
		
		return v$traitement;
	}
	
	/**
	 * Création d'un traitement spécifique avec modal panel
	 * 
	 * @param realCodeTraitement
	 * @param libelle
	 * @param description
	 * @param methode
	 * @param modalType
	 * @param modalId
	 * @param modalMsg
	 * @param clavier
	 * @param image
	 * @return
	 */
	public static Traitement getTraitementSpecifique(String realCodeTraitement, String libelle, String description, String methode, String modalType, String modalId, String modalMsg ,
			String progressBar, String clavier, String image){
	 
		Traitement v$traitement = getTraitementSpecifique(realCodeTraitement, libelle, description, methode, modalMsg, clavier, image);
		
		v$traitement.setModalPanel(modalId);
		v$traitement.setModalType(modalType);
		v$traitement.setProgressBar(progressBar);
		
		return v$traitement;
	}
		
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String codeEntite, String realCodeTraitement) {
		this.code = getCodeTrt(codeEntite, realCodeTraitement);
	}
	
	public void setCode(String code) {
		this.code = code;
	}

	public void setCodeEntite(String codeEntite) {
		this.codeEntite = codeEntite;
	}

	public String getCodeEntite() {
		return codeEntite;
	}

	public void setRealCodeTraitement(String realCodeTraitement) {
		this.realCodeTraitement = realCodeTraitement;
	}

	public String getRealCodeTraitement() {
		return realCodeTraitement;
	}

	public String getLibelle() {
		return libelle;
	}
	
	/**
	 * Mise à jour du libellé des traitements de navigation 
	 * Cette mise à jour se fait en fonction du type de Formulaire vers lequel l'on navigue (LISTE ou DETAILS)
	 * 
	 * @return
	 */
	@Deprecated // Revoir la logique de cette méthode
	private void setLibelle() {
		
		// Libellé de l'entité obtenu à partir de l'énumération du traitement 
		String v$oldLibelle = getLibelle();
				
		// Si aucun libellé alors l'on retourne le vide
		if(v$oldLibelle == null || v$oldLibelle.trim().isEmpty()){
			setLibelle("");
			return;
		} 
			
		// Split du libélle à l'aide du séparateur
		String [] v$libelles =  v$oldLibelle.split(BasicTrt.SEPERATEUR_2);
	
		// Si nous avons bien 2 libellés (Formulaire Liste & Formulaire Détails)
		if(v$libelles.length == 2){
			// Libellé du Formulaire Liste
			if(getCode().endsWith("Liste")){
				setLibelle(v$libelles[0]);
				return;
			}
			// Libellé du Formulaire Détails
			else if(getCode().endsWith("Details")){
				setLibelle(v$libelles[1]);
				return;
			} 
			
		}
					
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMethode() {
		return methode;
	}

	public void setMethode(String methode) {
		this.methode = methode;
	}

	public boolean[] getConfigIHM() {
		return configIHM;
	}

	public void setConfigIHM(boolean[] configIHM) {
		this.configIHM = configIHM;
	}
	
	/**
	 * @return the configVisibilite
	 */
	public String getConfigVisibilite() {
		return configVisibilite;
	}

	/**
	 * @param configVisibilite the configVisibilite to set
	 */
	public void setConfigVisibilite(String configVisibilite) {
		this.configVisibilite = configVisibilite;
	}

	public String getModalType() {
		return modalType;
	}

	public void setModalType(String modalType) {
		this.modalType = modalType;
		
		/*
		 * Configuration implicite : 
		 * Si le traitement fait appel à un modal panel stantard (motif, upload fichier) alors l'on configure implicitement le code du modal panel a afficher 
		 */
		if(Traitement.MODAL_MOTIF.equals(modalType) || Traitement.MODAL_FILE.equals(modalType)  ){
			setModalPanel(modalType);
		}
	}

	public void setModalType(EnmTypeModalPanel modalType) {
		setModalType(modalType.getModalId());	// fait ainsi afin de conserver la compatibilité ascendante
	}
		
	public String getModalMsg() {
		return modalMsg;
	}

	public void setModalMsg(String modalMsg) {
		this.modalMsg = modalMsg;
	}

	public String getProgressBar() {
		return progressBar;
	}

	public void setProgressBar(String progressBar) {
		this.progressBar = progressBar;
	}

	public String getClavier() {
		return clavier;
	}

	public void setClavier(String clavier) {
		this.clavier = clavier;
	}

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getReRender() {
		return reRender;
	}

	public void setReRender(String reRender) {
		/*
		 * Si le traitement fait appel à un modal panel alors, le reRender sera mis à jour implicitement via la méthode setModalType()
		 * ou explicitement par l'utilisateur
		 */
		if(Traitement.RERENDER_MODAL_PANEL.equals(reRender)){
			// Dans ce cas reRender = code du modal Panel
			return;
		}
		else{
			this.reRender = reRender;
		}
		
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getCtrlDestination() {
		return ctrlDestination;
	}

	public void setCtrlDestination(String ctrlDestination) {
		this.ctrlDestination = ctrlDestination;
	}

	/**
	 * @return the rendered
	 */
	public String getRendered() {
		return rendered;
	}

	/**
	 * @param rendered the rendered to set
	 */
	public void setRendered(String rendered) {
		this.rendered = rendered;
	}	

	/**
	 * @return the renderedBool
	 */
	public boolean getRenderedBool() {
		return renderedBool;
	}

	/**
	 * @param renderedBool the renderedBool to set
	 */
	public void setRenderedBool(boolean renderedBool) {
		this.renderedBool = renderedBool;
	}

	/**
	 * @return the modalPanel
	 */
	public String getModalPanel() {
		return modalPanel;
	}

	/**
	 * @param modalPanel the modalPanel to set
	 */
	public void setModalPanel(String modalPanel) {
		this.modalPanel = modalPanel;
		
		/*
		 * Configuration implicite :	
		 * Si le traitement necessite un modal panel, alors la propriété reRender  = à la propriété modalPanel (code du modal Panel)
		 */
		if(modalPanel != null && ! modalPanel.trim().isEmpty()){
			setReRender(modalPanel);
		}
	}
	
	/**
	 * Construit le code d'un traitement de navigation vers un formulaire Details
	 * 
	 * @return
	 */
	public String naviguerVersFormulaireDetails() {
		return codeEntite + "Details";
	}	
	
	/**
	 * Construit le code d'un traitement de navigation vers un formulaire Liste
	 * 
	 * @return
	 */
	public String naviguerVersFormulaireListe() {
		return codeEntite + "Liste";
	}		

	/**
	 * Contruit la clé servant à référencer un traitement dans un (Tree)Map 
	 * de sorte qu'à l'issue du paramétrage des traitements, ces derniers puissent être ordonnés
	 * 
	 * Permet généralement de construire la clé des traitements spécifiques et de navigation
	 * 
	 * @return
	 */
	public String getKey(){
		return getIndex()+Traitement.SEPERATEUR_1+getCode();
	}	
	
	/**
	 * Construit la clé servant à référencer un traitement dans un (Tree)Map
	 * Cette clé est construite à partir du code d'une entité et celui d'un traitement standard
	 * Principalement utile pour les traitements standards dont le code est au prélable préfixé par le code de l'entité
	 * 
	 * @param p$codeEntite
	 * @return
	 */
	public String getKey(String p$codeEntite){
		return getIndex() + Traitement.SEPERATEUR_1 + getCodeTrt(p$codeEntite, getCode());
		//return getIndex()+Traitement.SEPERATEUR_1+p$codeEntite+getCode();
	}	
	

	/**
	 * Construit la clé servant à référencer un traitement dans un (Tree)Map
	 * Cette clé est construite à partir du code d'une entité et celui d'un traitement standard
	 * 
	 * @param p$codeEntite	: code de l'entité
	 * @param p$traitement	: Traitement
	 * @return
	 */
	public static String getKey(String p$codeEntite, Traitement p$traitement){
		return p$traitement.getIndex()+Traitement.SEPERATEUR_1+ getCodeTrt(p$codeEntite, p$traitement.getCode());
		//return p$traitement.getIndex()+Traitement.SEPERATEUR_1+p$codeEntite+p$traitement.getCode();		
	}	

	
	/**
	 * Retourne la liste des traitements standards pour une entité donnée
	 * 
	 * @param p$codeEntite	: Code de l'entité
	 * @return
	 */
	public static Map<String, Traitement> getTrtStandards(String p$codeEntite) {
		
		Traitement v$traitement;
		Map<String,Traitement> trtStandards = new TreeMap<String, Traitement>();
		
		v$traitement = new Traitement(getCodeTrt(p$codeEntite,BasicTrt.AJOUTER.getCode()), BasicTrt.AJOUTER);
		trtStandards.put(v$traitement.getKey(), v$traitement);
		
		v$traitement = new Traitement(getCodeTrt(p$codeEntite,BasicTrt.AFFICHER.getCode()),BasicTrt.AFFICHER);
		trtStandards.put(v$traitement.getKey(), v$traitement);
		
		v$traitement = new Traitement(getCodeTrt(p$codeEntite,BasicTrt.COPIER.getCode()),BasicTrt.COPIER);
		trtStandards.put(v$traitement.getKey(), v$traitement);

		v$traitement = new Traitement(getCodeTrt(p$codeEntite,BasicTrt.MODIFIER.getCode()), BasicTrt.MODIFIER);
		trtStandards.put(v$traitement.getKey(), v$traitement);

		v$traitement = new Traitement(getCodeTrt(p$codeEntite,BasicTrt.SELECTIONNER.getCode()), BasicTrt.SELECTIONNER);
		trtStandards.put(v$traitement.getKey(), v$traitement);

		v$traitement = new Traitement(getCodeTrt(p$codeEntite,BasicTrt.SUPPRIMER.getCode()), BasicTrt.SUPPRIMER);
		trtStandards.put(v$traitement.getKey(), v$traitement);

		trtStandards.put(v$traitement.getKey(), v$traitement);

		return trtStandards;
		
	}	
	
	/**
	 * Retourne la liste des traitements contenu dans le Map 
	 * Cette liste est a priori ordonée  selon les index des traitements si le map est un TreeMap
	 * 
	 * @param p$map
	 * @return
	 */
	public static List<Traitement> getOrderedTrt(Map<String, Traitement> p$map){
		
		List<Traitement> v$liste = new ArrayList<Traitement>();
		
		for(String v$key: p$map.keySet()){
			v$liste.add(p$map.get(v$key));
			//System.out.println("-- Key : " + v$key);
		}
		
		return v$liste;
				
	} 
	
	public static String  getCodeTrt(String p$codeEntite, String p$codeTrtEnum){	
		return (p$codeEntite == null || p$codeEntite.isEmpty()) ? p$codeTrtEnum : p$codeEntite+Traitement.SEPERATEUR_3+p$codeTrtEnum;
	}
	
}
