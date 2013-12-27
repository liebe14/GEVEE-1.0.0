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
import com.gss.gevee.be.core.exception.GeveePersistenceException;
import com.gss.gevee.be.core.exception.GeveeSystemException;
import com.gss.gevee.be.core.sisv.base.IBaseSisv;
import com.gss.gevee.be.core.svco.base.BaseSvco;
import com.gss.gevee.be.mouv.entity.TabCon;
import com.gss.gevee.be.mouv.sisv.ISisvCon;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class SvcoCon extends BaseSvco<TabCon> implements IRemoteCon, ILocalCon{
	
	@EJB
	ISisvCon sisvCon;

	@Resource
	SessionContext session;

	private static BaseLogger logger = BaseLogger.getLogger(SvcoCon.class);

	@Override
	protected IBaseSisv<TabCon, String> getBaseSisv() {
		return sisvCon;
	}

	@Override
	public BaseLogger getLogger() {
		return logger;
	}
	
	public <X extends BaseEntity> X rechercher(X entity, Serializable id)
	throws GeveeAppException {
		try {
			return sisvCon.rechercher(entity,id);
		} catch (GeveeSystemException e) {
			e.printStackTrace();
			GeveeAppException sdr = new GeveeAppException(e);
			throw sdr;
		}
	}

	public <X extends BaseEntity> List<X> rechercherTout(X entity)
	throws GeveeAppException {
		try {
			return sisvCon.rechercherTout(entity);
		} catch (GeveeSystemException e) {
			e.printStackTrace();
			GeveeAppException sdr = new GeveeAppException(e);
			throw sdr;
		}
	}

	public <X extends BaseEntity> List<X> rechercherParCritere(X entity)
	throws GeveeAppException {
		try {
			return sisvCon.rechercherParCritere(entity);
		} catch (GeveeSystemException e) {
			e.printStackTrace();
			GeveeAppException sdr = new GeveeAppException(e);
			throw sdr;
		}
	}
	
	public <X extends BaseEntity> X creer(X p$entite) throws GeveeAppException {
		try {
			
			//Mise à jour du code du conteneur 
			TabCon con = ((TabCon)p$entite);
			((TabCon)p$entite).setCodCon(con.getTabOrdTran().getNumOrdTra()+"_"+con.getNumCon());
			return sisvCon.creer(p$entite);			
		} catch (GeveePersistenceException e) {
			rollbackTransactionContext();
			e.printStackTrace();
			GeveeAppException sbr = new GeveeAppException(e);
			throw sbr;
		} catch (Exception e) {
			rollbackTransactionContext();
			String message =  e.getMessage();
			GeveeAppException sysEx =  new GeveeAppException(message, e);
			getLogger().error(message, sysEx);
			throw sysEx;
		}
	}
	
	@Override
	public List<TabCon> rechercherParNumOrd(String numOrd)
	throws GeveeAppException {
		try {
			return sisvCon.rechercherParNumOrd(numOrd);
		} catch (GeveeSystemException e) {
			e.printStackTrace();
			GeveeAppException sdr = new GeveeAppException(e);
			throw sdr;
		}
	}

}
