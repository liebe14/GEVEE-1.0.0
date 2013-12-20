package com.gss.gevee.be.admin.sisv.utilisateur;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.gss.gevee.be.admin.dao.utilisateur.IDaoUsr;
import com.gss.gevee.be.admin.entity.utilisateur.TabUsr;
import com.gss.gevee.be.core.base.BaseEntity;
import com.gss.gevee.be.core.base.BaseLogger;
import com.gss.gevee.be.core.dao.base.IBaseDao;
import com.gss.gevee.be.core.exception.GeveePersistenceException;
import com.gss.gevee.be.core.exception.GeveeSystemException;
import com.gss.gevee.be.core.sisv.base.BaseSisv;

@Stateless
public class SisvUsr extends BaseSisv<TabUsr, String> implements ISisvUsr {

	private static BaseLogger logger = BaseLogger.getLogger(SisvUsr.class);

	@Override
	public BaseLogger getLogger() {
		return logger;
	} 
	@EJB
	IDaoUsr daoUsr; 

	public TabUsr authenticate(String p$login, String p$pwd)
			throws GeveeSystemException {
		try {
			return daoUsr.authenticate(p$login, p$pwd);
			//Faire un controle si le mot de passe est expiré
		} catch (GeveePersistenceException e) {
			e.printStackTrace();
			GeveeSystemException sbr = new GeveeSystemException(e);
			throw sbr;
		}
	}

	public TabUsr modifierPwd(String p$login, String p$oldPwd, String p$newPwd)
			throws GeveeSystemException {
		try {
			if(p$newPwd == null || p$newPwd.isEmpty()){
				GeveeSystemException sdr = new GeveeSystemException("Nouveau mot de passe vide");
				logger.error(sdr.getMessage(), sdr);
				throw sdr;
			}
			if(p$newPwd.length()>30){
				GeveeSystemException sdr = new GeveeSystemException("Taille du nouveau mot de passe trés longue, moins de 30 caractéres");
				logger.error(sdr.getMessage(), sdr);
				throw sdr;
			}
			if(p$oldPwd.equals(p$newPwd)){
				GeveeSystemException sdr = new GeveeSystemException("Ancien mot de passe identique au nouveau");
				logger.error(sdr.getMessage(), sdr);
				throw sdr;
			}
			//Récupération des informations de l'utilisateur en base de données		
			 TabUsr oldUsr = daoUsr.findById(new TabUsr(), p$login);
			 if(null != oldUsr){
				 oldUsr.setCodPwd(p$newPwd);
				 return daoUsr.update(oldUsr);
			 }
		} catch (GeveePersistenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public IBaseDao<TabUsr, String> getBaseDao() {
		return daoUsr;
	}

	public <X extends BaseEntity> X rechercher(X entity, Serializable id) throws GeveeSystemException {
		try {
			return daoUsr.findById(entity, id);
		} catch (GeveePersistenceException e) {
			e.printStackTrace();
			GeveeSystemException sbr = new GeveeSystemException(e);
			throw sbr;
		}
	}

	public <X extends BaseEntity> List<X> rechercherTout(X entity) throws GeveeSystemException {
			
		try {
			return daoUsr.findAll(entity);
		} catch (GeveePersistenceException e) {
			e.printStackTrace();
			GeveeSystemException sbr = new GeveeSystemException(e);
			throw sbr;
		}
	}
	
	public <X extends BaseEntity> List<X> rechercherParCritere(X entity) throws GeveeSystemException {
		
		try {
			return daoUsr.findByExample(entity);
		} catch (GeveePersistenceException e) {
			e.printStackTrace();
			GeveeSystemException sbr = new GeveeSystemException(e);
			throw sbr;
		}
	}

}
