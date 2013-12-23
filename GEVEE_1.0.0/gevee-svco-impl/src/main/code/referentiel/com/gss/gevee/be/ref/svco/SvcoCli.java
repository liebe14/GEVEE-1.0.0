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
import com.gss.gevee.be.ref.entity.TabCli;
import com.gss.gevee.be.ref.sisv.ISisvCli;

//@Stateless(name = "SvcoCli", mappedName = "SvcoCli")
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class SvcoCli extends BaseSvco<TabCli> implements IRemoteCli, ILocalCli{
	
	@EJB
	ISisvCli sisvCli;
	
	@Resource
	SessionContext session;

	private static BaseLogger logger = BaseLogger.getLogger(SvcoCli.class);
	
	@Override
	protected IBaseSisv<TabCli, String> getBaseSisv() {
		return sisvCli;
	}

	@Override
	public BaseLogger getLogger() {
		return logger;
	}
	


	public <X extends BaseEntity> X rechercher(X entity, Serializable id)
			throws GeveeAppException {
		try {
			return sisvCli.rechercher(entity,id);
		} catch (GeveeSystemException e) {
			e.printStackTrace();
			GeveeAppException sdr = new GeveeAppException(e);
			throw sdr;
		}
	}

	public <X extends BaseEntity> List<X> rechercherTout(X entity)
			throws GeveeAppException {
		try {
			return sisvCli.rechercherTout(entity);
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
