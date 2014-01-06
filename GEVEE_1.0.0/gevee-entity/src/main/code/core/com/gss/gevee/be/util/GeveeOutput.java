package com.gss.gevee.be.util;

public class GeveeOutput {
	
	/**
	 * 
	 */
	public GeveeOutput() {
		 
	}

	public GeveeOutput(byte[] p$fileStream, String p$uri) {
		this.fileStream = p$fileStream;
		this.uri = p$uri;
	}
	
	byte[] fileStream;
	
	String uri;
	
	Object mergeObject;
	
	private String fileExtention;

	
	public Object getMergeObject() {
		return mergeObject;
	}

	public void setMergeObject(Object mergeObject) {
		this.mergeObject = mergeObject;
	}

	public byte[] getFileStream() {
		return fileStream;
	}

	public void setFileStream(byte[] fileStream) {
		this.fileStream = fileStream;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public void setFileExtention(String fileExtention) {
		this.fileExtention = fileExtention;
	}

	public String getFileExtention() {
		return fileExtention;
	}
	

}
