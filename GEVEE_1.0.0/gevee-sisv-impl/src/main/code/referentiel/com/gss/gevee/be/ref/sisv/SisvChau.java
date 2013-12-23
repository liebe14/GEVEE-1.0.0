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
import com.gss.gevee.be.ref.dao.IDaoChau;
import com.gss.gevee.be.ref.entity.TabChau;

@Stateless
public class SisvChau extends BaseSisv<TabChau, String> implements ISisvChau{

	private static BaseLogger logger = BaseLogger.getLogger(SisvChau.class);

	@Override
	public BaseLogger getLogger() {
		return logger;
	} 
	@EJB
	IDaoChau daoChau; 



	@Override
	public IBaseDao<TabChau, String> getBaseDao() {
		return daoChau;
	}

	public <X extends BaseEntity> X rechercher(X entity, Serializable id) throws GeveeSystemException {
		try {
			return daoChau.findById(entity, id);
		} catch (GeveePersistenceException e) {
			e.printStackTrace();
			GeveeSystemException sbr = new GeveeSystemException(e);
			throw sbr;
		}
	}

	public <X extends BaseEntity> List<X> rechercherTout(X entity) throws GeveeSystemException {
			
		try {
			return daoChau.findAll(entity);
		} catch (GeveePersistenceException e) {
			e.printStackTrace();
			GeveeSystemException sbr = new GeveeSystemException(e);
			throw sbr;
		}
	}

	@Override
	public <X extends BaseEntity> List<X> rechercherParCritere(X entity)
			throws GeveeSystemException {
		// TODO Auto-generated method stub
		return null;
	}

}
