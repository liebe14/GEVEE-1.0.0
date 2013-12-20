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
import com.gss.gevee.be.ref.dao.IDaoTrac;
import com.gss.gevee.be.ref.entity.TabTrac;

@Stateless
public class SisvTrac extends BaseSisv<TabTrac, String> implements ISisvTrac{
	
	private static BaseLogger logger = BaseLogger.getLogger(SisvTrac.class);

	@Override
	public BaseLogger getLogger() {
		return logger;
	} 
	@EJB
	IDaoTrac daoTrac; 

	public <X extends BaseEntity> X rechercher(X entity, Serializable id) throws GeveeSystemException {
		try {
			return daoTrac.findById(entity, id);
		} catch (GeveePersistenceException e) {
			e.printStackTrace();
			GeveeSystemException sbr = new GeveeSystemException(e);
			throw sbr;
		}
	}

	public <X extends BaseEntity> List<X> rechercherTout(X entity) throws GeveeSystemException {
			
		try {
			return daoTrac.findAll(entity);
		} catch (GeveePersistenceException e) {
			e.printStackTrace();
			GeveeSystemException sbr = new GeveeSystemException(e);
			throw sbr;
		}
	}
	
	public <X extends BaseEntity> List<X> rechercherParCritere(X entity) throws GeveeSystemException {
		
		try {
			return daoTrac.findByExample(entity);
		} catch (GeveePersistenceException e) {
			e.printStackTrace();
			GeveeSystemException sbr = new GeveeSystemException(e);
			throw sbr;
		}
	}

	@Override
	public IBaseDao<TabTrac, String> getBaseDao() {
		return daoTrac;
	}

}
