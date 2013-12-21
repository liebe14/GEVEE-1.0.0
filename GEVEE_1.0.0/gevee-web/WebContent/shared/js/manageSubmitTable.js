var mapInput = new Object(), initInput = null, modRowNbr = 0;
var isInitialFirstVal = true, isInitialSecondVal = true;
var tab = new Array();

/**
 * Fonction d'initialisation du map à envoyer côté serveur en cas de validation des modifs du tableau
 */
function initMapInput(){
	mapInput = new Object();
	initInput = null;					
	modRowNbr = 0;
	isInitialFirstVal = true; // permet d'indiquer si la valeur 1 est égale à la valeur initiale
	isInitialSecondVal = true; // permet d'indiquer si la valeur 2 est égale à la valeur initiale
	try{
		
		document.getElementById("contentForm:optt_varInfos").innerHTML = "";
		if(document.getElementById("contentForm:cbtn_valider") != null)
			document.getElementById("contentForm:cbtn_valider").setAttribute("style","display:none");
		else if(document.getElementById("contentForm:tble_table2:cbtn_valider") != null)
			document.getElementById("contentForm:tble_table2:cbtn_valider").setAttribute("style","display:none");
		else alerte("Eléments d'id contentForm:tble_table2:cbtn_valider et contentForm:cbtn_valider introuvables");
		
	}catch(e){
		alerte("Erreur survenue lor de l'initialisation du map");		
		alerte(e.toString());
	}
	
}

/**
 * Fonction de mise à jour du map à envoyer côté serveur, contenant les valeurs initiales et les nouvelles valeurs saisies 
 * sur les lignes modifiées
 */
