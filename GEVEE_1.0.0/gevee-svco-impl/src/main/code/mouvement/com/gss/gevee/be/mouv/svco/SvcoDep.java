package com.gss.gevee.be.mouv.svco;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import com.gss.gevee.be.core.base.BaseEntity;
import com.gss.gevee.be.core.base.BaseLogger;
import com.gss.gevee.be.core.base.DateTools;
import com.gss.gevee.be.core.enums.EnuEtat;
import com.gss.gevee.be.core.enums.EnuTypCon;
import com.gss.gevee.be.core.exception.GeveeAppException;
import com.gss.gevee.be.core.exception.GeveePersistenceException;
import com.gss.gevee.be.core.exception.GeveeSystemException;
import com.gss.gevee.be.core.sisv.base.IBaseSisv;
import com.gss.gevee.be.core.svco.base.BaseSvco;
import com.gss.gevee.be.mouv.entity.TabCon;
import com.gss.gevee.be.mouv.entity.TabDep;
import com.gss.gevee.be.mouv.sisv.ISisvCon;
import com.gss.gevee.be.mouv.sisv.ISisvDep;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class SvcoDep extends BaseSvco<TabDep> implements IRemoteDep, ILocalDep{
	
	@EJB
	ISisvDep sisvDep;
	
	@EJB
	ISisvCon sisvCon;
	
	@Resource
	SessionContext session;

	private static BaseLogger logger = BaseLogger.getLogger(SvcoDep.class);
	
	private long CONST_DURATION_OF_DAY = 1000l * 60 * 60 * 24;

	@Override
	protected IBaseSisv<TabDep, String> getBaseSisv() {
		return sisvDep;
	}
	
	@Override
	public BaseLogger getLogger() {
		return logger;
	}


	@Override
	public <X extends BaseEntity> X rechercher(X entity, Serializable id)
			throws GeveeAppException {
		try {
			return sisvDep.rechercher(entity,id);
		} catch (GeveeSystemException e) {
			e.printStackTrace();
			GeveeAppException sdr = new GeveeAppException(e);
			throw sdr;
		}
	}

	@Override
	public <X extends BaseEntity> List<X> rechercherTout(X entity)
			throws GeveeAppException {
		try {
			return sisvDep.rechercherTout(entity);
		} catch (GeveeSystemException e) {
			e.printStackTrace();
			GeveeAppException sdr = new GeveeAppException(e);
			throw sdr;
		}
	}

	@Override
	public <X extends BaseEntity> List<X> rechercherParCritere(X entity)
			throws GeveeAppException {
		try {
			return sisvDep.rechercherParCritere(entity);
		} catch (GeveeSystemException e) {
			e.printStackTrace();
			GeveeAppException sdr = new GeveeAppException(e);
			throw sdr;
		}
	}
	
	@Override
	public TabDep demarrer(TabDep tabDep) throws GeveeAppException {
		try {
			tabDep.setEtatEnt(EnuEtat.DEMARRE.getValue());
			return sisvDep.demarrer(tabDep);
		} catch (GeveeSystemException e) {
			e.printStackTrace();
			GeveeAppException sdr = new GeveeAppException(e);
			throw sdr;
		}
	}
	
	@Override
	public TabDep cloturer(TabDep tabDep) throws GeveeAppException {
		try {
			BigDecimal mntDet;
			//On met à jour l'état du conteneur à livré
			TabCon conCour = tabDep.getTabCon();
			conCour.setEtatEnt(EnuEtat.LIVRE.getValue());
			sisvCon.modifier(conCour);
			//On calcule la détention 
			if(tabDep.getBEstCau()){
				Date datEstRet = tabDep.getDateEstRet();
				Date datEffRet = tabDep.getDateEffRet();
				if(null != datEstRet && null != datEffRet){
					long diff = datEffRet.getTime() - datEstRet.getTime();
					long nbrJrRetard = (long)diff/CONST_DURATION_OF_DAY;
					System.out.println("Le nombre de jour est : " + nbrJrRetard);
					if(EnuTypCon.is20Pieds(conCour.getEnuTypCon())){
						mntDet = new BigDecimal(7500*nbrJrRetard);
					}else{
						mntDet = new BigDecimal(15000*nbrJrRetard);
					}
					tabDep.setValDet(mntDet);
				}
			}
			//Mise à jour de l'entité
			tabDep.setBooEstClo(BigDecimal.ONE);
			tabDep.setEtatEnt(EnuEtat.CLOTURE.getValue());
			return sisvDep.cloturer(tabDep);
		} catch (GeveeSystemException e) {
			e.printStackTrace();
			GeveeAppException sdr = new GeveeAppException(e);
			throw sdr;
		}catch (GeveePersistenceException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public TabDep receptionner(TabDep tabDep) throws GeveeAppException {
		try {
			//Met à jour l'état du conteneur
			TabCon con = tabDep.getTabCon();
			con.setEtatEnt(EnuEtat.RECEPTIONNE.getValue());
			sisvCon.modifier(con);
			//Mis à jour de l'entité
			tabDep.setBooEstRecep(BigDecimal.ONE);
			tabDep.setEtatEnt(EnuEtat.RECEPTIONNE.getValue());
			TabDep depReturn = sisvDep.receptionner(tabDep);
			//envoit un mail au client
//			String  mailClient = con.getTabOrdTran().getTabClient().getLibMail();
			String  msgBody = "Conteneur n° " + con.getNumCon() +" réceptionné par : " + tabDep.getLibRecep();
			System.out.println("======== "+ msgBody);
//			final String username = "global@globalservices-sarl.com";
//			final String password = "global123";
//			Properties props = new Properties();
//			props.put("mail.smtp.auth", "true");
//			props.put("mail.smtp.starttls.enable", "true");
//			props.put("mail.smtp.host", "smtp.globalservices-sarl.com");
//			props.put("mail.smtp.port", "25");
//			Session session = Session.getInstance(props,
//			  new javax.mail.Authenticator() {
//				protected PasswordAuthentication getPasswordAuthentication() {
//					return new PasswordAuthentication(username, password);
//				}
//			});
//			Message message = new MimeMessage(session);
//			message.setFrom(new InternetAddress("global@globalservices-sarl.com"));
//			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("blaise.tsahata1@gmail.com"));
//			message.setSubject("Conteneur réceptionné");
//			MimeBodyPart mbp1 = new MimeBodyPart();
//			mbp1.setText(msgBody);
//			MimeMultipart mp = new MimeMultipart();
//			mp.addBodyPart(mbp1);
//			message.setContent(mp);
//			Transport.send(message);
//			logger.info("mail transmit");
			
			return depReturn;
		} catch (GeveeSystemException e) {
			e.printStackTrace();
			GeveeAppException sdr = new GeveeAppException(e);
			throw sdr;
		} catch (GeveePersistenceException e) {
			e.printStackTrace();
		}
//		catch(NoSuchProviderException e) {
//			e.printStackTrace();
//		    System.err.println("Pas de transport disponible pour ce protocole");
//		    System.err.println(e);
//		}
//		catch(AddressException e) {
//			e.printStackTrace();
//		    System.err.println("Adresse invalide");
//		    System.err.println(e);
//		}
//		catch(MessagingException e) {
//			e.printStackTrace();
//		    System.err.println("Erreur dans le message");
//		    System.err.println(e);
//		}
		return null;
	}
	
	@Override
	public List<TabCon> rechercherConParEtatEnt(String etatEnt)
	throws GeveeAppException {
		try {
			return sisvDep.rechercherConParEtatEnt(etatEnt);
		} catch (GeveeSystemException e) {
			e.printStackTrace();
			GeveeAppException sdr = new GeveeAppException(e);
			throw sdr;
		}
	}

}
