

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
 * Fonction contenant les op�rations � effectuer au chargement d'une page de l'application
 * Elle doit �tre appel�e par l'attribut "onload" du template de base par exemple, mais n'est pas encore utilis�e
 */
function init(){
	// Inclusion des fonctions utiles qui pourront �tre utilis�es
//	include("/shared/js/util.js");
	include("../../shared/js/util.js");
	include("../../../shared/js/util.js");
	include("../../../../shared/js/util.js");
	include("../../../../../shared/js/util.js");
	include("../../../../../../shared/js/util.js");

	// Inclusion des fonctions de gestion de calendriers. Normalement elles ne devront, par la suite �tre incluse que sur les pages qui ont des calendriers
//	include("/shared/js/calendar_functions.js");
	include("../../shared/js/calendar_functions.js");
	include("../../../shared/js/calendar_functions.js");
	include("../../../../shared/js/calendar_functions.js");
	include("../../../../../shared/js/calendar_functions.js");
	include("../../../../../../shared/js/calendar_functions.js");
	
}

/*
 * Fonction qui permet d'afficher les messages dans une zone sp�cialis�e, lorsqu'on est en mode debug
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
 * Fonction qui retourne l'id r�el d'un traitement, en faisant abstraction de la partie contenant l'index
 * car l'index ne sert qu'� ordonner les traitements lors de l'affichage des menus
 */
function getTrtRealId(trt){
	var tab = new Array();
	tab = trt;
	tab[tab.length-1] = null;
	return tab.join("-");					
}

/*
 * Fonction qui permet de savoir si une chaine de caract�re(celle du specialKey ou specialId en particulier) est nulle
 */
function isNull(key){
	return ((key == null) || (key == "null") || (key == undefined));
}

/*
 * Fonction qui permet de savoir si une ligne du tableau du formulaire liste est s�lectionn�e
 * Elle suppose qu'une ligne est s�lectionn� lorsqu'on est sur le formulaire D�tail ou Edition
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
						alerte("Une ligne a �t� s�lectionn�e");
						isRowSelected = true;
						return true;
					}
				}catch(e){
					//alerte("propri�t� du tr non trouv�e");
				}
			}						
		}catch(ex){
			alerte("Aucune ligne n'a �t� s�lectionn�e");
		}
		return false;
	}
}

/*
 * Fonction qui retourne la ligne du tableau qui est s�lectionn�e
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
			alerte("Aucune ligne n'a �t� s�lectionn�e");
		}
	}
	else return row;
	return null;
}

/*
 * Fonction qui permet de savoir si le tableau du formulaire liste contient des r�sultats
 */
