package com.gss.gevee.be.ref.sisv;

import javax.ejb.Local;

import com.gss.gevee.be.core.sisv.base.IBaseSisv;
import com.gss.gevee.be.ref.entity.TabCli;

@Local
public interface ISisvCli extends IBaseSisv<TabCli, String>{
	
}
