/***
 * Fonctions communes � toutes les pages
 * 
 *
 */


/**
 * Fonction qui s'ex�cute lors du chargement de la page
 */
function initPage(){
	
	
	/*
	 * Chargement conditionnel du tableau du formulaire liste
	 */
	if(isPageList()){ 
		refreshPage(); /* fonction cr��e � partir d'une balise richface*/
		//loadPageListTable(); 
	}
}
