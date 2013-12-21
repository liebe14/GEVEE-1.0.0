

var test = "test", activateHotKey = new Object();
var listCodeTrt = new Array(), listTrtNonAuth = new Array(), listTrtAuth = new Array(), globalListTrt = null, specialKey = "null", isRowSelected = false;
var mapGaoAsgTrt = null, mapGaoAsg = null, mapAsgTrt = null, mapEtatTrt = null;

var selectedRow = null, debug = false, debug_text = null, defaultStyle = null, enableMenuControls = "true";

function include(src, attributes){
	try {
		attributes = attributes || {};
		attributes.type = "text/javascript";
		attributes.src = src;
		
		var script = document.createElement("script");
		for(aName in attributes)
		    script[aName] = attributes[aName];
		
		document.getElementsByTagName("head")[0].appendChild(script);
		return true;
	} catch(e) { 
		return false; 
	}
}

/*
 * Fonction contenant les opérations à effectuer au chargement d'une page de l'application
 * Elle doit être appelée par l'attribut "onload" du template de base par exemple, mais n'est pas encore utilisée
 */
function init(){
	// Inclusion des fonctions utiles qui pourront être utilisées
//	include("/shared/js/util.js");
	include("../../shared/js/util.js");
	include("../../../shared/js/util.js");
	include("../../../../shared/js/util.js");
	include("../../../../../shared/js/util.js");
	include("../../../../../../shared/js/util.js");

	// Inclusion des fonctions de gestion de calendriers. Normalement elles ne devront, par la suite être incluse que sur les pages qui ont des calendriers
//	include("/shared/js/calendar_functions.js");
	include("../../shared/js/calendar_functions.js");
	include("../../../shared/js/calendar_functions.js");
	include("../../../../shared/js/calendar_functions.js");
	include("../../../../../shared/js/calendar_functions.js");
	include("../../../../../../shared/js/calendar_functions.js");
	
}

/*
 * Fonction qui permet d'afficher les messages dans une zone spécialisée, lorsqu'on est en mode debug
 */
function alerte(msg){
	if(debug){
		try{							
			var tag = document.getElementById("console");
			var initial = tag.innerHTML;
			tag.style.display = "block";
			tag.innerHTML = initial+"<br/>"+msg;
			tag.scrollTop = tag.scrollHeight;							
		}catch(e){
			//alert(msg);
		}
	}
}

/*
 * Fonction qui initialise la console sur laquelle s'affiche les messages de la fonction "alerte"
 */
function initialiseConsole(){
	try{						
		var tag = document.getElementById("console");						
		tag.innerHTML = "";
		tag.style.display = "none";
	}catch(e){
		//alert("console not found");
	}
}


/*
 * Fonction qui retourne l'id réel d'un traitement, en faisant abstraction de la partie contenant l'index
 * car l'index ne sert qu'à ordonner les traitements lors de l'affichage des menus
 */
function getTrtRealId(trt){
	var tab = new Array();
	tab = trt;
	tab[tab.length-1] = null;
	return tab.join("-");					
}

/*
 * Fonction qui permet de savoir si une chaine de caractère(celle du specialKey ou specialId en particulier) est nulle
 */
function isNull(key){
	return ((key == null) || (key == "null") || (key == undefined));
}

/*
 * Fonction qui permet de savoir si une ligne du tableau du formulaire liste est sélectionnée
 * Elle suppose qu'une ligne est sélectionné lorsqu'on est sur le formulaire Détail ou Edition
 */
function isARowSelected(){
	if((isRowSelected) || (!isPageList())) return true;
	else{
		try{						
			var tag = document.getElementById("contentForm:tble_table:tb");
			var tab = tag.getElementsByTagName("tr");
			var classe;
			//alerte("nombre de lignes du tableau = "+tab.length);
			for(var i=0; i < tab.length; i++){
				try{
					classe = tab[i].getAttribute("class");
					//alerte("classe du tr = "+classe);
					if(classe.indexOf("row-selected") >= 0) {
						alerte("Une ligne a été sélectionnée");
						isRowSelected = true;
						return true;
					}
				}catch(e){
					//alerte("propriété du tr non trouvée");
				}
			}						
		}catch(ex){
			alerte("Aucune ligne n'a été sélectionnée");
		}
		return false;
	}
}

