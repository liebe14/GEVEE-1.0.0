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
import com.gss.gevee.be.mouv.entity.TabOrd;
import com.gss.gevee.be.mouv.sisv.ISisvOrd;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class SvcoOrd extends BaseSvco<TabOrd> implements IRemoteOrd, ILocalOrd{

	@EJB
	ISisvOrd sisvOrd;

	@Resource
	SessionContext session;

	private static BaseLogger logger = BaseLogger.getLogger(SvcoOrd.class);

	@Override
	protected IBaseSisv<TabOrd, String> getBaseSisv() {
		return sisvOrd;
	}

	@Override
	public BaseLogger getLogger() {
		return logger;
	}
	
	public <X extends BaseEntity> X rechercher(X entity, Serializable id)
	throws GeveeAppException {
		try {
			return sisvOrd.rechercher(entity,id);
		} catch (GeveeSystemException e) {
			e.printStackTrace();
			GeveeAppException sdr = new GeveeAppException(e);
			throw sdr;
		}
	}

	public <X extends BaseEntity> List<X> rechercherTout(X entity)
	throws GeveeAppException {
		try {
			return sisvOrd.rechercherTout(entity);
		} catch (GeveeSystemException e) {
			e.printStackTrace();
			GeveeAppException sdr = new GeveeAppException(e);
			throw sdr;
		}
	}

	public <X extends BaseEntity> List<X> rechercherParCritere(X entity)
	throws GeveeAppException {
		try {
			return sisvOrd.rechercherParCritere(entity);
		} catch (GeveeSystemException e) {
			e.printStackTrace();
			GeveeAppException sdr = new GeveeAppException(e);
			throw sdr;
		}
	}

}
