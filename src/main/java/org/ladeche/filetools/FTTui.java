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
		DirToProcess dirToProcess = new DirToProcess("/home/poseidon/Documents");
		ArrayList arrayList = dirToProcess.listDirectoryContent();
		Integer size = (Integer)dirToProcess.getChildren().size();

		DirToProcess dirToProcess2 = new DirToProcess("/media/MBL/documents");
		ArrayList arrayList2 = dirToProcess2.listDirectoryContent();
		Integer size2 = (Integer)dirToProcess2.getChildren().size();
		
		
		logger.debug(size.toString());
		logger.debug(size2.toString());
		logger.debug(dirToProcess.getSize().toString());
		logger.debug(dirToProcess2.getSize().toString());
		
	}

}
