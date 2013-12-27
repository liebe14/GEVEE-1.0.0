package com.gss.gevee.be.core.svco.base;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.annotation.Resource;
import javax.ejb.SessionContext;

import com.gss.gevee.be.core.base.BaseEntity;
import com.gss.gevee.be.core.base.BaseLogger;
import com.gss.gevee.be.core.base.GeveeBaseEntity;
import com.gss.gevee.be.core.exception.GeveeAppException;
import com.gss.gevee.be.core.exception.GeveePersistenceException;
import com.gss.gevee.be.core.sisv.base.IBaseSisv;

public abstract class BaseSvco <T extends BaseEntity> implements IBaseSvco<T>{
	
	protected abstract IBaseSisv<T, ? extends Serializable> getBaseSisv();

	public abstract BaseLogger getLogger();

	@Resource
	private SessionContext sessionContext;
	
	public SessionContext getSessionContext(){
		return sessionContext;
	}
	protected void rollbackTransactionContext(){
		try {
			getSessionContext().setRollbackOnly();
		} catch (Exception e) {
			getLogger().warn("Erreur de récupération du Session Context : Vérifier sa redéfinition!", e);
			e.printStackTrace();
		}
	}
	
	public <X extends BaseEntity> X creer(X p$entite) throws GeveeAppException {
		try {
			//on raméne ces instructions au niveau du sisv
			
//			//fais un teste si l'entité existe déjà
//			X entRech = getBaseSisv().rechercher(p$entite, p$entite.getId());
//			if(entRech != null){
//				throw new GeveeAppException("Erreur : Cette entité existe déjà");
//			}
//			//Fixe l'état de l'entité à créer
//			((GeveeBaseEntity) p$entite).setEtatEnt(EnuEtat.CREE.getValue());
//			//On précise que l'entité est actif
//			((GeveeBaseEntity) p$entite).setBooAct(BigDecimal.ONE);
//			//On fixe l'année de vréation de l'entité
//			((GeveeBaseEntity) p$entite).setCodExeFis(DateTools.getYear(DateTools.formatDate(new Date())));
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
	
	public <X extends BaseEntity> X modifier(X p$entite) throws GeveeAppException {
		try {
			return getBaseSisv().modifier(p$entite);
		} catch (GeveePersistenceException sdr) {
			sdr.printStackTrace();
			rollbackTransactionContext();
			GeveeAppException sbr = new GeveeAppException(sdr);
			throw sbr;
		} catch (Exception e) {
			
			rollbackTransactionContext();
			String message = e.getMessage() + " !";
			GeveeAppException sysEx =  new GeveeAppException(message, e);
			getLogger().error(message, sysEx);
			throw sysEx;
			
		}
	}
	
	public <X extends BaseEntity> boolean supprimer(X p$entite) throws GeveeAppException {
		try {
			((GeveeBaseEntity) p$entite).setBooAct(BigDecimal.ZERO);
			 return getBaseSisv().supprimer(p$entite);
		} catch (GeveePersistenceException sdr) {
			sdr.printStackTrace();
			rollbackTransactionContext();
			GeveeAppException sbr = new GeveeAppException(sdr);
			throw sbr;
		} catch (Exception e) {
			
			rollbackTransactionContext();
			String message = e.getMessage() + " !";
			GeveeAppException sysEx =  new GeveeAppException(message, e);
			getLogger().error(message, sysEx);
			throw sysEx;
			
		}
	}
	
	public <X extends BaseEntity> void retirer(X p$entite) throws GeveeAppException {
		try {
			 getBaseSisv().retirer(p$entite);
		} catch (GeveePersistenceException sdr) {
			sdr.printStackTrace();
			rollbackTransactionContext();
			GeveeAppException sbr = new GeveeAppException(sdr);
			throw sbr;
		} catch (Exception e) {
			
			rollbackTransactionContext();
			String message = e.getMessage() + " !";
			GeveeAppException sysEx =  new GeveeAppException(message, e);
			getLogger().error(message, sysEx);
			throw sysEx;
			
		}
	}


}
