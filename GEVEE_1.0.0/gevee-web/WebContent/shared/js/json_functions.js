/**
 * Ensemble de fonctions utilitaires pour la gestion de l'envoi des données des éléments sélectionnés dans un tableau vers le serveur à l'aide de JSON
 * Repose sur quelques fonctions utilitaires déja écrites et contenues dans les fichiers (util.js, menu_functions.js)
 * 
 * 
 * version : 1.0.1
 * Crée le 25/05/2011
 * @author lkamhoua
 */



/**
 * Définition des variables globales
 */

var checkBoxMap = new Object();		// Map de données sélectionées en relation avec les données saisies par l'utilisateur à envoyer coté serveur en cas de validation
var checkBoxMap01 = new Object();	// Map de données sélectionées en relation avec les données saisies par l'utilisateur à envoyer coté serveur en cas de validation
var checkBoxMap02 = new Object();	// Map de données sélectionées en relation avec les données saisies par l'utilisateur à envoyer coté serveur en cas de validation

var dataMap = new Object();		// Map de données saisies  par l'utilisateur  à envoyer coté serveur en cas de validation (map a utiliser par défaut s'il y'a une seule colonne)
var dataMap01 = new Object();	// Map de données saisies  par l'utilisateur à envoyer coté serveur en cas de validation
var dataMap02 = new Object();	// Map de données saisies  par l'utilisateur à envoyer coté serveur en cas de validation
var dataMap03 = new Object();	// Map de données saisies  par l'utilisateur à envoyer coté serveur en cas de validation
var dataMap04 = new Object();	// Map de données saisies  par l'utilisateur à envoyer coté serveur en cas de validation


/**
* Getter du map de données sélectionnées (relativement aux données saisies)
* Check box a utiliser par defaut s'il y'en a un seul
* @return
*/
function getCheckBoxMap(){	
	return checkBoxMap;
}

/**
* Setter du map de données sélectionnées (relativement aux données saisies)
* Check box a utiliser par defaut s'il y'en a un seul
* 
*/
function setCheckBoxMap(){	
	checkBoxMap = new Object();
}

/**
* Getter du map de données sélectionnées (relativement aux données saisies)
* @return
*/
function getCheckBoxMap01(){	
	return checkBoxMap01;
}

/**
* Setter du map de données sélectionnées (relativement aux données saisies)
* 
*/
function setCheckBoxMap01(){	
	checkBoxMap01 = new Object();
}

/**
* Setter du map de données sélectionnées (relativement aux données saisies)
* 
*/
function setCheckBoxMap02(){	
	checkBoxMap02 = new Object();
}

/**
* Getter du map de données sélectionnées (relativement aux données saisies)
* @return
*/
function getCheckBoxMap02(){
	
	return checkBoxMap02;
}

/**
* Getter du map de données saisies correspondant à une entrée/colonne/champ nommé(e) 
* @return
*/
function getDataMap(){
	return dataMap;
}

/**
* Getter du map de données saisies correspondant à une entrée/colonne/champ nommé(e) 01
* @return
*/
function getDataMap01(){
	return dataMap01;
}


/**
* Getter du map de données saisies correspondant à une entrée/colonne/champ nommé(e) 02
* @return
*/
function getDataMap02(){
	return dataMap02;
}

/**
* Getter du map de données saisies correspondant à une entrée/colonne/champ nommé(e) 03
* @return
*/
function getDataMap03(){
	return dataMap03;
}

/**
* Getter du map de données saisies correspondant à une entrée/colonne/champ nommé(e) 04
* @return
*/
function getDataMap04(){
	return dataMap04;
}

/**
* Initialisation des variables
* 
*/
function initJsonContext(){
	logInfo("========================= initJsonContext =========================");

	var v$nbreArguments = arguments.length;
	logInfo("v$nbreArguments = " + v$nbreArguments  );

	try{
		
		if(v$nbreArguments == 0){
			
			logInfo("Initialisation Globale des variables JSON");
			
			checkBoxMap = new Object();
			checkBoxMap01 = new Object();
			checkBoxMap02 = new Object();
			
			 dataMap = new Object();
			 dataMap01 = new Object();
			 dataMap02 = new Object();
			 dataMap03 = new Object();
			 dataMap03 = new Object();			
		}
		else {
			logInfo("Initialisation Partielle des variables JSON");
			
			for(var i=0;i<v$nbreArguments;i++){
				//v$nbreArguments[i] = new Object();	
			}	
		}
	}
	catch(e){
		logError(e.toString());
	}

	 
	 logInfo("------------------------- initJsonContext -------------------------");

}

