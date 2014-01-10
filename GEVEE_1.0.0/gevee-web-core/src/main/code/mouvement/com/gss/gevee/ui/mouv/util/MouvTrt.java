package com.gss.gevee.ui.mouv.util;

import com.gss.gevee.ui.core.base.GeveeTrt;
import com.gss.gevee.ui.core.base.Traitement;

public class MouvTrt extends GeveeTrt{
	
	public static Traitement GENERER_ORD_TRANS;	
	static{
		
		GENERER_ORD_TRANS = new Traitement(/* Type */Traitement.SPECIFIQUE , /* Code */ "Gen_ord_tran" , /* Libell� */ "G�n�rer l'ordre de tranport" , /* Commentaire */ "" , /* M�thode */ "genererOrdTrans",
				/* Config IHM */ new boolean[]{true,true,true,false} , /* Config Visibilit� */ "000",
				/* Type de modal panel */ Traitement.MODAL_SPECIAL, /* Message du modal panel */ "", /* Type de progress bar */ Traitement.PROGRESS_SIMPLE, 
				/* Raccourci clavier */ "Shift+F2", /* index */ "11", /* reRender */ "", /* image */ "" );
	}
	
	/***
	 * DEMMARRER UN DEPLACEMENT
	 */
	public static Traitement DEMARRER_DEP;	
	static{
		
		DEMARRER_DEP = new Traitement(/* Type */Traitement.SPECIFIQUE , /* Code */ "Dem_dep" , /* Libell� */ "D�marrer le d�placement" , /* Commentaire */ "" , /* M�thode */ "demarrer",
				/* Config IHM */ new boolean[]{true,true,true,false} , /* Config Visibilit� */ "112",
				/* Type de modal panel */ Traitement.MODAL_SIMPLE, /* Message du modal panel */ "Confirmer le d�marrage ?", /* Type de progress bar */ Traitement.PROGRESS_SIMPLE, 
				/* Raccourci clavier */ "Shift+F3", /* index */ "10", /* reRender */ Traitement.RERENDER_MAIN_PANEL, /* image */ "" );
	}
	
	/***
	 * CLOTURER UN DEPLACEMENT
	 */
	public static Traitement CLOTURER_DEP;	
	static{
		
		CLOTURER_DEP = new Traitement(/* Type */Traitement.SPECIFIQUE , /* Code */ "Clo_dep" , /* Libell� */ "Cl�turer le d�placement" , /* Commentaire */ "" , /* M�thode */ "cloturer",
				/* Config IHM */ new boolean[]{true,true,true,false} , /* Config Visibilit� */ "112",
				/* Type de modal panel */ Traitement.MODAL_SIMPLE, /* Message du modal panel */ "Confirmer la cl�ture ?", /* Type de progress bar */ Traitement.PROGRESS_SIMPLE, 
				/* Raccourci clavier */ "Shift+F4", /* index */ "12", /* reRender */ Traitement.RERENDER_MAIN_PANEL, /* image */ "" );
	}
	
	public static Traitement RECEPTIONNER;	
	static{
		
		RECEPTIONNER = new Traitement(/* Type */Traitement.SPECIFIQUE , /* Code */ "Recep_dep" , /* Libell� */ "R�ceptionner" , /* Commentaire */ "" , /* M�thode */ "beforeReceptionner",
				/* Config IHM */ new boolean[]{true,true,true,false} , /* Config Visibilit� */ "000",
				/* Type de modal panel */ Traitement.MODAL_SPECIAL, /* Message du modal panel */ "", /* Type de progress bar */ Traitement.PROGRESS_SIMPLE, 
				/* Raccourci clavier */ "Shift+F5", /* index */ "14", /* reRender */ "mpnl_receptionner", /* image */ "" );
	}
	
	public static Traitement ENREG_MOUV;	
	static{
		
		ENREG_MOUV = new Traitement(/* Type */Traitement.SPECIFIQUE , /* Code */ "Enreg_mouv" , /* Libell� */ "Enregistrer un mouvement" , /* Commentaire */ "" , /* M�thode */ "enregistrerMouv",
				/* Config IHM */ new boolean[]{true,true,true,false} , /* Config Visibilit� */ "000",
				/* Type de modal panel */ Traitement.MODAL_SPECIAL, /* Message du modal panel */ "Confirmer l'enregistrement d'un mouvement ?", /* Type de progress bar */ Traitement.PROGRESS_SIMPLE, 
				/* Raccourci clavier */ "Shift+F6", /* index */ "15", /* reRender */ Traitement.RERENDER_MAIN_PANEL, /* image */ "" );
	}
	
	/***
	 * VALIDER MOUV
	 */
	public static Traitement VALIDER_MOUV;	
	static{
		
		VALIDER_MOUV = new Traitement(/* Type */Traitement.SPECIFIQUE , /* Code */ "Val_mouv" , /* Libell� */ "Valider" , /* Commentaire */ "" , /* M�thode */ "valider",
				/* Config IHM */ new boolean[]{true,true,true,false} , /* Config Visibilit� */ "112",
				/* Type de modal panel */ Traitement.MODAL_SIMPLE, /* Message du modal panel */ "Confirmer la validation ?", /* Type de progress bar */ Traitement.PROGRESS_SIMPLE, 
				/* Raccourci clavier */ "Shift+F3", /* index */ "10", /* reRender */ Traitement.RERENDER_MAIN_PANEL, /* image */ "" );
	}
	
	public static Traitement ENREG_CHKPT;	
	static{
		
		ENREG_CHKPT = new Traitement(/* Type */Traitement.SPECIFIQUE , /* Code */ "Enreg_chk" , /* Libell� */ "Enregistrer un chekpoint" , /* Commentaire */ "" , /* M�thode */ "enregistrerChk",
				/* Config IHM */ new boolean[]{true,true,true,false} , /* Config Visibilit� */ "000",
				/* Type de modal panel */ Traitement.MODAL_SPECIAL, /* Message du modal panel */ "Confirmer l'enregistrement du chekpoint ?", /* Type de progress bar */ Traitement.PROGRESS_SIMPLE, 
				/* Raccourci clavier */ "Shift+F4", /* index */ "11", /* reRender */ Traitement.RERENDER_MAIN_PANEL, /* image */ "" );
	}
	
	/***
	 * VALIDER CHK
	 */
	public static Traitement VALIDER_CHK;	
	static{
		
		VALIDER_CHK = new Traitement(/* Type */Traitement.SPECIFIQUE , /* Code */ "Val_chk" , /* Libell� */ "Valider" , /* Commentaire */ "" , /* M�thode */ "valider",
				/* Config IHM */ new boolean[]{true,true,true,false} , /* Config Visibilit� */ "112",
				/* Type de modal panel */ Traitement.MODAL_SIMPLE, /* Message du modal panel */ "Confirmer la validation ?", /* Type de progress bar */ Traitement.PROGRESS_SIMPLE, 
				/* Raccourci clavier */ "Shift+F3", /* index */ "10", /* reRender */ Traitement.RERENDER_MAIN_PANEL, /* image */ "" );
	}

}