function updateMapInput(inpt, nature){	
	alerte("updateMapInput(inpt, nature);");
	
	var inptId = inpt.id;
	alerte("input.id = "+inptId);
	alerte("nature = "+nature);
	// vérification et mise à jour de la valeur en suivant le bon format
	if((nature != "cptePriseEnCharge") && (nature != "selectPriseEnCharge"))
		formatMoney(inpt, true);
	
	var newInput ;
	//newInput = (nature == "selectPriseEnCharge") ? getComponentValue(inptId) : inpt.value.replace(/\s/g,'');
	newInput = (nature == "selectPriseEnCharge") ? inpt.checked : inpt.value.replace(/\s/g,'');
	if(nature == "selectPriseEnCharge"){
		// dans ce cas la valeur de initInput est forcément le contraire de newInput puisque c'est l'évènement onchange qui permet d'appeler cette fonction dans ce cas
		initInput = (newInput == true) ? false : true;
	}
	if(nature == "cptePriseEnCharge")
		newInput = inpt.value;
	
	alerte("newInput = "+newInput+" : initInput = "+initInput);
	if(newInput != initInput){
		// on n'exécute la fonction uniquement dans le cas où la valeur obtenue après contrôle des valeurs saisies est différente de la valeur initiale
		var valInput = new Array();			
		// Récupération de l'id de la cellule du tableau afin de récupérer le specialId
		var tdId = inpt.parentNode.parentNode.parentNode.parentNode.parentNode.parentNode.id;
		alerte("td id = "+tdId);
		var tab = tdId.split(":");					
		var specialId = "";
		specialId = tab[tab.length - 2];
		
		if((nature == "cptePriseEnCharge") || (nature == "selectPriseEnCharge")) {
			// La récupération du specialId est spéciale pour le cas de la prise en charge
			// Construction de l'id du composant le specialId
			tab = inpt.id.split(":");
			tab[tab.length - 1] = "iphd_specialId";
			var idSpecialId = tab.join(":");
			alerte("idSpecialId = "+idSpecialId);
			try{
				specialId = document.getElementById(idSpecialId).value.replace(/\s/g,'');
				alerte("specialId = "+specialId);
			}catch(e){
				alerte("la colonne specialId n'existe pas ici");
				alerte(e.toString());
			}
		}
		
		alerte("special id = "+specialId);		
		alerte("valeur initiale = "+initInput);
		alerte("nouvelle valeur = "+newInput);	
		
		alerte("nature = "+nature);
		// Tentative de récupération de la valeur correspondant au specialId dans le map
		valInput = mapInput[specialId];					
		

		if(nature == "variation"){		
			if(valInput == null){			
				// cas où l'élément n'existe pas encore dans le map
				alerte("ajout dans le map");
				// Construction de l'id du composant contenant le montant de la proposition
				tab[tab.length - 1] = "inpt_proposition";
				var idProposition = tab.join(":");
				alerte("idProposition = "+idProposition);
				var proposition = null;
				try{
					proposition = document.getElementById(idProposition).value.replace(/\s/g,'');
					alerte("proposition = "+proposition);
				}catch(e){
					alerte("la colonne proposition n'existe pas ici");
				}
				// Ajout de la nouvelle ligne dans le map
				addToMapInput(specialId, initInput, newInput, proposition, proposition, true);
				isInitialFirstVal = false;
				
			}				
			else if(newInput == valInput[0]){
				// Cas où la nouvelle valeur de variation est égale à la valeur initiale de la variation à l'initialisation de la page
				alerte("la variation indiquée est égale à la variation initiale");
				//deleteFromMapInput(specialId);
				isInitialFirstVal = true;
				
			}else{
				// Cas où il s'agit de mettre à jour une ligne qui existe déjà dans le map
				alerte("mise à jour de la valeur de la nouvelle variation dans le map");
				alerte("nouvelle valeur = "+newInput);
				addToMapInput(specialId, valInput[0], newInput, valInput[2], valInput[3], false);
				isInitialFirstVal = false;
			}		
			// Mise à jour du nouveau crédit ouvert
			tab[tab.length - 1] = "pngd_NcrdO";
			var idNCrdO = tab.join(":");
			alerte("idNCrdO = "+idNCrdO);
			tab[tab.length - 1] = "pngd_crdO";
			var idCrdO = tab.join(":");
			alerte("idCrdO = "+idCrdO);
			var tagIdNCrdO = document.getElementById(idNCrdO);
			var tagIdCrdO = document.getElementById(idCrdO);
			alerte("tagIdCrdO.innerHTML = "+tagIdCrdO.innerHTML);		
			tagIdNCrdO.innerHTML = parseInt(tagIdCrdO.innerHTML.replace(/\s|[&nbsp;]/g,'')) + parseInt(newInput);
			formatMoney(tagIdNCrdO, false);
				
		} 
		else if(nature == "creditInitial"){		
			if(valInput == null){
				// cas où l'élément n'existe pas encore dans le map
				alerte("ajout dans le map");
				tab[tab.length - 1] = "inpt_proposition";
				var idProposition = tab.join(":");
				alerte("idProposition = "+idProposition);
				var proposition = document.getElementById(idProposition).value.replace(/\s/g,'');
				alerte("proposition = "+proposition);
				addToMapInput(specialId, initInput, newInput, proposition, proposition, true);
				isInitialFirstVal = false;
				
			}				
			else if(newInput == valInput[0]){
				// cas où la nouvelle valeur de variation est égale à la valeur initiale à l'initialisation de la page
				alerte("la variation indiquée est égale à la variation initiale");
				//deleteFromMapInput(specialId);
				isInitialFirstVal = true;
				
			}else{
				alerte("mise à jour de la valeur de la nouvelle variation dans le map");
				alerte("nouvelle valeur = "+newInput);
				addToMapInput(specialId, valInput[0], newInput, valInput[2], valInput[3], false);
				isInitialFirstVal = false;
			}		
			// Mise à jour de la variation
			tab[tab.length - 1] = "optt_variation";
			var idNVariation = tab.join(":");
			alerte("idNVariation = "+idNVariation);
			tab[tab.length - 1] = "optt_VCrdIniA1";
			var idCrdIniA1 = tab.join(":");
			alerte("idCrdIniA1 = "+idCrdIniA1);
			var tagIdNVariation = document.getElementById(idNVariation);
			var tagIdCrdIniA1 = document.getElementById(idCrdIniA1);
			alerte("tagIdCrdIniA1.innerHTML = "+tagIdCrdIniA1.innerHTML);		
			tagIdNVariation.innerHTML = parseInt(newInput) - parseInt(tagIdCrdIniA1.innerHTML.replace(/\s|[&nbsp;]/g,''));
			formatMoney(tagIdNVariation, false);
				
		}
		else if(nature == "proposition"){
			if(valInput == null){
				// cas où l'élément n'existe pas encore dans le map
				alerte("ajout dans le map");
				var variation, idVariation;
				try{
					// On essaie comme si la colonne "variation" est visible et éditable dans le tableau
					tab[tab.length - 1] = "inpt_variation";
					idVariation = tab.join(":");
					alerte("idVariation = "+idVariation);
					variation = document.getElementById(idVariation).value.replace(/\s/g,'');
				}catch(ex){
					// Dans le cas où c'est la colonne "crédit initial" qui est visible et éditable dans le tableau
					tab[tab.length - 1] = "inpt_VCrdIni";
					idVariation = tab.join(":");
					alerte("idVariation (VCrdIni) = "+idVariation);
					variation = document.getElementById(idVariation).value.replace(/\s/g,'');
				}
				
				alerte("variation = "+variation);
				addToMapInput(specialId, variation, variation, initInput, newInput, true);
				isInitialSecondVal = false;
			}
			else if(newInput == valInput[2]){
				//cas où la nouvelle valeur de proposition est égale à la valeur initiale à l'initialisation de la page
				alerte("la proposition indiquée est égale à la proposition initiale");
				//deleteFromMapInput(specialId);
				isInitialSecondVal = true;
				
			}else{
				alerte("mise à jour de la valeur de la nouvelle proposition dans le map");
				alerte("nouvelle valeur = "+newInput);
				addToMapInput(specialId, valInput[0], valInput[1], valInput[2], newInput, false);
				isInitialSecondVal = false;
			}
			
		}
		else if(nature == "cptePriseEnCharge"){		
			if(valInput == null){
				// cas où l'élément n'existe pas encore dans le map
				alerte("ajout dans le map");
				tab[tab.length - 1] = "chbx_priseEnCharge";
				var idPriseEnCharge = tab.join(":");
				alerte("idPriseEnCharge = "+idPriseEnCharge);
				//var priseEnCharge = document.getElementById(idPriseEnCharge).checked;
				var priseEnCharge = getComponentValue(idPriseEnCharge);
				
				alerte("priseEnCharge = "+priseEnCharge);
				addToMapInput(specialId, initInput, newInput, priseEnCharge, priseEnCharge, true);
				isInitialFirstVal = false;
				
			}				
			else if(newInput == valInput[0]){
				// cas où la nouvelle valeur de variation est égale à la valeur initiale à l'initialisation de la page
				alerte("la variation indiquée est égale à la variation initiale");
				//deleteFromMapInput(specialId);
				isInitialFirstVal = true;
				
			}else{
				alerte("mise à jour de la valeur de la nouvelle variation dans le map");
				alerte("nouvelle valeur = "+newInput);
				addToMapInput(specialId, valInput[0], newInput, valInput[2], valInput[3], false);
				isInitialFirstVal = false;
			}		

			// Mise à jour des champs de totaux
			setTotauxPriseEnCharge();				
		}
		else if(nature == "selectPriseEnCharge"){
			if(valInput == null){
				// cas où l'élément n'existe pas encore dans le map
				alerte("ajout dans le map");
				var compte, idCompte;
				
				tab[tab.length - 1] = "inpt_compte";
				idCompte = tab.join(":");
				alerte("idCompte = "+idCompte);
				compte = document.getElementById(idCompte).value.replace(/\s/g,'');
				
				alerte("compte = "+compte);
				addToMapInput(specialId, compte, compte, initInput, newInput, true);
				isInitialSecondVal = false;
			}
			else if(newInput == valInput[2]){
				//cas où la nouvelle valeur de proposition est égale à la valeur initiale à l'initialisation de la page
				alerte("la valeur indiquée est égale à la valeur initiale");
				//deleteFromMapInput(specialId);
				isInitialSecondVal = true;
				
			}else{
				alerte("mise à jour de la nouvelle valeur dans le map");
				alerte("nouvelle valeur = "+newInput);
				addToMapInput(specialId, valInput[0], valInput[1], valInput[2], newInput, false);
				isInitialSecondVal = false;
			}
			
			// Mise à jour des champs de totaux
			setTotauxPriseEnCharge();
		}
		alerte("isInitialFirstVal = "+isInitialFirstVal+" and isInitialSecondVal = "+isInitialSecondVal);
		if((isInitialFirstVal) && (isInitialSecondVal)){
			//Dans le cas où les valeurs de variation et proposition de la ligne sont égales aux valeurs initiales, supprime la ligne du map
			deleteFromMapInput(specialId);
		}		

	}

}

