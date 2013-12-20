package com.gss.gevee.be.core.svco.base;

import java.io.Serializable;
import java.util.List;

import com.gss.gevee.be.core.base.BaseEntity;
import com.gss.gevee.be.core.exception.GeveeAppException;

public interface IBaseSvco<T extends BaseEntity> {
	
	<X extends BaseEntity> X creer(X p$entite) throws GeveeAppException ;
	
	<X extends BaseEntity> X modifier(X p$entite) throws GeveeAppException;
	
	<X extends BaseEntity> boolean supprimer(X p$entite) throws GeveeAppException;
	
	<X extends BaseEntity> void retirer(X p$entite) throws GeveeAppException;
	
	<X extends BaseEntity> X rechercher(X entity, Serializable p$valeur) throws GeveeAppException;
	
	<X extends BaseEntity> List<X> rechercherTout(X entity) throws GeveeAppException;

	<X extends BaseEntity> List<X> rechercherParCritere(X entity) throws GeveeAppException;

}
