package org.ladeche.filetools.beans;

import static java.nio.file.LinkOption.NOFOLLOW_LINKS;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class FileToProcess extends ItemToProcess{
	

	public FileToProcess (String fullPath) {
		super(fullPath);
		logger.debug(this.toString());
	}
	
	public FileToProcess (String fullPath, long length) {
		super(fullPath,length);
		logger.debug(this.toString());
	}
	public FileToProcess (String fullPath, String rootPath) {
		super(fullPath,rootPath);
		logger.debug(this.toString());
	}

	public FileToProcess (String fullPath, String rootPath, long length) {
		super(fullPath,rootPath,length);
		logger.debug(this.toString());
	}

	
    /**
     * Copy file to destination
     */
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
