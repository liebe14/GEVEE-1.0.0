package com.gss.gevee.ui.core.base;

public class BasicTrt {
	
public static String SEPERATEUR_2 = "##";
	
	public static Traitement AJOUTER;
	public static Traitement COPIER;
	public static Traitement MODIFIER;
	public static Traitement AFFICHER;
	public static Traitement SUPPRIMER;
	public static Traitement SELECTIONNER;
	
	public static String ajouter = "Ajouter";
	public static String copier = "Copier";
	public static String modifier = "Modifier";
	public static String afficher = "Afficher";
	public static String supprimer = "Supprimer";
	public static String selectionner = "Selectionner";
	
	
	static{
		
		/***
		 * AJOUTER 
		 */
		AJOUTER = new Traitement (/* Type */Traitement.STANDARD , /* Code */ "T01-1" , /* Libell� */ ajouter , /* Commentaire */ ajouter , /* M�thode */ "ajouter",
				/* Config IHM */ new boolean[]{true,true,true,false} , /* Config Visibilit� */ "100" ,
				/* Type de modal panel */ Traitement.MODAL_NO, /* Message du modal panel */ "", /* Type de progress bar */ Traitement.PROGRESS_NO, 
				/* Raccourci clavier */ "F2", /* index */ "01", /* reRender */ Traitement.RERENDER_NO, /* image */"/shared/images/new_01-22x22.png" );	
		
		/***
		 * COPIER
		 */
		COPIER = new Traitement(/* Type */Traitement.STANDARD , /* Code */ "T01-2" , /* Libell� */ copier , /* Commentaire */ copier , /* M�thode */ "copier",
				/* Config IHM */ new boolean[]{true,true,true,false} , /* Config Visibilit� */ "102" ,
				/* Type de modal panel */ Traitement.MODAL_NO, /* Message du modal panel */ "", /* Type de progress bar */ Traitement.PROGRESS_NO, 
				/* Raccourci clavier */ "F3", /* index */ "02", /* reRender */ Traitement.RERENDER_NO, /* image */"/shared/images/copy_02-24x24.png" );	
		
		/***
		 * MODIFIER
		 */
		MODIFIER = new Traitement(/* Type */Traitement.STANDARD , /* Code */ "T02" , /* Libell� */ modifier , /* Commentaire */ modifier , /* M�thode */ "modifier",
				/* Config IHM */ new boolean[]{true,true,true,false} , /* Config Visibilit� */ "112" ,
				/* Type de modal panel */ Traitement.MODAL_NO, /* Message du modal panel */ "", /* Type de progress bar */ Traitement.PROGRESS_NO, 
				/* Raccourci clavier */ "F4", /* index */ "03", /* reRender */ Traitement.RERENDER_NO, /* image */"/shared/images/edit_03-24x24.png" );	
		
		/***
		 * AFFICHER
		 */
		AFFICHER = new Traitement (/* Type */Traitement.STANDARD , /* Code */ "T03" , /* Libell� */ afficher , /* Commentaire */ afficher , /* M�thode */ "afficher",
				/* Config IHM */ new boolean[]{true,true,false,false} , /* Config Visibilit� */ "002" ,
				/* Type de modal panel */ Traitement.MODAL_NO, /* Message du modal panel */ "", /* Type de progress bar */ Traitement.PROGRESS_NO, 
				/* Raccourci clavier */ "F5", /* index */ "04", /* reRender */ Traitement.RERENDER_NO, /* image */"/shared/images/view_01-24x24.png" );	
		
		/***
		 * SUPPRIMER
		 */
		SUPPRIMER = new Traitement(/* Type */Traitement.STANDARD , /* Code */ "T08" , /* Libell� */ supprimer , /* Commentaire */ supprimer , /* M�thode */ "supprimer",
				/* Config IHM */ new boolean[]{true,true,true,false} , /* Config Visibilit� */ "112" ,
				/* Type de modal panel */ Traitement.MODAL_SIMPLE, /* Message du modal panel */ "Confirmer la suppression", /* Type de progress bar */ Traitement.PROGRESS_SIMPLE, 
				/* Raccourci clavier */ "F6", /* index */ "05", /* reRender */ Traitement.RERENDER_MAIN_PANEL, /* image */"/shared/images/delete_01-24x24.png" );	
		
		/***
		 * SELECTIONNER
		 */
		SELECTIONNER = new Traitement(/* Type */Traitement.STANDARD , /* Code */ "Selectionner" , /* Libell� */ selectionner , /* Commentaire */ selectionner , /* M�thode */ "selectionner", 
				/* Config IHM */ new boolean[]{true,true,true,false} , /* Config Visibilit� */ "002" ,
				/* Type de modal panel */ Traitement.MODAL_NO, /* Message du modal panel */ "", /* Type de progress bar */ Traitement.PROGRESS_NO, 
				/* Raccourci clavier */ "F7", /* index */ "06", /* reRender */ Traitement.RERENDER_NO, /* image */"/shared/images/select_01-24x24.png" );	
		
	}

}
