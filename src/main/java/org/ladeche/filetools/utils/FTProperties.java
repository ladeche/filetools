package org.ladeche.filetools.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FTProperties extends Properties {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2172864957405769474L;

	public FTProperties(String fileName) {

		InputStream inputStream = getClass().getClassLoader()
				.getResourceAsStream(fileName);
		try {
			this.load(inputStream);
			if (inputStream == null) {
				throw new FileNotFoundException("property file '"
						+ fileName + "' not found in the classpath");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

}
