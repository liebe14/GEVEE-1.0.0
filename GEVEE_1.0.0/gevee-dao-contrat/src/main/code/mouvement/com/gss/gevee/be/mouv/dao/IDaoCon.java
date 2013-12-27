package com.gss.gevee.be.mouv.dao;

import java.util.List;

import javax.ejb.Local;

import com.gss.gevee.be.core.dao.base.IBaseDao;
import com.gss.gevee.be.core.exception.GeveePersistenceException;
import com.gss.gevee.be.mouv.entity.TabCon;

@Local
public interface IDaoCon extends IBaseDao<TabCon, String>{

	List<TabCon> findByNumOrd(String numOrd) throws GeveePersistenceException;

}