/**
 * Fonction de récupération du spécial Id lorsqu'il est sur une colonne spéciale du tableau
 * @param inpt
 * @return
 */
function getSpecialIdInColumn(inpt){
	// La récupération du specialId dans le cas où il est spécifié dans une colonne du tableau
	var specialId = null;
	
	// Construction de l'id du composant le specialId
	tab = inpt.id.split(":");
	tab[tab.length - 1] = "iphd_specialId";
	var idSpecialId = tab.join(":");
	alerte("idSpecialId = "+idSpecialId);
	
	try{
		specialId = document.getElementById(idSpecialId).value.replace(/\s/g,'');
		alerte("specialId = "+specialId);
	}catch(e){
		alerte("la colonne specialId n'existe pas ici");
		alerte(e.toString());
	}

	return specialId;
}

/**
 * Fonction de récupération du specialId lorsque sa valeur est indiquée dans l'id de chaque ligne du tableau
 * @param inpt
 * @return
 */
function getSpecialIdInTr(inpt){
	
	var tdId = inpt.parentNode.parentNode.parentNode.parentNode.parentNode.parentNode.id;
	alerte("td id = "+tdId);
	tab = tdId.split(":");					
	var specialId = "";
	specialId = tab[tab.length - 2];
	
	return specialId;
}

/**
 * Fonction de récupération d'une valeur située sur la même ligne du tableau, et dont on connaît le suffixe de l'id
 * @param suffixId
 * @return
 */
function getOtherValueOnSameLine(suffixId){
	
	tab[tab.length - 1] = suffixId;
	var idOther = tab.join(":");
	alerte("completeId of "+suffixId+" = "+idOther);
	
	var other = null;
	if(document.getElementById(idOther) != null){
		other = getComponentValue(idOther).replace(/\s/g,'');
		alerte("value on id = "+other);
	}
	else{
		alerte("la colonne correspondant à "+suffixId+" n'existe pas ici");
	}
	
	return other;
}

