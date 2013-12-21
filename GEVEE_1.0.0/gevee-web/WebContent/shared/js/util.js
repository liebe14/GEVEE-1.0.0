var enableReadOnlyStyle = "true";
var idZoneGestionMsgJs = "iphd_idZoneGestionMsgJs";

function pleinEcran(lien,nom) {

if( (navigator.appName == "Microsoft Internet Explorer")
  && (navigator.platform != "MacPPC") ) {
	ff=window.open(lien,nom,'top=0,left=0,width='+screen.width
     +',height='+screen.height+',location=no,toolbar=no,menubar=no,status=no,scrollbars=yes,resizable=yes');
} else {
 	ff=window.open(lien,nom,'top=0,left=0,width='+screen.width
     +',height='+screen.height+',location=no,toolbar=no,menubar=no,status=no,scrollbars=yes,resizable=yes');
 }
}

/* 
function getParamValue(param,url)
{
	var u = url == undefined ? document.location.href : url;
	var reg = new RegExp('(\\?|&|^)'+param+'=(.*?)(&|$)');
	matches = u.match(reg);
	return matches[2] != undefined ? decodeURIComponent(matches[2]).replace(/\+/g,' ') : '';
}
*/

/*
 * Fonction qui permet de savoir si un élément appartient à un tableau
 */
function content(a,v){					
	var result = false;
	for(var i=0; i<a.length; i++){
		if(v == a[i]) {
			result = true;
		}
	}
	return result;
}		

/*
 * Fonction qui permet de convertir une liste en un tableau
 */
