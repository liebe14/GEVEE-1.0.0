package com.gss.gevee.ui.core.base;

public class SvcoDeleguate {
	
	/**
	 * Retourne le nom du Bean du Service composé en fonction du nom de l'entité (@param p$p$beanName)
	 * 
	 * @param p$beanName
	 * @return
	 */
	public static String getSvcoBeanName(String p$beanName, Class<?> p$remoteInterface){		
		//return "ccolloc-z-ear/" + p$beanName + "/remote";
//		String v$jndiName = JndiMapper.getRemoteJndiBeanName(p$beanName, p$remoteInterface);
		String v$jndiName = "gevee-z-ear/" + p$beanName + "/remote";
		
		return v$jndiName;
	}	

}
