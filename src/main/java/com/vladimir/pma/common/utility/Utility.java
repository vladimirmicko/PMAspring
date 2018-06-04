package com.vladimir.pma.common.utility;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.web.context.request.RequestContextHolder;

import com.vladimir.pma.data.entity.UserAccount;


public class Utility {
	
	public static int getAgeInYears(Date birthdate) {
//		LocalDate startDate = LocalDate.of(2004, Month.DECEMBER, 25);
	    LocalDate startDate = birthdate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	    LocalDate endDate = LocalDate.now();
	    int numberOfYears = (int)ChronoUnit.YEARS.between(startDate, endDate);
	     
		return numberOfYears;
	}
	
	
}
