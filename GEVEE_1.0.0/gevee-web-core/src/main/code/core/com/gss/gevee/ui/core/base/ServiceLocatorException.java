package com.gss.gevee.ui.core.base;

public class ServiceLocatorException extends Exception{
	
	private static final long serialVersionUID = 1L;
	

	public ServiceLocatorException(){}
    
    /**
	 * @param msg : message d'exception
	 */    
    public ServiceLocatorException(String msg){
        super(msg);
    }
     
    /**
	 * @param th  : cause
	 */
    public ServiceLocatorException(Throwable th){
        super(th);
    }    
    
	/**
	 * @param msg : message d'exception
	 * @param th  : cause
	 */
    public ServiceLocatorException(String msg, Throwable th){
        super(msg,th);
    }
}