/**
* Retourne l'ID client de la cellule du tableau contenant le special ID
* @param p$clientCellId			: Id client d'une cellule du tableau
* @param p$shortCellSpecialId	: Id court de la cellule contenant le special ID
* @return
*/
function getClientCellSpecialId(p$clientCellId, p$shortCellSpecialId){
	logInfo("========================= getClientCellSpecialId(p$clientCellId) =========================");
	logInfo("p$clientCellId = " + p$clientCellId + "		p$shortCellSpecialId = " + p$shortCellSpecialId );
	
	var v$clientCellSpecialId;
	
	try{		
		var v$tableau = p$clientCellId.split(":");	// Split de l'ID de la cellule
		v$tableau[v$tableau.length - 1] = p$shortCellSpecialId;	// On remplace le short ID de la cellule (dernier element du tableau en principe) par celui du special ID
		v$clientCellSpecialId = v$tableau.join(":");	// Reconstitution de l'ID client de la cellule contenant le specialID		
		
		logInfo("v$clientCellSpecialId = " + v$clientCellSpecialId);
	}
	catch(e){
		logError(e.toString());
	}
	
	logInfo("------------------------- getClientCellSpecialId(p$clientCellId) -------------------------");

	return v$clientCellSpecialId;	
}


/**
* Retourne l'ID client de la cellule du tableau contenant le special ID
* @param p$clientCellId	: Id client d'une cellule du tableau
* @return
*/
function getCellSpecialIdValue(p$clientCellSpecialId){
	logInfo("========================= getCellSpecialIdValue(p$clientCellSpecialId) =========================");
	logInfo("p$clientCellSpecialId = " + p$clientCellSpecialId);
	
	var v$cellSpecialIdValue;
	
	try{
				
		//v$cellSpecialIdValue = document.getElementById(p$clientCellSpecialId).value.replace(/\s/g,'');
		v$cellSpecialIdValue = document.getElementById(p$clientCellSpecialId).value;

		logInfo("v$cellSpecialIdValue = " + v$cellSpecialIdValue);
	}
	catch(e){
		logError(e.toString());
	}
	
	logInfo("------------------------- getCellSpecialIdValue(p$clientCellSpecialId) -------------------------");

	return v$cellSpecialIdValue;	
}


/**
* Fonction de mise à jour des checkBoxMap
* @param p$p$shortCellSpecialId	: Id court de la cellule contenant le special ID
* @param p$this				: Composant/tag sur lequel est appelé la fonction
* @param p$shortMsgZoneId	: Id court de la zone de message
* @param p$typeMsg			: Si p$typeMsg == FALSE alors affichés un méssage pour les éléments décochés SINON affichés un méssage pour les éléments sélectionnés/cochés
*/
function updateForCheckBoxMap(p$mapGetter, p$this, p$shortCellSpecialId, p$shortMsgZoneId, p$typeMsg){	
	logInfo("========================= updateForCheckBoxMap(p$mapGetter, p$this, p$shortCellSpecialId, p$shortMsgZoneId, p$typeMsg) =========================");
	logInfo("p$mapGetter = " + p$mapGetter + "		p$shortCellSpecialId = " + p$shortCellSpecialId + " 	p$shortMsgZoneId = " + p$shortMsgZoneId + " 	p$typeMsg = " + p$typeMsg);
	
	try{
		var v$value = p$this.checked;	 
		var v$key = getCellSpecialIdValue(getClientCellSpecialId(p$this.id, p$shortCellSpecialId));
		
		if(v$value){
			putInMap(p$mapGetter,v$key,true);
		}
		else{
			//removeFromMap(p$mapGetter,v$key);
			putInMap(p$mapGetter,v$key,false);
		}						
	}
	catch(e){
		logError(e.toString());
	}
	
	// Affichage d'un méssage si necessaire 
	try{
		if( !(typeof p$shortMsgZoneId == 'undefined')){
			
			var nbreSelections = 0;	
			var v$message = "";
			
			if( !(typeof p$typeMsg == 'undefined') && (p$typeMsg == false)){
				logInfo("Décompte du nombre d'éléments décochés : ");
				
				for(v$key in p$mapGetter()){
					if(p$mapGetter()[v$key] != true) {
						nbreSelections = nbreSelections + 1;
						logInfo("KEY N°" + nbreSelections + " = " + v$key);					
					}	
				}				
				v$message = "" + nbreSelections + "  élément(s) décoché(s)";											
			}
			else{
				logInfo("Décompte du nombre d'éléments cochés : ");
				
				for(v$key in p$mapGetter()){
					if(p$mapGetter()[v$key]) {
						nbreSelections = nbreSelections + 1;
						logInfo("KEY N°" + nbreSelections + " = " + v$key);					
					}	
				}
				v$message = "" + nbreSelections + "  élément(s) sélectioné(s)";							
			}
						
			logInfo("MESSAGE :" + v$message );			
			p$clientMsgZoneId = getIdFormulaire(p$this.id) + ":" + p$shortMsgZoneId;			
			setComponentValue(p$clientMsgZoneId,v$message);			
		}		
	}
	catch(e){
		logError(e.toString());
	}
	
	logInfo("------------------------- updateForCheckBoxMap(p$mapGetter, p$this, p$shortCellSpecialId, p$shortMsgZoneId, p$typeMsg) -------------------------");
}

