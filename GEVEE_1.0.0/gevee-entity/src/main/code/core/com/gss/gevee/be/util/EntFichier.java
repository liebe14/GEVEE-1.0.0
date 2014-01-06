package com.gss.gevee.be.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.Serializable;

public class EntFichier implements Serializable {

	private static final long serialVersionUID = 1L;

	public EntFichier() {
		 
	}

	public EntFichier(String uri, String name, byte[] data) {
		super();
		this.uri = uri;
		this.name = name;
		this.data = data;
		this.ext = getExtFromName();
		this.setLength();
	}

	private String uri;

	private String name;
	private String ext;
	private long length;
	private byte[] data;
	private String codePiece;
	private String storagePath;

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
		this.setLength();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		this.ext = getExtFromName();
	}

	public long getLength() {
		return length;
	}

	private void setLength() {
		try {
			int size = this.data.length;
			this.length = size / 1024;
		} catch (Exception e) {
			this.length = 0;
		}
	}

	public void setCodePiece(String codePiece) {
		this.codePiece = codePiece;
		this.setStorgePath();
	}

	public String getCodePiece() {
		return codePiece;
	}

	public MimeType getMime() {
		return MimeType.getFromExtention(this.ext);
	}

	public String getExtention() {
		return this.ext;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	private String getExtFromName() {
		try {
			String v$name = this.name;
			if (v$name == null || v$name.isEmpty())
				v$name = this.uri;

			int extDot = v$name.lastIndexOf('.');
			String extension = "";
			if (extDot > 0) {
				extension = v$name.substring(extDot + 1);
			}
			return extension;
		} catch (Exception e) {
			return "";
		}
	}

	public String getStoragePath() {
		return this.storagePath;
	}

	private void setStorgePath() {
		if (this.codePiece == null || this.codePiece.isEmpty())
			this.storagePath = null;

		String path = "";
		String[] strs = this.codePiece
				.split("_");
		path += (strs[0]==null || strs[0].isEmpty()) ? "" : strs[0] + File.separator; 
		path += (strs[1]==null || strs[1].isEmpty()) ? "" : strs[1] + File.separator;
		
		path += strs[2]	+ "." + MimeType.getFromCode(strs[3]).getExtention();

		System.out.println("Chemin relatif : " + path);
		this.storagePath = path;
	}
	
	public static EntFichier readFile(String fileName) throws Exception {
		try {
			File toGet = new File(fileName);
			EntFichier monFich = null;
			if(!toGet.exists())
				throw new Exception("readFile"+ fileName );
			
			FileInputStream finput = new FileInputStream(toGet);
			byte[] data = new byte[(int)toGet.length()];
			finput.read(data);
			monFich = new EntFichier(fileName, toGet.getName(), data);
			return monFich;
		} catch (Exception e) {
			Exception sdr = new Exception("readFile", e);
			throw sdr;
		}
	}
	
		public static String writeFile(EntFichier fichier) throws Exception {
		try {			
			if(fichier.getUri()==null || fichier.getUri().isEmpty())
				throw new Exception("writeFile");
			File toSet = new File(fichier.getUri());
			FileOutputStream foutput = new FileOutputStream(toSet);
			
			foutput.write(fichier.getData());
			foutput.flush();
			foutput.close();
						
			return fichier.getUri();
		} catch (Exception e) {
			Exception sdr = new Exception("writeFile", e);
			throw sdr;
		}
	}
	
	
	public static String createTempFile(EntFichier fichier) throws Exception {
		try {
			if(fichier == null || fichier.getData()==null || fichier.getData().length<=0)
			{
				throw new Exception("createTempFile");
			}
			String ext = fichier.getExtention();
			File tmpFile = File.createTempFile("geveeFile", "." + ext );
			
			FileOutputStream foutput = new FileOutputStream(tmpFile);
			
			foutput.write(fichier.getData());
			foutput.flush();
			foutput.close();
						
			return tmpFile.getAbsolutePath();
		} catch (Exception e) {
			Exception sdr = new Exception("createTempFile", e);
			throw sdr;
		}
	}
	
	public static String createTempFile(EntFichier fichier, String path) throws Exception {
		try {
			if(fichier == null || fichier.getData()==null || fichier.getData().length<=0)
			{
				throw new Exception("createTempFile");
			}
			String ext = fichier.getExtention();
			
			File file = new File(path);
			
			if(file.exists() && file.isFile())
				throw new Exception(path );
			if(!file.exists())
				file.mkdir();
			
			File tmpFile = File.createTempFile("geveeFile", "." + ext , file);
			
			FileOutputStream foutput = new FileOutputStream(tmpFile);
			
			foutput.write(fichier.getData());
			foutput.flush();
			foutput.close();
						
			return tmpFile.getAbsolutePath();
		} catch (Exception e) {
			Exception sdr = new Exception("createTempFile", e);
			throw sdr;
		}
	}
}

