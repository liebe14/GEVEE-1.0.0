package com.gss.gevee.be.core.sisv.base;

import java.io.Serializable;
import java.util.List;

import com.gss.gevee.be.core.base.BaseEntity;
import com.gss.gevee.be.core.exception.GeveePersistenceException;
import com.gss.gevee.be.core.exception.GeveeSystemException;

public interface IBaseSisv<T extends BaseEntity, ID extends Serializable> {
	
	<X extends BaseEntity> X creer(X p$entite) throws GeveePersistenceException ;
	
	<X extends BaseEntity> X modifier(X p$entite) throws GeveePersistenceException;
	
	<X extends BaseEntity> boolean supprimer(X p$entite) throws GeveePersistenceException;
	
	<X extends BaseEntity> void retirer(X p$entite) throws GeveeSystemException, GeveePersistenceException;
	
	<X extends BaseEntity> X rechercher(X entity, Serializable p$valeur) throws GeveeSystemException;
	
	<X extends BaseEntity> List<X> rechercherTout(X entity) throws GeveeSystemException;

	<X extends BaseEntity> List<X> rechercherParCritere(X entity) throws GeveeSystemException;
}
