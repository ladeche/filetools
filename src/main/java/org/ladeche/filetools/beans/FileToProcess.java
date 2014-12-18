package org.ladeche.filetools.beans;

import java.io.File;
import java.io.IOException;
import static java.nio.file.LinkOption.NOFOLLOW_LINKS;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;


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
	}
	
	public boolean add(ItemToProcess itemToProcessToAdd) {
        return false;
	}
	
	public boolean remove(ItemToProcess itemToProcessToRemove) {
        return false;
	}
	
	public boolean copyTo (String destinationFile) {
		logger.debug("(File)From "+this.fullPath+ " To "+destinationFile);
		try {
			File source = new File(this.fullPath);
			Files.copy(source.toPath(),Paths.get(destinationFile),NOFOLLOW_LINKS);
			// Prévoir une procédure de recréation du lien copy de lien
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
