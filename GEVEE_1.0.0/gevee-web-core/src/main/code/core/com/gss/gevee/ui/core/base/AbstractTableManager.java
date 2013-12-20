package com.gss.gevee.ui.core.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.faces.component.UIData;
import javax.faces.model.SelectItem;

import org.richfaces.model.DataProvider;
import org.richfaces.model.ExtendedTableDataModel;
import org.richfaces.model.selection.Selection;
import org.richfaces.model.selection.SimpleSelection;

import com.gss.gevee.be.core.base.BaseEntity;
import com.gss.gevee.be.core.base.GeveeBaseEntity;



/**
 * Cette classe encapsule toute la logique de gestion relative au table de données (Composant DataTable)
 * 
 * @param <T>	: Entité à manager
 */
public abstract class AbstractTableManager<T extends BaseEntity> {
	
	 public  static enum SelectionModeEnum{
		 
		SINGLE("single"),
		MULTI("multi");
		
		SelectionModeEnum(String value){
			this.value = value;
		}
		
		private String value;
		
		public String getValue(){
			return value;
		}
	}
	
	/**
	 * Composant JSF devant etre bindé à un dataTable ou table
	 */
	private UIData dataTable;
	
	/**
	 * Composant RichFaces contenant les clés des ligne sélectionnées dans un DataTable 
	 */
	private Selection selection = new SimpleSelection();
	
	/***
	 * Modèle de données devant etre injecté dans les composants de type DataTable 
	 */
	private ExtendedTableDataModel<T> dataModel;
	
	/**
	 * Liste des élements issues de la recherche 
	 */
	private List<T> listeRecherche  = new ArrayList<T>();
	
	/***
	 * Nombre de lignes a affichées dans le tableau 
	 */
	private String rows = "20";
	
	/**
	 * Garde l'état du tableau après modification (ordre des colonnes, largeur des colonnes, ...)
	 */
	private String tableState;
	
	/**
	 * Mode de sélection de données sur la table 
	 * Par défaut, un seul élement selectionnable à la fois
	 */
	private String selectionMode = SelectionModeEnum.SINGLE.getValue();
	
	/**
	 * Entité sélectionnée dans le tableau
	 */
	private T selectedEntity;
	
	
	/**
	 * Attributs intégrer pour la gestion de la pagination 
	 */	
	private long totalRecherche;
	
	/**
	 * N° de Page de la pagination
	 */
	private int page = -1;
		
	/**
	 * Données de la pagination 
	 */
	private  List<SelectItem> listePagination = new ArrayList<SelectItem>();
	
	/**
	 * Constructeur par défaut
	 */
	public AbstractTableManager() {
		super();
		//  Auto-generated constructor stub
	}
	
	
	/**
	 * @return the dataTable
	 */
	public UIData getDataTable() {
		return dataTable;
	}

	/**
	 * @param dataTable the dataTable to set
	 */
	public void setDataTable(UIData dataTable) {
		this.dataTable = dataTable;
	}

	/**
	 * @return the selection
	 */
	public Selection getSelection() {
		return selection;
	}

	/**
	 * @param selection the selection to set
	 */
	public void setSelection(Selection selection) {
		this.selection = selection;
	}
	
	/**
	 * @return the totalRecherche
	 */
	public long getTotalRecherche() {
		return totalRecherche;
	}


	/**
	 * @param totalRecherche the totalRecherche to set
	 */
	public void setTotalRecherche(long totalRecherche) {
		this.totalRecherche = totalRecherche;
	}

	
	/**
	 * @return the page
	 */
	public int getPage() {
		return page;
	}


	/**
	 * @param page the page to set
	 */
	public void setPage(int page) {
		this.page = page;
	}
	

	public void setListePagination(List<SelectItem> listePagination) {
		this.listePagination = listePagination;
	}


	public List<SelectItem> getListePagination() {
		return listePagination;
	}


	/**
	 * @return the dataModel
	 */
	public ExtendedTableDataModel<T>  getDataModel() {
		return dataModel;
	}

	/**
	 * @param dataModel the dataModel to set
	 */
	public void setDataModel(ExtendedTableDataModel<T>  dataModel) {
		this.dataModel = dataModel;
	}

	/**
	 * @return the listeRecherche
	 */
	public List<T>  getListeRecherche() {
			
		return listeRecherche; 
	}

	/**
	 * @param listeRecherche the listeRecherche to set
	 */
	public void setListeRecherche(List<T>   listeRecherche) {
		
		//this.listeRecherche = (listeRecherche != null)? listeRecherche : new ArrayList<T>();
		
		if(listeRecherche == null){
			this.listeRecherche = new ArrayList<T>();
		}
		else{
			this.listeRecherche = new ArrayList<T>(listeRecherche);
		}						
		
		// La mise à jour de la liste de recherche met implicitement à jour le modèle du tableau
		updateDataModel();
	}
	
	
	/**
	 * @param listeRecherche the listeRecherche to set
	 */
	public void setListeRecherche(Set<T>   listeRecherche) {
			
		if(listeRecherche == null){
			this.listeRecherche = new ArrayList<T>();
		}
		else{
			this.listeRecherche = new ArrayList<T>(listeRecherche);
		}
		
		// La mise à jour de la liste de recherche met implicitement à jour le modèle du tableau
		updateDataModel();
	}
	
