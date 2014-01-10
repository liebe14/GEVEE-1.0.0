package com.gss.gevee.be.mouv.dao;

import java.util.List;

import javax.ejb.Local;

import com.gss.gevee.be.core.dao.base.IBaseDao;
import com.gss.gevee.be.core.exception.GeveePersistenceException;
import com.gss.gevee.be.mouv.entity.TabCon;
import com.gss.gevee.be.mouv.entity.TabDep;

@Local
public interface IDaoDep extends IBaseDao<TabDep, String>{

	List<TabCon> findConByEtatEnt(String etatEnt) throws GeveePersistenceException;

}
