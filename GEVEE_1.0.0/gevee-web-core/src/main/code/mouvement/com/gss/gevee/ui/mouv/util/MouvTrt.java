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

}