/**
 * Fonction de mise à jour de la valeur de la variation d'une ligne dans le map
 * @param valInput
 * @param specialId
 * @param initInput
 * @param newInput
 * @param OtherValSuffixId
 * @return
 */
function updateVariationInMap(valInput, specialId, initInput, newInput, OtherValSuffixId){
	
	if(valInput == null){			
		// cas où l'élément n'existe pas encore dans le map
		alerte("ajout dans le map");
		
		// Construction de l''id du composant contenant le montant de la proposition
		var proposition = getOtherValueOnSameLine(OtherValSuffixId);
		
		// Ajout de la nouvelle ligne dans le map
		addToMapInput(specialId, initInput, newInput, proposition, proposition, true);
		isInitialFirstVal = false;
		
	}				
	else if(newInput == valInput[0]){
		// Cas où la nouvelle valeur  est égale à la valeur initiale à l''initialisation de la page
		alerte("la valeur indiquée est égale à la valeur initiale");
		isInitialFirstVal = true;
		
	}else{
		// Cas où il s''agit de mettre à jour une ligne qui existe déjà dans le map
		alerte("mise à jour de la valeur de la nouvelle valeur dans le map : nouvelle valeur = "+newInput);
		addToMapInput(specialId, valInput[0], newInput, valInput[2], valInput[3], false);
		isInitialFirstVal = false;
	}		
	// Mise à jour du nouveau crédit ouvert
	tab[tab.length - 1] = "pngd_NcrdO";
	var idNCrdO = tab.join(":");
	alerte("idNCrdO = "+idNCrdO);
	tab[tab.length - 1] = "pngd_crdO";
	var idCrdO = tab.join(":");
	alerte("idCrdO = "+idCrdO);
	var tagIdNCrdO = document.getElementById(idNCrdO);
//	var tagIdCrdO = document.getElementById(idCrdO);
//	alerte("tagIdCrdO.innerHTML = "+tagIdCrdO.innerHTML);		
//	tagIdNCrdO.innerHTML = parseInt(tagIdCrdO.innerHTML.replace(/\s|[&nbsp;]/g,'')) + parseInt(newInput);
	var newVal = validateUnsignedIntVal(getComponentValue(idCrdO)) + validateUnsignedIntVal(newInput);
	setComponentValue(idNCrdO, newVal);
	formatMoney(tagIdNCrdO, false);
	
}

/**
 * Fonction de mise à jour de la valeur du crédit initial d'une ligne du tableau dans le map
 * @param valInput
 * @param specialId
 * @param initInput
 * @param newInput
 * @param OtherValSuffixId
 * @return
 */
function updateCrdInitialInMap(valInput, specialId, initInput, newInput, OtherValSuffixId){
	if(valInput == null){
		// cas où l'élément n'existe pas encore dans le map
		
		var proposition = getOtherValueOnSameLine(OtherValSuffixId);
		
		addToMapInput(specialId, initInput, newInput, proposition, proposition, true);
		isInitialFirstVal = false;
		
	}				
	else if(newInput == valInput[0]){
		// cas où la nouvelle valeur est égale à la valeur initiale à l'initialisation de la page
		alerte("la variation indiquée est égale à la variation initiale");
		isInitialFirstVal = true;
		
	}else{
		alerte("mise à jour de la valeur de la nouvelle variation dans le map");
		alerte("nouvelle valeur = "+newInput);
		addToMapInput(specialId, valInput[0], newInput, valInput[2], valInput[3], false);
		isInitialFirstVal = false;
	}		
	// Mise à jour de la variation
	tab[tab.length - 1] = "optt_variation";
	var idNVariation = tab.join(":");
	alerte("idNVariation = "+idNVariation);
	tab[tab.length - 1] = "optt_VCrdIniA1";
	var idCrdIniA1 = tab.join(":");
	alerte("idCrdIniA1 = "+idCrdIniA1);
	var tagIdNVariation = document.getElementById(idNVariation);
//	var tagIdCrdIniA1 = document.getElementById(idCrdIniA1);
//	alerte("tagIdCrdIniA1.innerHTML = "+tagIdCrdIniA1.innerHTML);		
//	tagIdNVariation.innerHTML = parseInt(newInput) - parseInt(tagIdCrdIniA1.innerHTML.replace(/\s|[&nbsp;]/g,''));	
	var newVal = validateUnsignedIntVal(newInput) - validateUnsignedIntVal(getComponentValue(idCrdIniA1));
	setComponentValue(idNVariation, newVal);
	formatMoney(tagIdNVariation, false);
	
}

/**
 * Fonction de mise à jour de la valeur de la proposition d'une ligne du tableau dans le map
 * @param valInput
 * @param specialId
 * @param initInput
 * @param newInput
 * @param OtherValSuffixId
 * @return
 */
