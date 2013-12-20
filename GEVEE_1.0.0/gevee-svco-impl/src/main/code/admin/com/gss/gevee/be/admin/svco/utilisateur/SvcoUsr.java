package com.gss.gevee.be.admin.svco.utilisateur;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import com.gss.gevee.be.admin.entity.utilisateur.TabUsr;
import com.gss.gevee.be.admin.sisv.utilisateur.ILocalUsr;
import com.gss.gevee.be.admin.sisv.utilisateur.IRemoteUsr;
import com.gss.gevee.be.admin.sisv.utilisateur.ISisvUsr;
import com.gss.gevee.be.core.base.BaseEntity;
import com.gss.gevee.be.core.base.BaseLogger;
import com.gss.gevee.be.core.exception.GeveeAppException;
import com.gss.gevee.be.core.exception.GeveeSystemException;
import com.gss.gevee.be.core.sisv.base.IBaseSisv;
import com.gss.gevee.be.core.svco.base.BaseSvco;

//@Stateless(name = "SvcoUsr", mappedName = "SvcoUsr")
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class SvcoUsr extends BaseSvco<TabUsr> implements IRemoteUsr, ILocalUsr{
	
	@EJB
	ISisvUsr sisvUsr;
	
	@Resource
	SessionContext session;

	private static BaseLogger logger = BaseLogger.getLogger(SvcoUsr.class);
	
	@Override
	protected IBaseSisv<TabUsr, String> getBaseSisv() {
		return sisvUsr;
	}

	@Override
	public BaseLogger getLogger() {
		return logger;
	}
	
	public TabUsr authenticate(String p$login, String p$pwd) throws GeveeAppException {
		try {
			return sisvUsr.authenticate(p$login, p$pwd);
		} catch (GeveeSystemException e) {
			e.printStackTrace();
			GeveeAppException sdr = new GeveeAppException(e);
			throw sdr;
		}
	}
	
	public TabUsr modifierPwd(String p$login, String p$oldPwd, String p$newPwd) throws GeveeAppException {
		try {
			return sisvUsr.modifierPwd(p$login, p$oldPwd, p$newPwd);
		} catch (GeveeSystemException e) {
			e.printStackTrace();
			GeveeAppException sdr = new GeveeAppException(e);
			throw sdr;
		}
	}

	public <X extends BaseEntity> X rechercher(X entity, Serializable id)
			throws GeveeAppException {
		try {
			return sisvUsr.rechercher(entity,id);
		} catch (GeveeSystemException e) {
			e.printStackTrace();
			GeveeAppException sdr = new GeveeAppException(e);
			throw sdr;
		}
	}

	public <X extends BaseEntity> List<X> rechercherTout(X entity)
			throws GeveeAppException {
		try {
			return sisvUsr.rechercherTout(entity);
		} catch (GeveeSystemException e) {
			e.printStackTrace();
			GeveeAppException sdr = new GeveeAppException(e);
			throw sdr;
		}
	}
	
	public <X extends BaseEntity> List<X> rechercherParCritere(X entity)
	throws GeveeAppException {
		try {
			return sisvUsr.rechercherParCritere(entity);
		} catch (GeveeSystemException e) {
			e.printStackTrace();
			GeveeAppException sdr = new GeveeAppException(e);
			throw sdr;
		}
	}

}
