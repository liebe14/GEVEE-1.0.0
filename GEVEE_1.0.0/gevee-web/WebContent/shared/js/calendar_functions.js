var yearCalendarCalculated = false, calendarHeaderUpdated = false;

/*
 * Fonction sur les calendriers qui permet de masquer les mois et les jours du calendriers, lors de la sélection des années uniquement
 * notamment lors de la création d'un nouvel exercice budgétaire
 */
function hideCalendarMonths(calendarId){
	var racine = calendarId+"DateEditorLayoutM";
	if(! yearCalendarCalculated){
		try{
			for(var i = 0; i < 12 ; i++){
				var monthId = racine+""+i+"";
				var divMonth = document.getElementById(monthId);
				divMonth.setAttribute("style", "display:none");
			}
			// Mise à jour des colspan des boutons Ok et Annuler du calendrier
			var divOk = document.getElementById(calendarId+"DateEditorButtonOk");
			divOk.parentNode.setAttribute("colspan","1");
			var tdOk = divOk.parentNode;
					
			var divCancel = document.getElementById(calendarId+"DateEditorButtonCancel");
			divCancel.parentNode.setAttribute("colspan","1");
			var tdCancel = divCancel.parentNode;		
			
			var trOkCancel = tdOk.parentNode;
			
			trOkCancel.innerHTML = "<td ></td><td ></td>"+trOkCancel.innerHTML;
			
			// Masquage du div d'arrière plan
			document.getElementById(calendarId+"EditorLayoutShadow").setAttribute("style", "display:none");
						
			yearCalendarCalculated = true;
		}catch(e){
			alerte("élément introuvable pour hideCalendarMonths: la fonction peut-être déjà été exécutée");
		}
	}
}

/*
 * Fonction qui traduit les valeurs anglaises affichés par les contrôles du calendrier, en leurs équivalents à Français
 */
function translateCalendarFooter(calendarId){
	alerte("translateCalendarFooter");
	try{
		
		var racine = calendarId+"Footer"; // id du td contenant le Footer du calendar
		var tagRacine = document.getElementById(racine); 
		var length = tagRacine.getElementsByTagName("div").length;
		alerte("length = "+length);
		for(var i=0; i<length; i++){
			if(tagRacine.getElementsByTagName("div")[i].innerHTML == "Clean")
				tagRacine.getElementsByTagName("div")[i].innerHTML = "Initialiser";
			else if(tagRacine.getElementsByTagName("div")[i].innerHTML == "Today")
				tagRacine.getElementsByTagName("div")[i].innerHTML = "Aujourd'hui";
			else if(tagRacine.getElementsByTagName("div")[i].innerHTML == "Apply")
				tagRacine.getElementsByTagName("div")[i].innerHTML = "Appliquer";

		}
	}catch(e){
		alerte("Elément introuvable lors de l'exécution de la méthode translateCalendarFooter");
	}
	
}

/*
 *	Fonction qui permet de définir la propriété "onclick" du calendrier, de sorte d'y mettre la méthode à appeller
 * 	et qui masquera notamment les mois et les jours lors de la sélection des années, par exemple lors de la création d'une exercice budgétaire
 */
function setOnclickCalendar(calendarId){
	
	setOnclick(calendarId, "hideCalendarMonths(this.id)");
	updateCalendarHeader(document.getElementById(calendarId));
	
}

/*
 *	Fonction qui permet de définir la propriété "onclick" du calendrier, de sorte d'y mettre la methode qui permet de traduire les labels de
 *  composants en français
 */
function setOnclickCalendarFr(calendarId){
	
	var fonction = "translateCalendarFooter('"+calendarId+"')";
	var id = calendarId+"Popup";
	
	// Renseignement de la propriété onclick du popup
	setOnclick(id, fonction);
	
	// Renseignement de la propriété onclik du calendrier
	setOnclick(calendarId, fonction);

}

/*
 * Fonction qui permet de mettre à jour la propriété "onclick" du composant d'id componentId, pour y ajouter une fonction à appeler
 * Elle suppose que la valeur actuelle de la propriété se termine par un point-virgule (ce qui est conseillé, mais pas obligatoire)
 * Son implémentation pourra être revue de sorte qu'elle soit utilisable également lorsque la propriété ne se termine pas par un point-vigule (en y ajoutant un)
 */
function setOnclick(componentId, fonction){
	try{		
		var tag = document.getElementById(componentId);
		var var_onclick = tag.getAttribute("onclick");
		var_onclick = (var_onclick == null) ? "" : var_onclick;
		var_onclick = var_onclick+""+fonction+";";
		tag.setAttribute("onclick", var_onclick);
		
		//updateCalendarHeader(tag);
	}catch(e){
		alerte("élément d'id "+componentId+" introuvable lors de l'exécution de setOnclick()");
	}
}

/*
 *	Fonction qui permet de mettre à jour l'entête du calendrier, notamment en masquant le mois
 *	Elle est utilisée pour la sélection des années, lorsqu'on ne veut voir que les années dans le calendrier 
 */
function updateCalendarHeader(tag_cald){
		try{
			// Masquage du mois sur l'entête du calendrier			
			var calendarId = tag_cald.getAttribute("id").replace('InputDate','');
			var calendarHeader = document.getElementById(calendarId+"Header")
			var divEntete = calendarHeader.getElementsByTagName("td")[2].firstChild;
			divEntete.innerHTML = divEntete.innerHTML.replace(/[^0-9]*/g,'');
			
			// Masquage des flèches permettant de naviguer entre les mois
			//calendarHeader.getElementsByTagName("td")[1].firstChild.innerHTML = "";
			//calendarHeader.getElementsByTagName("td")[3].firstChild.innerHTML = "";
			
			var tdFlecheGauche = calendarHeader.getElementsByTagName("td")[1];
			var tdFlecheDroite = calendarHeader.getElementsByTagName("td")[3];
			tdFlecheGauche.removeChild(tdFlecheGauche.firstChild);
			tdFlecheDroite.removeChild(tdFlecheDroite.firstChild);
		}catch(e){
			alerte("élément introuvable pour la méthode updateCalendarHeader()");
		}
		
}