/**
* Fonction de mise à jour du checkBoxMap
* @param p$p$shortCellSpecialId	: Id court de la cellule contenant le special ID
* @param p$this			: Composant/tag sur lequel est appelé la fonction
* @param p$shortMsgZoneId	: Id court de la zone de message
* @param p$typeMsg			: Si p$typeMsg == FALSE alors affichés un méssage pour les éléments décochés SINON affichés un méssage pour les éléments sélectionnés/cochés
*/
function updateCheckBoxMap(p$this, p$shortCellSpecialId, p$shortMsgZoneId, p$typeMsg){	
	logInfo("========================= updateCheckBoxMap(p$this, p$shortCellSpecialId, p$shortMsgZoneId, p$typeMsg) =========================");
	logInfo("p$shortCellSpecialId = " + p$shortCellSpecialId + "  	p$shortMsgZoneId = " + p$shortMsgZoneId + "  	p$typeMsg = " + p$typeMsg);
	
	try{
		updateForCheckBoxMap(getCheckBoxMap,p$this,p$shortCellSpecialId,p$shortMsgZoneId,p$typeMsg);
	}
	catch(e){
		logError(e.toString());
	}
	
	logInfo("------------------------- updateCheckBoxMap(p$this, p$shortCellSpecialId, p$shortMsgZoneId, p$typeMsg) -------------------------");
}

/**
* Fonction de mise à jour du checkBoxMap01
* @param p$p$shortCellSpecialId	: Id court de la cellule contenant le special ID
* @param p$this			: Composant/tag sur lequel est appelé la fonction
* @param p$shortMsgZoneId	: Id court de la zone de message
* @param p$typeMsg			: Si p$typeMsg == FALSE alors affichés un méssage pour les éléments décochés SINON affichés un méssage pour les éléments sélectionnés/cochés
*/
function updateCheckBoxMap01(p$this, p$shortCellSpecialId, p$shortMsgZoneId, p$typeMsg){	
	logInfo("========================= updateCheckBoxMap01(p$this, p$shortCellSpecialId, p$shortMsgZoneId, p$typeMsg) =========================");
	logInfo("p$shortCellSpecialId = " + p$shortCellSpecialId + "  	p$shortMsgZoneId = " + p$shortMsgZoneId + "  	p$typeMsg = " + p$typeMsg);
	
	try{
		updateForCheckBoxMap(getCheckBoxMap01,p$this,p$shortCellSpecialId,p$shortMsgZoneId,p$typeMsg);
	}
	catch(e){
		logError(e.toString());
	}
	
	logInfo("------------------------- updateCheckBoxMap01(p$this, p$shortCellSpecialId, p$shortMsgZoneId,p$typeMsg) -------------------------");
}

