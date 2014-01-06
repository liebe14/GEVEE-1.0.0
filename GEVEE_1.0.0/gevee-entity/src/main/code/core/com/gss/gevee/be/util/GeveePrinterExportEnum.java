package com.gss.gevee.be.util;

public enum GeveePrinterExportEnum {
	
	PDF,
	EXCEL,
	EXCELX,
	DOC,
	DOCX,
	HTML,
	CSV;
	
	public static GeveePrinterExportEnum getByName(String value){
		for(GeveePrinterExportEnum prt : GeveePrinterExportEnum.class.getEnumConstants()){
			if(prt.name().equalsIgnoreCase(value))
				return prt;
		}
		return null;
	}

}
