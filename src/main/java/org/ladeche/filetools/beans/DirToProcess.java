package org.ladeche.filetools.beans;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.ListIterator;


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
	
    /**
     * List all children at all levels and store result in children
     */
	public ArrayList<ItemToProcess> listDirectoryContentFull() {
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
				childDir.listDirectoryContentFull();
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
	
    /**
     * List only items into first level
     */
	public ArrayList<ItemToProcess> listDirectoryContentSimple() {
		logger.debug("listDirectoryContentSimple:"+this.fullPath);

		ArrayList<ItemToProcess> arrayList = new ArrayList<ItemToProcess>();
		
		// List directory children
		File file = new File(this.fullPath);
		File[] list = file.listFiles();

		// Parse children
		for (int i = 0; i < list.length; i++) {
			if (list[i].isDirectory()) {
				// Add Directory
				arrayList.add(new DirToProcess(list[i].getAbsolutePath()));
			} else {
				// Add simple file and increment size
				arrayList.add(new FileToProcess(list[i].getAbsolutePath(),list[i].length()));
			}

		}
		return arrayList;
	}
	
    /**
     * Copy directory and content to destination
     */
	public boolean copyTo (String destinationFile) {
		logger.debug("(Dir)From "+this.fullPath+ " To "+destinationFile);
		ArrayList<ItemToProcess> dirContent = this.listDirectoryContentSimple();
		
		try {
			// create directory
			File source = new File(this.fullPath);
			File dest = new File(destinationFile);
			Files.copy(source.toPath(),dest.toPath());
			
			// Now parse directory content
			for (ItemToProcess itp : dirContent) {
				if (itp instanceof DirToProcess) {
					((DirToProcess) itp).copyTo(destinationFile+"/"+itp.getFileName());
				} else {
					itp.copyTo(destinationFile+"/"+itp.getFileName());
				}
			}       
		
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
