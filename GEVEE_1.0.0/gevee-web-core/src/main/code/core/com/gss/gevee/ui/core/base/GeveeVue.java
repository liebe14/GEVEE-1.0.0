package com.gss.gevee.ui.core.base;

import java.util.ArrayList;
import java.util.List;

import com.gss.gevee.be.core.base.BaseEntity;

public abstract class GeveeVue <T extends BaseEntity> {
	
	/**
	 * Encapsule toutes les propriétés et méthodes  utile pour gérer les tables dans les formulaires
	 */
	protected AbstractTableManager<T> tableMgr;
	
	/***
	 * Encapsule toutes les propriétés et méthodes utiles pour gérer la navigation entre les formulaires
	 */
	protected AbstractNavigationManager navigationMgr;
	
	/***
	 * Représente l'entité courante (qui sera matché) dans les 3 formulaires d'une entité 
	 * 		- Pour le Formulaire Liste c'est l'entité sélectionné
	 * 		- Pour le Formulaie Edition c'est l'entité qui sera enregisté
	 * 		- Pour le Formulaire  Details, c'est l'entité en consultation 
	 */
	protected T entiteCourante;
	
	/**
	 * Données de la pagination 
	 */
	protected List listePagination;
	
	
	/***
	 * Contient la sauvegarde de l'entité courante lorsqu'on va en modification ou création 
	 * 		- La raison est du au fait que lors la modification ou la création d'une entité 
	 * 			les validations partielles des propriétés sont susceptibles de d'ecraser les anciennes valeurs
	 * 			et dans ce cas s'il y'a annulation de l'opération l'on en pourra plus recupérer les valeurs écrasées 
	 */
	protected T entiteTemporaire;
	
	/**
	 * Type de la recherche (ID, Critère, Tout)
	 */
	protected String typeRecherche = "TOUT";
	
	/***
	 * Représente l'entité dont les propriétés seront utilisées pour la recherche 
	 */
	protected T entiteRecherche;
	
	/***
	 */
	protected List<T> listElement;
	
	
	public List<T> getListElement() {
		return listElement;
	}

	public void setListElement(List<T> listElement) {
		this.listElement = listElement;
	}

	public T getEntiteTemporaire() {
		return entiteTemporaire;
	}

	/**
	 * Constructeur par défaut
	 */
	public GeveeVue(){
		
		super();
		
//		// Instance des propriétés génériques héritées  
//		this.tableMgr = new TableManager<T>();
		
		// Instance des propriétés génériques héritées  
		this.entiteCourante = this.getNewEntity();	
		this.entiteRecherche = this.getEntityForSearch();
		this.listElement = new ArrayList<T>();
	}
	
	/**
	 * @return the entiteCourante
	 */
	public T  getEntiteCourante() {
						
		return entiteCourante;
	}

		
	/**
	 * @param entiteCourante the entiteCourante to set
	 */
	public void setEntiteCourante(T  entiteCourante) {
		
		/**
		 * Cette méthode a été redéfinie dans certaines entités telles :
		 * 	- Budgets, Engagements, Liquidations, Mandats (pour la gestion des pièces jointes)
		 * 	- Utilisateurs (pour la gestion des signatures)
		 * Donc Toute modification à ce nivo doit impérativement tenir compte des méthodes redéfinies
		 * 
		 */
		
		if(entiteCourante != null && entiteCourante.getId() != null){
							
			// Création des entités liées de valeur null;
			entiteCourante.initData();
		}
		
		this.entiteCourante = entiteCourante;
		
		// Injection du Spécial Id de l'entité courante dans le Scope Session de l'application
		//FacesUtil.setSessionMapValue(Constantes.SPECIAL_ID, getSpecialKey(getHidden().getValue().toString()));	
	}


	/**
	 * @param entiteTemporaire the entiteTemporaire to set
	 */
	public void setEntiteTemporaire(T  entiteTemporaire) {
		
		this.entiteTemporaire = entiteTemporaire;		
	}
	

	/**
	 * @return the entiteRecherche
	 */
	public T getEntiteRecherche() {
				
		return entiteRecherche;
	}

	/**
	 * @param entiteRecherche the entiteRecherche to set
	 */
	public void setEntiteRecherche(T entiteRecherche) {
				
		this.entiteRecherche = entiteRecherche;
	}
		
	/**
	 * @return the typeRecherche
	 */
	public String getTypeRecherche() {
		return typeRecherche;
	}

	/**
	 * @param typeRecherche the typeRecherche to set
	 */
	public void setTypeRecherche(String typeRecherche) {
		this.typeRecherche = typeRecherche;
	}

	/**
	 * @return the navigationMgr
	 */
	public AbstractNavigationManager getNavigationMgr() {
		return navigationMgr;
	}



	/**
	 * @param navigationMgr the navigationMgr to set
	 */
	public void setNavigationMgr(AbstractNavigationManager navigationMgr) {
		this.navigationMgr = navigationMgr;
	}

	/**
	 * @return the tableMgr
	 */
	public AbstractTableManager<T> getTableMgr() {
		return tableMgr;
	}

	/**
	 * @param tableMgr the tableMgr to set
	 */
	public void setTableMgr(AbstractTableManager<T> tableMgr) {
		this.tableMgr = tableMgr;
	}
			

	/***
	 * Retourne une nouvelle instance d'une entité; Instance sans données hors mis les valeur par defaut 
	 * Méthode générique, abstraite que la classe concrète doit implementer 
	 * 
	 * @return
	 */
	public abstract T  getNewEntity();
	
	
	/***
	 * Retourne une nouvelle instance d'une entité utile pour la recherche des données;  
	 * Méthode générique, abstraite que la classe concrète doit implementer 
	 * 
	 * @return
	 */
	public abstract T  getEntityForSearch();
	
	/**
	 * @return the listePagination
	 */
	public List getListePagination() {
		return listePagination;
	}

	/**
	 * @param listePagination the listePagination to set
	 */
	public void setListePagination(List listePagination) {
		this.listePagination = listePagination;
	}
	
	/**
	 *  Clone d'une entité
	 *  
	 * @param entity
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public T clone(T  entity){
		
		T v$entity;
			
		try {
			// 1ière Méthode de clone
			v$entity = (T) entity.clone();					
		} 
		catch (CloneNotSupportedException e) {			
			//Si Echec 2ième méthode de clone
			v$entity = entity;
			//e.printStackTrace();
		}			
		return v$entity;
	}	
	
}
