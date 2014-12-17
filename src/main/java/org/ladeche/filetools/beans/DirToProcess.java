package org.ladeche.filetools.beans;

import java.io.File;
import java.util.ArrayList;
import java.util.ListIterator;

import org.ladeche.filetools.FTGui;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DirToProcess extends ItemToProcess {
    //static final Logger logger = LoggerFactory.getLogger(FTGui.class);

	public DirToProcess(String relativePath, String sourcedir) {
		super(relativePath, sourcedir);

		this.children = new ArrayList();
	}

	public DirToProcess(String fullPath) {
		super(fullPath);
		logger.debug(this.toString());
	}

	public int countItemsToProcess() {
		int totalItemsToProcess = 0;
		ListIterator listIterator = this.createListIterator();
		ItemToProcess itemToProcess;
		while (listIterator.hasNext()) {
			itemToProcess = (ItemToProcess) listIterator.next();
			totalItemsToProcess += itemToProcess.countItemsToProcess();
		}
		return totalItemsToProcess;
	}

	public boolean add(ItemToProcess itemToProcessToAdd) {
		itemToProcessToAdd.setParent(this);
		return children.add(itemToProcessToAdd);
	}

	public boolean remove(ItemToProcess itemToProcessToRemove) {
		ListIterator listIterator = this.createListIterator();
		ItemToProcess itemToProcess;
		while (listIterator.hasNext()) {
			itemToProcess = (ItemToProcess) listIterator.next();
			if (itemToProcess == itemToProcessToRemove) {
				listIterator.remove();
				return true;
			}
		}
		return false;
	}

	public ListIterator createListIterator() {
		ListIterator listIterator = children.listIterator();
		return listIterator;
	}

	public ArrayList<ItemToProcess> listDirectoryContent() {
		ArrayList<ItemToProcess> arrayList = new ArrayList<ItemToProcess>();
		DirToProcess childDir;
		
		// List directory children
		File file = new File(this.fullPath);
		File[] list = file.listFiles();

		// Parse children
		for (int i = 0; i < list.length; i++) {
			Long fileSize = (Long)list[i].length();
			logger.debug(list[i].getAbsolutePath()+":"+fileSize.toString());
			if (list[i].isDirectory()) {
				// Add Directory
				logger.debug("AddDirectory");
				childDir = new DirToProcess(list[i].getAbsolutePath());
				childDir.listDirectoryContent();
				arrayList.add(childDir);
				this.size=this.size+childDir.size;
			} else {
				logger.debug("AddFile");
				// Add simple file and increment size
				arrayList.add(new FileToProcess(list[i].getAbsolutePath(),list[i].length()));
				this.size=this.size+list[i].length();
			}

		}

		this.children=arrayList;
		return arrayList;

	}

}