function updatePropositionInMap(valInput, specialId, initInput, newInput, OtherValSuffixId){
	if(valInput == null){
		// cas où l'élément n'existe pas encore dans le map
		alerte("ajout dans le map");
		var variation = getOtherValueOnSameLine(OtherValSuffixId);
		// Dans le cas où la colonne variation n'est pas affichée, il faut utiliser la colonne crédit initial
		variation = (variation == null) ? getOtherValueOnSameLine("inpt_variation") : variation;
				
		alerte("variation = "+variation);
		addToMapInput(specialId, variation, variation, initInput, newInput, true);
		isInitialSecondVal = false;
	}
	else if(newInput == valInput[2]){
		//cas où la nouvelle valeur de proposition est égale à la valeur initiale à l'initialisation de la page
		alerte("la proposition indiquée est égale à la proposition initiale");
		//deleteFromMapInput(specialId);
		isInitialSecondVal = true;
		
	}else{
		alerte("mise à jour de la valeur de la nouvelle proposition dans le map");
		alerte("nouvelle valeur = "+newInput);
		addToMapInput(specialId, valInput[0], valInput[1], valInput[2], newInput, false);
		isInitialSecondVal = false;
	}
	
}

/**
 * Fonction de mise à jour de la valeur du compte de prise en charge d'une ligne dans le map
 * @param valInput
 * @param specialId
 * @param initInput
 * @param newInput
 * @param OtherValSuffixId
 * @return
 */
function updateCptePriseEnChargeInMap(valInput, specialId, initInput, newInput, OtherValSuffixId){
	if(valInput == null){
		// cas où l'élément n'existe pas encore dans le map
		alerte("ajout dans le map");
		var priseEnCharge = getOtherValueOnSameLine(OtherValSuffixId);
		
		alerte("priseEnCharge = "+priseEnCharge);
		addToMapInput(specialId, initInput, newInput, priseEnCharge, priseEnCharge, true);
		isInitialFirstVal = false;
		
	}				
	else if(newInput == valInput[0]){
		// cas où la nouvelle valeur de variation est égale à la valeur initiale à l'initialisation de la page
		alerte("la variation indiquée est égale à la variation initiale");
		//deleteFromMapInput(specialId);
		isInitialFirstVal = true;
		
	}else{
		alerte("mise à jour de la valeur de la nouvelle variation dans le map");
		alerte("nouvelle valeur = "+newInput);
		addToMapInput(specialId, valInput[0], newInput, valInput[2], valInput[3], false);
		isInitialFirstVal = false;
	}		

	// Mise à jour des champs de totaux
	setTotauxPriseEnCharge();
}

/**
 * Fonction de mise à jour de la valeur du booléen de sélection de prise en charge d'une ligne du tableau dans le map
 * @param valInput
 * @param specialId
 * @param initInput
 * @param newInput
 * @param OtherValSuffixId
 * @return
 */
function updateSelectedPriseEnChargeInMap(valInput, specialId, initInput, newInput, OtherValSuffixId){
	if(valInput == null){
		// cas où l'élément n'existe pas encore dans le map
		alerte("ajout dans le map");
		var compte = getOtherValueOnSameLine(OtherValSuffixId);
		
		alerte("compte = "+compte);
		addToMapInput(specialId, compte, compte, initInput, newInput, true);
		isInitialSecondVal = false;
	}
	else if(newInput == valInput[2]){
		//cas où la nouvelle valeur de proposition est égale à la valeur initiale à l'initialisation de la page
		alerte("la valeur indiquée est égale à la valeur initiale");
		//deleteFromMapInput(specialId);
		isInitialSecondVal = true;
		
	}else{
		alerte("mise à jour de la nouvelle valeur dans le map");
		alerte("nouvelle valeur = "+newInput);
		addToMapInput(specialId, valInput[0], valInput[1], valInput[2], newInput, false);
		isInitialSecondVal = false;
	}
	
	// Mise à jour des champs de totaux
	setTotauxPriseEnCharge();
}

 /**
  * Fonction de mise à jour de la valeur du booléen de sélection de l'ordre de virement d'une ligne du tableau dans le map
  * @param valInput
  * @param specialId
  * @param initInput
  * @param newInput
  * @param OtherValSuffixId
  * @return
  */
 function updateSelectedAVirerInMap(valInput, specialId, initInput, newInput, OtherValSuffixId){
 	if(valInput == null){
 		// cas où l'élément n'existe pas encore dans le map
 		alerte("ajout dans le map");
 		
 		addToMapInput(specialId, "", "", initInput, newInput, true);
 		isInitialSecondVal = false;
 	}
 	else if(newInput == valInput[2]){
 		// cas où la nouvelle valeur de proposition est égale à la valeur initiale à l'initialisation de la page
 		alerte("la valeur indiquée est égale à la valeur initiale");
 		isInitialSecondVal = true;
 		
 	}else{
 		alerte("mise à jour de la nouvelle valeur dans le map");
 		alerte("nouvelle valeur = "+newInput);
 		addToMapInput(specialId, valInput[0], valInput[1], valInput[2], newInput, false);
 		isInitialSecondVal = false;
 	}
 	
 	// Mise à jour des champs de totaux
 	setTotauxOrdreVirement();
 }

