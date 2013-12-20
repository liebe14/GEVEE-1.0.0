package com.gss.gevee.be.admin.sisv.utilisateur;

import javax.ejb.Local;

import com.gss.gevee.be.admin.entity.utilisateur.TabUsr;
import com.gss.gevee.be.core.exception.GeveeSystemException;
import com.gss.gevee.be.core.sisv.base.IBaseSisv;

@Local
public interface ISisvUsr extends IBaseSisv<TabUsr, String>{
	
	TabUsr authenticate(String p$login, String p$pwd) throws GeveeSystemException;
	
	TabUsr modifierPwd(String p$login, String p$oldPwd, String p$newPwd) throws GeveeSystemException;

}
