/***
 * Fonctions communes à toutes les pages
 * 
 *
 */


/**
 * Fonction qui s'exécute lors du chargement de la page
 */
function initPage(){
	
	
	/*
	 * Chargement conditionnel du tableau du formulaire liste
	 */
	if(isPageList()){ 
		refreshPage(); /* fonction créée à partir d'une balise richface*/
		//loadPageListTable(); 
	}
}
