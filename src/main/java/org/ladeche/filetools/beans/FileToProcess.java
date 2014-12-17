package org.ladeche.filetools.beans;

import java.io.IOException;
import java.util.ListIterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileToProcess extends ItemToProcess{
	
    static final Logger logger = LoggerFactory.getLogger(FileToProcess.class);
	
	public FileToProcess (String sourceRelativePath, String sourcedir) {
		this.sourceRelativePath = sourceRelativePath;
		this.sourceFullPath = sourcedir+"/"+sourceFullPath.substring(2);
		this.fileName = sourceRelativePath.substring(sourceRelativePath.lastIndexOf(".")+1);
	}

	public int countItemsToProcess() {
        return 1;
	};
	
	public boolean add(ItemToProcess itemToProcessToAdd) {
        return false;
	};
	
	public boolean remove(ItemToProcess itemToProcessToRemove) {
        return false;
	};
	
	public ListIterator createListIterator() {
		return null;
	};

	@Override
	public String toString() {
		return   this.id+":"
				+this.sourceFullPath + ":"
				+this.sourceRelativePath + ":" 
				+this.fileName;

	}

}
