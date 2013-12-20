package com.gss.gevee.ui.admin.util;

/**
 * 
 */

import com.gss.gevee.be.admin.sisv.utilisateur.IRemoteUsr;
import com.gss.gevee.be.core.base.BaseLogger;
import com.gss.gevee.ui.core.base.GeveeSvcoDeleguate;
import com.gss.gevee.ui.core.base.ServiceLocatorException;

public class AdminSvcoDeleguate {

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
	 * Retourne le service composé pour la gestion des Utilisateurs
	 * 
	 * @return
	 * @throws ServiceLocatorException
	 */
	public static IRemoteUsr getSvcoUsr() throws ServiceLocatorException{
        return (IRemoteUsr) GeveeSvcoDeleguate.getCachingServiceLocator().lookup(getSvcoBeanName("SvcoUsr", IRemoteUsr.class));
    }



	public BaseLogger getLogger() {
		return logger;
	}



	public void setLogger(BaseLogger logger) {
		this.logger = logger;
	}	
	
}