function pageContainsResults(){
	if(isPageList()){
		try{							
			var tag = document.getElementById("contentForm:tble_table:noDataRow");
			if(isNull(tag) == false) {
				alerte("Le tableau ne contient aucun �l�ment");
				return false;	
			}				
		}catch(e){
			alerte("Le tableau contient des �l�ments");
		}						
	}
	alerte("Le tableau contient des �l�ments");
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
 * Fonction qui permet d'initialiser tous les maps qui seront utilis�es pour la gestion dynamique des menus
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
 * Fonction qui permet de construire la liste des traitements autoris�s, pour une valeur du sp�cialKey ou specialId
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
//			alerte("assignataire du gao g�n�rique = "+asgOfGenericGao);
//		}catch(e){
//			alerte("l'entit� n'a pas le GAO g�n�rique");
//			getParamValue = null;
//		}
		//alert("asg du gao g�n�rique = "+asgOfGenericGao);
		// Liste courante des traitements � autoriser
		
		
		if(isNull(gao)){ 
			try{
				// Utiliser assignataire par d�faut dans le cas o� entit� n a pas de gao
				asg = mapGaoAsg["$"];
				listTrtAsg = listToArray(mapAsgTrt[asg]);
				alerte("Assignataire par d�faut = "+asg);
			}catch(ex){
				alerte("Erreur lors de la r�cup�ration de l'assignataire par d�faut");
			}
		}
		else {
//			asg = mapGaoAsg[gao];
			var allGao = new Array();
			alerte("r�cup�ration de tous les gao");
			allGao = getAllGao(gao);
			var currentListTrt = new Array();
			alerte("allGao.length = "+allGao.length);
			//for(var i in allGao){
			for(var i=0; i<allGao.length; i++){
				var oneGao = allGao[i];
				oneAsg = mapGaoAsg[oneGao];
				listTrtAsg1 = currentListTrt;
				listTrtAsg2 = listToArray(mapAsgTrt[oneAsg]);
				alerte("gao N�"+i+" = "+oneGao);
				alerte("asg N�"+i+" = "+oneAsg);
				alerte("liste de traitements N�"+i+" = "+listTrtAsg2);
				currentListTrt = union(listTrtAsg1, listTrtAsg2);
			}
			listTrtAsg = currentListTrt;
			
		}
		alerte("liste complete de traitements"+i+" = "+listTrtAsg);
		
//		alerte("assignataire = "+asg);
//		//if(isNull(asg)) alerte("asg est null ou ind�fini");
//		try{			
//			listTrtAsg1 = listToArray(mapAsgTrt[asg]);							
//		}catch(e){
//			alerte("Element non trouv� dans asg-trt");
//		}
//		try{							
//			listTrtAsg2 = listToArray(mapAsgTrt[asgOfGenericGao]);
//		}catch(e){
//			alerte("Element non trouv� dans asg du gao g�n�rique-trt");
//		}
//		alerte("liste de traitements par rapport � l'assignataire = "+listTrtAsg1);
//		alerte("liste de traitements par rapport � l'assignataire du gao g�n�rique = "+listTrtAsg2);
//
//		listTrtAsg = union(listTrtAsg1, listTrtAsg2);
//		alerte("Fin de l'union des array");
//		alerte("liste de traitements par rapport � l'assignataire + gao g�n�rique = "+listTrtAsg);
		globalListTrtAsg = listTrtAsg;
		alerte("globalListTrtAsg est assign� et a pour longueur "+globalListTrtAsg.length);
		try{
			listTrtEtat = listToArray(mapEtatTrt[etat]);
			alerte("liste de traitements par rapport � l'�tat"+etat+" = "+listTrtEtat);						
		}catch(e){
			alerte("erreur lors de la recuperation de a liste des traitements par rapport � l'etat "+etat);
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
				part.pop(); // suppression du dernier �l�ment du tableau
				trtRealId = part.join("-");
				var autorise = false;
				//alerte("impact de "+trtRealId+" = "+impact);
				/*
				 * si le premier caract�re vaut 1, alors il faut tenir compte du droit de l'utilisateur pour ce traitement, si 0, ne pas
				 * Si le second caract�re vaut 1, alors il faut tenir compte de l'�tat de l'entit� pour ce traitement, si 0, ne pas
				 * Si le troisi�me caract�re vaut 0, alors il ne faux pas tenir compte du fait qu'il y ait des r�sultats dans la liste
				 * s'il vaur 1, alors il faut, pour que le traitement s'applique, qu'il y ait des r�sultlats issues d'une recherche
				 * s'il vaut 2, alors il est n�cessaire qu'une entit� soit s�lectionn�e pour que le traitement s'affiche
				 */
				if(impact.charAt(0) == '1'){
					if(content(listTrtAsg,Trt)){
							if(impact.charAt(1) == '1'){			
								if(content(listTrtEtat,Trt)){							
									if(((impact.charAt(2) == '2') && (isARowSelected())) || ((impact.charAt(2) == '1') && (pageContainsResults())) || (impact.charAt(2) == '0')){
										//alerte(fullTrt+" est autoris�");
										autorise = true;
									}	
								}
							}
							else if(((impact.charAt(2) == '2') && (isARowSelected())) || ((impact.charAt(2) == '1') && (pageContainsResults())) || (impact.charAt(2) == '0')){
								//alerte(fullTrt+" est autoris�");
									autorise = true;
							}						
					}	
				}
				else{
					if(impact.charAt(1) == '1'){
						if(content(listTrtEtat,Trt)){
							if(((impact.charAt(2) == '2') && (isARowSelected())) || ((impact.charAt(2) == '1') && (pageContainsResults())) || (impact.charAt(2) == '0')){
								//alerte(fullTrt+" est autoris�");
								autorise = true;
							}	
						}								
					}
					else if(((impact.charAt(2) == '2') && (isARowSelected())) || ((impact.charAt(2) == '1') && (pageContainsResults())) || (impact.charAt(2) == '0')){
						//alerte(fullTrt+" est autoris�");
						autorise = true;
					}	
				}	
			}catch(e){ 
				// en cas d'erreur
				autorise = true;
				alerte("Trt non conforme (sera de ce fait autoris�) : "+fullTrt);
			}
			
			if(autorise){
				// tests des traitements particuliers dont la disponibilit� est d�termin�e par des tags
				// affichage du traitement de saisie de proposition si la ligne est ouverte en proposition et en pr�paration
				if(trtRealId == "LgnBdg_T44"){
					if ((ouvertEnPrep == "1") && (ouvertEnProp == "1")) autorise = true;
					else autorise = false;
				}
				// affichage des traitements saisie de pr�vision ou de modification de la ligne, si elle est ouverte en pr�paration
				else if ((trtRealId == "LgnBdg_T45") || (trtRealId == "LgnBdg_T02")){
					if (ouvertEnPrep == "1") autorise = true;
					else autorise = false;
				}
			}
			
			if(autorise) listTrtAuth.push(trtRealId);
			else listTrtNonAuth.push(trtRealId);
									
		}
		
		alerte("Traitements autoris�s = "+listTrtAuth);
		alerte("Traitements non autoris�s = "+listTrtNonAuth);
								
	//}
							
}

