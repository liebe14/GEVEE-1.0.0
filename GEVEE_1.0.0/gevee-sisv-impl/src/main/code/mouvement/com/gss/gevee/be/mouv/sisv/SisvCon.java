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
import com.gss.gevee.be.mouv.dao.IDaoCon;
import com.gss.gevee.be.mouv.entity.TabCon;

@Stateless
public class SisvCon extends BaseSisv<TabCon, String> implements ISisvCon{
	
	private static BaseLogger logger = BaseLogger.getLogger(SisvCon.class);

	@Override
	public BaseLogger getLogger() {
		return logger;
	} 
	@EJB
	IDaoCon daoCon;
	
	@Override
	public IBaseDao<TabCon, String> getBaseDao() {
		return daoCon;
	}
	public <X extends BaseEntity> X rechercher(X entity, Serializable id) throws GeveeSystemException {
		try {
			return daoCon.findById(entity, id);
		} catch (GeveePersistenceException e) {
			e.printStackTrace();
			GeveeSystemException sbr = new GeveeSystemException(e);
			throw sbr;
		}
	}
	
	public <X extends BaseEntity> List<X> rechercherTout(X entity) throws GeveeSystemException {
		
		try {
			return daoCon.findAll(entity);
		} catch (GeveePersistenceException e) {
			e.printStackTrace();
			GeveeSystemException sbr = new GeveeSystemException(e);
			throw sbr;
		}
	}

	public <X extends BaseEntity> List<X> rechercherParCritere(X entity) throws GeveeSystemException {
		
		try {
			return daoCon.findByExample(entity);
		} catch (GeveePersistenceException e) {
			e.printStackTrace();
			GeveeSystemException sbr = new GeveeSystemException(e);
			throw sbr;
		}
	}
	
	@Override
	public  List<TabCon> rechercherParNumOrd(String numOrd) throws GeveeSystemException {
		try {
			return daoCon.findByNumOrd(numOrd);
		} catch (GeveePersistenceException e) {
			e.printStackTrace();
			GeveeSystemException sbr = new GeveeSystemException(e);
			throw sbr;
		}
	}

}