/*
 * Fonction qui retourne la ligne du tableau qui est sélectionnée
 */
function getSelectedRow(row){					
	if((isPageList()) && (row == null) && (isARowSelected())){
		try{							
			var tag = document.getElementById("contentForm:tble_table:tb");
			var tab = tag.getElementsByTagName("tr");
			var classe;
			for(var i=0; i < tab.length; i++){
				try{
					classe = tab[i].getAttribute("class");
					if(classe.indexOf("row-selected") >= 0) {									
						return tab[i];
					}
				}catch(e){
					
				}
			}						
		}catch(ex){
			alerte("Aucune ligne n'a été sélectionnée");
		}
	}
	else return row;
	return null;
}

/*
 * Fonction qui permet de savoir si le tableau du formulaire liste contient des résultats
 */
function pageContainsResults(){
	if(isPageList()){
		try{							
			var tag = document.getElementById("contentForm:tble_table:noDataRow");
			if(isNull(tag) == false) {
				alerte("Le tableau ne contient aucun élément");
				return false;	
			}				
		}catch(e){
			alerte("Le tableau contient des éléments");
		}						
	}
	alerte("Le tableau contient des éléments");
	return true;
}

/*
 * Fonction qui permet de savoir si la page est de type formulaire liste
 */
function isPageList(){
	try{						
		var tag = document.getElementById("iphd_page");
		var iphd = tag.value;
		if(iphd.indexOf("Liste") >= 0) {
			//alerte("Formulaire = Liste");
			return true;
		}
	}catch(e){
		alerte("Formulaire n'est pas une liste");
	}
	return false;
}

/*
 * Fonction qui permet d'initialiser tous les maps qui seront utilisées pour la gestion dynamique des menus
 */
function setMaps(){
	var jsonGaoAsgTrt, jsonEtatCodeTrt,  jsonGaoAsg, jsonAsgTrt, mapEtatCodeTrt;
	try{
		jsonGaoAsgTrt = document.getElementById('mapGaoAsgTrt').value;
		jsonEtatCodeTrt = document.getElementById('mapEtatTrt').value; 
		
		//if((specialKey == "null") || (specialKey == null))
		if(isNull(specialKey))
			specialKey = document.getElementById('contentForm:specialKey').value;
		
		alerte("gaoAsgTrt = "+jsonGaoAsgTrt);
		alerte("EtatTrt = "+jsonEtatCodeTrt);
		//alert("specialKey = "+specialKey);
		
		mapGaoAsgTrt = eval("("+jsonGaoAsgTrt+")");
		mapEtatCodeTrt = eval("("+jsonEtatCodeTrt+")");
		
		mapGaoAsg = mapGaoAsgTrt["mapGaoAsg"];
		mapAsgTrt = mapGaoAsgTrt["mapAsgTrt"];						
		
		mapEtatTrt = mapEtatCodeTrt["mapEtatTrt"];
		listCodeTrt = mapEtatCodeTrt["codeTrt"];

		alerte("liste codes traitements = "+listCodeTrt);
		
	}catch(e){
		alerte("Au moins un element est introuvable");
	}
	
}

/*
 * Fonction qui permet de construire la liste des traitements autorisés, pour une valeur du spécialKey ou specialId
 */
