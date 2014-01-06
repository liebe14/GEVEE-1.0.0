package com.gss.gevee.be.mouv.svco;

import com.gss.gevee.be.core.exception.GeveeAppException;
import com.gss.gevee.be.core.svco.base.IBaseSvco;
import com.gss.gevee.be.mouv.entity.TabDep;

public interface ISvcoDep extends IBaseSvco<TabDep>{

	TabDep demarrer(TabDep tabDep) throws GeveeAppException;

	TabDep cloturer(TabDep tabDep) throws GeveeAppException;

	TabDep receptionner(TabDep tabDep) throws GeveeAppException;
 
}
