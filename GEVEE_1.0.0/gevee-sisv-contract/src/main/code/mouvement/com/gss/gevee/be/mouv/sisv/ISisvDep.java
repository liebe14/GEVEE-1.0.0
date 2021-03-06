package com.gss.gevee.be.mouv.sisv;

import java.util.List;

import javax.ejb.Local;

import com.gss.gevee.be.core.exception.GeveeSystemException;
import com.gss.gevee.be.core.sisv.base.IBaseSisv;
import com.gss.gevee.be.mouv.entity.TabCon;
import com.gss.gevee.be.mouv.entity.TabDep;

@Local
public interface ISisvDep extends IBaseSisv<TabDep, String>{

	TabDep demarrer(TabDep tabDep) throws GeveeSystemException;

	TabDep cloturer(TabDep tabDep) throws GeveeSystemException;

	TabDep receptionner(TabDep tabDep) throws GeveeSystemException;

	List<TabCon> rechercherConParEtatEnt(String etatEnt) throws GeveeSystemException; 

}
