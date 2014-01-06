package com.gss.gevee.be.mouv.sisv;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.gss.gevee.be.core.base.BaseEntity;
import com.gss.gevee.be.core.base.BaseLogger;
import com.gss.gevee.be.core.dao.base.IBaseDao;
import com.gss.gevee.be.core.exception.GeveePersistenceException;
import com.gss.gevee.be.core.exception.GeveeSystemException;
import com.gss.gevee.be.core.sisv.base.BaseSisv;
import com.gss.gevee.be.mouv.dao.IDaoMouv;
import com.gss.gevee.be.mouv.entity.TabMouv;

@Stateless
public class SisvMouv extends BaseSisv<TabMouv, String> implements ISisvMouv{
	
	private static BaseLogger logger = BaseLogger.getLogger(SisvMouv.class);

	@Override
	public BaseLogger getLogger() {
		return logger;
	} 
	@EJB
	IDaoMouv daoMouv;
	
	@Override
	public IBaseDao<TabMouv, String> getBaseDao() {
		return daoMouv;
	}

	@Override
	public <X extends BaseEntity> X rechercher(X entity, Serializable id)
			throws GeveeSystemException {
		try {
			return daoMouv.findById(entity, id);
		} catch (GeveePersistenceException e) {
			e.printStackTrace();
			GeveeSystemException sbr = new GeveeSystemException(e);
			throw sbr;
		}
	}

	@Override
	public <X extends BaseEntity> List<X> rechercherTout(X entity)
			throws GeveeSystemException {
		try {
			return daoMouv.findAll(entity);
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
			return daoMouv.findByExample(entity);
		} catch (GeveePersistenceException e) {
			e.printStackTrace();
			GeveeSystemException sbr = new GeveeSystemException(e);
			throw sbr;
		}	
	}
	
	@Override
	public  List<TabMouv> rechercherParCodRefDep(String refDep) throws GeveeSystemException {
		try {
			return daoMouv.findByCodRefDep(refDep); 
		} catch (GeveePersistenceException e) {
			e.printStackTrace();
			GeveeSystemException sbr = new GeveeSystemException(e);
			throw sbr;
		}
	}
	
	@Override
	public  TabMouv valider(TabMouv tabMouv) throws GeveeSystemException {
		try {
			tabMouv.setBooEstVal(BigDecimal.ONE);
			return super.modifier(tabMouv);
		} catch (GeveePersistenceException e) {
			e.printStackTrace();
			GeveeSystemException sbr = new GeveeSystemException(e);
			throw sbr;
		}
	}
}
