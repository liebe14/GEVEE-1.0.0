package com.gss.gevee.ui.core.base;

import java.util.ArrayList;
import java.util.List;

import com.gss.gevee.be.core.base.BaseEntity;

public abstract class GeveeVue <T extends BaseEntity> {
	
	/**
	 * Encapsule toutes les propri�t�s et m�thodes  utile pour g�rer les tables dans les formulaires
	 */
	protected AbstractTableManager<T> tableMgr;
	
	/***
	 * Encapsule toutes les propri�t�s et m�thodes utiles pour g�rer la navigation entre les formulaires
	 */
	protected AbstractNavigationManager navigationMgr;
	
	/***
	 * Repr�sente l'entit� courante (qui sera match�) dans les 3 formulaires d'une entit� 
	 * 		- Pour le Formulaire Liste c'est l'entit� s�lectionn�
	 * 		- Pour le Formulaie Edition c'est l'entit� qui sera enregist�
	 * 		- Pour le Formulaire  Details, c'est l'entit� en consultation 
	 */
	protected T entiteCourante;
	
	/**
	 * Donn�es de la pagination 
	 */
	protected List listePagination;
	
	
	/***
	 * Contient la sauvegarde de l'entit� courante lorsqu'on va en modification ou cr�ation 
	 * 		- La raison est du au fait que lors la modification ou la cr�ation d'une entit� 
	 * 			les validations partielles des propri�t�s sont susceptibles de d'ecraser les anciennes valeurs
	 * 			et dans ce cas s'il y'a annulation de l'op�ration l'on en pourra plus recup�rer les valeurs �cras�es 
	 */
	protected T entiteTemporaire;
	
	/**
	 * Type de la recherche (ID, Crit�re, Tout)
	 */
	protected String typeRecherche = "TOUT";
	
	/***
	 * Repr�sente l'entit� dont les propri�t�s seront utilis�es pour la recherche 
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
	 * Constructeur par d�faut
	 */
	public GeveeVue(){
		
		super();
		
//		// Instance des propri�t�s g�n�riques h�rit�es  
//		this.tableMgr = new TableManager<T>();
		
		// Instance des propri�t�s g�n�riques h�rit�es  
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
		 * Cette m�thode a �t� red�finie dans certaines entit�s telles :
		 * 	- Budgets, Engagements, Liquidations, Mandats (pour la gestion des pi�ces jointes)
		 * 	- Utilisateurs (pour la gestion des signatures)
		 * Donc Toute modification � ce nivo doit imp�rativement tenir compte des m�thodes red�finies
		 * 
		 */
		
		if(entiteCourante != null && entiteCourante.getId() != null){
							
			// Cr�ation des entit�s li�es de valeur null;
			entiteCourante.initData();
		}
		
		this.entiteCourante = entiteCourante;
		
		// Injection du Sp�cial Id de l'entit� courante dans le Scope Session de l'application
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
	 * Retourne une nouvelle instance d'une entit�; Instance sans donn�es hors mis les valeur par defaut 
	 * M�thode g�n�rique, abstraite que la classe concr�te doit implementer 
	 * 
	 * @return
	 */
	public abstract T  getNewEntity();
	
	
	/***
	 * Retourne une nouvelle instance d'une entit� utile pour la recherche des donn�es;  
	 * M�thode g�n�rique, abstraite que la classe concr�te doit implementer 
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
	 *  Clone d'une entit�
	 *  
	 * @param entity
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public T clone(T  entity){
		
		T v$entity;
			
		try {
			// 1i�re M�thode de clone
			v$entity = (T) entity.clone();					
		} 
		catch (CloneNotSupportedException e) {			
			//Si Echec 2i�me m�thode de clone
			v$entity = entity;
			//e.printStackTrace();
		}			
		return v$entity;
	}	
	
}
