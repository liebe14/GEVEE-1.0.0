package com.gss.gevee.be.core.dao.base;

import java.io.Serializable;
import java.util.List;

import com.gss.gevee.be.core.base.BaseEntity;
import com.gss.gevee.be.core.exception.GeveePersistenceException;

public interface IBaseDao <T extends BaseEntity, ID extends Serializable>{

	public <X extends BaseEntity> X save(X entity) throws GeveePersistenceException;
	
	public <X extends BaseEntity> X update(X entity) throws GeveePersistenceException;
	
	public <X extends BaseEntity> boolean delete(X entity) throws GeveePersistenceException;
	
	<X extends BaseEntity> void remove(X entity) throws GeveePersistenceException;
	
	<X extends BaseEntity> X findById(X entity, Serializable id) throws GeveePersistenceException;

	<X extends BaseEntity> List<X> findAll(X entity) throws GeveePersistenceException;

	<X extends BaseEntity> List<X> findByExample(X entity) throws GeveePersistenceException;
	
}