	/**
	 * Retourne la liste des données sous forme de SET
	 * @return the listeRecherche
	 */
	public Set<T>  getDataSet() {
		
		if(getListeRecherche() == null || getListeRecherche().isEmpty()  ){
			return  new HashSet<T>();
		}
		else{
			return new HashSet<T>(getListeRecherche());
		}	
	}	

	/**
	 * @return the rows
	 */
	public String getRows() {
		return rows;
	}

	/**
	 * @param rows the rows to set
	 */
	public void setRows(String rows) {
		this.rows = rows;
	}
	
	
	/**
	 * @return the tableState
	 */
	public String getTableState() {
		return tableState;
	}
	

	/**
	 * @return the selectionMode
	 */
	public String getSelectionMode() {
		return selectionMode;
	}


	/**
	 * @param selectionMode the selectionMode to set
	 */
	public void setSelectionMode(String selectionMode) {
		this.selectionMode = selectionMode;
		
		// En  sélection simple, l'on réinitialise la propriété de sélection
		if(SelectionModeEnum.SINGLE.getValue().equals(selectionMode)){
			setSelection(new SimpleSelection());
		}
		
	}


	/**
	 * @param tableState the tableState to set
	 */
	public void setTableState(String tableState) {		
		this.tableState = tableState;
	}


	/**
	 * @return the selectedEntity
	 */
	public T getSelectedEntity() {
		
		if(selectedEntity == null) return null;
		
		selectedEntity.initData();
		
		return selectedEntity;
	}


	/**
	 * @param selectedEntity the selectedEntity to set
	 */
	public void setSelectedEntity(T selectedEntity) {
		this.selectedEntity = selectedEntity;
	}

	/**
	 * @param selectedEntity the selectedEntity to set
	 */
	public void setSelectedEntity(String selectedEntity) {
		this.selectedEntity = getEntityByKey(selectedEntity);
	}
	
	/**
	 * Permet de mettre coté serveur l'entité sélectionnée sur tableau à partir du special ID de l'entité
	 * 
	 * @param p$key	: Clé de l'entité
	 */
	public void setKey(Serializable p$key){
		this.selectedEntity = getEntityByKey(p$key);		
	}
	
	/**
	 * Nombre d'entités dans la liste de Recherche 
	 * 
	 * @return
	 */
	public int getRowCount(){		
		return getListeRecherche().size();
	}
	
	/**
	 * Nombre de lignes selectionnés dans le tableau
	 * @return
	 */
	public int getRowSelected(){
		return selection.size();
	}
	
	
	/***
	 * Retourne l'entité  selectionnée dans le tableau 
	 * 
	 * @return
	 */
	public T getEntiteSelectionne(){
		
		// S'il y'a au moins une entité selectionnée
		if(getEntiteSelectionnes().size() > 0){
			
			// Récupération du premier élément sélectionné ds la liste 
			T v$entite = getEntiteSelectionnes().get(0);
			
			// Etant donné que l'entité provient de la BD il n'ya pas de ticket, l'on met dc à jour de ticket
			//v$entite.setTicket(SessionCtrl.getUserTicket());
			
			// Création des entités liés inexistantes
			//v$entite.initData();
								
			return   v$entite;
		}
	
		else 
			return null;
	}
		
	/***
	 * Retourne la liste des éléments sélectionnés dans le tableau 
	 * 
	 * @return
	 */
	public List<T>	getEntiteSelectionnes(){
		
		//TODO Voir comment changer ce code ; Les parcours de liste seront couteux 
		
		// Liste des entités sélectionnés dans le tableau 
		List<T> v$listeSelectionne = new ArrayList<T>();
		
		// Obtention d'un itérateur sur l'ensemble des clés des entités selectionnés 
		Iterator<Object> v$iterator = getSelection().getKeys();
		
		while (v$iterator.hasNext()) {
				
			// Obtention de la clé d'une entité sélectionné 
			Object v$key = v$iterator.next();
			
			// Ajout de l'entité sélectionné dans la liste 
			//v$listeSelectionne.add(getDataModel().getObjectByKey(v$key));
			v$listeSelectionne.add(getEntityByKey((Serializable) v$key));		
		}
		
		return v$listeSelectionne;				
	}	

	
	/***
	 * Transforme de la liste de recherche en modèle de données à injecter dans le tableau
	 * 
	 */
	public void updateDataModel(){
		
		 dataModel = new ExtendedTableDataModel<T>(new DataProvider<T>(){

			private static final long serialVersionUID = 5054087821033164847L;

			public T getItemByKey(Object key) {
				for(T c : getListeRecherche()){
					if (key.equals(getKey(c))){
						return  c;
					}
				}
				return null;
			}

			public List<T> getItemsByRange(int firstRow, int endRow) {
				return getListeRecherche().subList(firstRow, endRow);
			}

			public Object getKey(T item) {
				return ((GeveeBaseEntity) item).getSpecialId();
			}

			public int getRowCount() {
				return getListeRecherche().size();
			}
			
		});						
	}	
	
	
	/**
	 * Remplace une entité existante dans la liste par une autre 
	 * 
	 * @param p$old
	 * @param p$new
	 */
	public void replace(T p$old, T p$new){
				
		if(contains(p$old)){
			getListeRecherche().set(getIndexOfEntity(p$old), p$new);
			updateDataModel();
		}
		else{
			add(p$new);	
		}
				
	}
	
