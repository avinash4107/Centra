package com.centraprise.hrmodule.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.centraprise.hrmodule.entity.EmployeeDetails;

public class EmployeeValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		if (clazz.isAssignableFrom(EmployeeDetails.class)) {
			return true;
		}
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {
		EmployeeDetails employeeDetails = (EmployeeDetails) target;

		if (employeeDetails.getPanNumber() != null && employeeDetails.getPanNumber().length() != 0) {
         // '/[A-Z]{5}\d{4}[A-Z]{1}/i'
		//	[A-Z]{5}[0-9]{4}[A-Z]{1}
			errors.rejectValue("", "employeedetails.pannumber.empty");
		}

	}

}
