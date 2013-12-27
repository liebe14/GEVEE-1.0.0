package com.gss.gevee.be.mouv.svco;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import com.gss.gevee.be.core.base.BaseEntity;
import com.gss.gevee.be.core.base.BaseLogger;
import com.gss.gevee.be.core.base.DateTools;
import com.gss.gevee.be.core.exception.GeveeAppException;
import com.gss.gevee.be.core.exception.GeveePersistenceException;
import com.gss.gevee.be.core.exception.GeveeSystemException;
import com.gss.gevee.be.core.sisv.base.IBaseSisv;
import com.gss.gevee.be.core.svco.base.BaseSvco;
import com.gss.gevee.be.mouv.entity.TabCon;
import com.gss.gevee.be.mouv.entity.TabOrd;
import com.gss.gevee.be.mouv.sisv.ISisvCon;
import com.gss.gevee.be.mouv.sisv.ISisvOrd;

@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class SvcoOrd extends BaseSvco<TabOrd> implements IRemoteOrd, ILocalOrd{

	@EJB
	ISisvOrd sisvOrd;
	
	@EJB
	ISisvCon sisvCon;


	@Resource
	SessionContext session;

	private static BaseLogger logger = BaseLogger.getLogger(SvcoOrd.class);

	@Override
	protected IBaseSisv<TabOrd, String> getBaseSisv() {
		return sisvOrd;
	}

	@Override
	public BaseLogger getLogger() {
		return logger;
	}
	
	public <X extends BaseEntity> X rechercher(X entity, Serializable id)
	throws GeveeAppException {
		try {
			return sisvOrd.rechercher(entity,id);
		} catch (GeveeSystemException e) {
			e.printStackTrace();
			GeveeAppException sdr = new GeveeAppException(e);
			throw sdr;
		}
	}

	public <X extends BaseEntity> List<X> rechercherTout(X entity)
	throws GeveeAppException {
		try {
			return sisvOrd.rechercherTout(entity);
		} catch (GeveeSystemException e) {
			e.printStackTrace();
			GeveeAppException sdr = new GeveeAppException(e);
			throw sdr;
		}
	}

	public <X extends BaseEntity> List<X> rechercherParCritere(X entity)
	throws GeveeAppException {
		try {
			return sisvOrd.rechercherParCritere(entity);
		} catch (GeveeSystemException e) {
			e.printStackTrace();
			GeveeAppException sdr = new GeveeAppException(e);
			throw sdr;
		}
	}
	
	public <X extends BaseEntity> X creer(X p$entite) throws GeveeAppException {
		try {
			TabOrd ordToSave = (TabOrd)p$entite;
			//On fabrique le numero de dossier de l'ordre 
			//num dossier = N°Ordre/Mois/Année
			String numDoss = ordToSave.getNumOrdTra()+"/"+DateTools.getMonth(DateTools.formatDate(new Date()))
							+"/"+DateTools.getYear(DateTools.formatDate(new Date()));
			//On enregistre l'ordre
			ordToSave.setNumDoss(numDoss);
			TabOrd ordSave = (TabOrd) sisvOrd.creer(ordToSave);
			//Récupére la liste des conteneurs de l'ordre
			List<TabCon> listeCon = ordToSave.getListCon();
			if(listeCon != null && listeCon.size() > 0){
				//On parcour la liste des conteneurs, on fixe le code du conteneur puis on l'enregistre
				for(TabCon conCour : listeCon){
					String codCon = ((TabOrd)p$entite).getNumOrdTra()+"_"+conCour.getNumCon();
					conCour.setCodCon(codCon);
					conCour.setTabOrdTran((TabOrd)p$entite);
					sisvCon.creer(conCour);
				}
			}
			return (X) ordSave;			
		} catch (GeveePersistenceException e) {
			rollbackTransactionContext();
			e.printStackTrace();
			GeveeAppException sbr = new GeveeAppException(e);
			throw sbr;
		} catch (Exception e) {
			rollbackTransactionContext();
			String message =  e.getMessage();
			GeveeAppException sysEx =  new GeveeAppException(message, e);
			getLogger().error(message, sysEx);
			throw sysEx;
		}
	}

}
