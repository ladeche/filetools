package org.ladeche.filetools;

import java.util.ArrayList;

import org.ladeche.filetools.beans.DirToProcess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FTTui {
    static final Logger logger = LoggerFactory.getLogger(FTGui.class);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		logger.debug("Start");
		logger.debug("*** ANALYSE 1 ***");

		DirToProcess dirToProcess = new DirToProcess("/home/poseidon/Documents/Scolaire");
		ArrayList arrayList = dirToProcess.listDirectoryContentFull();
		Integer size = (Integer)dirToProcess.getChildren().size();

		logger.debug("*** COPIE 2 ***");
		DirToProcess dirToProcess2 = new DirToProcess("/media/MBL-Public/test");
		dirToProcess.copyTo(dirToProcess2.getFullPath());
		
		logger.debug("*** ANALYSE 2 ***");
		ArrayList arrayList2 = dirToProcess2.listDirectoryContentFull();
		Integer size2 = (Integer)dirToProcess2.getChildren().size();
		
		
		logger.debug(size.toString());
		logger.debug(size2.toString());
		logger.debug(dirToProcess.getSize().toString());
		logger.debug(dirToProcess2.getSize().toString());
		
	}

}
