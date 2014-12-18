package org.ladeche.filetools.beans;

import java.util.ArrayList;
import java.util.ListIterator;

import org.ladeche.filetools.FTGui;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class ItemToProcess {
    static final Logger logger = LoggerFactory.getLogger(FTGui.class);

	ArrayList<ItemToProcess> children;
	ItemToProcess parent;
	String fileName;
	String fullPath;
	String relativePath;
	Long size;
	Integer id;
	
	public ItemToProcess (String relativePath, String sourcedir) {
		this.relativePath = relativePath;
		this.fullPath = sourcedir+"/"+relativePath.substring(2);
		this.fileName = relativePath.substring(relativePath.lastIndexOf(".")+1);
	}

	public ItemToProcess (String fullPath) {
		this.fullPath = fullPath;
		this.fileName = fullPath.substring(fullPath.lastIndexOf("/")+1);
		this.size=new Long(0);
		//this.fileName = fullPath.substring(relativePath.lastIndexOf(".")+1);
	}

	public ItemToProcess (String fullPath, long length) {
		this.fullPath = fullPath;
		this.fileName = fullPath.substring(fullPath.lastIndexOf("/")+1);
		this.size=(Long)length;
		//this.fileName = fullPath.substring(relativePath.lastIndexOf(".")+1);
	}
	
    /**
     * Count all items into sub items.
     */
	public abstract int countItemsToProcess();

    /**
     * Add an item to itemlist
     */
	public abstract boolean add(ItemToProcess itemToProcessToAdd);

    /**
     * Remove item from itemlist
     */
	public abstract boolean remove(ItemToProcess itemToProcessToRemove);
    /**
     * Copy item to destination
     */
	public abstract boolean copyTo(String destinationFile);

	public String toString() {
		return   this.id+":"
				+this.fullPath + ":"
//				+this.relativePath + ":" 
				+this.fileName + ":"
				+this.size;

	}
	

	public ArrayList<ItemToProcess> getChildren() {
		return children;
	}

	public void setChildren(ArrayList<ItemToProcess> children) {
		this.children = children;
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

	public ItemToProcess getParent() {
		return parent;
	}

	public void setParent(ItemToProcess parentIn) {
		parent = parentIn;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
