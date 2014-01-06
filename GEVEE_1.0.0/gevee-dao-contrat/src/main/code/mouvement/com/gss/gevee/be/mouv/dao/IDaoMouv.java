package com.gss.gevee.be.mouv.dao;

import java.util.List;

import javax.ejb.Local;

import com.gss.gevee.be.core.dao.base.IBaseDao;
import com.gss.gevee.be.core.exception.GeveePersistenceException;
import com.gss.gevee.be.mouv.entity.TabMouv;

@Local
public interface IDaoMouv extends IBaseDao<TabMouv, String>{

	List<TabMouv> findByCodRefDep(String refDep) throws GeveePersistenceException; 

}