/**
 * Fonction de mise à jour des données du map après modification d'une ligne du tableau
 * @param inpt
 * @param nature
 * @return
 */
function updateMapInputs(inpt, nature){	
	alerte("updateMapInput(inpt, nature);");
	
	var inptId = inpt.id;
	
	alerte("input.id = "+inptId);
	alerte("nature = "+nature);
	// vérification et mise à jour de la valeur en suivant le bon format
	if((nature != "cptePriseEnCharge") && (nature != "selectPriseEnCharge") && (nature != "selectAVirer"))
		formatMoney(inpt, true);
	
	var newInput ;
	newInput = getComponentValue(inptId);
	newInput = (newInput != true && newInput != false) ? newInput.replace(/\s/g,'') : newInput;
		
	if((nature == "selectPriseEnCharge") || (nature == "selectAVirer")){
		// dans ce cas la valeur de initInput est forcément le contraire de newInput puisque c'est l'évènement onchange qui permet d'appeler cette fonction dans ce cas
		initInput = (newInput == true) ? false : true;
	}
	
	alerte("newInput = "+newInput+" : initInput = "+initInput);
	
	if(newInput != initInput){
		
		// on n'exécute la fonction uniquement dans le cas où la valeur obtenue après contrôle des valeurs saisies est différente de la valeur initiale
		var valInput = new Array();			
		// Récupération de l'id de la cellule du tableau afin de récupérer le specialId
		var specialId = getSpecialIdInTr(inpt);
		
		if((nature == "cptePriseEnCharge") || (nature == "selectPriseEnCharge") || (nature == "selectAVirer")) {
			specialId = getSpecialIdInColumn(inpt);
		}
		
		alerte("special id = "+specialId);		
		alerte("valeur initiale = "+initInput);
		alerte("nouvelle valeur = "+newInput);			
		alerte("nature = "+nature);
		
		// Tentative de récupération de la valeur correspondant au specialId dans le map
		valInput = mapInput[specialId];					
		
		if(nature == "variation"){		
			// Mise à jour ou ajout de la variation dans le map
			updateVariationInMap(valInput, specialId, initInput, newInput, "inpt_proposition");				
		} 
		else if(nature == "creditInitial"){		
			// Mise à jour ou ajout du crédit initial dans le map
			updateCrdInitialInMap(valInput, specialId, initInput, newInput, "inpt_proposition");	
		}
		else if(nature == "proposition"){
			// Mise à jour ou ajout de la proposition dans le map
			updatePropositionInMap(valInput, specialId, initInput, newInput, "inpt_variation");
		}
		else if(nature == "cptePriseEnCharge"){		
			// Mise à jour ou ajout de la valeur du compte de prise en charge dans le map
			updatePriseEnChargeInMap(valInput, specialId, initInput, newInput, "chbx_priseEnCharge");				
		}
		else if(nature == "selectPriseEnCharge"){
			// Mise à jour ou ajout de la valeur du booléen de selection de titres à prendre en charge dans le map
			updateSelectedPriseEnChargeInMap(valInput, specialId, initInput, newInput, "inpt_compte");
		}
		else if(nature == "selectAVirer"){
			// Mise à jour ou ajout de la valeur du booléen de selection de titres à virer dans le map
			updateSelectedAVirerInMap(valInput, specialId, initInput, newInput, "");
		}
		
		alerte("isInitialFirstVal = "+isInitialFirstVal+" and isInitialSecondVal = "+isInitialSecondVal);
		if((isInitialFirstVal) && (isInitialSecondVal)){
			// Dans le cas où les valeurs de variation et proposition de la ligne sont égales aux valeurs initiales, supprime la ligne du map
			deleteFromMapInput(specialId);
		}		

	}

}

 /**
  * Fonction de mise à jour et envoie des lignes d'un tableau à envoyer au serveur, sélectionnées par simple checkbox
  * @param inpt
  * @param nature
  * @return
  */
 function updateMapSingleCheckbox(inpt){	
 	alerte("updateMapInput(inpt);");
 	
 	// Récupération de l'id cu composant
 	var inptId = inpt.id; 	
	alerte("input.id = "+inptId);
 	
 	// Récupération de la nouvelle valeur
 	var newInput ;
 	newInput = getComponentValue(inptId);
 	//newInput = (newInput != true && newInput != false) ? newInput.replace(/\s/g,'') : newInput;
 		
	// Le composant étant un checkbox, la valeur de initInput est forcément le contraire de newInput puisque c'est l'évènement onchange qui permet d'appeler cette fonction dans ce cas
	initInput = (newInput == true) ? false : true;  	
 	alerte("newInput = "+newInput+" : initInput = "+initInput);
 	
 	// Mise à jour et envoie des données uniquement si la nouvelle valeur est différente de la valeur initiale
 	if(newInput != initInput){
 		
 		var valInput = new Array();
 		
 		// Récupération de l'id de la cellule du tableau afin de récupérer le specialId
 		var specialId = getSpecialIdInColumn(inpt);
 		
 		alerte("special id = "+specialId);		
 		alerte("valeur initiale = "+initInput);
 		alerte("nouvelle valeur = "+newInput);			
 		
 		// Tentative de récupération de la valeur correspondant au specialId dans le map
 		valInput = mapInput[specialId];					
 		
		// Mise à jour ou ajout de la valeur du booléen de selection de titres à virer dans le map
 	 	if(valInput == null){
 	 		// Dans le cas où l'élément n'existe pas encore dans le map, alors on l'y ajoute 	 	
 	 		alerte("Ajout de la valeur dans le map");
 	 		addToMapInput(specialId, initInput, newInput, "", "", true);
 	 		isInitialSecondVal = false;
 	 	}
 	 	else if(newInput == valInput[0]){
 	 		// Dans le cas où la nouvelle valeur est égale à la valeur initiale à l'initialisation de la page
 	 		alerte("la valeur indiquée est égale à la valeur initiale");
 	 		// Déclaration de la valeur comme étant égale à la valeur initiale
 	 		isInitialSecondVal = true;
 	 		
 	 	}else{
 	 		// Dans le cas où la nouvelle valeur est différente de la valeur initiale, on met à jour la valeur dans le map
 	 		alerte("mise à jour de la nouvelle valeur dans le map");
 	 		alerte("nouvelle valeur = "+newInput);
 	 		addToMapInput(specialId, newInput, valInput[1], valInput[2],valInput[3], false);
 	 		isInitialSecondVal = false;
 	 	}
 	 	
 	 	// Mise à jour des champs de totaux
 	 	//setTotauxOrdreVirement();
 		
 		alerte("isInitialFirstVal = "+isInitialFirstVal+" and isInitialSecondVal = "+isInitialSecondVal);
 		if((isInitialFirstVal) && (isInitialSecondVal)){
 			// Dans le cas où les nouvelles valeurs de la ligne sont égales aux valeurs initiales, supprime la ligne du map
 			deleteFromMapInput(specialId);
 		}		

 	}

 }

 
