package com.gss.gevee.be.ref.svco;

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
import com.gss.gevee.be.ref.entity.TabRem;
import com.gss.gevee.be.ref.sisv.ISisvRem;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class SvcoRem extends BaseSvco<TabRem> implements IRemoteRem, ILocalRem{
	
	@EJB
	ISisvRem sisvRem;

	@Resource
	SessionContext session;

	private static BaseLogger logger = BaseLogger.getLogger(SvcoRem.class);

	@Override
	protected IBaseSisv<TabRem, String> getBaseSisv() {
		return sisvRem;
	}

	@Override
	public BaseLogger getLogger() {
		return logger;
	}

	public <X extends BaseEntity> X rechercher(X entity, Serializable id)
	throws GeveeAppException {
		try {
			return sisvRem.rechercher(entity,id);
		} catch (GeveeSystemException e) {
			e.printStackTrace();
			GeveeAppException sdr = new GeveeAppException(e);
			throw sdr;
		}
	}

	public <X extends BaseEntity> List<X> rechercherTout(X entity)
	throws GeveeAppException {
		try {
			return sisvRem.rechercherTout(entity);
		} catch (GeveeSystemException e) {
			e.printStackTrace();
			GeveeAppException sdr = new GeveeAppException(e);
			throw sdr;
		}
	}
	public <X extends BaseEntity> List<X> rechercherParCritere(X entity)
	throws GeveeAppException {
		try {
			return sisvRem.rechercherParCritere(entity);
		} catch (GeveeSystemException e) {
			e.printStackTrace();
			GeveeAppException sdr = new GeveeAppException(e);
			throw sdr;
		}
	}

}
