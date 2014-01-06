package com.gss.gevee.ui.core.controleur;

import com.gss.gevee.ui.core.base.FacesUtil;




public class RequestCtrl {
	
	public RequestCtrl() {
								
	}

	public String pageBeforePreview(){
		return FacesUtil.getSessionMapValue("pageBeforePreview").toString();
	}
	

}
