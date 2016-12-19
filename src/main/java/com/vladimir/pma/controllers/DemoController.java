package com.vladimir.pma.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vladimir.pma.data.dao.UserDao;

@RestController
@RequestMapping("/rest/demo")
public class DemoController {
	private static final Log log = LogFactory.getLog(DemoController.class);

	public DemoController() {
	}


	@RequestMapping(value = "/value/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getTestObject(@PathVariable(value = "id") int id) {

		log.info("----------------------");
		return new ResponseEntity<String>("Value:"+id, HttpStatus.OK);

	}


	@RequestMapping(value = "/header", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> testHeader(@RequestHeader(value = "test") String test,
			@RequestHeader(value = "Accept-Language") String acceptLanguage,
			@RequestHeader(value = "User-Agent", defaultValue = "foo") String userAgent) {

		return new ResponseEntity<String>(
				"Header example test: " + test + " Accept-Language: " + acceptLanguage + " User-Agent: " + userAgent,
				HttpStatus.OK);
	}


	@RequestMapping(value = "/displayFile", method = RequestMethod.GET, produces = "application/pdf")
	public ResponseEntity<InputStreamResource> displayPdfFile(@RequestParam(required = true) String fileName)
			throws IOException {

		InputStream inputStream = new FileInputStream("G:/" + fileName);
		File file = new File("G:/" + fileName);

		return ResponseEntity.ok().contentLength((int) file.length())
				.header("Content-Disposition", String.format("inline; filename=\"" + fileName + "\""))
				.header("Content-Type", "application/octet-stream")
				.contentType(MediaType.parseMediaType("application/pdf"))
				.body(new InputStreamResource(inputStream));
	}


	@RequestMapping(value = "/downloadFile", method = RequestMethod.GET, produces = "application/pdf")
	public ResponseEntity<InputStreamResource> displayFileInternal(@RequestParam(required = true) String fileName)
			throws IOException {

		ClassPathResource file = new ClassPathResource(fileName);

		return ResponseEntity.ok().contentLength((int) file.contentLength())
				.header("Content-Disposition", String.format("attachment; filename=\"" + fileName + "\""))
				.header("Content-Type", "application/octet-stream")
				.contentType(MediaType.parseMediaType("application/pdf"))

				.body(new InputStreamResource(file.getInputStream()));
	}
}
