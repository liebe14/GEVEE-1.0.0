package com.gss.gevee.ui.ref.util;

import com.gss.gevee.be.core.base.BaseLogger;
import com.gss.gevee.be.ref.svco.IRemoteRem;
import com.gss.gevee.be.ref.svco.IRemoteTrac;
import com.gss.gevee.ui.admin.util.AdminSvcoDeleguate;
import com.gss.gevee.ui.core.base.GeveeSvcoDeleguate;
import com.gss.gevee.ui.core.base.ServiceLocatorException;

public class RefSvcoDeleguate {
	
	BaseLogger logger = BaseLogger.getLogger(AdminSvcoDeleguate.class);
	/**
	 * Retourne le nom du Bean du Service composé en fonction du nom de l'entité (@param p$p$beanName)
	 * 
	 * @param p$beanName
	 * @return
	 */
	private static String getSvcoBeanName(String p$beanName, Class<?> p$remoteInterface){		
		
		return GeveeSvcoDeleguate.getSvcoBeanName(p$beanName, p$remoteInterface);
	}
				
	
			
	/*================================== Services composés de la Partie Technique =================================*/
	
	/**
	 * Retourne le service composé pour la gestion des remorques
	 * 
	 * @return
	 * @throws ServiceLocatorException
	 */
	public static IRemoteRem getSvcoRem() throws ServiceLocatorException{
        return (IRemoteRem) GeveeSvcoDeleguate.getCachingServiceLocator().lookup(getSvcoBeanName("SvcoRem", IRemoteRem.class));
    }
	
	/**
	 * Retourne le service composé pour la gestion des tracteurs
	 * 
	 * @return
	 * @throws ServiceLocatorException
	 */
	public static IRemoteTrac getSvcoTrac() throws ServiceLocatorException{
        return (IRemoteTrac) GeveeSvcoDeleguate.getCachingServiceLocator().lookup(getSvcoBeanName("SvcoTrac", IRemoteTrac.class));
    }



	public BaseLogger getLogger() {
		return logger;
	}



	public void setLogger(BaseLogger logger) {
		this.logger = logger;
	}	

}