/**
* Fonction de mise à jour du checkBoxMap01
* @param p$p$shortCellSpecialId	: Id court de la cellule contenant le special ID
* @param p$this			: Composant/tag sur lequel est appelé la fonction
* @param p$shortMsgZoneId	: Id court de la zone de message
* @param p$typeMsg			: Si p$typeMsg == FALSE alors affichés un méssage pour les éléments décochés SINON affichés un méssage pour les éléments sélectionnés/cochés
*/
function updateCheckBoxMap02(p$this, p$shortCellSpecialId, p$shortMsgZoneId, p$typeMsg){	
	logInfo("========================= updateCheckBoxMap02(p$this, p$shortCellSpecialId, p$shortMsgZoneId, p$typeMsg) =========================");
	logInfo("p$shortCellSpecialId = " + p$shortCellSpecialId + "  	p$shortMsgZoneId = " + p$shortMsgZoneId + "  	p$typeMsg = " + p$typeMsg);
	
	try{
		updateForCheckBoxMap(getCheckBoxMap02,p$this,p$shortCellSpecialId,p$shortMsgZoneId,p$typeMsg);
	}
	catch(e){
		logError(e.toString());
	}
	
	logInfo("------------------------- updateCheckBoxMap02(p$this, p$shortCellSpecialId, p$shortMsgZoneId, p$typeMsg) -------------------------");
}

/**
* Fonction de mise à jour des dataMap
* @param p$mapGetter	: getter du map
* @param p$shortCellSpecialId	: Id court de la cellule contenant le special ID
* @param p$this			: Composant/tag sur lequel est appelé la fonction
*/
function updateForDataMap(p$mapGetter, p$this, p$shortCellSpecialId){
	
	logInfo("========================= updateForDataMap(p$mapGetter, p$this, p$shortCellSpecialId) =========================");
	logInfo("p$mapGetter = " + p$mapGetter + "		p$shortCellSpecialId = " + p$shortCellSpecialId);
	
	try{		
		var v$value = p$this.value;	 
		var v$key = getCellSpecialIdValue(getClientCellSpecialId(p$this.id, p$shortCellSpecialId));
		
		putInMap(p$mapGetter,v$key,v$value);		
	}
	catch(e){
		logError(e.toString());
	}
	
	logInfo("------------------------- updateForDataMap(p$mapGetter, p$this, p$shortCellSpecialId) -------------------------");	
}

/**
* Fonction de mise à jour du dataMap
* @param p$p$shortCellSpecialId	: Id court de la cellule contenant le special ID
* @param p$this					: Composant/tag sur lequel est appelé la fonction
*/
function updateDataMap(p$this, p$shortCellSpecialId){
	
	logInfo("========================= updateDataMap(p$this, p$shortCellSpecialId) =========================");
	logInfo("p$shortCellSpecialId = " + p$shortCellSpecialId);
	
	try{
		updateForDataMap(getDataMap,p$this,p$shortCellSpecialId);
	}
	catch(e){
		logError(e.toString());
	}
	
	logInfo("------------------------- updateDataMap(p$this, p$shortCellSpecialId) -------------------------");	
}

/**
* Fonction de mise à jour du dataMap01
* @param p$p$shortCellSpecialId	: Id court de la cellule contenant le special ID
* @param p$this					: Composant/tag sur lequel est appelé la fonction
*/
function updateDataMap01(p$this, p$shortCellSpecialId){
	
	logInfo("========================= updateDataMap01(p$this, p$shortCellSpecialId) =========================");
	logInfo("p$shortCellSpecialId = " + p$shortCellSpecialId);
	
	try{
		updateForDataMap(getDataMap01,p$this,p$shortCellSpecialId);
	}
	catch(e){
		logError(e.toString());
	}
	
	logInfo("------------------------- updateDataMap01(p$this, p$shortCellSpecialId) -------------------------");	
}



/**
* Fonction d'insertion dans un map
* @param p$mapGetter	: getter du map
* @param p$key			: clé du map  
* @param p$value		: valeur associé à la clé 
*/
function putInMap(p$mapGetter, p$key, p$value){
	logInfo("========================= putInMap(p$mapGetter, p$key, p$value) =========================");
	logInfo("p$mapGetter = " + p$mapGetter + "		p$key = " + p$key + "		p$value = " + p$value);

	try{
		logInfo("AVANT	: " + mapToString(p$mapGetter()));
		p$mapGetter()[p$key] = p$value;	
		logInfo("APRES	: " + mapToString(p$mapGetter()));
	}
	catch(e){
		logError(e.toString());
	}
	
	logInfo("------------------------- putInMap(p$mapGetter, p$key, p$value) -------------------------");		
}