function listToArray(list){
	result = new Array();
	try{
		var toString = list.replace("[","");
		list = toString.replace("]","");
		result = list.split(", ");
	}catch(e){
		alerte("erreur de conversion de la liste en tableau : list = "+list);
		alerte(e.toString());
	}
	
	return result;
}

 /*
  * Fonction qui renvoie l'union distincte de deux tableaux
  */
 function union(array1, array2){
 	if((array1 == null) || (array1.length < 1)) {
 		//array1 = new Array();
 		alerte("le premier array à concatener est nul");
 		
 		return array2;
 	}
 	if((array2 == null) || (array2.length < 1)) {
 		 //array2 = new Array();
 		 alerte("le second array à concatener est nul");
 		 return array1;
 	}
 	
 	var array3 = new Array();
 	try{
 		//alerte("debut concatenation");
 		//for(var i=0; i<array1.length; i++) array3[i] = array1[i];
 		//for(var j=0; j<array2.length; j++) array3[i+j] = array2[j];
 		array3 = array1.concat(array2);
 	}catch(e){
 		//alerte("reessaie debut concatenation");
 		//array3 = array2.concat(array1);
 		alerte(e.toString());
 	}
 	//alerte("array 3 = "+array3);
 	//var array4 = unique(array3);
 	//alerte("array 4 ="+array4);
 	return unique(array3);
 }

 /*
  * Fonction qui permet de supprimer les doublons d'un tableau
  */
 function unique(oldarr){					
 	oldarr.sort();
 	var j=0;
 	var newarr= new Array()
 	for(var i=0; i<oldarr.length; i++){
 		newarr[j]=oldarr[i];
 		j++;
 		if((i>0)&&(oldarr[i]==oldarr[i-1])){
 			newarr.pop();
 			j = j - 1;
 		}
 	}
 	return newarr;
 }

 /*
  * Fonction qui renvoie l'intersection de deux tableaux
  */
 function intersect(array1, array2){
 	a = [];
 	var l = array1.length;
 	var l2 = array2.length;
 	for(var i=0; i<l; i++) {
 		for(var j=0; j<l2; j++) {
 	    	if (array1[i] == array2[j])
 	      		a.push(array1[i]);
   		}
 	}
 	return a;
 }
  
 /**
  * Fonction qui renvoie une copie des valeurs d'un tableau donné
  * @param arr
  * @return
  */
 function cloneArray(arr){
	var result = new Array();
	for(var k = 0; k < arr.length ; k++)
		result[k] = arr[k];
	return result;
 }
 
  /*
   * Fonction qui permet de formater convenablement une zone devant contenir une valeur monétaire
   * Cette fonction est commune à tous les formulaires manipulant les montants, et n'a pas de rapport avec le menu en lui même
   * Elle pourra être déplacée ultérieurement vers un fichier propre aux fonctions communes
   * Pour plus d'efficacité et de réutilisabilité, elle doit utiliser les fonctions de validation écrites plus bas (validateXXXXX)
   * et peut-être aussi la fonction formatMoneyVal
   */
  function formatMoney (component, hasValueAttribute) {
  	alerte("id du composant = "+component.id);
  	// un nombre arrondi ou entier pas de virgules.
  	var x = null;
  	if(hasValueAttribute)
  		x = component.value.replace(/[^0-9|^-]*/g,''); 		// suppression des caractères non décimaux
  	else
  		x = component.innerHTML.replace(/[^0-9|^-]*/g,''); 	// suppression des caractères non décimaux
  	alerte("chaine après suppression des caractères non décimaux et - = "+x);
  	var str = x.toString();
  	var sign = (str.substr(0,1) == "-" ? "-" : '');
  	if (sign == "-") {
  		str = str.replace(/-/g,'');			// suppression de tous les "-" de l'expression, on est à présent sûr que l'expression ne contient que des chiffres		
  	}
  	str = str.replace(/^[0]*/g,'');			// suppression des '0' du début
  	str = str.replace(/^$/g,'0');			// si c'est une expression vide qui reste, on la remplace par "0"
  	var n = str.length;
  	var rest = n % 3;
  	var sep = ' ';
  	alerte("chaine après suppression de tous les caractères non conformes = "+str);
  	if(n >= 3){
  		// on ne formate que si la longueur de la chaîne dépasse 3
  		try{
  			if(hasValueAttribute){				
  				component.value = sign + (rest ? str.substr(0, rest) + sep : '') + str.substr(rest).match(new RegExp('[0-9]{3}', 'g')).join(sep);
  				alerte("nouvelle valeur du composant = "+component.value);
  			}
  			else {				
  				component.innerHTML = sign + (rest ? str.substr(0, rest) + sep : '') + str.substr(rest).match(new RegExp('[0-9]{3}', 'g')).join(sep);
  				alerte("nouvelle valeur du composant = "+component.innerHTML);
  			}
  		}catch(e){
  			if(hasValueAttribute) component.value = "0";
  			else component.innerHTML = "0";
  		}
  		
  	}
  	else{
  		if(hasValueAttribute){
  			component.value = str;
  		}
  		else {
  			component.innerHTML = str;
  		}
  	}
  }

  /*
   * Fonction qui permet de formatter une chaine de caractère ou un entier sous forme monétaire
   * Pour plus d'efficacité et de réutilisabilité, elle doit utilisée les fonctions de validation écrites plus bas (validateXXXXX)
   */
  function formatMoneyVal(valueToFormat){
  	var value = valueToFormat+"";
  	var formattedValue = "0";
  	// un nombre arrondi ou entier pas de virgules.
  	var x = null;
  	x = value.replace(/[^0-9|^-]*/g,''); 		// suppression des caractères non décimaux
  	
  	alerte("chaine après suppression des caractères non décimaux et - = "+x);
  	var str = x.toString();
  	var sign = (str.substr(0,1) == "-" ? "-" : '');
  	if (sign == "-") {
  		str = str.replace(/-/g,'');			// suppression de tous les "-" de l'expression, on est à présent sûr que l'expression ne contient que des chiffres		
  	}
  	//var str = value+"";
  	var n = str.length;
  	var rest = n % 3;
  	var sep = ' ';
  	alerte("chaine après suppression de tous les caractères non conformes = "+str);
  	if(n >= 3){
  		//on ne formate que si la longueur de la chaîne dépasse 3
  		try{
  			formattedValue = sign + (rest ? str.substr(0, rest) + sep : '') + str.substr(rest).match(new RegExp('[0-9]{3}', 'g')).join(sep);
  			alerte("Valeur formatée = "+formattedValue);
  		}catch(e){
  			formattedValue = "0";
  			alerte(e.toString());
  		}
  		
  	}
  	else{
  		formattedValue = str;
  	}
  	return formattedValue;
  }

  /*
   * Fonction qui permet de valider(supprimer les caractères non valides) une chaine de caractères qui doit être utilisée comme une valeur entière décimale
   * Fonction régulièrement utilisée pour les valeurs de pourcentage, mais dont l'implémentation est à clôturer avec le traitements tenant compte du nombre d'ocurrences du "."
   */
  function validateUnsignedDecimal(number){
  	number = number.replace(/[^0-9,\.]*/g,'');		// suppression des caractères non valides
  	number = number.replace(/^[0]*/g,'');		// suppression des '0' du début
  	number = number.replace(/^$/g,'0');			// si une chaine vide est obtenue, mettre 0

  	// Traitements spécifiques au traitement des décimales
  	
  	return number;
  }

  /*
  * Fonction qui permet de valider(supprimer les caractères non valides) une chaine de caractères qui doit être utilisée comme une valeur entière positive 
  */
  function validateUnsignedInteger(number){
  	number = number.replace(/[^0-9]*/g,'');		// suppression des caractères non valides
  	number = number.replace(/^[0]*/g,'');		// suppression des '0' du début
  	number = number.replace(/^$/g,'0');			// si une chaine vide est obtenue, mettre 0
  	
  	
  	return number;
  }

  /*
  * Fonction qui permet de valider(supprimer les caractères non valides) une chaine de caractères qui doit être utilisée comme une valeur entière positive ou négative
  */
  function validateSignedInteger(number){
  	number = number.replace(/[^0-9,-]*/g,'');		// suppression des caractères non valides
  	number = number.replace(/^[0]*/g,'');		// suppression des '0' du début
  	number = number.replace(/^$/g,'0');			// si une chaine vide est obtenue, mettre 0

  	var sign = (number.substr(0,1) == "-" ? "-" : '');
  	if (sign == "-") {
  		number = number.replace(/-/g,'');			// suppression de tous les "-" de l'expression, on est à présent sûr que l'expression ne contient que des chiffres		
  	}
  	
  	return sign+''+number;
  }

  /*
  * Fonction qui prend en entrée une chaine de caractères à utiliser comme valeur entière positive, et retourne la valeur entière validée correspondante 
  */
  function validateUnsignedIntVal(number){
  	return parseInt(validateUnsignedInteger(number));
  }

  /*
  * Fonction qui prend en entrée une chaine de caractères à utiliser comme valeur entière positive ou négative, et retourne la valeur entière validée correspondante 
  */
  function validateSignedIntVal(number){
  	return parseInt(validateSignedInteger(number));
  }

  /*
   * Fonction qui retourne le pourcentage représenté par le premier argument (partValue) par rapport au deuxième (totalValue)
   */
  function calculatePercent(partValue, totalValue){
  	//calcul du pourcentage avec 10 chiffres après la virgule
  	try{		
  		partValue = validateUnsignedInteger(partValue);
  		totalValue = validateUnsignedInteger(totalValue);
  		
  		var partValueInt = parseInt(partValue);		
  		var totalValueInt = parseInt(totalValue);
  		//alert('montant = '+partValueInt+' et montant total = '+totalValueInt);
  		if(totalValueInt > 0){
  			return Math.round((100 * partValueInt / totalValueInt) * 10000000000) / 10000000000 ;		// resultat arrondi à 10 chiffres après la virgule
  			//return 100 * partValueInt / totalValueInt;
  		}
  		else return 0;
  	}catch(e){
  		alert('Erreur survenue lors du calcul du pourcentage: Veuillez saisir une valeur conforme, ou sélectionner une imputation avec un crédit ouvert non nul');
  		alerte(e.toString());
  		return 0;
  	}

  }

  /*
  * Fonction qui retourne la valeur corroespondant au pourcentage (percent) de la valeur totale(totalValue)
  */
  function calculateFromPercent(percent, totalValue){
  	//calcul de la valeur en fonction du pourcentage
  	try{
  		percent = validateUnsignedDecimal(percent);
  		totalValue = validateUnsignedInteger(totalValue);
  		
  		var percentInt = parseFloat(percent);
  		var totalValueInt = parseInt(totalValue);
  		return Math.round((percentInt * totalValueInt) / 100) ;		
  	}catch(e){
  		alert('Erreur survenue lors du calcul du montant: Veuillez saisir une valeur conforme, ou sélectionner une imputation avec un crédit ouvert non nul');
  		alerte(e.toString());
  		return 0;
  	}
  }

  /*
   * Fonction qui affiche un message dans une zone appropriée en tenant compte du type de message
   */
  function showMessage(zoneId, msg, msgType){
	  try{
		  var tagMsg = document.getElementById(zoneId) ;
		  if(msgType == "ERROR"){
			  tagMsg.setAttribute("class", "rich-message msg_error");
			  tagMsg.innerHTML = msg;
			  
			  // Gestion de l'erreur en JSF via un inputHidden
			  	// 1. S'il y'a un message à afficher (donc normalement une erreur) alors rendre l'inputHidden  obligatoire
			  if(msg != null && msg != ""){
				  try{
					  setComponentValue("contentForm:"+idZoneGestionMsgJs, "");
				  }
				  catch(e){
					  alerte("showMessage( " +zoneId+ " , "+msg+" , " +msgType +" ) :	Erreur dans la méthode : setComponentValue(contentForm:iphd_idZoneGestionMsgJs, )" );  
					  alerte(e.toString());
				  }
			  }
			  	// 2. S'il n y a pas de message à afficher (donc  normalement pas d'erreur) alors rendre l'inputHidden  non obligatoire
			  else{
				  try{
					  setComponentValue("contentForm:"+idZoneGestionMsgJs, "message");
				  }
				  catch(e){
					  alerte("showMessage( " +zoneId+ " , "+msg+" , " +msgType +" ) :	Erreur dans la méthode : setComponentValue(contentForm:iphd_idZoneGestionMsgJs, message)" );  
					  alerte(e.toString());
					  
				  }				  				  
			  }
			  		  			  
		  }
		  else if(msgType == "WARN"){
			  tagMsg.setAttribute("class", "rich-message msg_warn");
			  tagMsg.innerHTML = msg;
		  }
		  else if(msgType == "INFO"){
			  tagMsg.setAttribute("class", "rich-message msg_infos");
			  tagMsg.innerHTML = msg;
		  }
		  else{
			  tagMsg.innerHTML = msg;
		  }
		  
		  return true;
	  }catch(e){
		  alerte("Erreur survenue lors de l'affichage du message ");
		  alerte(e.toString());
		  return false;
	  }
  }
  
  /*
   * Fonction qui récupère la valeur renseignée dans un composant
   */
  function getComponentValue(componentId){
	  try{
		  var tag = document.getElementById(componentId);
		  var tagName = "";
		  var tagType = "";
		  tagName = tag.tagName.toLowerCase();
		  
		  // By k@ms : Just for Test
/*		  try{
			  var v$var = tag.getValue();  
			  alert("getValue() = "+v$var);
		  }catch(e){
			  alert("getValue() = ERREUR");
		  }	*/  
		  // End de la zone de test
		  
		  // Tags : OutputText, Span
		  if(tagName == "span") 
			  return tag.innerHTML;
		  
		  // Tags : InputText, inputHidden, SelectBooleanCheckbox, SelectOneRadio (selectOneRadio dans le cas de l'id de l'input)
		  else if(tagName == "input"){ 
			  tagType = tag.getAttribute("type").toLowerCase();
			  
			  if(tagType == "text" || tagType == "hidden") //inputText, inputHidden
				  return tag.getValue();
			  
			  else if((tagType == "checkbox") || (tagType == "radio")){ 
				  // SelectBooleanCheckbox, SelectOneRadio (selectOneRadio dans le cas de l'id de l'input)
				  alerte("tagType = "+tagType+" : valeur à retourner = "+tag.checked);
				  return tag.checked;
			  }
				  
		  }
		  
		  // Tags : SelectOneMenu
		  else if(tagName == "select"){
			  tagType = tag.getAttribute("type").toLowerCase();
			  //alert(tagType);
			  //if(tagType == "select-one") // selectOneMenu
				  return tag.getValue();
		  }
		  
		// Tags : SelectOneRadio
		  else {
			  var tagInput = tag.getElementsByTagName("input");
			  var length = tagInput.length;
			  for(var i=0; i<length; i++){
				  if((tagInput[i].checked == true) && (tagInput[i].name == tag.id) && (tagInput[i].type == "radio")){
					  // selectOneRadio Cas des boutons radio dans le cas où on passe l'id défini dans la page jsf
					  return tagInput[i].getValue();
				  }
			  }
		  }
	  }catch(e){
		  alerte("Erreur survenue lors de la récupération de la valeur du composant d'id = "+componentId);
		  alerte(e.toString());
	  }
	  
	  alerte("Element d'id "+componentId+" non identifié : tagName = "+tagName+", tagType = "+tagType);
	  return null;
	 
  }
  
  function setComponentValue(componentId, value){
	  try{
		  		  
		  var tag = document.getElementById(componentId);
		  var tagName = "";
		  tagName = tag.tagName.toLowerCase();
		  
		  if(tagName == "span") {	// Test OK
			  //span, outputText
			  tag.innerHTML = value;
			  return true;
		  }		  
		  else if(tagName == "input"){ // Test OK
			  
			  var tagType = tag.getAttribute("type").toLowerCase();
			  
			  if(tagType == "text" || tagType == "hidden" ) {
				  //inputText 				  					
					tag.setValue(value); 
			  				  				  
				  return true;
			  }			  
			  else if((tagType == "checkbox") || (tagType == "radio")) {
				  // selectBooleanCheckbox, selectOneRadio dans le cas de l'id de l'input
				  tag.checked = value;
				  return true;
			  }
		  }
		  else if(tagName == "select"){
			  var tagType = tag.getAttribute("type").toLowerCase();
			  if(tagType == "select-one") {
				  // selectOneMenu
				  tag.value = value;
				  var tagInput = tag.getElementsByTagName("option");
				  var length = tagInput.length;
				  for(var i=0; i<length; i++){
					  if(tagInput[i].selected == "selected")
						  tagInput[i].removeAttribute("selected"); // Désactivation de la valeur qui était déjà sélectionnée
					  if(tagInput[i].value == value)
						  tagInput[i].selected = "selected"; // Activation de la valeur correspondant à la valeur à sélectionner
				  }
				  return true;
			  }
		  }
		  else {
			  var tagInput = tag.getElementsByTagName("input");
			  var length = tagInput.length;
			  for(var i=0; i<length; i++){
				  if((tagInput[i].checked == true) && (tagInput[i].name == tag.id) && (tagInput[i].type == "radio")){
					  // selectOneRadio Cas des boutons radio dans le cas où on passe l'id défini dans la page jsf
					  tagInput[i].checked = false; // Désactivation de la valeur qui était déjà sélectionnée
				  }
				  if((tagInput[i].value == value) && (tagInput[i].name == tag.id) && (tagInput[i].type == "radio")){
					  // selectOneRadio Cas des boutons radio dans le cas où on passe l'id défini dans la page jsf
					  tagInput[i].checked = true; // Activation de la valeur correspondant à la valeur à sélectionner
				  }
				  return true;
			  }
		  }
	  }catch(e){
		  alerte("Erreur survenue lors de l'assignation de la valeur "+value+" au composant d'id = "+componentId);
		  alerte(e.toString());
		  return false;
	  }
	  
  }
  
  /*
   * Fonction qui masque un composant
   * Tags : bouton, outputText, inputText,  selectBooleanCheckBox, selectOneRadio, selectOneMenu   
   */
  function hideComponent(componentId){
	  try{
		  var tag = document.getElementById(componentId);
		  tag.style.display = "none";
//		  tag.setAttribute("style","display:none");
	  }catch(e){
		  alerte("Erreur survenue en masquant le composant d'id = "+componentId);
		  alerte(e.toString());
	  }
  }
  
  /*
   * Fonction qui affiche ou masque un composant en fonction de l'état
   * Tags : bouton, outputText, inputText,  selectBooleanCheckBox, selectOneRadio, selectOneMenu   
   */
  function showHideComponent(componentId, state){

	if(state)
		showComponent(componentId);		
	else
		hideComponent(componentId);		
  }
  
  /*
  * Fonction qui affiche un composant qui a été masqué
  * Tags : bouton, outputText, inputText,  selectBooleanCheckBox, selectOneRadio, selectOneMenu   
  */
 function showComponent(componentId){
	  try{
		  var tag = document.getElementById(componentId);
		  tag.style.display = "inline";
//		  tag.removeAttribute("style");
	  }catch(e){
		  alerte("Erreur survenue en affichant le composant d'id = "+componentId);
		  alerte(e.toString());
	  }
 }
 
  /*
  * Fonction qui désactive un composant
  * Tags : inputText, bouton, selectBooleanCheckBox, selectOneMenu
  */
 function disableComponent(componentId){
	  try{
		  var tag = document.getElementById(componentId);
		  tag.disabled = "true";		 		  
	  }catch(e){
		  alerte("Erreur survenue en désactivant le composant d'id = "+componentId);
		  alerte(e.toString());
		  tag.readonly = "true";
	  }
 }
 
 /*
 * Fonction qui affiche un composant qui a été masqué
 * Tags : inputText, bouton, selectBooleanCheckBox, selectOneMenu
 */
 function enableComponent(componentId){
	  try{
		  var tag = document.getElementById(componentId);
		  //tag.disabled = "false";
		  tag.removeAttribute("disabled");
	  }catch(e){
		  alerte("Erreur survenue en activant le composant d'id = "+componentId);	
		  alerte(e.toString());
		  //tag.readonly = "false";
		  tag.removeAttribute("readonly");
	  }
 }
 
 /*
  * Fonction qui permet de comparer deux valeurs entières contenues dans des composants
  */
 function isInferior(componentId1, componentId2, canBeEquals){
	  
	  // Début DEBUG
//	  var m1 = getComponentValue(componentId1);
//	  var m2 = getComponentValue(componentId2);
//	  alert("isInferior() : " + m1 + " <=? " + m2 + " = " + (m2 > m1));
	  // Fin DEBUG
	  	 	  
	 try{
		 if((canBeEquals == null) || canBeEquals)
			 return (validateUnsignedIntVal(getComponentValue(componentId1)) <= validateUnsignedIntVal(getComponentValue(componentId2)));
		 else
			return (validateUnsignedIntVal(getComponentValue(componentId1)) < validateUnsignedIntVal(getComponentValue(componentId2))); 
		 		 
	 }catch(e){
		 alerte("Erreur survenue lors de la comparaison des montants contenus dans les composants d'id "+componentId1+" et "+componentId2);
		 alerte(e.toString());
		 return false;
	 }
 }
 
 /*
  * Fonction qui fait la comparaison entre les valeurs contenues dans deux composants, et affiche un message d'erreur 
  */
 function checkInferiority(componentId1, componentId2, canBeEquals, msgComponentId, msg){
	 if (isInferior(componentId1, componentId2, canBeEquals))
		 showMessage(msgComponentId, "", "ERROR");
	 else
		 showMessage(msgComponentId, msg, "ERROR");
 }
  
  /*
   * Fonction qui fait la comparaison stricte entre les valeurs contenues dans deux composants, et affiche un message d'erreur 
   */
  function checkStrictInferiority(componentId1, componentId2, msgComponentId, msg){
	  checkInferiority(componentId1, componentId2, false ,msgComponentId, msg);  
  }  
  
  /*
   * Fonction qui fait la comparaison stricte entre les valeurs contenues dans deux composants, et affiche un message d'erreur 
   */
  function checkInferiorityEqual(componentId1, componentId2, msgComponentId, msg){
	  checkInferiority(componentId1, componentId2, true ,msgComponentId, msg);  
  }  
  
  function replaceLast(element, separator, newValue){
	  var part = new Array();
		part = element.split(separator);
		part[part.length-1] = newValue;
		
//		part.pop(); // suppression du dernier élément du tableau
//		part.push(newValue); //Ajout de la nouvelle valeur dans le tableau
		
		return part.join(separator);
  }
  
  function replaceFirst(element, separator, newValue){
	  var part = new Array();
		part = element.split(separator);
		part[0] = newValue;
		
		return part.join(separator);
  }
  
  function setTotalByColumn(prefixColumnId, suffixColumnId, suffixTotalId, summable, checkBoxSuffix){
  	alerte("setTotalByColumn("+prefixColumnId+", "+suffixColumnId+", "+suffixTotalId+", "+summable+", "+checkBoxSuffix+")");
  	var totalColumnId = prefixColumnId+":"+suffixTotalId;
  	var totalColumn = calculateTotalColumn(prefixColumnId, suffixColumnId, summable, checkBoxSuffix);
  	var tagTotalColumn = document.getElementById(totalColumnId);
  	alerte("Total Column id = "+totalColumnId+" : totalColumn = "+totalColumn);
//  	document.getElementById(totalColumnId).innerHTML = totalColumn;
//  	alerte("value directly setted");
  	setComponentValue(totalColumnId, totalColumn);
  	alerte("value set by setComponentValue function");
  	formatMoney(tagTotalColumn, false);
  }

  function calculateTotalColumn(prefixId, suffixId, summable, checkBoxSuffix){
  	alerte("calculateTotalColumn("+prefixId+", "+suffixId+", "+summable+", "+checkBoxSuffix+")");
  	var total = 0;
  	var tagTable = document.getElementById(prefixId);
  	// Récupération du nombre de lignes du tableau qui sera utile pour l'itération de la boucle for mais ce résultat n'est pas bon
  	//var NbRows = tagTable.getElementsByTagName("tr").length;
  	//alert("Nb de lignes du tableau = "+NbRows);
  	
  	var i = 0;
  	var elementId = prefixId+":"+i+":"+suffixId;
  	//for(var i=0 ; i < NbRows ; i++){
  	while(document.getElementById(elementId) != null){
  			
  		try{
  			alerte("i = "+i+" : elementId = "+elementId);
//  			alert("document.getElementById("+elementId+").value) = "+document.getElementById(elementId).innerHTML);
//  			alert("validateUnsignedIntVal(document.getElementById("+elementId+").value) = "+validateUnsignedIntVal(document.getElementById(elementId).innerHTML));
//  			alert("getComponentValue("+elementId+") = "+getComponentValue(elementId));
//  			alert("validateUnsignedIntVal(getComponentValue("+elementId+")) = "+validateUnsignedIntVal(getComponentValue(elementId)));
  			var elementValue = validateUnsignedIntVal(getComponentValue(elementId));
  			//var elementValue = validateUnsignedIntVal(document.getElementById(elementId).innerHTML);
  			
//  			alert("getComponentValue("+prefixId+":"+i+":chbx_priseEnCharge) = "+getComponentValue(prefixId+":"+i+":chbx_priseEnCharge"));
//  			alert("document.getElementById("+prefixId+":"+i+":chbx_priseEnCharge).checked = "+document.getElementById(prefixId+":"+i+":chbx_priseEnCharge").checked);
  			//if(getComponentValue(prefixId+":"+i+":chbx_priseEnCharge") == true)
//  			if((checkBoxSuffix != null) && (document.getElementById(prefixId+":"+i+":"+checkBoxSuffix) != null)){
//  				
//  			}
  			if(checkBoxSuffix == null){
  				alerte("paramètre checkBoxSuffix non défini")
  				total = (summable) ? total + elementValue : total + 1;
  			}  				
  			else if((document.getElementById(prefixId+":"+i+":"+checkBoxSuffix) != null) 
  					&& (document.getElementById(prefixId+":"+i+":"+checkBoxSuffix).checked == true)){
  				alerte("le champ checBoxSuffix est coché");
  				total = (summable) ? total + elementValue : total + 1;
  			}
  			else{
  				alerte("Element d'id = "+elementId+" à ignorer dans la somme");
  			}
  				
//  			if((document.getElementById(prefixId+":"+i+":"+checkBoxSuffix) == null) 
//  					|| (document.getElementById(prefixId+":"+i+":"+checkBoxSuffix).checked == true))
//  				total = (summable) ? total + elementValue : total + 1;
  		}catch(e){
  			alerte("Problème survenue lors du calcul en incluant dans le total le composant d'id = "+elementId);
  			alerte(e.message);
  			//break;
  		}
  		i++;
  		elementId = prefixId+":"+i+":"+suffixId;
  	}
  	alerte("total column "+suffixId+" = "+total);
  	return total;
  }
  
  /**
   * Fonction qui permet d'ajouter à la valeur du composant du 2ème argument, la valeur entière contenue dans le composant dont l'id est le premier paramètre
   * @param componentIdToAdd
   * @param componentId
   * @return
   */
  function addValueTo(componentIdToAdd, componentId, oldQuantity, unitValComponentId){
	  try{
		  var tagToModify = document.getElementById(componentId);
		  
		  var valueToAdd = validateSignedIntVal(getComponentValue(componentIdToAdd));
		  var value = validateSignedIntVal(getComponentValue(componentId));
		  var oldQuantityVal = validateSignedIntVal(oldQuantity);
		  var unitVal = validateSignedIntVal(getComponentValue(unitValComponentId));
		  
		  value +=  unitVal * (valueToAdd - oldQuantityVal);
		  
		  setComponentValue(componentId, value);
		  formatMoney(tagToModify, false);
		  alerte("value "+value+" added to component id "+componentId);
	  }catch(e){
		  alerte("Problème survenue lors de l'ajout de la valeur du composant d'id = "+componentIdToAdd+" à celui d'id = "+componentId);
		  alerte(e.message);
	  }
	  
  }
  
  function addValueTo2(componentIdToAdd, componentId, oldQuantity, unitValComponentId){
	  try{
		  var tagToModify = document.getElementById(componentId);
		  
		  var valueToAdd = validateSignedIntVal(getComponentValue(componentIdToAdd));
		  var value = validateSignedIntVal(getComponentValue(componentId));
		  var oldQuantityVal = validateSignedIntVal(oldQuantity);
		  var unitVal = validateSignedIntVal(getComponentValue(unitValComponentId));
		  
		  value +=  unitVal * (valueToAdd - oldQuantityVal);
		  
		  setComponentValue(componentId, value);
		  formatMoney(tagToModify, true);
		  alerte("value "+value+" added to component id "+componentId);
	  }catch(e){
		  alerte("Problème survenue lors de l'ajout de la valeur du composant d'id = "+componentIdToAdd+" à celui d'id = "+componentId);
		  alerte(e.message);
	  }
	  
  }
  
  
   /**
    * Fonction qui permet d'ajouter à la valeur du composant du 2ème argument, la valeur entière contenue dans le composant dont l'id est le premier paramètre
    * @param componentIdToAdd
    * @param componentId
    * @return
    */
   function addMontantValueTo(componentIdToAdd, componentId, oldQuantity, unitValComponentId){
 	  try{
 		  var tagToModify = document.getElementById(componentId);
 		  var tagMontantToModify = document.getElementById(componentIdToAdd); 
 		  
 		  var valueToAdd = validateSignedIntVal(getComponentValue(componentIdToAdd));
 		  var value = validateSignedIntVal(getComponentValue(componentId));
 		  var oldMontantVal = validateSignedIntVal(oldQuantity);
 		  var unitVal = validateSignedIntVal(getComponentValue(unitValComponentId));
 		  
 		  // Calcul de la nouvelle valeur de la quantité de billets avec arrondie par défaut
 		  value += Math.floor( (valueToAdd - oldMontantVal) / unitVal );
 		  
 		  var montantValue = unitVal * value;
 		  
 		  // Attribution de la valeur de la nouvelle quantité déterminée
 		  setComponentValue(componentId, value);
 		  
 		  // Attribution de la valeur du nouveau montant déterminé à partir de la nouvelle quantité déterminée
 		  setComponentValue(componentIdToAdd, montantValue);
 		  
 		  // Formatage des montants qui ont été mis à jour
 		  formatMoney(tagToModify, true);
 		  formatMoney(tagMontantToModify, true);
 		  
 		  alerte("value "+value+" added to component id "+componentId);
 		  
 	  }catch(e){
 		  alerte("Problème survenue lors de l'ajout de la valeur du composant d'id = "+componentIdToAdd+" à celui d'id = "+componentId);
 		  alerte(e.message);
 	  }
 	  
   }
   
   
  /**
   * Fonction permettant d'avoir l'Id complet d'un composant dont on connait le suffixe, à partir d'un autre composant se trouvant sur la même ligne du tableau (Tr)
   * @param componentId
   * @param componentIdSuffix
   * @return
   */
  function getIdOnSameTr(componentId, componentIdSuffix){
	  var tab = new Array();
	  tab = componentId.split(":");
	  tab[tab.length - 1] = componentIdSuffix;
	  var result = tab.join(":");
	  
	  alerte("getIdOnSameTr("+componentId+", "+componentIdSuffix+") = "+result);
	  
	  return result;
  }
  
  function setReadonlyStyle(){
	  if(enableReadOnlyStyle == "true"){
		  try{
			  setTagFamilyReadonlyStyle("input");
			  setTagFamilyReadonlyStyle("textarea");
			  setTagFamilyReadonlyStyle("select");
		  }catch(e){
			  alerte(e.message);
		  }
	  }
	  
  }
 
  function setTagFamilyReadonlyStyle(tagName){
	  var tagInputs = document.getElementsByTagName(tagName);
	  var tagInputsLength = tagInputs.length;
	  for(var i=0; i<tagInputsLength; i++){
		  // parcours de tous les composants input
		  var tagInput = tagInputs[i];
		  setATagReadonlyStyle(tagInput);
	  }
  }
  
  function setATagReadonlyStyle(tagInput){
	  var classEditableValue = "editable";
	  var classRequiredValue = "required";
	  var classReadonlyValue = "readonly";
	  var classNameSeparator = " ";
	  var tagName = tagInput.tagName;
	  var tagType = tagInput.getAttribute("type");
	  
	  // Conditions dans lesquelles il ne faut pas modifier le style
	  if((tagName == "INPUT") && (tagType != "text")){
		  
	  }
	  else {
		  var readonly = tagInput.getAttribute("readonly");
		  var disabled = tagInput.getAttribute("disabled");
		  if((readonly == "readonly") || (disabled == "disabled")){
			  addAttributeValueByTag(tagInput, "class", classReadonlyValue, classNameSeparator);
		  }
		  else{
			  addAttributeValueByTag(tagInput, "class", classEditableValue, classNameSeparator);
		  }
	  }
  }
  
  /*
   * Fonction qui permet de mettre à jour la propriété "attribute" du composant d'id "componentId", pour y ajouter une valeur "value"
   * Elle suppose que la valeur actuelle de la propriété se termine par un point-virgule (ce qui est conseillé, mais pas obligatoire)
   * Son implémentation pourra être revue de sorte qu'elle soit utilisable également lorsque la propriété ne se termine pas par un point-virgule (en y ajoutant un)
   */
  function addAttributeValue(componentId, attribute, value, separator){
  	try{		

  		var tag = document.getElementById(componentId);
  		addAttributeValueByTag(tag, attribute, value, separator)
  		
  	}catch(e){
  		alerte("élément d'id "+componentId+" introuvable lors de l'ajout de la valeur "+value+" à l'attribut "+attribute);
  		alerte(e.message);
  	}
  }

   /*
    * Fonction qui permet de mettre à jour la propriété "attribute" du composant "tag", pour y ajouter une valeur "value"
    * Elle suppose que la valeur actuelle de la propriété se termine par un point-virgule (ce qui est conseillé, mais pas obligatoire)
    * Son implémentation pourra être revue de sorte qu'elle soit utilisable également lorsque la propriété ne se termine pas par un point-virgule (en y ajoutant un)
    */
   function addAttributeValueByTag(tag, attribute, value, separator){
		try{		
			attribute = (attribute == null) ? "" : attribute;
			var var_attribute = tag.getAttribute(attribute);
			var_attribute = (var_attribute == null) ? "" : var_attribute;
			var_attribute = var_attribute+""+separator+""+value+""+separator;
			tag.setAttribute(attribute, var_attribute);
			
		}catch(e){
			alerte(e.message);
		}
   }
   
   //cacher et afficher
   function afficher(id) {
		if(document.getElementById(id).style.visibility=="hidden")
			document.getElementById(id).style.visibility="visible";
	}
	function cacher(id) {
		if(document.getElementById(id).style.visibility=="visible")
			document.getElementById(id).style.visibility="hidden";
	}
