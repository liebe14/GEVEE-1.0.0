package com.gss.gevee.be.core.sisv.base;

import java.io.Serializable;

import javax.naming.Context;
import javax.naming.InitialContext;

import com.gss.gevee.be.core.base.BaseEntity;
import com.gss.gevee.be.core.base.BaseLogger;
import com.gss.gevee.be.core.dao.base.IBaseDao;
import com.gss.gevee.be.core.exception.GeveePersistenceException;

public abstract class BaseSisv<T extends BaseEntity, ID extends Serializable>
implements IBaseSisv<T, ID>{
	
	public abstract IBaseDao<T, ID> getBaseDao();
	protected static Context ctx = null;
	
	static{
		try {
			ctx = new InitialContext();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public abstract BaseLogger getLogger();
	
	public <X extends BaseEntity> X creer(X p$entite) throws GeveePersistenceException  {
		return getBaseDao().save(p$entite);
	}

	public <X extends BaseEntity> X modifier(X p$entite) throws GeveePersistenceException {
		return getBaseDao().update(p$entite);
	}
	
	public <X extends BaseEntity> boolean supprimer(X p$entite) throws GeveePersistenceException {
		 return getBaseDao().delete(p$entite);
	}
	
	public <X extends BaseEntity> void retirer(X p$entite) throws GeveePersistenceException {
		 getBaseDao().remove(p$entite);
	}

}
