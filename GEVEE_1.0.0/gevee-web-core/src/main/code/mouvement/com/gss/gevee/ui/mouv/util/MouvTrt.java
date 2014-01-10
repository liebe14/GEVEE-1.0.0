package com.gss.gevee.ui.mouv.util;

import com.gss.gevee.ui.core.base.GeveeTrt;
import com.gss.gevee.ui.core.base.Traitement;

public class MouvTrt extends GeveeTrt{
	
	public static Traitement GENERER_ORD_TRANS;	
	static{
		
		GENERER_ORD_TRANS = new Traitement(/* Type */Traitement.SPECIFIQUE , /* Code */ "Gen_ord_tran" , /* Libellé */ "Générer l'ordre de tranport" , /* Commentaire */ "" , /* Méthode */ "genererOrdTrans",
				/* Config IHM */ new boolean[]{true,true,true,false} , /* Config Visibilité */ "000",
				/* Type de modal panel */ Traitement.MODAL_SPECIAL, /* Message du modal panel */ "", /* Type de progress bar */ Traitement.PROGRESS_SIMPLE, 
				/* Raccourci clavier */ "Shift+F2", /* index */ "11", /* reRender */ "", /* image */ "" );
	}
	
	/***
	 * DEMMARRER UN DEPLACEMENT
	 */
	public static Traitement DEMARRER_DEP;	
	static{
		
		DEMARRER_DEP = new Traitement(/* Type */Traitement.SPECIFIQUE , /* Code */ "Dem_dep" , /* Libellé */ "Démarrer le déplacement" , /* Commentaire */ "" , /* Méthode */ "demarrer",
				/* Config IHM */ new boolean[]{true,true,true,false} , /* Config Visibilité */ "112",
				/* Type de modal panel */ Traitement.MODAL_SIMPLE, /* Message du modal panel */ "Confirmer le démarrage ?", /* Type de progress bar */ Traitement.PROGRESS_SIMPLE, 
				/* Raccourci clavier */ "Shift+F3", /* index */ "10", /* reRender */ Traitement.RERENDER_MAIN_PANEL, /* image */ "" );
	}
	
	/***
	 * CLOTURER UN DEPLACEMENT
	 */
	public static Traitement CLOTURER_DEP;	
	static{
		
		CLOTURER_DEP = new Traitement(/* Type */Traitement.SPECIFIQUE , /* Code */ "Clo_dep" , /* Libellé */ "Clôturer le déplacement" , /* Commentaire */ "" , /* Méthode */ "cloturer",
				/* Config IHM */ new boolean[]{true,true,true,false} , /* Config Visibilité */ "112",
				/* Type de modal panel */ Traitement.MODAL_SIMPLE, /* Message du modal panel */ "Confirmer la clôture ?", /* Type de progress bar */ Traitement.PROGRESS_SIMPLE, 
				/* Raccourci clavier */ "Shift+F4", /* index */ "12", /* reRender */ Traitement.RERENDER_MAIN_PANEL, /* image */ "" );
	}
	
	public static Traitement RECEPTIONNER;	
	static{
		
		RECEPTIONNER = new Traitement(/* Type */Traitement.SPECIFIQUE , /* Code */ "Recep_dep" , /* Libellé */ "Réceptionner" , /* Commentaire */ "" , /* Méthode */ "beforeReceptionner",
				/* Config IHM */ new boolean[]{true,true,true,false} , /* Config Visibilité */ "000",
				/* Type de modal panel */ Traitement.MODAL_SPECIAL, /* Message du modal panel */ "", /* Type de progress bar */ Traitement.PROGRESS_SIMPLE, 
				/* Raccourci clavier */ "Shift+F5", /* index */ "14", /* reRender */ "mpnl_receptionner", /* image */ "" );
	}
	
	public static Traitement ENREG_MOUV;	
	static{
		
		ENREG_MOUV = new Traitement(/* Type */Traitement.SPECIFIQUE , /* Code */ "Enreg_mouv" , /* Libellé */ "Enregistrer un mouvement" , /* Commentaire */ "" , /* Méthode */ "enregistrerMouv",
				/* Config IHM */ new boolean[]{true,true,true,false} , /* Config Visibilité */ "000",
				/* Type de modal panel */ Traitement.MODAL_SPECIAL, /* Message du modal panel */ "Confirmer l'enregistrement d'un mouvement ?", /* Type de progress bar */ Traitement.PROGRESS_SIMPLE, 
				/* Raccourci clavier */ "Shift+F6", /* index */ "15", /* reRender */ Traitement.RERENDER_MAIN_PANEL, /* image */ "" );
	}
	
	/***
	 * VALIDER MOUV
	 */
	public static Traitement VALIDER_MOUV;	
	static{
		
		VALIDER_MOUV = new Traitement(/* Type */Traitement.SPECIFIQUE , /* Code */ "Val_mouv" , /* Libellé */ "Valider" , /* Commentaire */ "" , /* Méthode */ "valider",
				/* Config IHM */ new boolean[]{true,true,true,false} , /* Config Visibilité */ "112",
				/* Type de modal panel */ Traitement.MODAL_SIMPLE, /* Message du modal panel */ "Confirmer la validation ?", /* Type de progress bar */ Traitement.PROGRESS_SIMPLE, 
				/* Raccourci clavier */ "Shift+F3", /* index */ "10", /* reRender */ Traitement.RERENDER_MAIN_PANEL, /* image */ "" );
	}
	
	public static Traitement ENREG_CHKPT;	
	static{
		
		ENREG_CHKPT = new Traitement(/* Type */Traitement.SPECIFIQUE , /* Code */ "Enreg_chk" , /* Libellé */ "Enregistrer un chekpoint" , /* Commentaire */ "" , /* Méthode */ "enregistrerChk",
				/* Config IHM */ new boolean[]{true,true,true,false} , /* Config Visibilité */ "000",
				/* Type de modal panel */ Traitement.MODAL_SPECIAL, /* Message du modal panel */ "Confirmer l'enregistrement du chekpoint ?", /* Type de progress bar */ Traitement.PROGRESS_SIMPLE, 
				/* Raccourci clavier */ "Shift+F4", /* index */ "11", /* reRender */ Traitement.RERENDER_MAIN_PANEL, /* image */ "" );
	}
	
	/***
	 * VALIDER CHK
	 */
	public static Traitement VALIDER_CHK;	
	static{
		
		VALIDER_CHK = new Traitement(/* Type */Traitement.SPECIFIQUE , /* Code */ "Val_chk" , /* Libellé */ "Valider" , /* Commentaire */ "" , /* Méthode */ "valider",
				/* Config IHM */ new boolean[]{true,true,true,false} , /* Config Visibilité */ "112",
				/* Type de modal panel */ Traitement.MODAL_SIMPLE, /* Message du modal panel */ "Confirmer la validation ?", /* Type de progress bar */ Traitement.PROGRESS_SIMPLE, 
				/* Raccourci clavier */ "Shift+F3", /* index */ "10", /* reRender */ Traitement.RERENDER_MAIN_PANEL, /* image */ "" );
	}

}
