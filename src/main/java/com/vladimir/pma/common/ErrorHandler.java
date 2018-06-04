package com.vladimir.pma.common;

import java.nio.charset.StandardCharsets;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.vladimir.pma.common.constants.ErrorConstants;
import com.vladimir.pma.data.dto.RestResponseDto;




@ControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private ApplicationContext applicationContext;
	
	private static final String APPLICATION = "application";
	private static Log log = LogFactory.getLog(ErrorHandler.class);

	@ExceptionHandler(Exception.class)
	protected ResponseEntity<RestResponseDto> handleRuntimeException(Exception ex, HttpServletRequest req){
			HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;				
			HttpHeaders headers = createHeader();
			log.error("Exception message: "+ex);
			ex.printStackTrace();
			RestResponseDto restResponseDto = new RestResponseDto(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.toString(), ex.getMessage());
			return new ResponseEntity<RestResponseDto>(restResponseDto, headers, httpStatus);
	}
	
	
	private HttpHeaders createHeader() {
		HttpHeaders headers = new HttpHeaders();
		MediaType mediaType = new MediaType(APPLICATION, "json", StandardCharsets.UTF_8);
		headers.setContentType(mediaType);
		return headers;
	}
}
