package com.gss.gevee.be.mouv.svco;

import com.gss.gevee.be.core.exception.GeveeAppException;
import com.gss.gevee.be.core.svco.base.IBaseSvco;
import com.gss.gevee.be.mouv.entity.TabOrd;
import com.gss.gevee.be.util.EntFichier;

public interface ISvcoOrd extends IBaseSvco<TabOrd>{

	EntFichier genererEtatOrdTrans(TabOrd ord) throws GeveeAppException;

	TabOrd cloturer(TabOrd tabOrd) throws GeveeAppException;

}