/*
 * Fonction qui retourne la liste des GAO d�riv�s pour lesquels il faut rechercher l'existence d'assignataires
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
		
		// Construction de la liste des cl�s de GAO possibles avec une structure hi�rarchique
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
		// Le GAO_* doit toujours faire partie des GAO � partir desquels la recherche doit �tre faite, il faut donc l'ajouter
		// Toutefois, d'apr�s la formule de calcul hi�rarchiqe, il devrait d�j� faire partie des r�sultats: simple m�sure de pr�caution
		tabGao.push(GAO_GEN);
		
		// Ajout du r�sultat obtenu parmi les r�sultats � fournir
		result = cloneArray(tabGao);
		
		// Ajout des combinaisons contenant une fois le s�parateur g�n�rique ou suffixe GAO � la place d'un des �l�ments du tableau initial obtenu � partir du GAO
		result = replaceOneByOne(initialTabGao, result, GAO_SUF, GAO_SEP);
		
		// Ajout d'au maximum [longueur du tableau initial - 1] s�parateurs g�n�riques (ou suffixe GAO)
		for(var l = 1; l < initialTabGao.length; l++){
		
			// Pour chaque �l�ment du r�sultat, on ajoute encore une fois le caract�re g�n�rique ou suffixe GAO
			for(var k = 1; k < result.length; k++){
				
				// On utilise cette fois-ci les �l�ments du tableau initial comme si chacun d'eux �tait un GAO auquel on allait ajouter au maximum un caract�re g�n�rique parmi ses possibilit�s
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
		alerte(e.toString()+" Erreur lors de la r�cup�ration des gao g�n�riques");
	}
	
	return result;

}
 
function replaceOneByOne(initialTab, finalTab, newString, separator){
	
	// Initialisation du r�sultat final : c''est le r�le du finalTab
	var result = cloneArray(finalTab);
	
	// Ajout des combinaisons contenant le s�parateur g�n�rique une fois � la place d'un des �l�ments du tableau
	for(var j = 1; j < initialTab.length; j++){
		
		var tabToAdd = cloneArray(initialTab);
		tabToAdd[j] = newString;
		
		result.push(tabToAdd.join(separator));
		
	}

	result = unique(result);
	
	return result;
}

/*
 * Fonction qui permet de controler la valeur de l'attribut "readonly" d'une zone selon la disponibilit� d'un traitement
 * Elle renvoie true si le traitement "codeTrt" n'est autoris�, rendant ainsi non �ditable le composant "variation"
 */ 
