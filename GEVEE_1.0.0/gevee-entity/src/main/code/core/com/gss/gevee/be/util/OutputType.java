package com.gss.gevee.be.util;

public enum OutputType {
	
	PDF("Adobe PDF (*.pdf)", "pdf"),
	EXCEL("Classeur Excel 97 - 2003 (*.xls)", "xls"),
	EXCELX("Classeur Excel (*.xlsx)", "xlsx");
	//DOC("Document Word 97 - 2003 (*.doc)", "doc"),
	//DOCX("Document Word (*.docx)", "docx");
	//HTML("Page Web (*.html)", "html"),
	//CSV("CSV", "csv");
	
	private String type;
	private String extension;
	private OutputType(String typeName, String ext){
		type = typeName;
		extension = ext;
	}
	
	public String getExtension(){
		return extension;
	}
	
	public String getType(){
		return type;
	}
	

}
