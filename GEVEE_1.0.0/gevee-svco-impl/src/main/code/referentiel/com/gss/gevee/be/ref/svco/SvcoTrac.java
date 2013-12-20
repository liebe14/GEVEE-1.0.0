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
import com.gss.gevee.be.ref.entity.TabTrac;
import com.gss.gevee.be.ref.sisv.ISisvTrac;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class SvcoTrac extends BaseSvco<TabTrac> implements IRemoteTrac, ILocalTrac{
	
	@EJB
	ISisvTrac sisvTrac;

	@Resource
	SessionContext session;

	private static BaseLogger logger = BaseLogger.getLogger(SvcoTrac.class);

	@Override
	protected IBaseSisv<TabTrac, String> getBaseSisv() {
		return sisvTrac;
	}

	@Override
	public BaseLogger getLogger() {
		return logger;
	}

	public <X extends BaseEntity> X rechercher(X entity, Serializable id)
	throws GeveeAppException {
		try {
			return sisvTrac.rechercher(entity,id);
		} catch (GeveeSystemException e) {
			e.printStackTrace();
			GeveeAppException sdr = new GeveeAppException(e);
			throw sdr;
		}
	}

	public <X extends BaseEntity> List<X> rechercherTout(X entity)
	throws GeveeAppException {
		try {
			return sisvTrac.rechercherTout(entity);
		} catch (GeveeSystemException e) {
			e.printStackTrace();
			GeveeAppException sdr = new GeveeAppException(e);
			throw sdr;
		}
	}
	
	public <X extends BaseEntity> List<X> rechercherParCritere(X entity)
	throws GeveeAppException {
		try {
			return sisvTrac.rechercherParCritere(entity);
		} catch (GeveeSystemException e) {
			e.printStackTrace();
			GeveeAppException sdr = new GeveeAppException(e);
			throw sdr;
		}
	}

}