/**
 * Fonction d'ajout ou de modification d'une entrée dans le map
 * @param specialId
 * @param initVariation
 * @param newVariation
 * @param initProposition
 * @param newProposition
 * @param isNewRow
 * @return
 */
function addToMapInput(specialId, initVariation, newVariation, initProposition, newProposition, isNewRow){
	var valInput = new Array();	
	valInput[0] = initVariation;
	valInput[1] = newVariation;
	valInput[2] = initProposition;
	valInput[3] = newProposition;
	mapInput[specialId] = valInput;
	if(isNewRow){
		//incrémenter le nombre de lignes modifiées
		
		modRowNbr++;
		alerte("incrémentation du nombre de lignes modifiées effectuée: nombre = "+modRowNbr);
		updateVarView();
	}
}

/**
 * Fonction de suppression d'une entrée dans le map
 * @param specialId
 * @return
 */
function deleteFromMapInput(specialId){

	mapInput[specialId] = null;
	//mapInput.Delete(specialId);
	alerte("fin de la suppression dans le map");
	//Effectuer d'autres actions comme la modification du message, la décrémentation du nombre de lignes modifiées ou alors la désactivation du bouton
	modRowNbr -= 1;
	alerte("décrémentation du nombre de lignes modifiées effectuée: nombre = "+modRowNbr);
	updateVarView();
}

/**
 * Fonction de mise à jour de la vue, en spécifiant notamment le nombre d'éléments modifiés (dans le map)
 * @return
 */
function updateVarView(){
	var text = "";
	var btnValider;
	
	btnValider = document.getElementById("contentForm:cbtn_valider"); // cas de la ligne budgétaire par exple

	btnValider = (btnValider == null)? document.getElementById("contentForm:tble_table2:cbtn_valider") : btnValider; // cas de la prise en charge par exple
	
	btnValider = (btnValider == null)? document.getElementById("contentForm:tble_table:cbtn_valider") : btnValider; // cas de l'ordre de virement par exple
	
	if(modRowNbr <= 0){
		//text = "Aucune ligne n'a subi de modification par rapport à son état d'origine";
		text = "";
		//on masque le bouton valider
		btnValider.setAttribute("style","display:none");
	}
	else if(modRowNbr == 1){
		text = "une ligne a subi une modification";
		//affichage du bouton valider
		btnValider.setAttribute("style","display:inline");
		
	}
	else {
		text = modRowNbr+" lignes ont subi des modifications";
		//affichage du bouton valider est déjà fait, puisque'on est passé par nb=1
		
	}
	document.getElementById("contentForm:optt_varInfos").innerHTML = text;
	alerte("mise à jour de la vue effectuée, avec modRowNbr = "+modRowNbr);
}

