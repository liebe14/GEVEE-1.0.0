package com.gss.gevee.be.mouv.svco;

import java.util.List;

import com.gss.gevee.be.core.exception.GeveeAppException;
import com.gss.gevee.be.core.svco.base.IBaseSvco;
import com.gss.gevee.be.mouv.entity.TabCon;

public interface ISvcoCon extends IBaseSvco<TabCon>{

	List<TabCon> rechercherParNumOrd(String numOrd) throws GeveeAppException;

}
