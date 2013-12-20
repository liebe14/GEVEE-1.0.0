package com.gss.gevee.be.admin.dao.utilisateur;

import javax.ejb.Local;

import com.gss.gevee.be.admin.entity.utilisateur.TabUsr;
import com.gss.gevee.be.core.dao.base.IBaseDao;
import com.gss.gevee.be.core.exception.GeveePersistenceException;

@Local
public interface IDaoUsr extends IBaseDao<TabUsr, String>{
	
	TabUsr authenticate(String p$login, String p$pwd) throws GeveePersistenceException;
	
}