/**
* Fonction de suppression dans un map
* @param p$mapGetter	: getter du map
* @param p$key			: clé du map
*/
function removeFromMap(p$mapGetter, p$key){
	logInfo("========================= removeFromMap(p$mapGetter, p$key) =========================");
	logInfo("p$mapGetter = " + p$mapGetter + "		p$key = " + p$key);

	try{
		logInfo("AVANT	: " + mapToString(p$mapGetter()));	
		p$mapGetter()[p$key] = null;	// NB : L'entrée du tableau n'est pas supprimé, mais sa valeur est mise à null
		logInfo("APRES	: " + mapToString(p$mapGetter()));

	}
	catch(e){
		logError(e.toString());
	}
	
	logInfo("------------------------- removeFromMap(p$mapGetter, p$key) -------------------------");		
}

/**
* Fonction de qui retourne le map de données à envoyer au serveur
* Elle retourne un Map (SpéciaId (clé), true(valeur)) ou les clés correspondent aux lignes sélectionnées
* Et comme il n'ya pas de cas de valeur saisie, l'on associe au spécial Id de la ligne la valeur true
* 
* @param p$mapGetter	: getter du map
* 
*/
function getJsonCheckBoxMapX(p$mapGetter){
	logInfo("========================= getJsonCheckBoxMapX(p$mapGetter) =========================");
	logInfo("p$mapGetter = " + p$mapGetter);
	
	var v$dataToSend = new Object();
	var v$jsonData;
	
	try{
		logInfo("CheckBoxMap	: " + mapToString(p$mapGetter()));			
		
		for(v$key in p$mapGetter()){
			
			// SI le checkBox a été sélectionné, alors l'on lui associe la valeur TRUE
			if(p$mapGetter()[v$key]){
				v$dataToSend[v$key] = true;				
			}
			// SINON l'on asscoie au checkBox  la valeur FALSE
			else{
				v$dataToSend[v$key] = false;
			}
				
		}
		logInfo("v$dataToSend	: " + mapToString(v$dataToSend));			
		v$jsonData = JSON.stringify(v$dataToSend);
		logInfo("v$jsonData	: " + v$jsonData);			
		
	}
	catch(e){
		logError(e.toString());
	}
	
	logInfo("------------------------- getJsonCheckBoxMapX(p$mapGetter) -------------------------");
	
	return v$jsonData;
}

/**
* Fonction de qui retourne le map de données à envoyer au serveur
* Fonction par défaut devant être utilisé si l'on a fait  usage du checkbox pour la selection et du dataMap01 pour les données saisies
* 
*/
function getJsonDataMap(){
	logInfo("========================= getJsonDataMap() =========================");
	
	var v$dataToSend = new Object();
	var v$jsonData;
	
	try{
		logInfo("CheckBoxMap	: " + mapToString(getCheckBoxMap()));			
		logInfo("DataMap		: " + mapToString(getDataMap()));			
		
		for(v$key in getCheckBoxMap()){
			if(getCheckBoxMap()[v$key]){
				v$dataToSend[v$key] = getDataMap()[v$key];
				
				// Ce contrôle est important afin que les données puissent être correctement converties sous format Json
				// Car s'il y'a dans le map une seule valeur indéfini alors la transformation en Json échoue
				if(typeof v$dataToSend[v$key] == 'undefined' ){
					v$dataToSend[v$key] = null;
				}
			}
		}
		logInfo("v$dataToSend	: " + mapToString(v$dataToSend));			
		v$jsonData = JSON.stringify(v$dataToSend);
		logInfo("v$jsonData	: " + v$jsonData);			
		
	}
	catch(e){
		logError(e.toString());
	}
	
	logInfo("------------------------- getJsonDataMap() -------------------------");
	
	return v$jsonData;
}



