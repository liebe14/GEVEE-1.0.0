package com.gss.gevee.ui.core.base;


/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 * Exception de Base  niveau FE.
 */
public class FrontEndException extends Exception{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	public FrontEndException(){}
    
    /**
	 * @param msg : message d'exception
	 */    
    public FrontEndException(String msg){
        super(msg);
    }
     
    /**
	 * @param th  : cause
	 */
    public FrontEndException(Throwable th){
        super(th);
    }    
    
	/**
	 * @param msg : message d'exception
	 * @param th  : cause
	 */
    public FrontEndException(String msg, Throwable th){
        super(msg,th);
    }

}
