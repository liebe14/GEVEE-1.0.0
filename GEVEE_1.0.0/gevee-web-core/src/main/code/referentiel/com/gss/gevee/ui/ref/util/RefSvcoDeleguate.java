package com.gss.gevee.ui.ref.util;

import com.gss.gevee.be.core.base.BaseLogger;
import com.gss.gevee.be.ref.svco.IRemoteChau;
import com.gss.gevee.be.ref.svco.IRemoteCli;
import com.gss.gevee.be.ref.svco.IRemoteLieu;
import com.gss.gevee.be.ref.svco.IRemoteRem;
import com.gss.gevee.be.ref.svco.IRemoteTrac;
import com.gss.gevee.ui.core.base.GeveeSvcoDeleguate;
import com.gss.gevee.ui.core.base.ServiceLocatorException;

public class RefSvcoDeleguate {
	
	BaseLogger logger = BaseLogger.getLogger(RefSvcoDeleguate.class);
	/**
	 * Retourne le nom du Bean du Service compos� en fonction du nom de l'entit� (@param p$p$beanName)
	 * 
	 * @param p$beanName
	 * @return
	 */
	private static String getSvcoBeanName(String p$beanName, Class<?> p$remoteInterface){		
		
		return GeveeSvcoDeleguate.getSvcoBeanName(p$beanName, p$remoteInterface);
	}
				
	
			
	/*================================== Services compos�s de la Partie Technique =================================*/
	
	/**
	 * Retourne le service compos� pour la gestion des remorques
	 * 
	 * @return
	 * @throws ServiceLocatorException
	 */
	public static IRemoteRem getSvcoRem() throws ServiceLocatorException{
        return (IRemoteRem) GeveeSvcoDeleguate.getCachingServiceLocator().lookup(getSvcoBeanName("SvcoRem", IRemoteRem.class));
    }
	
	/**
	 * Retourne le service compos� pour la gestion des tracteurs
	 * 
	 * @return
	 * @throws ServiceLocatorException
	 */
	public static IRemoteTrac getSvcoTrac() throws ServiceLocatorException{
        return (IRemoteTrac) GeveeSvcoDeleguate.getCachingServiceLocator().lookup(getSvcoBeanName("SvcoTrac", IRemoteTrac.class));
    }
	
	/**
	 * Retourne le service compos� pour la gestion des Chauffeurs
	 * 
	 * @return
	 * @throws ServiceLocatorException
	 */
	public static IRemoteChau getSvcoChau() throws ServiceLocatorException{
        return (IRemoteChau) GeveeSvcoDeleguate.getCachingServiceLocator().lookup(getSvcoBeanName("SvcoChau", IRemoteChau.class));
    }	
	
	/**
	 * Retourne le service compos� pour la gestion des Clients
	 * 
	 * @return
	 * @throws ServiceLocatorException
	 */
	public static IRemoteCli getSvcoCli() throws ServiceLocatorException{
        return (IRemoteCli) GeveeSvcoDeleguate.getCachingServiceLocator().lookup(getSvcoBeanName("SvcoCli", IRemoteCli.class));
    }	

	/**
	 * Retourne le service compos� pour la gestion des Lieux
	 * 
	 * @return
	 * @throws ServiceLocatorException
	 */
	public static IRemoteLieu getSvcoLieu() throws ServiceLocatorException{
        return (IRemoteLieu) GeveeSvcoDeleguate.getCachingServiceLocator().lookup(getSvcoBeanName("SvcoLieu", IRemoteLieu.class));
    }	



	public BaseLogger getLogger() {
		return logger;
	}



	public void setLogger(BaseLogger logger) {
		this.logger = logger;
	}	

}
