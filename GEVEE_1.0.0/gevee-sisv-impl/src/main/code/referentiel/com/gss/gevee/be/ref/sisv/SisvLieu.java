package com.gss.gevee.be.ref.sisv;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.gss.gevee.be.core.base.BaseEntity;
import com.gss.gevee.be.core.base.BaseLogger;
import com.gss.gevee.be.core.dao.base.IBaseDao;
import com.gss.gevee.be.core.exception.GeveePersistenceException;
import com.gss.gevee.be.core.exception.GeveeSystemException;
import com.gss.gevee.be.core.sisv.base.BaseSisv;
import com.gss.gevee.be.ref.dao.IDaoLieu;
import com.gss.gevee.be.ref.entity.TabLieu;

@Stateless
public class SisvLieu extends BaseSisv<TabLieu, String> implements ISisvLieu{

	private static BaseLogger logger = BaseLogger.getLogger(SisvLieu.class);

	@Override
	public BaseLogger getLogger() {
		return logger;
	} 
	@EJB
	IDaoLieu daoLieu; 



	@Override
	public IBaseDao<TabLieu, String> getBaseDao() {
		return daoLieu;
	}

	public <X extends BaseEntity> X rechercher(X entity, Serializable id) throws GeveeSystemException {
		try {
			return daoLieu.findById(entity, id);
		} catch (GeveePersistenceException e) {
			e.printStackTrace();
			GeveeSystemException sbr = new GeveeSystemException(e);
			throw sbr;
		}
	}

	public <X extends BaseEntity> List<X> rechercherTout(X entity) throws GeveeSystemException {
			
		try {
			return daoLieu.findAll(entity);
		} catch (GeveePersistenceException e) {
			e.printStackTrace();
			GeveeSystemException sbr = new GeveeSystemException(e);
			throw sbr;
		}
	}

	@Override
	public <X extends BaseEntity> List<X> rechercherParCritere(X entity)
			throws GeveeSystemException {
		try {
			return daoLieu.findByExample(entity);
		} catch (GeveePersistenceException e) {
			e.printStackTrace();
			GeveeSystemException sbr = new GeveeSystemException(e);
			throw sbr;
		}
	}

}