function setListTrtAuth(key){
	var id, gao, etat, ouvertEnProp, ouvertEnPrep, ouvertEnExec, asg, asgOfGenericGao, listTrtAsg = new Array(), listTrtEtat = new Array(), listTrtAsg1 = new Array(), listTrtAsg2 = new Array();
	
	if(key!=undefined) specialKey = key;
							
	if((mapGaoAsgTrt == null) && (mapGaoAsg == null) && (mapAsgTrt == null)){
		setMaps();
	}

	if((isARowSelected() == false) && (isPageList() == true)){						
		specialKey = "null";												
	}
	
		try{
		alerte("special key = "+specialKey);
		
		keys = specialKey.split("-");
		}catch(e){
			keys = new Array();
		}
		id = keys[0];
		gao = keys[1];
		etat = keys[2];
		ouvertEnProp = keys[3];
		ouvertEnPrep = keys[4];
		ouvertEnExec = keys[5];
		
		alerte("id = "+id+", gao = "+gao+", etat = "+etat);
//		try{
//			asgOfGenericGao = mapGaoAsg["GAO_*"];
//			alerte("assignataire du gao générique = "+asgOfGenericGao);
//		}catch(e){
//			alerte("l'entité n'a pas le GAO générique");
//			getParamValue = null;
//		}
		//alert("asg du gao générique = "+asgOfGenericGao);
		// Liste courante des traitements à autoriser
		
		
		if(isNull(gao)){ 
			try{
				// Utiliser assignataire par défaut dans le cas où entité n a pas de gao
				asg = mapGaoAsg["$"];
				listTrtAsg = listToArray(mapAsgTrt[asg]);
				alerte("Assignataire par défaut = "+asg);
			}catch(ex){
				alerte("Erreur lors de la récupération de l'assignataire par défaut");
			}
		}
		else {
//			asg = mapGaoAsg[gao];
			var allGao = new Array();
			alerte("récupération de tous les gao");
			allGao = getAllGao(gao);
			var currentListTrt = new Array();
			alerte("allGao.length = "+allGao.length);
			//for(var i in allGao){
			for(var i=0; i<allGao.length; i++){
				var oneGao = allGao[i];
				oneAsg = mapGaoAsg[oneGao];
				listTrtAsg1 = currentListTrt;
				listTrtAsg2 = listToArray(mapAsgTrt[oneAsg]);
				alerte("gao N°"+i+" = "+oneGao);
				alerte("asg N°"+i+" = "+oneAsg);
				alerte("liste de traitements N°"+i+" = "+listTrtAsg2);
				currentListTrt = union(listTrtAsg1, listTrtAsg2);
			}
			listTrtAsg = currentListTrt;
			
		}
		alerte("liste complete de traitements"+i+" = "+listTrtAsg);
		
//		alerte("assignataire = "+asg);
//		//if(isNull(asg)) alerte("asg est null ou indéfini");
//		try{			
//			listTrtAsg1 = listToArray(mapAsgTrt[asg]);							
//		}catch(e){
//			alerte("Element non trouvé dans asg-trt");
//		}
//		try{							
//			listTrtAsg2 = listToArray(mapAsgTrt[asgOfGenericGao]);
//		}catch(e){
//			alerte("Element non trouvé dans asg du gao générique-trt");
//		}
//		alerte("liste de traitements par rapport à l'assignataire = "+listTrtAsg1);
//		alerte("liste de traitements par rapport à l'assignataire du gao générique = "+listTrtAsg2);
//
//		listTrtAsg = union(listTrtAsg1, listTrtAsg2);
//		alerte("Fin de l'union des array");
//		alerte("liste de traitements par rapport à l'assignataire + gao générique = "+listTrtAsg);
		globalListTrtAsg = listTrtAsg;
		alerte("globalListTrtAsg est assigné et a pour longueur "+globalListTrtAsg.length);
		try{
			listTrtEtat = listToArray(mapEtatTrt[etat]);
			alerte("liste de traitements par rapport à l'état"+etat+" = "+listTrtEtat);						
		}catch(e){
			alerte("erreur lors de la recuperation de a liste des traitements par rapport à l'etat "+etat);
		}
	
		
		listTrtAuth = new Array();
		listTrtNonAuth = new Array();
		alerte("rappel liste des codes traitements = "+listCodeTrt);
		for(var j=0; j<listCodeTrt.length; j++){
		// for(fullTrt in listCodeTrt){
			try{
				var fullTrt = listCodeTrt[j];
				var part = new Array();
				part = fullTrt.split("-");
				var Trt = part[0];
				var impact = part[part.length-1];
				var trtRealId = "";
				part.pop(); // suppression du dernier élément du tableau
				trtRealId = part.join("-");
				var autorise = false;
				//alerte("impact de "+trtRealId+" = "+impact);
				/*
				 * si le premier caractère vaut 1, alors il faut tenir compte du droit de l'utilisateur pour ce traitement, si 0, ne pas
				 * Si le second caractère vaut 1, alors il faut tenir compte de l'état de l'entité pour ce traitement, si 0, ne pas
				 * Si le troisième caractère vaut 0, alors il ne faux pas tenir compte du fait qu'il y ait des résultats dans la liste
				 * s'il vaur 1, alors il faut, pour que le traitement s'applique, qu'il y ait des résultlats issues d'une recherche
				 * s'il vaut 2, alors il est nécessaire qu'une entité soit sélectionnée pour que le traitement s'affiche
				 */
				if(impact.charAt(0) == '1'){
					if(content(listTrtAsg,Trt)){
							if(impact.charAt(1) == '1'){			
								if(content(listTrtEtat,Trt)){							
									if(((impact.charAt(2) == '2') && (isARowSelected())) || ((impact.charAt(2) == '1') && (pageContainsResults())) || (impact.charAt(2) == '0')){
										//alerte(fullTrt+" est autorisé");
										autorise = true;
									}	
								}
							}
							else if(((impact.charAt(2) == '2') && (isARowSelected())) || ((impact.charAt(2) == '1') && (pageContainsResults())) || (impact.charAt(2) == '0')){
								//alerte(fullTrt+" est autorisé");
									autorise = true;
							}						
					}	
				}
				else{
					if(impact.charAt(1) == '1'){
						if(content(listTrtEtat,Trt)){
							if(((impact.charAt(2) == '2') && (isARowSelected())) || ((impact.charAt(2) == '1') && (pageContainsResults())) || (impact.charAt(2) == '0')){
								//alerte(fullTrt+" est autorisé");
								autorise = true;
							}	
						}								
					}
					else if(((impact.charAt(2) == '2') && (isARowSelected())) || ((impact.charAt(2) == '1') && (pageContainsResults())) || (impact.charAt(2) == '0')){
						//alerte(fullTrt+" est autorisé");
						autorise = true;
					}	
				}	
			}catch(e){ 
				// en cas d'erreur
				autorise = true;
				alerte("Trt non conforme (sera de ce fait autorisé) : "+fullTrt);
			}
			
			if(autorise){
				// tests des traitements particuliers dont la disponibilité est déterminée par des tags
				// affichage du traitement de saisie de proposition si la ligne est ouverte en proposition et en préparation
				if(trtRealId == "LgnBdg_T44"){
					if ((ouvertEnPrep == "1") && (ouvertEnProp == "1")) autorise = true;
					else autorise = false;
				}
				// affichage des traitements saisie de prévision ou de modification de la ligne, si elle est ouverte en préparation
				else if ((trtRealId == "LgnBdg_T45") || (trtRealId == "LgnBdg_T02")){
					if (ouvertEnPrep == "1") autorise = true;
					else autorise = false;
				}
			}
			
			if(autorise) listTrtAuth.push(trtRealId);
			else listTrtNonAuth.push(trtRealId);
									
		}
		
		alerte("Traitements autorisés = "+listTrtAuth);
		alerte("Traitements non autorisés = "+listTrtNonAuth);
								
	//}
							
}

