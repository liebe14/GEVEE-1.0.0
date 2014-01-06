package com.gss.gevee.be.core.sisv.base;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.query.JRXPathQueryExecuterFactory;
import net.sf.jasperreports.engine.util.JRXmlUtils;

import org.w3c.dom.Document;
import org.xml.sax.SAXParseException;

import com.gss.gevee.be.core.base.BaseEntity;
import com.gss.gevee.be.core.base.BaseLogger;
import com.gss.gevee.be.core.base.DateTools;
import com.gss.gevee.be.core.base.GeveeBaseEntity;
import com.gss.gevee.be.core.dao.base.IBaseDao;
import com.gss.gevee.be.core.enums.EnuEtat;
import com.gss.gevee.be.core.exception.GeveePersistenceException;
import com.gss.gevee.be.core.exception.GeveeSystemException;
import com.gss.gevee.be.util.GeveeOutput;
import com.gss.gevee.be.util.GeveePrinterExportEnum;
import com.gss.gevee.be.util.XmlFactory;

public abstract class BaseSisv<T extends BaseEntity, ID extends Serializable>
implements IBaseSisv<T, ID>{
	
	public static final String XML_BAD_CHAR_PATTERN = "&#[xX]([0-9a-fA-F]+);";
	public static final String XML_ENCODING = "ISO-8859-1"; //"UTF-8"; 
	public static final String XML_REPLACE = " ";
	public abstract IBaseDao<T, ID> getBaseDao();
	protected static Context ctx = null;
	
	static{
		try {
			ctx = new InitialContext();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public abstract BaseLogger getLogger();
	
	public <X extends BaseEntity> X creer(X p$entite) throws GeveePersistenceException  {
		
		//fais un teste si l'entit� existe d�j�
		X entRech = getBaseDao().findById(p$entite, p$entite.getId());
		if(entRech != null){
			throw new GeveePersistenceException("Erreur : Cette entit� existe d�j�");
		}
		//Fixe l'�tat de l'entit� � cr�er
		((GeveeBaseEntity) p$entite).setEtatEnt(EnuEtat.CREE.getValue());
		//On pr�cise que l'entit� est actif
		((GeveeBaseEntity) p$entite).setBooAct(BigDecimal.ONE);
		//On fixe l'ann�e de vr�ation de l'entit�
		((GeveeBaseEntity) p$entite).setCodExeFis(DateTools.getYear(DateTools.formatDate(new Date())));
		return getBaseDao().save(p$entite);
	}

	public <X extends BaseEntity> X modifier(X p$entite) throws GeveePersistenceException {
		return getBaseDao().update(p$entite);
	}
	
	public <X extends BaseEntity> boolean supprimer(X p$entite) throws GeveePersistenceException {
		 return getBaseDao().delete(p$entite);
	}
	
	public <X extends BaseEntity> void retirer(X p$entite) throws GeveePersistenceException {
		 getBaseDao().remove(p$entite);
	}
	
	/**
	 * - Verifie que l'utilisateur a le droit de lancer une telle impression
	 * pour ce type de document - Construit le fichier d'etat (merge : xmlData +
	 * model) - puis retourne une version exporter du document en fonction du
	 * format
	 */

	public GeveeOutput fillAndExport(Object objectData, String model,	GeveePrinterExportEnum format, String typDoc, String codeUser ,String licence)
			throws GeveeSystemException {

		try {

			getLogger().debug("[fillAndExport] generation de la source XML");
			// generer la source xml
			String xmlString = XmlFactory.getXmlStream(objectData);
			
			//System.out.println("[SOS LOG] Contenu du fichier Xml = " + xmlString);
			String versionXml = "<?xml version=\"1.0\" encoding=\"" + XmlFactory.XML_ENCODING + "\"?>";
			
			// produire le fichier Xml			
			File xmlFile = File.createTempFile("temp", ".xml");
			//File xmlFile = new File("temp", ".xml");
			System.out.println(xmlFile.getAbsolutePath());
			
			// Enr�gistrement du r�sultat dans un fichier
			FileWriter fw = new FileWriter(xmlFile);
			
			getLogger().debug("[fillAndExport] //Ecriture de l'ent�te dans le fichier " );
			fw.write(versionXml);		
			System.out.println("Encodage du fichier  : " + versionXml);
			getLogger().debug("[fillAndExport] Ecriture du contenu XML");
			//Ecriture du contenu XML
			fw.write(xmlString);
			fw.close();
			return this.fillAndExport(xmlFile, model, format, typDoc, codeUser,licence);
		} catch (GeveeSystemException sos_e) {
			throw sos_e;
		} catch (Exception  e) {
			e.printStackTrace();
			throw new GeveeSystemException("fillAndExport", e);
		}

	}
	
	public GeveeOutput fillAndExport(File xmlFile, String model, GeveePrinterExportEnum format, String typDoc, String codeUser,String licence)
	throws GeveeSystemException {

		GeveeOutput output = null;
		try {

			// Cr�ation de l'InputStream
			FileInputStream xmlData = new FileInputStream(xmlFile);

			// Construiure le fichier d'�tat
			if (format == GeveePrinterExportEnum.PDF) {
				getLogger().debug("[fillAndExport] Export au format PDF");
				output = new GeveeOutput();
				output.setFileExtention("pdf");
				output.setFileStream(runReportToPdf(xmlData,
						model, licence));
			}
			
		} catch (JRException jre) {
			jre.printStackTrace();
			throw new GeveeSystemException("fillAndExport", jre);
		} catch (Exception e) {
			e.printStackTrace();
			throw new GeveeSystemException("fillAndExport", e);
		}

		// Retourner la sortie de la g�n�ration
		return output;
	}

	public byte[] runReportToPdf(FileInputStream xmlSource, String jasperfile, String licence)
	throws Exception {
		try {
			JasperPrint jasperPrint = fillOperation(xmlSource, jasperfile,licence);
		
			return JasperExportManager.exportReportToPdf(jasperPrint);
		} catch (Exception e) {
			throw new Exception("runReportToPdf", e);
		}
	}
	
	private JasperPrint fillOperation(FileInputStream xmlSource,
			String jasperfile,String licence) throws Exception {
		try {
			return fillSingleOperation(xmlSource, jasperfile,licence);
		} 
		catch (JRException jre) {
			try { 
				getLogger().warn("Erreur de g�n�ration de l'�tat : " + jre.getMessage() + " !", jre);
				jre.printStackTrace();
				
				// Traitement du cas sp�cifique des caract�res incorrect dans le fichier xml
				if (jre.getCause() instanceof SAXParseException) {
					FileInputStream xmlSourceClean = cleanXmlFile(xmlSource);
					return fillSingleOperation(xmlSourceClean, jasperfile);
				} else
					throw new Exception("",jre);
			} catch (Exception e) {
				throw new Exception("",e);
			}
		} catch (Exception e) {
			throw new Exception("", e);
		}
	}

	
	/* Lancement unique d'un g�n�ration du Jasper print sans reprise sur erreur */
	@SuppressWarnings("unchecked")
	private JasperPrint fillSingleOperation(FileInputStream xmlSource,
			String jasperfile) throws JRException {

		getLogger().debug("[SOS LOG : JasperReportFactory ]Fill op�ration ...");
		Map params = new HashMap();
		Document document = JRXmlUtils.parse(xmlSource);
		params.put(JRXPathQueryExecuterFactory.PARAMETER_XML_DATA_DOCUMENT,
				document);
		params.put(JRXPathQueryExecuterFactory.XML_DATE_PATTERN, "dd-MM-yyyy");
		params.put(JRXPathQueryExecuterFactory.XML_NUMBER_PATTERN, "#,##0");
		params.put(JRXPathQueryExecuterFactory.XML_LOCALE, Locale.FRENCH);
		params.put(JRParameter.REPORT_LOCALE, Locale.FRANCE);
		
		// mapping fichier template et fichier de la source de donn�es
		getLogger().debug("[SOS LOG : JasperReportFactory ]mapping fichier template et fichier de la source de donn�es : G�n�ration du JasperPrint ...");

		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperfile,
				params);

		// SAXParseException

//		if (showViewer)
//			JasperViewer.viewReport(jasperPrint);

		getLogger().debug("[SOS LOG : JasperReportFactory ]G�n�ration du JasperPrint termin�e.");
		return jasperPrint;

	}
	
	/* Lancement unique d'un g�n�ration du Jasper print sans reprise sur erreur */
	@SuppressWarnings("unchecked")
	private JasperPrint fillSingleOperation(FileInputStream xmlSource,
			String jasperfile , String licence) throws JRException {

		getLogger().debug("[SOS LOG : JasperReportFactory ]Fill op�ration ...");
		Map params = new HashMap();
		Document document = JRXmlUtils.parse(xmlSource);
		params.put(JRXPathQueryExecuterFactory.PARAMETER_XML_DATA_DOCUMENT,
				document);
		params.put(JRXPathQueryExecuterFactory.XML_DATE_PATTERN, "dd-MM-yyyy");
		params.put(JRXPathQueryExecuterFactory.XML_NUMBER_PATTERN, "#,##0");
		params.put(JRXPathQueryExecuterFactory.XML_LOCALE, Locale.FRENCH);
		params.put(JRParameter.REPORT_LOCALE, Locale.FRANCE);
		getLogger().debug("[SOS LOG : JasperReportFactory ]Passer le param�tre Type de licence � l'�tat");
		params.put("LICENCE", licence);
		getLogger().debug("[SOS LOG : JasperReportFactory ]Param�tre recuperer est:"+"<< "+licence+" >>");


		// mapping fichier template et fichier de la source de donn�es
		getLogger()
				.debug("[SOS LOG : JasperReportFactory ]mapping fichier template et fichier de la source de donn�es : G�n�ration du JasperPrint ...");

		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperfile,
				params);

		// SAXParseException

//		if (showViewer)
//			JasperViewer.viewReport(jasperPrint);

		getLogger()
				.debug("[SOS LOG : JasperReportFactory ]G�n�ration du JasperPrint termin�e.");
		return jasperPrint;

	}

	
	private FileInputStream cleanXmlFile(FileInputStream xmlSourceStream)
	throws Exception {
		try {
			StringBuilder xmlStrBuilder = new StringBuilder();

			InputStreamReader streamReader = new InputStreamReader(
					xmlSourceStream);
			BufferedReader reader = new BufferedReader(streamReader);
			String str = reader.readLine();

			while (str != null) {
				xmlStrBuilder.append(str.replaceAll(XML_BAD_CHAR_PATTERN, XML_REPLACE));
			}

			String versionXml = "<?xml version=\"1.0\" encoding=\""
				+ XML_ENCODING + "\"?>";

			// produire le nouveau fichier Xml
			File xmlFileOut = File.createTempFile("tempClean", ".xml");
			// File xmlFile = new File("temp", ".xml");
			System.out.println(xmlFileOut.getAbsolutePath());

			// Enr�gistrement du r�sultat dans un fichier
			FileWriter fw = new FileWriter(xmlFileOut);

			// Ecriture de l'entete du fichier
			fw.write(versionXml);

			// Ecriture du contenu XML
			fw.write(xmlStrBuilder.toString());
			fw.close();

			return new FileInputStream(xmlFileOut);

		} catch (FileNotFoundException e) {
			System.err.println("Fichier non trouv� :  " + e);
			throw new Exception("", e);
		}
		catch (Exception e) {
			throw new Exception("", e);
		}
	}

}
