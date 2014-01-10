package com.gss.gevee.be.mouv.svco;

import java.util.List;

import com.gss.gevee.be.core.exception.GeveeAppException;
import com.gss.gevee.be.core.svco.base.IBaseSvco;
import com.gss.gevee.be.mouv.entity.TabCon;
import com.gss.gevee.be.mouv.entity.TabDep;

public interface ISvcoDep extends IBaseSvco<TabDep>{

	TabDep demarrer(TabDep tabDep) throws GeveeAppException;

	TabDep cloturer(TabDep tabDep) throws GeveeAppException;

	TabDep receptionner(TabDep tabDep) throws GeveeAppException;

	List<TabCon> rechercherConParEtatEnt(String etatEnt) throws GeveeAppException;
 
}
