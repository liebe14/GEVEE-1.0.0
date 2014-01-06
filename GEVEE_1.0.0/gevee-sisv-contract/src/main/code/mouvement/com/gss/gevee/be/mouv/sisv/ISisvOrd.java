package com.gss.gevee.be.mouv.sisv;

import javax.ejb.Local;

import com.gss.gevee.be.core.exception.GeveeSystemException;
import com.gss.gevee.be.core.sisv.base.IBaseSisv;
import com.gss.gevee.be.mouv.entity.TabOrd;
import com.gss.gevee.be.util.EntFichier;

@Local
public interface ISisvOrd extends IBaseSisv<TabOrd, String>{

	EntFichier genererEtatOrdTrans(TabOrd $vTabOrd) throws GeveeSystemException;

	TabOrd cloturer(TabOrd tabOrd) throws GeveeSystemException; 

}