	/**
	 * Ajoute une entité dans la liste de recherche (en première position)
	 * 
	 * @param p$entity
	 */
	public void add(T p$entity){
		getListeRecherche().add(0, p$entity);
		updateDataModel();
	}
	
	/**
	 * Ajoute une entité dans la liste de recherche (en première position)
	 * 
	 * @param p$entity
	 */
	public void add(List<T> p$list){
		if(p$list != null && !p$list.isEmpty()){
			getListeRecherche().addAll(p$list);
			updateDataModel();			
		}
	}
	
	/**
	 * Retire une entité de la liste de recherche 
	 * 
	 * @param p$entity
	 */
	public void remove(T p$entity){
				
//		if(contains(p$entity)){
//			getListeRecherche().remove(getIndexOfEntity(p$entity));
//			updateDataModel();
//		}
		
		if (p$entity == null) return ;
		remove(((GeveeBaseEntity) p$entity).getSpecialId());
	}
	
	/**
	 * Retire une entité de la liste de recherche 
	 * 
	 * @param p$key
	 */
	public void remove(Serializable p$key){
				
		if(contains(p$key)){
			getListeRecherche().remove(getIndexOfEntity(p$key));
			updateDataModel();
		}
	}	
	
	/**
	 * Retourne une entité de la liste de recherche à partir du special ID de l'entité
	 * 
	 * @param v$key
	 * @return
	 */
	public T getEntityByKey(Serializable v$key){
		
		if(getDataModel() == null) return null;
				
		else return getDataModel().getObjectByKey(v$key);
			
	}
	
	/**
	 * Retourne Vrai si la liste de Recherche contient l'entité et Faux dans le cas contraire
	 * Utile pour éviter de faire des comparaisons propriétés par propriétés en utilisant la méthode "contain" sur la liste de Recherche
	 * 
	 * @param p$entity
	 * @return
	 */
	public boolean contains(T p$entity){
		
		//boolean bol = (getEntityByKey(p$entity.getSpecialId()) != null)? true:false ;
		
		if(p$entity == null) return false;
		boolean bol = contains(((GeveeBaseEntity) p$entity).getSpecialId());
		
		return bol;
	}
	
	
	/**
	 * Retourne Vrai si la liste de Recherche contient l'entité et Faux dans le cas contraire
	 * Utile pour éviter de faire des comparaisons propriétés par propriétés en utilisant la méthode "contain" sur la liste de Recherche
	 * 
	 * @param p$key
	 * @return
	 */
	public boolean contains(Serializable p$key){
		
		boolean bol = (getEntityByKey(p$key) != null)? true:false ;
		
		return bol;
	}	
	
	/**
	 * Retourne l'index d'une entité dans le modèle de données (Pour l'instant la liste de recherche)
	 * 
	 * @param p$entity
	 * @return
	 */
	public int getIndexOfEntity(T p$entity){
		
//		int v$index = -1;
//				
//		// Récupération de L'entité dans la liste
//		T v$entity = getEntityByKey(p$entity.getSpecialId());
//		
//		// Index de l'entité retrouvé 
//		v$index = getListeRecherche().indexOf(v$entity);
//				
//		return v$index;	
		
		if(p$entity == null) return -1;
		return getIndexOfEntity(((GeveeBaseEntity) p$entity).getSpecialId());
		
	}
	

	/**
	 * Retourne l'index d'une entité dans le modèle de données (Pour l'instant la liste de recherche)
	 * 
	 * @param p$entity
	 * @return
	 */
	public int getIndexOfEntity(Serializable p$key){
		
		int v$index = -1;
				
		// Récupération de L'entité dans la liste
		T v$entity = getEntityByKey(p$key);
		
		// Index de l'entité retrouvé 
		v$index = getListeRecherche().indexOf(v$entity);
				
		return v$index;	
	}	
	
	/**
	 * Retourne une entité du modèle de données à partir de son  index (Pour l'instant dans la liste de Recherche)
	 * 
	 * @param p$index
	 * @return
	 */
	public T getEntityByIndex(int p$index){
		
		return getListeRecherche().get(p$index);
	}
	
	/**
	 * Retourne le spécial ID de l'entité sélectionné dans le tableau
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public String getSelectionKey(){
		String v$key = null;
		Iterator v$list =  getSelection().getKeys();
		
		if (v$list.hasNext()){
			v$key = (String) v$list.next();
		}
		return v$key;
	}
	
	/**
	 * Réinitialise la liste des données
	 */
	public void clear(){
		setListeRecherche(new ArrayList<T>());
	}
	
}
