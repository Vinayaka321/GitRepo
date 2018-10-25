package com.saartha.dom.application;

public class Validation {
	
	public static boolean validateEmployee(Employee employee) {
		boolean isValid = true;
		if(employee.getName()==null || employee.getName().trim()=="") {
			isValid = false;
		}
		if(employee.getSalary()<=0) {
			isValid = false;
		}
		if(employee.getAge()<18 || employee.getAge()>120) {
			isValid = false;
		}
		return isValid;
	}
	public static boolean validateString(String name) {
		boolean isValid = true;
		if(name==null || name.trim()=="") {
			isValid = false;
		}
		return isValid;
	}

}
