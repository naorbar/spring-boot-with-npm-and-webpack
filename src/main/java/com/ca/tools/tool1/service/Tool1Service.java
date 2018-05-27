package com.ca.tools.tool1.service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.ca.tools.tool1.dto.MyRequest;

@Component
public class Tool1Service {

	private static final Logger log = LogManager.getLogger();
	
	/**
	 * This method gets a zipOutputStream and creates the files as new entries in this stream; the zip stream will be downloaded by the client.
	 * @author barna10
	 */
	public ZipOutputStream generateOutputs(MyRequest request, ZipOutputStream zipOutputStream) throws Exception {
		try {
			log.info("Generating the zip file output");
			
			// Write the outputFiles to a zip package:
			log.debug("Writing the entry 1 into a zip file");
			String targetFileName = "file1";
			String content = "This is a sample content";
			zipOutputStream.putNextEntry(new ZipEntry(targetFileName));
	        InputStream inputStream = new ByteArrayInputStream(content.getBytes());
	        IOUtils.copy(inputStream, zipOutputStream);
	        inputStream.close();
	        zipOutputStream.closeEntry();
			
	    	log.debug("Writing the entry 2 into a zip file");
			targetFileName = "file2.txt";
	        content = "This is a sample content";
	        zipOutputStream.putNextEntry(new ZipEntry(targetFileName));
	        inputStream = new ByteArrayInputStream(content.getBytes());
	        IOUtils.copy(inputStream, zipOutputStream);
	        inputStream.close();
	        zipOutputStream.closeEntry();
			
			return zipOutputStream;
		} catch (Exception e) {
			throw new Exception("Failed to generate the zip outputs: " + e.getMessage(), e);
		} 
	}
	
}