/*
 * Fonction qui retourne la liste des GAO dérivés pour lesquels il faut rechercher l'existence d'assignataires
 */
function getAllGao(gao){
	
	var GAO_SUF = "*";
	var GAO_SEP = "_";	
	//var GAO_GEN = "GAO_*";
	var GAO_GEN = "*";
	var tabGao = new Array();
	var result = new Array();
	try{
		tabGao = gao.split(GAO_SEP, gao.length);
		var initialTabGao = cloneArray(tabGao);
		var currentPrefix = "";
		alerte("tabGao.length = "+tabGao.length);
		
		// Construction de la liste des clés de GAO possibles avec une structure hiérarchique
		for(var i=0; i<tabGao.length; i++){	
			
			oneGao = tabGao[i];
			alerte("tabGao["+i+"] = "+tabGao[i]);
			currentPrefix += oneGao;
			var currentName = currentPrefix;
			currentPrefix += GAO_SEP;
			if(i < tabGao.length-1)
				tabGao[i] = currentPrefix + GAO_SUF;
			else 
				tabGao[i] = currentName;
			
		}
		// Le GAO_* doit toujours faire partie des GAO à partir desquels la recherche doit être faite, il faut donc l'ajouter
		// Toutefois, d'après la formule de calcul hiérarchiqe, il devrait déjà faire partie des résultats: simple mésure de précaution
		tabGao.push(GAO_GEN);
		
		// Ajout du résultat obtenu parmi les résultats à fournir
		result = cloneArray(tabGao);
		
		// Ajout des combinaisons contenant une fois le séparateur générique ou suffixe GAO à la place d'un des éléments du tableau initial obtenu à partir du GAO
		result = replaceOneByOne(initialTabGao, result, GAO_SUF, GAO_SEP);
		
		// Ajout d'au maximum [longueur du tableau initial - 1] séparateurs génériques (ou suffixe GAO)
		for(var l = 1; l < initialTabGao.length; l++){
		
			// Pour chaque élément du résultat, on ajoute encore une fois le caractère générique ou suffixe GAO
			for(var k = 1; k < result.length; k++){
				
				// On utilise cette fois-ci les éléments du tableau initial comme si chacun d'eux était un GAO auquel on allait ajouter au maximum un caractère générique parmi ses possibilités
				var currentTabGao = result[k].split(GAO_SEP);
				
				result = replaceOneByOne(currentTabGao, result, GAO_SUF, GAO_SEP);
				
			}
		
		}
		
		var resultat = "";
		//for(j in tabGao){
		for(var j=0; j<result.length; j++){
			resultat += "tabGao["+j+"] = "+result[j]+"   ";
		}
		alerte("resultat : "+resultat);
		
	}catch(e){
		alerte(e.toString()+" Erreur lors de la récupération des gao génériques");
	}
	
	return result;

}
 
