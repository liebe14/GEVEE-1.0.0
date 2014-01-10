package com.gss.gevee.be.mouv.svco;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import com.gss.gevee.be.core.base.BaseEntity;
import com.gss.gevee.be.core.base.BaseLogger;
import com.gss.gevee.be.core.enums.EnuEtat;
import com.gss.gevee.be.core.exception.GeveeAppException;
import com.gss.gevee.be.core.exception.GeveePersistenceException;
import com.gss.gevee.be.core.exception.GeveeSystemException;
import com.gss.gevee.be.core.sisv.base.IBaseSisv;
import com.gss.gevee.be.core.svco.base.BaseSvco;
import com.gss.gevee.be.mouv.entity.TabDep;
import com.gss.gevee.be.mouv.entity.TabMouv;
import com.gss.gevee.be.mouv.sisv.ISisvMouv;

@Stateless
public class SvcoMouv extends BaseSvco<TabMouv> implements IRemoteMouv, ILocalMouv{
	
	@EJB
	ISisvMouv sisvMouv;

	@Resource
	SessionContext session;

	private static BaseLogger logger = BaseLogger.getLogger(SvcoMouv.class);

	@Override
	protected IBaseSisv<TabMouv, String> getBaseSisv() {
		return sisvMouv;
	}

	@Override
	public BaseLogger getLogger() {
		return logger;
	}

	@Override
	public <X extends BaseEntity> X rechercher(X entity, Serializable id)
			throws GeveeAppException {
		try {
			return sisvMouv.rechercher(entity,id);
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
			return sisvMouv.rechercherTout(entity);
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
			return sisvMouv.rechercherParCritere(entity);
		} catch (GeveeSystemException e) {
			e.printStackTrace();
			GeveeAppException sdr = new GeveeAppException(e);
			throw sdr;
		}
	}
	
	@Override
	public List<TabMouv> rechercherParCodRefDep(String refDep)
	throws GeveeAppException {
		try {
			return sisvMouv.rechercherParCodRefDep(refDep);
		} catch (GeveeSystemException e) {
			e.printStackTrace();
			GeveeAppException sdr = new GeveeAppException(e);
			throw sdr;
		}
	}
	
	@Override
	public TabMouv valider(TabMouv tabMouv)
	throws GeveeAppException {
		try {
			//Mise à jour de l'entité
			tabMouv.setBooEstVal(BigDecimal.ONE);
			tabMouv.setEtatEnt(EnuEtat.VALIDE.getValue());
			return sisvMouv.valider(tabMouv);
		} catch (GeveeSystemException e) {
			e.printStackTrace();
			GeveeAppException sdr = new GeveeAppException(e);
			throw sdr;
		}
	}
	
	public <X extends BaseEntity> X creer(X p$entite) throws GeveeAppException {
		try {
			//On recherche tout les mouvements du déplacement afin de set la position du mouvement
			TabDep dep = ((TabMouv)p$entite).getTabDep();
			List<TabMouv> allMouv = sisvMouv.rechercherParCodRefDep(dep.getCodRefDep());
			((TabMouv)p$entite).setPosMouv(allMouv.size()+1);
			return getBaseSisv().creer(p$entite);			
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

}