/**
* Fonction de qui retourne le map de données à envoyer au serveur
* Elle retourne un Map (SpéciaId (clé), Tableau de valeurs (valeur)) ou les clés correspondent aux spécialId des lignes modifiées
* Et les valeurs issues des différents Maps sont contenues dans un tableau 
* 
* @param p$mapXGetter		: Getter du map X dont l'on veut les données
*/
function getJsonFromMapX(p$map1Getter, p$map2Getter, p$map3Getter, p$map4Getter){
	logInfo("========================= getJsonFromMapX(p$map1Getter, p$map2Getter, p$map3Getter, p$map4Getter) =========================");
	logInfo("p$map1Getter = " + p$map1Getter	+ "		p$map2Getter = " + p$map2Getter + "		p$map3Getter = " + p$map3Getter + "		p$map4Getter = " + p$map4Getter );
	
	var v$dataToSend = new Object();
	var v$jsonData;
	var v$data;
	var v$NO_VALUE = null;
	
	try{
		logInfo("p$map1Getter	: " + mapToString(p$map1Getter()));
		logInfo("p$map2Getter	: " + mapToString(p$map2Getter()));
		logInfo("p$map3Getter	: " + mapToString(p$map3Getter()));
		logInfo("p$map4Getter	: " + mapToString(p$map4Getter()));
		
		if( !(typeof p$map1Getter == 'undefined') ){			
			for(v$key in p$map1Getter()){
				v$data =  new Array();
				v$data[0] = p$map1Getter()[v$key];
				v$dataToSend[v$key] = v$data;
			}			
		}
		
		if( !(typeof p$map2Getter == 'undefined') ){			
			for(v$key in p$map2Getter()){
				
				v$data = v$dataToSend[v$key];
				
				if( typeof v$data == 'undefined'){
					v$data =  new Array();
					v$data[0] = v$NO_VALUE;					
				}
				
				v$data[1] = p$map2Getter()[v$key];
				v$dataToSend[v$key] = v$data;						
			}			
		}
		
		if( !(typeof p$map3Getter == 'undefined') ){			
			for(v$key in p$map3Getter()){
				
				v$data = v$dataToSend[v$key];
				
				if( typeof v$data == 'undefined'){
					v$data =  new Array();
					v$data[0] = v$NO_VALUE;	
					v$data[1] = v$NO_VALUE;
				}
				
				v$data[2] = p$map3Getter()[v$key];
				v$dataToSend[v$key] = v$data;						
			}			
		}
		
		if( !(typeof p$map4Getter == 'undefined') ){			
			for(v$key in p$map4Getter()){
				
				v$data = v$dataToSend[v$key];
				
				if( typeof v$data == 'undefined'){
					v$data =  new Array();
					v$data[0] = v$NO_VALUE;	
					v$data[1] = v$NO_VALUE;
					v$data[2] = v$NO_VALUE;
				}
				
				v$data[3] = p$map4Getter()[v$key];
				v$dataToSend[v$key] = v$data;						
			}			
		}
		
		logInfo("v$dataToSend	: " + mapToString(v$dataToSend));			
		v$jsonData = JSON.stringify(v$dataToSend);
		logInfo("v$jsonData	: " + v$jsonData);			
		
	}
	catch(e){
		logError(e.toString());
	}
	
	logInfo("------------------------- getJsonFromMapX(p$map1Getter, p$map2Getter, p$map3Getter, p$map4Getter) -------------------------");
	
	return v$jsonData;
}


/**
* Fonction qui set les données sous format JSON sur un champ caché de la page
* @param p$shortInputHiddenId	: Id court de la zone cachée devant contenir le JSON
* 
*/
function setJsonData(p$this, p$shortInputHiddenId){
	logInfo("========================= setJsonData() =========================");
	logInfo("p$shortInputHiddenId = " + p$shortInputHiddenId);
	
	try{		
		var v$tableau = p$this.id.split(":");
		var v$idFormulaire = v$tableau[0];
		var v$clientInputHiddenId = v$idFormulaire + ":" + p$shortInputHiddenId;
		logInfo("v$clientInputHiddenId	: " + v$clientInputHiddenId);	

		logInfo("AVANT LE SET	: " + document.getElementById(v$clientInputHiddenId).value);	

		document.getElementById(v$clientInputHiddenId).value = getJsonDataMap() ;
		
		logInfo("APRES LE SET	: " + document.getElementById(v$clientInputHiddenId).value);	
			
	}
	catch(e){
		logError(e.toString());
	}
	
	logInfo("------------------------- setJsonData() -------------------------");
	
}

