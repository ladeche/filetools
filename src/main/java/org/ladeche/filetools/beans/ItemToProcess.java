package org.ladeche.filetools.beans;


import org.ladeche.filetools.FTGui;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class ItemToProcess {
    static final Logger logger = LoggerFactory.getLogger(FTGui.class);

	protected String fullPath;
	protected String fileName;
	protected Long size;
	protected String relativePath;
	
	public ItemToProcess (String fullPath) {
		this.fullPath = fullPath;
		this.fileName = fullPath.substring(fullPath.lastIndexOf("/")+1);
		this.size=new Long(0);
	}

	public ItemToProcess (String fullPath, long length) {
		this.fullPath = fullPath;
		this.fileName = fullPath.substring(fullPath.lastIndexOf("/")+1);
		this.size=length;
	}

	public ItemToProcess (String fullPath, String rootPath) {
		this.fullPath = fullPath;
		this.fileName = fullPath.substring(fullPath.lastIndexOf("/")+1);
		this.relativePath = fullPath.substring(rootPath.length());
		this.size=new Long(0);
	}

	public ItemToProcess (String fullPath, String rootPath, long length) {
		this.fullPath = fullPath;
		this.fileName = fullPath.substring(fullPath.lastIndexOf("/")+1);
		this.relativePath = fullPath.substring(rootPath.length());
		this.size=length;
	}


    /**
     * Copy item to destination
     */
	public abstract boolean copyTo(String destinationFile);

	
	
	public String toString() {
		return   this.fullPath + ":"
				+this.relativePath + ":" 
				+this.fileName + ":"
				+this.size;
	}
	
	public String getFullPath() {
		return fullPath;
	}

	public void setFullPath(String fullPath) {
		this.fullPath = fullPath;
	}

	public String getRelativePath() {
		return relativePath;
	}

	public void setRelativePath(String relativePath) {
		this.relativePath = relativePath;
	}

	public Long getSize() {
		return size;
	}

	public void setSize(Long size) {
		this.size = size;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
