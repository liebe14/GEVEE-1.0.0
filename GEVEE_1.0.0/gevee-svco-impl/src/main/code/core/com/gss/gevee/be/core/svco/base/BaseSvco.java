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
			getLogger().warn("Erreur de r�cup�ration du Session Context : V�rifier sa red�finition!", e);
			e.printStackTrace();
		}
	}
	
	public <X extends BaseEntity> X creer(X p$entite) throws GeveeAppException {
		try {
			//on ram�ne ces instructions au niveau du sisv
			
//			//fais un teste si l'entit� existe d�j�
//			X entRech = getBaseSisv().rechercher(p$entite, p$entite.getId());
//			if(entRech != null){
//				throw new GeveeAppException("Erreur : Cette entit� existe d�j�");
//			}
//			//Fixe l'�tat de l'entit� � cr�er
//			((GeveeBaseEntity) p$entite).setEtatEnt(EnuEtat.CREE.getValue());
//			//On pr�cise que l'entit� est actif
//			((GeveeBaseEntity) p$entite).setBooAct(BigDecimal.ONE);
//			//On fixe l'ann�e de vr�ation de l'entit�
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
