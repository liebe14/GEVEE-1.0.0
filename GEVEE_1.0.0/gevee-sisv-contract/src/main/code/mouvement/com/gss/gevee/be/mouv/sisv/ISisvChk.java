package com.gss.gevee.be.mouv.sisv;

import javax.ejb.Local;

import com.gss.gevee.be.core.exception.GeveeSystemException;
import com.gss.gevee.be.core.sisv.base.IBaseSisv;
import com.gss.gevee.be.mouv.entity.TabChk;

@Local
public interface ISisvChk extends IBaseSisv<TabChk, String>{

	TabChk valider(TabChk tabChk) throws GeveeSystemException;
 
}
