package com.gss.gevee.be.mouv.sisv;

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
import com.gss.gevee.be.mouv.dao.IDaoOrd;
import com.gss.gevee.be.mouv.entity.TabOrd;

@Stateless
public class SisvOrd extends BaseSisv<TabOrd, String> implements ISisvOrd{

	
	private static BaseLogger logger = BaseLogger.getLogger(SisvOrd.class);

	@Override
	public BaseLogger getLogger() {
		return logger;
	} 
	@EJB
	IDaoOrd daoOrd;
	
	@Override
	public IBaseDao<TabOrd, String> getBaseDao() {
		return daoOrd;
	}

	public <X extends BaseEntity> X rechercher(X entity, Serializable id) throws GeveeSystemException {
		try {
			return daoOrd.findById(entity, id);
		} catch (GeveePersistenceException e) {
			e.printStackTrace();
			GeveeSystemException sbr = new GeveeSystemException(e);
			throw sbr;
		}
	}
	
	public <X extends BaseEntity> List<X> rechercherTout(X entity) throws GeveeSystemException {
		
		try {
			return daoOrd.findAll(entity);
		} catch (GeveePersistenceException e) {
			e.printStackTrace();
			GeveeSystemException sbr = new GeveeSystemException(e);
			throw sbr;
		}
	}

	public <X extends BaseEntity> List<X> rechercherParCritere(X entity) throws GeveeSystemException {
		
		try {
			return daoOrd.findByExample(entity);
		} catch (GeveePersistenceException e) {
			e.printStackTrace();
			GeveeSystemException sbr = new GeveeSystemException(e);
			throw sbr;
		}
	}
}
