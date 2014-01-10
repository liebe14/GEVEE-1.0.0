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
import com.gss.gevee.be.mouv.dao.IDaoDep;
import com.gss.gevee.be.mouv.entity.TabCon;
import com.gss.gevee.be.mouv.entity.TabDep;

@Stateless
public class SisvDep extends BaseSisv<TabDep, String> implements ISisvDep{
	
	private static BaseLogger logger = BaseLogger.getLogger(SisvDep.class);

	@Override
	public BaseLogger getLogger() {
		return logger;
	} 
	@EJB
	IDaoDep daoDep;
	
	@Override
	public IBaseDao<TabDep, String> getBaseDao() {
		return daoDep;
	}

	@Override
	public <X extends BaseEntity> X rechercher(X entity, Serializable id)
			throws GeveeSystemException {
		try {
			return daoDep.findById(entity, id);
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
			return daoDep.findAll(entity);
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
			return daoDep.findByExample(entity);
		} catch (GeveePersistenceException e) {
			e.printStackTrace();
			GeveeSystemException sbr = new GeveeSystemException(e);
			throw sbr;
		}	
	}
	
	@Override
	public  TabDep demarrer(TabDep tabDep) throws GeveeSystemException {
		try {
			tabDep.setBooEstDem(BigDecimal.ONE);
			return super.modifier(tabDep);
		} catch (GeveePersistenceException e) {
			e.printStackTrace();
			GeveeSystemException sbr = new GeveeSystemException(e);
			throw sbr;
		}
	}
	
	@Override
	public  TabDep cloturer(TabDep tabDep) throws GeveeSystemException {
		try {
			return super.modifier(tabDep);
		} catch (GeveePersistenceException e) {
			e.printStackTrace();
			GeveeSystemException sbr = new GeveeSystemException(e);
			throw sbr;
		}
	}
	
	@Override
	public  TabDep receptionner(TabDep tabDep) throws GeveeSystemException {
		try {
			return super.modifier(tabDep);
		} catch (GeveePersistenceException e) {
			e.printStackTrace();
			GeveeSystemException sbr = new GeveeSystemException(e);
			throw sbr;
		}
	}
	
	@Override
	public  List<TabCon> rechercherConParEtatEnt(String etatEnt) throws GeveeSystemException {
		try {
			return daoDep.findConByEtatEnt(etatEnt); 
		} catch (GeveePersistenceException e) {
			e.printStackTrace();
			GeveeSystemException sbr = new GeveeSystemException(e);
			throw sbr;
		}
	}
	

}
