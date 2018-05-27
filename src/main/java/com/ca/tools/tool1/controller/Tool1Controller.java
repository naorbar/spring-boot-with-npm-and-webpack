package com.ca.tools.tool1.controller;

import java.util.Collections;
import java.util.Map;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ca.tools.tool1.dto.MyRequest;
import com.ca.tools.tool1.service.Tool1Service;

/**
 * <h3>
 * This tool is ...
 * </h3>
 * It gets as input ...
 * <ol>
 * 	<li>Aaaa</li>
 * 	<li>Bbbb</li>
 * </ol>
 * @author barna10
 */

@RestController
@RequestMapping("/tool1")
public class Tool1Controller {

	@Autowired
	private Tool1Service service;

	//@RequestMapping(value="/test", method=RequestMethod.GET)
	@RequestMapping(value="/test")
	@GetMapping
	public @ResponseBody Map<String, String> test() throws Exception {
		return Collections.singletonMap("response", "HELLO");
	}
		
	/**
	 * Generate all outputs and create a zip file ready to be downloaded by the client
	 * @Sample https://localhost:8443/mapper/zip
	 * @author barna10
	 */
	@RequestMapping(value="/zip", produces="application/zip", method=RequestMethod.POST)
	public void zipFiles(@RequestBody MyRequest request, HttpServletResponse response) throws Exception {
		ZipOutputStream zipOutputStream = null;
		try {
			response.setStatus(HttpServletResponse.SC_OK);
			response.addHeader("Content-Disposition", "attachment; filename=\"output.zip\"");
			zipOutputStream = new ZipOutputStream(response.getOutputStream());
			// Get the UpdateObjectRequest from the client and generate the output zip accordingly:
			zipOutputStream = this.service.generateOutputs(request , zipOutputStream);
		} catch (Exception e) {
			throw new Exception("Failed to create the zip file: " + e.getMessage(), e);
		} finally {
			zipOutputStream.close();
		}
	}

}
