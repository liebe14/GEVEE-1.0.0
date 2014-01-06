package com.gss.gevee.be.mouv.sisv;

import java.util.List;

import javax.ejb.Local;

import com.gss.gevee.be.core.exception.GeveeSystemException;
import com.gss.gevee.be.core.sisv.base.IBaseSisv;
import com.gss.gevee.be.mouv.entity.TabMouv;

@Local
public interface ISisvMouv extends IBaseSisv<TabMouv, String>{

	List<TabMouv> rechercherParCodRefDep(String refDep) throws GeveeSystemException;

	TabMouv valider(TabMouv tabMouv) throws GeveeSystemException; 

}
