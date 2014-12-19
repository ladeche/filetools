package org.ladeche.filetools.beans;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;


public class DirToProcess extends ItemToProcess {

	protected ArrayList<ItemToProcess> children;

	public DirToProcess(String fullPath) {
		super(fullPath);
		this.children = new ArrayList<ItemToProcess>();
		logger.debug(this.toString());
	}

	public DirToProcess (String fullPath, long length) {
		super(fullPath,length);
		this.children = new ArrayList<ItemToProcess>();
		logger.debug(this.toString());
	}
	public DirToProcess (String fullPath, String rootPath) {
		super(fullPath,rootPath);
		this.children = new ArrayList<ItemToProcess>();
		logger.debug(this.toString());
	}

	public DirToProcess (String fullPath, String rootPath, long length) {
		super(fullPath,rootPath,length);
		this.children = new ArrayList<ItemToProcess>();
		logger.debug(this.toString());
	}
	
	public ArrayList<ItemToProcess> getChildren() {
		return children;
	}

	public void setChildren(ArrayList<ItemToProcess> children) {
		this.children = children;
	}

	
    /**
     * Add a child file to children
     */
	public boolean add(ItemToProcess itemToProcess) {
		return children.add(itemToProcess);
	}

	
    /**
     * List all children at all levels and initialize children with result
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
				if (Files.isSymbolicLink(list[i].toPath())) {
					logger.debug("AddLink");
					// Add simple file and increment size
					arrayList.add(new LinkToProcess(list[i].getAbsolutePath(),list[i].length()));
					this.size=this.size+list[i].length();
				} else {
					logger.debug("AddFile");
					// Add simple file and increment size
					arrayList.add(new FileToProcess(list[i].getAbsolutePath(),list[i].length()));
					this.size=this.size+list[i].length();
				}
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
				if (Files.isSymbolicLink(list[i].toPath())) {
					// Add simple file and increment size
					arrayList.add(new LinkToProcess(list[i].getAbsolutePath()));
				} else {
					// Add simple file and increment size
					arrayList.add(new FileToProcess(list[i].getAbsolutePath()));
				}
				// Add simple file and increment size
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