function replaceOneByOne(initialTab, finalTab, newString, separator){
	
	// Initialisation du résultat final : c''est le rôle du finalTab
	var result = cloneArray(finalTab);
	
	// Ajout des combinaisons contenant le séparateur générique une fois à la place d'un des éléments du tableau
	for(var j = 1; j < initialTab.length; j++){
		
		var tabToAdd = cloneArray(initialTab);
		tabToAdd[j] = newString;
		
		result.push(tabToAdd.join(separator));
		
	}

	result = unique(result);
	
	return result;
}

/*
 * Fonction qui permet de controler la valeur de l'attribut "readonly" d'une zone selon la disponibilité d'un traitement
 * Elle renvoie true si le traitement "codeTrt" n'est autorisé, rendant ainsi non éditable le composant "variation"
 */ 
function controlReadonly(variation, codeTrt){
	
	var readonly = "true";  // par défaut la zone de saisie est en lecture seule
	var variationId = variation.id;
	alerte("id du composant = "+variationId);
	var tab = variationId.split(":");
	var specialId = tab[2];
	alerte("special key de la ligne = "+specialId);
	
	setListTrtAuth(specialId);
	try{
		var i = 0;						
		while((i < listTrtAuth.length) && (readonly == "true")){							
			// Si l élément à la position "i" du tableau contient une chaine correspondant au code du traitement, la saisie est autorisée
			if(listTrtAuth[i].indexOf(codeTrt) >= 0){	 
				readonly = "false"; // on autorise la saisie sur la zone
				alerte("traitement "+codeTrt+" est autorisé");
			}
			i++;	
		}
	}catch(e){
		alerte('liste de traitement autorisée vide ou non conforme');
	}				
	return readonly;
						
}

