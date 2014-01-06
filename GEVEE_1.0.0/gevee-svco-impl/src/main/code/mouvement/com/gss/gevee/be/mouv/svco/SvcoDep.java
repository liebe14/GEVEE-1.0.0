package com.gss.gevee.be.mouv.svco;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import com.gss.gevee.be.core.base.BaseEntity;
import com.gss.gevee.be.core.base.BaseLogger;
import com.gss.gevee.be.core.exception.GeveeAppException;
import com.gss.gevee.be.core.exception.GeveeSystemException;
import com.gss.gevee.be.core.sisv.base.IBaseSisv;
import com.gss.gevee.be.core.svco.base.BaseSvco;
import com.gss.gevee.be.mouv.entity.TabDep;
import com.gss.gevee.be.mouv.sisv.ISisvDep;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class SvcoDep extends BaseSvco<TabDep> implements IRemoteDep, ILocalDep{
	
	@EJB
	ISisvDep sisvDep;
	
	@Resource
	SessionContext session;

	private static BaseLogger logger = BaseLogger.getLogger(SvcoDep.class);

	@Override
	protected IBaseSisv<TabDep, String> getBaseSisv() {
		return sisvDep;
	}
	
	@Override
	public BaseLogger getLogger() {
		return logger;
	}


	@Override
	public <X extends BaseEntity> X rechercher(X entity, Serializable id)
			throws GeveeAppException {
		try {
			return sisvDep.rechercher(entity,id);
		} catch (GeveeSystemException e) {
			e.printStackTrace();
			GeveeAppException sdr = new GeveeAppException(e);
			throw sdr;
		}
	}

	@Override
	public <X extends BaseEntity> List<X> rechercherTout(X entity)
			throws GeveeAppException {
		try {
			return sisvDep.rechercherTout(entity);
		} catch (GeveeSystemException e) {
			e.printStackTrace();
			GeveeAppException sdr = new GeveeAppException(e);
			throw sdr;
		}
	}

	@Override
	public <X extends BaseEntity> List<X> rechercherParCritere(X entity)
			throws GeveeAppException {
		try {
			return sisvDep.rechercherParCritere(entity);
		} catch (GeveeSystemException e) {
			e.printStackTrace();
			GeveeAppException sdr = new GeveeAppException(e);
			throw sdr;
		}
	}
	
	@Override
	public TabDep demarrer(TabDep tabDep) throws GeveeAppException {
		try {
			return sisvDep.demarrer(tabDep);
		} catch (GeveeSystemException e) {
			e.printStackTrace();
			GeveeAppException sdr = new GeveeAppException(e);
			throw sdr;
		}
	}
	
	@Override
	public TabDep cloturer(TabDep tabDep) throws GeveeAppException {
		try {
			return sisvDep.cloturer(tabDep);
		} catch (GeveeSystemException e) {
			e.printStackTrace();
			GeveeAppException sdr = new GeveeAppException(e);
			throw sdr;
		}
	}
	
	@Override
	public TabDep receptionner(TabDep tabDep) throws GeveeAppException {
		try {
			return sisvDep.receptionner(tabDep);
		} catch (GeveeSystemException e) {
			e.printStackTrace();
			GeveeAppException sdr = new GeveeAppException(e);
			throw sdr;
		}
	}

}
