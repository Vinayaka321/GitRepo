package com.saartha.dom.dao;

import java.util.List;

import com.saartha.dom.application.Employee;

public interface IEmployeeDAO {
	public boolean addEmployee(Employee emp);
	public Employee getEmployee(int empId);
	public List<Employee> getEmployees();
	public boolean deleteEmployees(List<String> employeeIds);
	public boolean updateEmployee(Employee employee);
}
