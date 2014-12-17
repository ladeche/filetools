package org.ladeche.filetools.beans;

import java.io.IOException;
import java.util.ArrayList;
import java.util.ListIterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FileToProcess extends ItemToProcess{
	
    //static final Logger logger = LoggerFactory.getLogger(FileToProcess.class);
	
    public FileToProcess(String relativePath, String sourcedir) {
		super(relativePath,sourcedir);

        this.children = new ArrayList();
    }
	public FileToProcess (String fullPath) {
		super(fullPath);
		logger.debug(this.toString());
	}
	
	public FileToProcess (String fullPath, long length) {
		super(fullPath,length);
		logger.debug(this.toString());
		//this.fileName = fullPath.substring(relativePath.lastIndexOf(".")+1);
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
	

	@Override
	public String toString() {
		return   this.id+":"
				+this.fullPath + ":"
				+this.relativePath + ":" 
				+this.fileName;

	}

}