/**
* Fonction qui set les données sous format JSON sur un champ caché de la page
* @param p$shortInputHiddenId	: Id court de la zone cachée devant contenir le JSON
* @param p$mapGetter			: Getter du CheckBoxMap dont l'on veut les données
*/
function setJsonCheckBoxDataX(p$this, p$shortInputHiddenId, p$mapGetter){
	logInfo("========================= setJsonCheckBoxData(p$this, p$shortInputHiddenId, p$mapGetter) =========================");
	logInfo("p$shortInputHiddenId = " + p$shortInputHiddenId + " 	p$mapGetter = " + p$mapGetter);
	
	try{		
		var v$tableau = p$this.id.split(":");
		var v$idFormulaire = v$tableau[0];
		var v$clientInputHiddenId = v$idFormulaire + ":" + p$shortInputHiddenId;
		logInfo("v$clientInputHiddenId	: " + v$clientInputHiddenId);	

		logInfo("AVANT LE SET	: " + document.getElementById(v$clientInputHiddenId).value);	

		document.getElementById(v$clientInputHiddenId).value = getJsonCheckBoxMapX(p$mapGetter) ;
		
		logInfo("APRES LE SET	: " + document.getElementById(v$clientInputHiddenId).value);	
			
	}
	catch(e){
		logError(e.toString());
	}
	
	logInfo("------------------------- setJsonCheckBoxData(p$this, p$shortInputHiddenId, p$mapGetter) -------------------------");
	
}

/**
* Fonction qui set les données sous format JSON sur un champ caché de la page
* @param p$shortInputHiddenId	: Id court de la zone cachée devant contenir le JSON
* @param p$mapXGetter			: Getter d'un map de données X
*/
function setJsonFromMapX(p$this, p$shortInputHiddenId, p$map1Getter, p$map2Getter, p$map3Getter, p$map4Getter){
	logInfo("========================= setJsonFromMapX(p$this, p$shortInputHiddenId, p$map1Getter, p$map2Getter, p$map3Getter, p$map4Getter) =========================");
	logInfo("p$shortInputHiddenId = " + p$shortInputHiddenId + " 	p$map1Getter = " + p$map1Getter + " 	p$map2Getter = " + p$map2Getter + " 	p$map3Getter = " + p$map3Getter + " 	p$map4Getter = " + p$map4Getter );
	
	try{		
		var v$tableau = p$this.id.split(":");
		var v$idFormulaire = v$tableau[0];
		var v$clientInputHiddenId = v$idFormulaire + ":" + p$shortInputHiddenId;
		logInfo("v$clientInputHiddenId	: " + v$clientInputHiddenId);	

		logInfo("AVANT LE SET	: " + document.getElementById(v$clientInputHiddenId).value);	

		document.getElementById(v$clientInputHiddenId).value = getJsonFromMapX(p$map1Getter, p$map2Getter, p$map3Getter, p$map4Getter) ;
		
		logInfo("APRES LE SET	: " + document.getElementById(v$clientInputHiddenId).value);	
			
	}
	catch(e){
		logError(e.toString());
	}
	
	logInfo("------------------------- setJsonFromMapX(p$this, p$shortInputHiddenId, p$map1Getter, p$map2Getter, p$map3Getter, p$map4Getter) -------------------------");
	
}


/**
* Fonction qui retourne l'ID du formulaire à partir de celui d'un tag
* @param p$clientTagId	: Id client d'un tag
* 
*/
function getIdFormulaire(p$clientTagId){
	logInfo("========================= getIdFormulaire() =========================");
	logInfo("p$clientTagId = " + p$clientTagId);
	
	var v$idFormulaire = "";
	try{		
		var v$tableau = p$clientTagId.split(":");
		v$idFormulaire = v$tableau[0];
		logInfo("v$idFormulaire	: " + v$idFormulaire);				
	}
	catch(e){
		logError(e.toString());
	}
	
	logInfo("------------------------- getIdFormulaire() -------------------------");
	
	return v$idFormulaire;
}

/**
* Fonction de log des informations
* @param p$msg	: Message à logger
* 
*/
function logInfo(p$msg){
	// La fonction alerte est définie dans le fichier util.js
	try{
		alerte("[INFO]		"	+ p$msg);		
	}
	catch(e){		
	}
}

/**
* Fonction de log des informations
* @param p$msg	: Message à logger
* 
*/
function logError(p$msg){
	// La fonction alerte est définie dans le fichier util.js
	try{
		alerte("[ERROR]		"	+ p$msg);			
	}
	catch(e){		
	}
}

/**
* Fonction de convertion d'un tableau/map/object en String
* @param p$mapGetter	: Getter du tableau
* 
*/
function mapToString(p$mapGetter){
	
	var v$string = "";
	try{
		for(v$key in p$mapGetter)	{
			var v$value = p$mapGetter[v$key];
			v$string = v$string + " [ " + v$key + " = " + v$value + " ] ";
		}		
	}
	catch(e){
		logError(e.toString());
	}
	
	return v$string;
}