/*
 * Fonction qui permet rendre disponible en édition une zone en fonction de la disponibilité d'un traitement
 */
function controlVariation(variation, traitement){
	var readonly = "true";
	if(traitement == "prevision") readonly = controlReadonly(variation, "LgnBdg_T45");
	else if(traitement == "proposition") readonly = controlReadonly(variation, "LgnBdg_T44");
	else if(traitement == "mesuresNouvelles") readonly = controlReadonly(variation, "701117");

	alerte("Idenfifiant du composant de saisie = "+variation.id);
	
	if(readonly == "true") {
		variation.setAttribute("readonly",readonly);
		alerte("Interdiction de saisie sur le composant d'id = "+variation.id);
	}
	else {
		try{
			variation.removeAttribute("readonly");
			alerte("Autorisation de saisie sur le composant d'id = "+variation.id);
		}catch(ex){
			alerte("l'attribut readonly n'existe pas");
		}
	}
}
		

/*
 * Fonction qui permet de mettre à jour les menus, juste avant leur affichage
 * C'est la fonction d'entrée de la gestion dynamique des menus de l'application
 */
function updateMenu(){
	
	try{
		enableMenuControls = document.getElementById("enableControls").getAttribute("value");
	}catch(e){
		alerte("Valeur d'activation de la gestion du menu contextuel dynamique non définie");
	}
	alerte('enableControls = '+enableMenuControls);
	// On n'exécute la fonction que si la valeur enableMenuControls est true
	if(enableMenuControls == "true"){
		
		var row = getSelectedRow();
		var key = getSpecialKey(row);

		initialiseConsole();
		
		
		setListTrtAuth(key);
		
		for(var i=0;i < listTrtAuth.length; i++){
			renderMenuItem(listTrtAuth[i], true);
		}

		for(var i=0;i < listTrtNonAuth.length; i++){
			renderMenuItem(listTrtNonAuth[i], false);
		}

	}
	
}

/*
 * Fonction de retour de l id du menu contextuel
 * Elle n est pas utilisée et doit être supprimée
 */
function getContextMenuId(){
	var tagForm = document.getElementById("actionForm");
	var tags = tagForm.getElementsByTagName("div");
	var ids = new Array();
	for(var i=0; i < tags.length; i++){
		try{
			var id = tags[i].id;
			if(id.indexOf("contextMenu") >= 0){
				ids.push(id);
				//alert("ID span N°"+i+" de contextMenu = "+id);
			}
		}catch(e){
			
		}						
	}
}

/*
 * Fonction qui retourne, pour une ligne donnée, le specialKey qu'elle contient
 */
function getSpecialKey(row) {
	if(isPageList()){
		try{
			var tds = row.getElementsByTagName("td");						
			var td_id = tds[0].id;
			//alert("Id = "+td_id);
			var part = td_id.split(":");
			isRowSelected = true;
			//alerte("special key déterminé à partir du formulaire liste, c-à-d de la ligne du tableau sélectionnée");
			debug_text = "special key déterminé à partir du formulaire liste, c-à-d de la ligne du tableau sélectionnée";
			return part[part.length - 2];
		}catch(e){
			//alerte("un special key null sera retourné sur formulaire liste");
			debug_text = "un special key null sera retourné sur formulaire liste";
			isRowSelected = false;
			return null;
		}	
	}
	else{
		try{
			//alerte("special key déterminé à partir de la valeur en session");
			debug_text = "special key déterminé à partir de la valeur en session";
			return document.getElementById('contentForm:specialKey').value;
		}catch(e){
			//alerte("un special key null sera retourné sur formulaire detail");
			debug_text = "un special key null sera retourné sur formulaire detail";
			return null;
		}	
	}
}

/*
 * Fonction qui permet d'afficher ou de masquer une zone (principalement un menu), connaissant son id
 */
