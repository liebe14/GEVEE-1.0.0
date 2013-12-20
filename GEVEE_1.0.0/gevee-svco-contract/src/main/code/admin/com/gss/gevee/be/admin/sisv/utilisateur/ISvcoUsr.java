package com.gss.gevee.be.admin.sisv.utilisateur;

import com.gss.gevee.be.admin.entity.utilisateur.TabUsr;
import com.gss.gevee.be.core.exception.GeveeAppException;
import com.gss.gevee.be.core.svco.base.IBaseSvco;

public interface ISvcoUsr extends IBaseSvco<TabUsr>{

	TabUsr authenticate(String p$login, String p$pwd) throws GeveeAppException;
	
	TabUsr modifierPwd(String p$login, String p$oldPwd, String p$newPwd) throws GeveeAppException;

}
