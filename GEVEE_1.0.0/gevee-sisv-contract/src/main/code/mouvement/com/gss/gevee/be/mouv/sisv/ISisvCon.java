package com.gss.gevee.be.mouv.sisv;

import java.util.List;

import javax.ejb.Local;

import com.gss.gevee.be.core.exception.GeveeSystemException;
import com.gss.gevee.be.core.sisv.base.IBaseSisv;
import com.gss.gevee.be.mouv.entity.TabCon;

@Local
public interface ISisvCon extends IBaseSisv<TabCon, String>{

	List<TabCon> rechercherParNumOrd(String numOrd) throws GeveeSystemException;

}