function renderMenuItem(id, show){
	var prefix_menuBar = "actionForm:menu-";
	var prefix_toolBar = "actionForm:toolbar-";
	var prefix_contextMenu = "actionForm:contextMenu-";
	//var prefix_contextMenu = "contentForm:contextMenu-";
	var tag_m, tag_t, tag_c, control = "initial";
	
	try { 
		// On tente de récupérer la balise cible dont on doit changer la visibilité
		tag_m = document.getElementById(prefix_menuBar+""+id);						
		tag_c = document.getElementById(prefix_contextMenu+""+id);
		tag_t = document.getElementById(prefix_toolBar+""+id);  

		if(show){
			
			alerte("affichage de "+id);
			
			if(tag_m != null){
				//tag_m.style.visibility = "visible";
				tag_m.removeAttribute("style");
				//tag_m.setAttribute("style","display:inherit");								
				/*
				var tag_div = document.getElementById("actionForm:ctmn_menu_menu");
				tag_div.style.left="751px";
				tag_div.style.top="284px";
				*/
			}
			if(tag_c != null){
				//tag_c.style.visibility = "visible";
				tag_c.removeAttribute("style");
			}	
			if(tag_t != null)
				tag_t.disabled = false;
			activateHotKey[id] = true;
		}
		else{
//			if(defaultStyle == null){
//				defaultStyle = tag_m.getAttribute("style");
//				//alerte("style par défaut = "+defaultStyle);
//			}
//			
			alerte("masquage de "+id);
			if(tag_m != null){
				tag_m.setAttribute("style","display:none");
			}
			if(tag_c != null){
				tag_c.setAttribute("style","display:none");
			}
			if(tag_t != null)
				tag_t.disabled = true;
			activateHotKey[id] = false;
		}
	} catch (e) { 
		// Si échec de la récupération de la balise cible
		alerte("erreur: "+e);
		alerte(e.message);
		//alert('La balise cible ('+control+' '+id+') n\'a pu être trouvée à partir de son ID ou a rencontré un autre problème');
	}
						
}

/*
 * Fonction qui permet de mettre à jour le menu de gauche de l'application
 * TODO - vérifier que cette fonction est utilisée, et dans le cas contraire, la supprimer
 */
function updateLeftMenu(){
//	alerte('update of left menu');
//	var listEntityCodeAuth = new Array(), msg = "";
//	if(globalListTrt == null){
//		if(mapAsgTrt == null){
//			setMaps();
//		}
//		globalListTrt = new Array();
//		for(asg in mapAsgTrt){
//			globalListTrt = union(globalListTrt,listToArray(mapAsgTrt[asg]));
//		}
//	}
//	alerte("longeur du tableau des traitements autorisés à l'utilisateur quelque soit l'assignataire = "+globalListTrt.length);
//	alerte("liste des traitements = "+globalListTrt);
//	msg = "codes des entités à afficher : ";
//	for(var j=0; j<globalListTrt.length; j++){
//		var glabalTrt = globalListTrt[j];
//		var entityCode = glabalTrt.substr(0,3);
//		listEntityCodeAuth.push(entityCode);
//		msg = msg+", "+entityCode;
//		var idAuth = "MenuForm:pnel_"+entityCode;
//		try{
//			//document.getElementById(idAuth).setAttribute("style", "display:inline");
//			//document.getElementById(idAuth).setAttribute("style", "display:none");
//			document.getElementById(idAuth).setAttribute("disabled", "true");
//			
//			//document.getElementById(idAuth).setAttribute("style", "visibility:hidden");			
//			//document.getElementById(idAuth).style = "display:none;";
//			//alert('property style of id '+idAuth+' has been changed');
//			//document.getElementById(idAuth).parentNode.setAttribute("style", "display:inline");
//			//document.getElementById(idAuth).parentNode.setAttribute("style", "display:none");
//			//alert('property style of parent of id '+idAuth+' has been changed');
//			alerte("Elément de menu d'identitifant "+idAuth+" trouvé et masqué");
//		}catch(e){
//			alerte("Elément de menu d'identitifant "+idAuth+" non trouvé");
//		}		
//	}
//	alerte(msg);
	
}