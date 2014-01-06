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
import com.gss.gevee.be.mouv.dao.IDaoChk;
import com.gss.gevee.be.mouv.entity.TabChk;

@Stateless
public class SisvChk extends BaseSisv<TabChk, String> implements ISisvChk{
	
	private static BaseLogger logger = BaseLogger.getLogger(SisvChk.class);

	@Override
	public BaseLogger getLogger() {
		return logger;
	} 
	@EJB
	IDaoChk daoChk;
	
	@Override
	public IBaseDao<TabChk, String> getBaseDao() {
		return daoChk;
	}

	@Override
	public <X extends BaseEntity> X rechercher(X entity, Serializable id)
			throws GeveeSystemException {
		try {
			return daoChk.findById(entity, id);
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
			return daoChk.findAll(entity);
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
			return daoChk.findByExample(entity);
		} catch (GeveePersistenceException e) {
			e.printStackTrace();
			GeveeSystemException sbr = new GeveeSystemException(e);
			throw sbr;
		}	
	}
	
	@Override
	public  TabChk valider(TabChk tabChk) throws GeveeSystemException {
		try {
			tabChk.setBooEstVal(BigDecimal.ONE);
			return super.modifier(tabChk);
		} catch (GeveePersistenceException e) {
			e.printStackTrace();
			GeveeSystemException sbr = new GeveeSystemException(e);
			throw sbr;
		}
	}

}
