package com.gss.gevee.be.mouv.sisv;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import com.gss.gevee.be.core.base.BaseEntity;
import com.gss.gevee.be.core.base.BaseLogger;
import com.gss.gevee.be.core.dao.base.IBaseDao;
import com.gss.gevee.be.core.exception.GeveePersistenceException;
import com.gss.gevee.be.core.exception.GeveeSystemException;
import com.gss.gevee.be.core.sisv.base.BaseSisv;
import com.gss.gevee.be.core.util.locator.ResourceLocator;
import com.gss.gevee.be.mouv.dao.IDaoCon;
import com.gss.gevee.be.mouv.dao.IDaoOrd;
import com.gss.gevee.be.mouv.entity.TabCon;
import com.gss.gevee.be.mouv.entity.TabOrd;
import com.gss.gevee.be.mouv.serializer.SrlOrdTran;
import com.gss.gevee.be.mouv.serializer.SrlOrdTranElt;
import com.gss.gevee.be.util.EntFichier;
import com.gss.gevee.be.util.GeveeOutput;
import com.gss.gevee.be.util.GeveePrinterExportEnum;
import com.gss.gevee.be.util.ReportNames;

@Stateless
public class SisvOrd extends BaseSisv<TabOrd, String> implements ISisvOrd{

	
	private static BaseLogger logger = BaseLogger.getLogger(SisvOrd.class);

	@Override
	public BaseLogger getLogger() {
		return logger;
	} 
	@EJB
	IDaoOrd daoOrd;
	
	@EJB
	IDaoCon daoCon;
	
	@Override
	public IBaseDao<TabOrd, String> getBaseDao() {
		return daoOrd;
	}

	public <X extends BaseEntity> X rechercher(X entity, Serializable id) throws GeveeSystemException {
		try {
			return daoOrd.findById(entity, id);
		} catch (GeveePersistenceException e) {
			e.printStackTrace();
			GeveeSystemException sbr = new GeveeSystemException(e);
			throw sbr;
		}
	}
	
	public <X extends BaseEntity> List<X> rechercherTout(X entity) throws GeveeSystemException {
		
		try {
			return daoOrd.findAll(entity);
		} catch (GeveePersistenceException e) {
			e.printStackTrace();
			GeveeSystemException sbr = new GeveeSystemException(e);
			throw sbr;
		}
	}

	public <X extends BaseEntity> List<X> rechercherParCritere(X entity) throws GeveeSystemException {
		
		try {
			return daoOrd.findByExample(entity);
		} catch (GeveePersistenceException e) {
			e.printStackTrace();
			GeveeSystemException sbr = new GeveeSystemException(e);
			throw sbr;
		}
	}
	
	private SrlOrdTran getEtatOrdTran(TabOrd v$TabOrd) throws GeveeSystemException {
		SrlOrdTran srlOrdTran = new SrlOrdTran();
		try {
			//Recherche tous les conteneurs de cet ordre
			List<TabCon> listCon = daoCon.findByNumOrd(v$TabOrd.getNumOrdTra());
			if(listCon != null && listCon.size() > 0){
				//On parcour la liste des conteneurs obtenus et on construit la sérialization  
				for(TabCon conCour : listCon){
					srlOrdTran.addElement(new SrlOrdTranElt(v$TabOrd, conCour));
				}
			}
		} catch (GeveePersistenceException e) {
			e.printStackTrace();
			throw new GeveeSystemException(e.getMessage());
		}
		return srlOrdTran;
	}
	
	
	@Override
	public EntFichier genererEtatOrdTrans(TabOrd $vTabOrd) throws GeveeSystemException{
		
		try{
			SrlOrdTran etatOrdTran = getEtatOrdTran($vTabOrd);
			getLogger().debug("SisvOrd.genererEtatOrdTrans Serialisation ...");
			GeveeOutput result = fillAndExport(etatOrdTran,
					ResourceLocator.getReportModel(ReportNames.ETAT_ORD_TRANS),
					GeveePrinterExportEnum.PDF, null, null, null);
			
			// Construction du nom par défaut du fichier
			String str = (ReportNames.ETAT_ORD_TRANS).getDefaulFileName() + "."
					+ result.getFileExtention();
			
			// Création de l'entité fichier
			getLogger().debug("SisvOrd.genererEtatOrdTrans Creation de l'entite fichier ..."+result.getUri());
			EntFichier v$fichier = new EntFichier(result.getUri(), str,
					result.getFileStream());
			
			logger.debug("Fichier généré " + str + " >> avec "
					+ v$fichier.getLength() + "Ko.");
			return v$fichier;
			
		}catch(Exception e){
			throw new GeveeSystemException(e.getMessage());
		}
		
	}
	
	@Override
	public  TabOrd cloturer(TabOrd tabOrd) throws GeveeSystemException {
		try {
			tabOrd.setBooEstClo(BigDecimal.ONE);
			return super.modifier(tabOrd);
		} catch (GeveePersistenceException e) {
			e.printStackTrace();
			GeveeSystemException sbr = new GeveeSystemException(e);
			throw sbr;
		}
	}

	
}
