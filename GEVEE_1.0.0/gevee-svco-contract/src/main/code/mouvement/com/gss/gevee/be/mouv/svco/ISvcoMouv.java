package com.gss.gevee.be.mouv.svco;

import java.util.List;

import com.gss.gevee.be.core.exception.GeveeAppException;
import com.gss.gevee.be.core.svco.base.IBaseSvco;
import com.gss.gevee.be.mouv.entity.TabMouv;

public interface ISvcoMouv extends IBaseSvco<TabMouv>{

	List<TabMouv> rechercherParCodRefDep(String refDep) throws GeveeAppException;

	TabMouv valider(TabMouv tabMouv) throws GeveeAppException; 

}