function controlReadonly(variation, codeTrt){
	
	var readonly = "true";  // par d�faut la zone de saisie est en lecture seule
	var variationId = variation.id;
	alerte("id du composant = "+variationId);
	var tab = variationId.split(":");
	var specialId = tab[2];
	alerte("special key de la ligne = "+specialId);
	
	setListTrtAuth(specialId);
	try{
		var i = 0;						
		while((i < listTrtAuth.length) && (readonly == "true")){							
			// Si l �l�ment � la position "i" du tableau contient une chaine correspondant au code du traitement, la saisie est autoris�e
			if(listTrtAuth[i].indexOf(codeTrt) >= 0){	 
				readonly = "false"; // on autorise la saisie sur la zone
				alerte("traitement "+codeTrt+" est autoris�");
			}
			i++;	
		}
	}catch(e){
		alerte('liste de traitement autoris�e vide ou non conforme');
	}				
	return readonly;
						
}

/*
 * Fonction qui permet rendre disponible en �dition une zone en fonction de la disponibilit� d'un traitement
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
 * Fonction qui permet de mettre � jour les menus, juste avant leur affichage
 * C'est la fonction d'entr�e de la gestion dynamique des menus de l'application
 */
function updateMenu(){
	
	try{
		enableMenuControls = document.getElementById("enableControls").getAttribute("value");
	}catch(e){
		alerte("Valeur d'activation de la gestion du menu contextuel dynamique non d�finie");
	}
	alerte('enableControls = '+enableMenuControls);
	// On n'ex�cute la fonction que si la valeur enableMenuControls est true
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
 * Elle n est pas utilis�e et doit �tre supprim�e
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
				//alert("ID span N�"+i+" de contextMenu = "+id);
			}
		}catch(e){
			
		}						
	}
}

/*
 * Fonction qui retourne, pour une ligne donn�e, le specialKey qu'elle contient
 */
function getSpecialKey(row) {
	if(isPageList()){
		try{
			var tds = row.getElementsByTagName("td");						
			var td_id = tds[0].id;
			//alert("Id = "+td_id);
			var part = td_id.split(":");
			isRowSelected = true;
			//alerte("special key d�termin� � partir du formulaire liste, c-�-d de la ligne du tableau s�lectionn�e");
			debug_text = "special key d�termin� � partir du formulaire liste, c-�-d de la ligne du tableau s�lectionn�e";
			return part[part.length - 2];
		}catch(e){
			//alerte("un special key null sera retourn� sur formulaire liste");
			debug_text = "un special key null sera retourn� sur formulaire liste";
			isRowSelected = false;
			return null;
		}	
	}
	else{
		try{
			//alerte("special key d�termin� � partir de la valeur en session");
			debug_text = "special key d�termin� � partir de la valeur en session";
			return document.getElementById('contentForm:specialKey').value;
		}catch(e){
			//alerte("un special key null sera retourn� sur formulaire detail");
			debug_text = "un special key null sera retourn� sur formulaire detail";
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
		// On tente de r�cup�rer la balise cible dont on doit changer la visibilit�
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
//				//alerte("style par d�faut = "+defaultStyle);
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
		// Si �chec de la r�cup�ration de la balise cible
		alerte("erreur: "+e);
		alerte(e.message);
		//alert('La balise cible ('+control+' '+id+') n\'a pu �tre trouv�e � partir de son ID ou a rencontr� un autre probl�me');
	}
						
}

/*
 * Fonction qui permet de mettre � jour le menu de gauche de l'application
 * TODO - v�rifier que cette fonction est utilis�e, et dans le cas contraire, la supprimer
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
//	alerte("longeur du tableau des traitements autoris�s � l'utilisateur quelque soit l'assignataire = "+globalListTrt.length);
//	alerte("liste des traitements = "+globalListTrt);
//	msg = "codes des entit�s � afficher : ";
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
//			alerte("El�ment de menu d'identitifant "+idAuth+" trouv� et masqu�");
//		}catch(e){
//			alerte("El�ment de menu d'identitifant "+idAuth+" non trouv�");
//		}		
//	}
//	alerte(msg);
	
}