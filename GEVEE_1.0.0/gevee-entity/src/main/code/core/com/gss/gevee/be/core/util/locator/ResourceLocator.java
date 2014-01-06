package com.gss.gevee.be.core.util.locator;

import java.util.ResourceBundle;

import com.gss.gevee.be.util.ReportNames;

public class ResourceLocator {
	
	/***
	 * configuration du répertoire de stockage du fichier resource "Config"
	 */
	private static String BUNDLE_PATH_NAME = "com.gss.gevee.Config";
	
	/****
	 * Proriété de configuration du chemin de stockage des fichiers détat compilé Jasper
	 */
	public static String REPORT_PATH_NAME = "report.path.model";
	
	
	public static String IP_ADD = "java.naming.provider.url";
	public static String SVR_NAME = "java.naming.factory.url.pkgs";
	public static String SVR_CONTEXT = "java.naming.factory.initial";
	
	/**
	 * Nom de l'EAR déployé
	 */
	public static String EAR_NAME = "ear.name";
	
	private static ResourceBundle rb;
	static{
		rb = ResourceBundle
		.getBundle(BUNDLE_PATH_NAME);
	}
	
	public static String getReportModel(ReportNames reportName){
		try{
			return rb.getString(REPORT_PATH_NAME) + reportName.getReportName() + ".jasper";	
		}
		catch(Exception e){
			return null;
		}
	}
	
	public static String getReportModel(String reportName){
		try{
		return rb.getString(REPORT_PATH_NAME) + reportName + ".jasper";
		}
		catch(Exception e){
			return null;
		}
	}
	

}
