package com.gss.gevee.be.util;

public enum MimeType {

	/****
	 * Fichier image bitmap <br>
	 * Code="1001", Extention="bmp", mime="image/bmp"
	 */
	BMP("1001", "bmp", "image/bmp", "Fichier image bitmap"),
	// else if ("jpg".equals(extension)) {
	
	/****
	 * Fichier image jpeg <br>
	 * Code="1002", Extention="jpeg", mime="image/jpeg"
	 */
	JPEG("1002", "jpeg", "image/jpeg", "Fichier image jpeg"),
	
	/****
	 * Fichier image gif <br>
	 * Code="1003", Extention="gif", mime="image/gif"
	 */
	GIF("1003", "gif", "image/gif", "Fichier image gif"),

	/****
	 * Fichier image png <br>
	 * Code="1004", Extention="png", mime="image/png"
	 */
	PNG("1004", "png", "image/png", "Fichier image png"),	

	/****
	 * Fichier txt <br>
	 * Code="1101", Extention="txt", mime="txt/plain"
	 */
	TXT("1101", "txt", "txt/plain", "Fichier texte"),	

	/****
	 * Fichier doc <br>
	 * Code="1102", Extention="doc", mime="application/msword"
	 */
	DOC("1102", "doc", "application/msword", "Fichier Microsoft world"),
	
	/****
	 * Fichier pdf <br>
	 * Code="1103", Extention="pdf", mime="application/pdf"
	 */
	PDF("1103", "pdf", "application/pdf", "Fichier pdf"),
	
	/****
	 * Fichier txt <br>
	 * Code="1104", Extention="xls", mime="application/x-excel"
	 */
	XLS("1104", "xls", "application/x-excel", "Fichier Microsoft Excel"),
	
	/****
	 * Fichier inconnu <br>
	 * Code="0000", Extention="", mime="image/unknown"
	 */
	UNKNOWN("0000", "", "image/unknown", "Fichier inconnu");
	
	
	private final String extention;
	private final String mimeValue;
	private final String description;
	private final String mimeCode;
	
	private MimeType(String code, String extention, String mime, String description){
		this.description = description;
		this.mimeValue = mime;
		this.extention = extention;
		this.mimeCode = code;
	}

	public String getExtention() {
		return extention;
	}

	public String getMimeValue() {
		return mimeValue;
	}

	public String getDescription() {
		return description;
	}
	
	public String getMimeCode() {
		return mimeCode;
	}

	/******
	 * 
	 * @param mimeValue
	 * @return
	 */
	public static MimeType getFromMimeValue(String mimeValue){
		 if (mimeValue.equalsIgnoreCase(BMP.getMimeValue())) {
			 return BMP;
		 } 
		 if (mimeValue.equalsIgnoreCase(DOC.getMimeValue())) {
			 return DOC;
		 } 
		 if (mimeValue.equalsIgnoreCase(PDF.getMimeValue())) {
			 return PDF;
		 } 
		 if (mimeValue.equalsIgnoreCase(PNG.getMimeValue())) {
			 return PNG;
		 } 
		 if (mimeValue.equalsIgnoreCase(JPEG.getMimeValue())) {
			 return JPEG;
		 } 
		 if (mimeValue.equalsIgnoreCase(TXT.getMimeValue())) {
			 return TXT;
		 } 
		 if (mimeValue.equalsIgnoreCase(GIF.getMimeValue())) {
			 return GIF;
		 } 
		 return UNKNOWN;		
	}
	
	public static MimeType getFromExtention(String extention){
		 if (extention.equalsIgnoreCase(BMP.getExtention())) {
			 return BMP;
		 } 
		 if (extention.equalsIgnoreCase(DOC.getExtention())) {
			 return DOC;
		 } 
		 if (extention.equalsIgnoreCase(PDF.getExtention())) {
			 return PDF;
		 } 
		 if (extention.equalsIgnoreCase(PNG.getExtention())) {
			 return PNG;
		 } 
		 if (extention.equalsIgnoreCase(JPEG.getExtention())) {
			 return JPEG;
		 } 
		 if (extention.equalsIgnoreCase(TXT.getExtention())) {
			 return TXT;
		 } 
		 if (extention.equalsIgnoreCase(GIF.getExtention())) {
			 return GIF;
		 } 
		 return UNKNOWN;		
	}
	
	public static MimeType getFromCode(String code){
		 if (code.equalsIgnoreCase(BMP.getMimeCode())) {
			 return BMP;
		 } 
		 if (code.equalsIgnoreCase(DOC.getMimeCode())) {
			 return DOC;
		 } 
		 if (code.equalsIgnoreCase(PDF.getMimeCode())) {
			 return PDF;
		 } 
		 if (code.equalsIgnoreCase(PNG.getMimeCode())) {
			 return PNG;
		 } 
		 if (code.equalsIgnoreCase(JPEG.getMimeCode())) {
			 return JPEG;
		 } 
		 if (code.equalsIgnoreCase(TXT.getMimeCode())) {
			 return TXT;
		 } 
		 if (code.equalsIgnoreCase(GIF.getMimeCode())) {
			 return GIF;
		 } 
		 return UNKNOWN;		
	}
}

