package com.gss.gevee.be.mouv.svco;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import com.gss.gevee.be.core.base.BaseEntity;
import com.gss.gevee.be.core.base.BaseLogger;
import com.gss.gevee.be.core.exception.GeveeAppException;
import com.gss.gevee.be.core.exception.GeveeSystemException;
import com.gss.gevee.be.core.sisv.base.IBaseSisv;
import com.gss.gevee.be.core.svco.base.BaseSvco;
import com.gss.gevee.be.mouv.entity.TabChk;
import com.gss.gevee.be.mouv.sisv.ISisvChk;

@Stateless
public class SvcoChk extends BaseSvco<TabChk> implements IRemoteChk, ILocalChk{
	
	@EJB
	ISisvChk sisvChk;

	@Resource
	SessionContext session;

	private static BaseLogger logger = BaseLogger.getLogger(SvcoChk.class);

	@Override
	protected IBaseSisv<TabChk, String> getBaseSisv() {
		return sisvChk;
	}

	@Override
	public BaseLogger getLogger() {
		return logger;
	}

	@Override
	public <X extends BaseEntity> X rechercher(X entity, Serializable id)
			throws GeveeAppException {
		try {
			return sisvChk.rechercher(entity,id);
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
			return sisvChk.rechercherTout(entity);
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
			return sisvChk.rechercherParCritere(entity);
		} catch (GeveeSystemException e) {
			e.printStackTrace();
			GeveeAppException sdr = new GeveeAppException(e);
			throw sdr;
		}
	}
	
	@Override
	public TabChk valider(TabChk tabChk)
	throws GeveeAppException {
		try {
			return sisvChk.valider(tabChk);
		} catch (GeveeSystemException e) {
			e.printStackTrace();
			GeveeAppException sdr = new GeveeAppException(e);
			throw sdr;
		}
	}


}
