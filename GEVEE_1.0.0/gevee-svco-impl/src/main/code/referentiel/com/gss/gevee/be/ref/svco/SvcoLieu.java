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
import com.gss.gevee.be.ref.entity.TabLieu;
import com.gss.gevee.be.ref.sisv.ISisvLieu;

//@Stateless(name = "SvcoChau", mappedName = "SvcoChau")
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class SvcoLieu extends BaseSvco<TabLieu> implements IRemoteLieu, ILocalLieu{
	
	@EJB
	ISisvLieu sisvLieu;
	
	@Resource
	SessionContext session;

	private static BaseLogger logger = BaseLogger.getLogger(SvcoLieu.class);
	
	@Override
	protected IBaseSisv<TabLieu, String> getBaseSisv() {
		return sisvLieu;
	}

	@Override
	public BaseLogger getLogger() {
		return logger;
	}
	


	public <X extends BaseEntity> X rechercher(X entity, Serializable id)
			throws GeveeAppException {
		try {
			return sisvLieu.rechercher(entity,id);
		} catch (GeveeSystemException e) {
			e.printStackTrace();
			GeveeAppException sdr = new GeveeAppException(e);
			throw sdr;
		}
	}

	public <X extends BaseEntity> List<X> rechercherTout(X entity)
			throws GeveeAppException {
		try {
			return sisvLieu.rechercherTout(entity);
		} catch (GeveeSystemException e) {
			e.printStackTrace();
			GeveeAppException sdr = new GeveeAppException(e);
			throw sdr;
		}
	}

	@Override
	public <X extends BaseEntity> List<X> rechercherParCritere(X entity)
			throws GeveeAppException {
		// TODO Auto-generated method stub
		return null;
	}

}
