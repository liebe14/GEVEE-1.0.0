package com.gss.gevee.be.mouv.svco;

import com.gss.gevee.be.core.exception.GeveeAppException;
import com.gss.gevee.be.core.svco.base.IBaseSvco;
import com.gss.gevee.be.mouv.entity.TabChk;

public interface ISvcoChk extends IBaseSvco<TabChk>{

	TabChk valider(TabChk tabChk) throws GeveeAppException; 

}
