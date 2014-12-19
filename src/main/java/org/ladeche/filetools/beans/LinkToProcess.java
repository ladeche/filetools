package org.ladeche.filetools.beans;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LinkToProcess extends ItemToProcess {
	protected Path target;

	public LinkToProcess (String fullPath) {
		super(fullPath);
		this.target=this.buildTarget();
		logger.debug(this.toString());
	}
	
	public LinkToProcess (String fullPath, long length) {
		super(fullPath,length);
		this.target=this.buildTarget();
		logger.debug(this.toString());
	}
	public LinkToProcess (String fullPath, String rootPath) {
		super(fullPath,rootPath);
		this.target=this.buildTarget();
		logger.debug(this.toString());
	}

	public LinkToProcess (String fullPath, String rootPath, long length) {
		super(fullPath,rootPath,length);
		this.target=this.buildTarget();
		logger.debug(this.toString());
	}
	
	private Path buildTarget() {
		File file = new File(this.fullPath);
		logger.debug("buildTarget");

		try {
			return Files.readSymbolicLink(file.toPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public String toString() {
		return   super.toString() + ":"
				+this.target.toString();
	}
	
	public boolean copyTo (String destinationFile) {
		logger.debug("(Link)From "+this.fullPath+ " To "+destinationFile);
		try {
			File source = new File(this.fullPath);
			Files.copy(source.toPath(),Paths.get(destinationFile));
			// reste à créer le lien avec la nouvelle cible au lieu de copier le fichier cible : besoin du relativepath
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

}
