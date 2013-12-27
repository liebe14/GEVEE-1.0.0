package com.gss.gevee.ui.mouv.util;

import com.gss.gevee.be.core.base.BaseLogger;
import com.gss.gevee.be.mouv.svco.IRemoteCon;
import com.gss.gevee.be.mouv.svco.IRemoteOrd;
import com.gss.gevee.ui.core.base.GeveeSvcoDeleguate;
import com.gss.gevee.ui.core.base.ServiceLocatorException;

public class MouvSvcoDelegaute {
	
	BaseLogger logger = BaseLogger.getLogger(MouvSvcoDelegaute.class);
	/**
	 * Retourne le nom du Bean du Service composé en fonction du nom de l'entité (@param p$p$beanName)
	 * 
	 * @param p$beanName
	 * @return
	 */
	private static String getSvcoBeanName(String p$beanName, Class<?> p$remoteInterface){		
		return GeveeSvcoDeleguate.getSvcoBeanName(p$beanName, p$remoteInterface);
	}
			
	/**
	 * Retourne le service composé pour la gestion des ordres de transport
	 * 
	 * @return
	 * @throws ServiceLocatorException
	 */
	public static IRemoteOrd getSvcoOrd() throws ServiceLocatorException{
        return (IRemoteOrd) GeveeSvcoDeleguate.getCachingServiceLocator().lookup(getSvcoBeanName("SvcoOrd", IRemoteOrd.class));
    }
	
	/**
	 * Retourne le service composé pour la gestion des contebeurs
	 * 
	 * @return
	 * @throws ServiceLocatorException
	 */
	public static IRemoteCon getSvcoCon() throws ServiceLocatorException{
        return (IRemoteCon) GeveeSvcoDeleguate.getCachingServiceLocator().lookup(getSvcoBeanName("SvcoCon", IRemoteCon.class));
    }
	
	public BaseLogger getLogger() {
		return logger;
	}



	public void setLogger(BaseLogger logger) {
		this.logger = logger;
	}	

}
