package com.gss.gevee.be.core.base;

import java.io.Serializable;


public abstract class BaseEntity implements Serializable, Cloneable{
	
	private static final long serialVersionUID = 1L;
	
	protected boolean applyFilter = false;
	
	/**
	 * Constructeur par défaut
	 */
	public BaseEntity(){
		
	}
	
	/****
	 * Retourne le code réel de l'entité; Correspond au code entité dans le cas d'une entité simple
	 * <br> Dans le cas d'une entité composée, retourne le code de l'entité concrète représenté par cette instance
	 * @return
	 */
	public abstract String getEntityCode();

	/*****
	 * Retourne le code de l'entité
	 * <br> Si l'entité est une entité composée , retourne la liste des code des entités composantes 
	 * @return
	 */
	public String getRealEntityCode(){
		return getEntityCode();
	}
	
	public abstract Serializable getId();
	
	public abstract void setId(Serializable id);
	
	public abstract void validateData();
	
	public abstract void initData();

	/***
	 * Réalise une copie de l'entité en réinitialisant les champs qu'il faut
	 * @return
	 */
	public BaseEntity copie(){
		return null;		
	}
	
	/***
	 * Utilisé pour la pagination : position de début de sélection de données
	 */
	protected int offset;
	
	/***
	 * Utilisé pour la pagination : position de fin de sélection de données
	 */
	protected int maxRow;

	/**** 
	 * @return Retourne la position de début de sélection de données dans le mécanisme de pagination
	 */
	public int getOffset() {
		return offset;
	}
	
	/***
	 * Définit la position de début de sélection de données dans le mécanisme de pagination
	 * @param maxRow : Position de fin de sélection de données
	 */
	public void setOffset(int offset) {
		this.offset = offset;
	}
	
	/**** 
	 * @return Retourne la position de fin de sélection de données dans le mécanisme de pagination
	 */
	public int getMaxRow() {
		return maxRow;
	}

	/***
	 * Définit la position de fin de sélection de donnéesdans le mécanisme de pagination
	 * @param maxRow : Position de fin de sélection de données
	 */
	public void setMaxRow(int maxRow) {
		this.maxRow = maxRow;
	}

	public boolean getApplyFilter() {
		return applyFilter;
	}
	
	public void setApplyFilter(boolean applyFilter) {
		this.applyFilter = applyFilter;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		
		return super.clone();
	}
}
