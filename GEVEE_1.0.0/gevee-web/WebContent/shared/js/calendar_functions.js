var yearCalendarCalculated = false, calendarHeaderUpdated = false;

/*
 * Fonction sur les calendriers qui permet de masquer les mois et les jours du calendriers, lors de la s�lection des ann�es uniquement
 * notamment lors de la cr�ation d'un nouvel exercice budg�taire
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
			// Mise � jour des colspan des boutons Ok et Annuler du calendrier
			var divOk = document.getElementById(calendarId+"DateEditorButtonOk");
			divOk.parentNode.setAttribute("colspan","1");
			var tdOk = divOk.parentNode;
					
			var divCancel = document.getElementById(calendarId+"DateEditorButtonCancel");
			divCancel.parentNode.setAttribute("colspan","1");
			var tdCancel = divCancel.parentNode;		
			
			var trOkCancel = tdOk.parentNode;
			
			trOkCancel.innerHTML = "<td ></td><td ></td>"+trOkCancel.innerHTML;
			
			// Masquage du div d'arri�re plan
			document.getElementById(calendarId+"EditorLayoutShadow").setAttribute("style", "display:none");
						
			yearCalendarCalculated = true;
		}catch(e){
			alerte("�l�ment introuvable pour hideCalendarMonths: la fonction peut-�tre d�j� �t� ex�cut�e");
		}
	}
}

/*
 * Fonction qui traduit les valeurs anglaises affich�s par les contr�les du calendrier, en leurs �quivalents � Fran�ais
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
		alerte("El�ment introuvable lors de l'ex�cution de la m�thode translateCalendarFooter");
	}
	
}

/*
 *	Fonction qui permet de d�finir la propri�t� "onclick" du calendrier, de sorte d'y mettre la m�thode � appeller
 * 	et qui masquera notamment les mois et les jours lors de la s�lection des ann�es, par exemple lors de la cr�ation d'une exercice budg�taire
 */
function setOnclickCalendar(calendarId){
	
	setOnclick(calendarId, "hideCalendarMonths(this.id)");
	updateCalendarHeader(document.getElementById(calendarId));
	
}

/*
 *	Fonction qui permet de d�finir la propri�t� "onclick" du calendrier, de sorte d'y mettre la methode qui permet de traduire les labels de
 *  composants en fran�ais
 */
function setOnclickCalendarFr(calendarId){
	
	var fonction = "translateCalendarFooter('"+calendarId+"')";
	var id = calendarId+"Popup";
	
	// Renseignement de la propri�t� onclick du popup
	setOnclick(id, fonction);
	
	// Renseignement de la propri�t� onclik du calendrier
	setOnclick(calendarId, fonction);

}

/*
 * Fonction qui permet de mettre � jour la propri�t� "onclick" du composant d'id componentId, pour y ajouter une fonction � appeler
 * Elle suppose que la valeur actuelle de la propri�t� se termine par un point-virgule (ce qui est conseill�, mais pas obligatoire)
 * Son impl�mentation pourra �tre revue de sorte qu'elle soit utilisable �galement lorsque la propri�t� ne se termine pas par un point-vigule (en y ajoutant un)
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
		alerte("�l�ment d'id "+componentId+" introuvable lors de l'ex�cution de setOnclick()");
	}
}

/*
 *	Fonction qui permet de mettre � jour l'ent�te du calendrier, notamment en masquant le mois
 *	Elle est utilis�e pour la s�lection des ann�es, lorsqu'on ne veut voir que les ann�es dans le calendrier 
 */
function updateCalendarHeader(tag_cald){
		try{
			// Masquage du mois sur l'ent�te du calendrier			
			var calendarId = tag_cald.getAttribute("id").replace('InputDate','');
			var calendarHeader = document.getElementById(calendarId+"Header")
			var divEntete = calendarHeader.getElementsByTagName("td")[2].firstChild;
			divEntete.innerHTML = divEntete.innerHTML.replace(/[^0-9]*/g,'');
			
			// Masquage des fl�ches permettant de naviguer entre les mois
			//calendarHeader.getElementsByTagName("td")[1].firstChild.innerHTML = "";
			//calendarHeader.getElementsByTagName("td")[3].firstChild.innerHTML = "";
			
			var tdFlecheGauche = calendarHeader.getElementsByTagName("td")[1];
			var tdFlecheDroite = calendarHeader.getElementsByTagName("td")[3];
			tdFlecheGauche.removeChild(tdFlecheGauche.firstChild);
			tdFlecheDroite.removeChild(tdFlecheDroite.firstChild);
		}catch(e){
			alerte("�l�ment introuvable pour la m�thode updateCalendarHeader()");
		}
		
}