/**
 * Fonction d'envoie du map par JSON au serveur
 * @param btnId
 * @return
 */
function sendMapInput(btnId){
	//var json = JSON.stringify(MapInputiation);
	//alert("json = "+json);
	var mapInputId = replaceLast(btnId, ":", "iphd_mapInput");
	document.getElementById(mapInputId).value = JSON.stringify(mapInput);
	alerte("Map prêt à être envoyé");
}

/**
 * Fonction de calcul des totaux de la prise en charge
 * @return
 */
function setTotauxPriseEnCharge(){
	var tagTableId = "contentForm:tble_table2";
	var checkBoxSuffix = "chbx_priseEnCharge";
	
	alerte("Mise à jour des totaux de la prise en charge");
	
	try{

		// Total des titres de paiements
		setTotalByColumn(tagTableId, "optt_Tit", "optt_totalTit", false, checkBoxSuffix);
		
		// Total des montants TTC
		setTotalByColumn(tagTableId, "optt_VMnt", "optt_totalVMnt", true, checkBoxSuffix);
		
		// Total des montants net
		setTotalByColumn(tagTableId, "optt_VMntNet", "optt_totalVMntNet", true, checkBoxSuffix);
		
		// Total des retenues
		setTotalByColumn(tagTableId, "optt_VMntRtn", "optt_totalVMntRtn", true, checkBoxSuffix);
		
	}catch(e){
		alerte("Problème survenu lors de la mise à jour des totaux de la prise en charge");
		alerte(e.toString());
	}
	
}
 function setTotauxbdrEncaissement(){
		var tagTableId = "contentForm:tble_table2";
		//var checkBoxSuffix = "chbx_priseEnCharge";
		
		//alerte("Mise à jour des totaux de la prise en charge");
		
		try{

			// Total des titres de paiements
		//	setTotalByColumn(tagTableId, "optt_Tit", "optt_totalTit", false, checkBoxSuffix);
			
			// Total des montants TTC
			setTotalByColumn(tagTableId,"optt_VMnt" ,"optt_totalVMnt", true);
			// Total des montants net
		//	setTotalByColumn(tagTableId, "optt_VMntNet", "optt_totalVMntNet", true, checkBoxSuffix);
			
			// Total des retenues
		//	setTotalByColumn(tagTableId, "optt_VMntRtn", "optt_totalVMntRtn", true, checkBoxSuffix);
			
		}catch(e){
			alerte("Problème survenu lors de la mise à jour des totaux du bordereau d'encaissement");
			alerte(e.toString());
		}
		
	}
 function setTotauxdeclaration(){
		var tagTableId = "contentForm:tble_table";
		var checkBoxSuffix = "chbx_ENCAIS";
		
		//alerte("Mise à jour des totaux de la prise en charge");
		
		try{

			// Total des encaissements
		//	setTotalByColumn(tagTableId, "optt_Tit", "optt_totalTit", false, checkBoxSuffix);
			
			// Total des montants TTC
			setTotalByColumn(tagTableId,"optt_VMnt" ,"optt_totalencais", true, checkBoxSuffix);
			// Total des montants net
		//	setTotalByColumn(tagTableId, "optt_VMntNet", "optt_totalVMntNet", true, checkBoxSuffix);
			
			// Total des retenues
		//	setTotalByColumn(tagTableId, "optt_VMntRtn", "optt_totalVMntRtn", true, checkBoxSuffix);
			
		}catch(e){
			alerte("Problème survenu lors de la mise à jour des totaux du bordereau d'encaissement");
			alerte(e.toString());
		}
		
	}
 
 /**
  * Fonction de calcul des totaux de l'ordre de virement
  * @return
  */
 function setTotauxOrdreVirement(){
 	var tagTableId = "contentForm:tble_table";
 	var tagTableDtlOrdVirId = "contentForm:tble_table2";
 	var checkBoxSuffix = "chbx_aVirer";
 	
 	alerte("Mise à jour des totaux de l'ordre de virement");
 	
 	try{

 		// Total des montants de Détails d'Ordre de Virement
 		setTotalByColumn(tagTableDtlOrdVirId, "optt_VMntDtlOrdVir", "optt_totalVMntDtlOrdVir", true);
 	}catch(e){
 		alerte("Problème survenu lors de la mise à jour des totaux des détails de l'ordre de virement");
 		alerte(e.toString());
 	}
 	
 	try{
 		// Total des titres de paiements
 		setTotalByColumn(tagTableId, "optt_titVir", "optt_totalTitVir", false, checkBoxSuffix);
 		
 		// Total des montants
 		setTotalByColumn(tagTableId, "optt_VMnt", "optt_totalVMnt", true, checkBoxSuffix);
 		
 	}catch(e){
 		alerte("Problème survenu lors de la mise à jour des totaux des titres de virement de l'ordre de virement");
 		alerte(e.toString());
 	}
 	
 }
