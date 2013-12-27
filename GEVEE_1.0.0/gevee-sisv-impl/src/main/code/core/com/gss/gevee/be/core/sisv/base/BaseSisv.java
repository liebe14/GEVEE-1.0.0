package com.gss.gevee.be.core.sisv.base;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.naming.Context;
import javax.naming.InitialContext;

import com.gss.gevee.be.core.base.BaseEntity;
import com.gss.gevee.be.core.base.BaseLogger;
import com.gss.gevee.be.core.base.DateTools;
import com.gss.gevee.be.core.base.GeveeBaseEntity;
import com.gss.gevee.be.core.dao.base.IBaseDao;
import com.gss.gevee.be.core.enums.EnuEtat;
import com.gss.gevee.be.core.exception.GeveePersistenceException;

public abstract class BaseSisv<T extends BaseEntity, ID extends Serializable>
implements IBaseSisv<T, ID>{
	
	public abstract IBaseDao<T, ID> getBaseDao();
	protected static Context ctx = null;
	
	static{
		try {
			ctx = new InitialContext();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public abstract BaseLogger getLogger();
	
	public <X extends BaseEntity> X creer(X p$entite) throws GeveePersistenceException  {
		
		//fais un teste si l'entit� existe d�j�
		X entRech = getBaseDao().findById(p$entite, p$entite.getId());
		if(entRech != null){
			throw new GeveePersistenceException("Erreur : Cette entit� existe d�j�");
		}
		//Fixe l'�tat de l'entit� � cr�er
		((GeveeBaseEntity) p$entite).setEtatEnt(EnuEtat.CREE.getValue());
		//On pr�cise que l'entit� est actif
		((GeveeBaseEntity) p$entite).setBooAct(BigDecimal.ONE);
		//On fixe l'ann�e de vr�ation de l'entit�
		((GeveeBaseEntity) p$entite).setCodExeFis(DateTools.getYear(DateTools.formatDate(new Date())));
		return getBaseDao().save(p$entite);
	}

	public <X extends BaseEntity> X modifier(X p$entite) throws GeveePersistenceException {
		return getBaseDao().update(p$entite);
	}
	
	public <X extends BaseEntity> boolean supprimer(X p$entite) throws GeveePersistenceException {
		 return getBaseDao().delete(p$entite);
	}
	
	public <X extends BaseEntity> void retirer(X p$entite) throws GeveePersistenceException {
		 getBaseDao().remove(p$entite);
	}

}
