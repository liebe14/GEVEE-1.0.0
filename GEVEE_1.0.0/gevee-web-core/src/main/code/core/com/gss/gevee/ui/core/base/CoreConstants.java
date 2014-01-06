package com.gss.gevee.ui.core.base;




public class CoreConstants {

	
	
	/****
	 * Constantes �numerant les Ids de certains composants graphiques 
	 */
	public static final String ID_BARRE_MSG = "msgs_barreMessage";
	
	public static final String USR_IS_ADMIN = "estAdministrateur";
	
	public static final String USR_IS_LOGGED = "ISLOGGED";
	
	public static final String CURRENT_LOCALE = "CURRENT_LOCALE";
	
	
	
	public static final String COMBO_BOX = "map";
	
	public static final String SPECIAL_ID = "specialKey";
	public static final String MEMO_ENTITE = "memoEntite";
	
	
	/**
	 * Code du m�ssage d'�rreur (warning) inject� dans le SessionMap ou Scope session
	 * Utile lorsque la gestion standard des m�ssages ne peut-�tre appliqu�e
	 */
	public static final String CODE_MSG_ERROR = "errorMsg";
	
	/****
	 * Constantes �numerant les suffixes des r�gles de  navigation
	 * Utilis�es pour g�n�raliser la construction des r�gles de navigation � partir des memo des Entit�s  
	 */
	public  static final String SUFFIXE_NVGT_LISTE = "Liste";
	public  static final String SUFFIXE_NVGT_DETAILS = "Details";
	public  static final String SUFFIXE_NVGT_EDITION = "Edition";
	
	public  static final String SUFFIXE_MANAGED_BEAN = "Ctrl";

	/***
	 * Constantes enumerant les differents contexte de page 
	 * Utilis�es pour savoir de quel contexte une op�ration �t� effectu�e
	 * 
	 */
	public  static final String  CONTEXTE_DETAILS = "DETAILS";
	public  static final String  CONTEXTE_LISTE = "LISTE";
	public  static final String  CONTEXTE_EDITION = "EDITION";
	
	/***
	 * M�ssages applicatifs 
	 */
    public static final String MSG_ERROR_SERVICE = "Service distant inaccessible";	
    public static final String MSG_ERROR_JndiProps = "Echec lors du chargement du fichier de propri�t�s JNDI d'accc�s au serveur";
    

    /***
     * Constantes enumerant les diff�rents types de recherche possibles 
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
