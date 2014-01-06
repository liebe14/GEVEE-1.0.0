package com.gss.gevee.ui.core.base;




public class CoreConstants {

	
	
	/****
	 * Constantes énumerant les Ids de certains composants graphiques 
	 */
	public static final String ID_BARRE_MSG = "msgs_barreMessage";
	
	public static final String USR_IS_ADMIN = "estAdministrateur";
	
	public static final String USR_IS_LOGGED = "ISLOGGED";
	
	public static final String CURRENT_LOCALE = "CURRENT_LOCALE";
	
	
	
	public static final String COMBO_BOX = "map";
	
	public static final String SPECIAL_ID = "specialKey";
	public static final String MEMO_ENTITE = "memoEntite";
	
	
	/**
	 * Code du méssage d'érreur (warning) injecté dans le SessionMap ou Scope session
	 * Utile lorsque la gestion standard des méssages ne peut-être appliquée
	 */
	public static final String CODE_MSG_ERROR = "errorMsg";
	
	/****
	 * Constantes énumerant les suffixes des règles de  navigation
	 * Utilisées pour généraliser la construction des règles de navigation à partir des memo des Entités  
	 */
	public  static final String SUFFIXE_NVGT_LISTE = "Liste";
	public  static final String SUFFIXE_NVGT_DETAILS = "Details";
	public  static final String SUFFIXE_NVGT_EDITION = "Edition";
	
	public  static final String SUFFIXE_MANAGED_BEAN = "Ctrl";

	/***
	 * Constantes enumerant les differents contexte de page 
	 * Utilisées pour savoir de quel contexte une opération été effectuée
	 * 
	 */
	public  static final String  CONTEXTE_DETAILS = "DETAILS";
	public  static final String  CONTEXTE_LISTE = "LISTE";
	public  static final String  CONTEXTE_EDITION = "EDITION";
	
	/***
	 * Méssages applicatifs 
	 */
    public static final String MSG_ERROR_SERVICE = "Service distant inaccessible";	
    public static final String MSG_ERROR_JndiProps = "Echec lors du chargement du fichier de propriétés JNDI d'acccès au serveur";
    

    /***
     * Constantes enumerant les différents types de recherche possibles 
     * 
     */
    public static final String TYPE_RECHERCHE_ID = "ID";
    public static final String TYPE_RECHERCHE_TOUT = "TOUT";
    public static final String TYPE_RECHERCHE_CRITERE = "CRITERE";
    
   
   public static final String DEPLOIEMENT_SANS_EAR = "0";
   public static final String DEPLOIEMENT_AVEC_EAR = "1";

   public static final String FILE_PATH_SEPARATOR = "_";
   public static final String NUMBER_FORMAT = "#,###.###";
    
}
