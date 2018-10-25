package com.saartha.dom.application;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saartha.dom.dao.EmployeeDAO;

/**
 * Servlet implementation class EmployeeControllerServlet
 */
@WebServlet("/EmployeeControllerServlet")
public class EmployeeControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EmployeeControllerServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String uri = request.getRequestURI();
		EmployeeDAO dao = EmployeeDAO.getInstance();
		RequestDispatcher rd= null;
		boolean isAdded = false;
		System.out.println(uri);
		if (uri.contains("/employeeSave")) {
			String name = request.getParameter("name");	
			String sal = request.getParameter("salary"); 
			String empAge = request.getParameter("age");
			int age = 0;
			double salary = 0;
			if(sal!=null && sal.trim()!="") {
				salary = Double.parseDouble(sal);
			}
			if(empAge!=null && empAge.trim()!="") {
				age = Integer.parseInt(empAge);
			}
			Employee employee=new Employee(name, salary, age);
			if(!Validation.validateEmployee(employee)) {
				rd = request.getRequestDispatcher("/views/HomePage.jsp");
				request.setAttribute("invalidEntry", "Invalid Entry");
			}
			else {
				isAdded = dao.addEmployee(employee);
				rd = request.getRequestDispatcher("/views/Result.jsp");
				request.setAttribute("name", name);
			}
			if(isAdded) {
				request.setAttribute("result", "Employee is added Successfully");
			}
			else {
				request.setAttribute("result", "Employee addition failed");
			}
			rd.forward(request, response);

		} else if (uri.contains("/employeeDelete")) {
			List<String> employeeIds = Arrays.asList(request.getParameterValues("employeeIds"));
			boolean isDeleted = dao.deleteEmployees(employeeIds);
			rd = request.getRequestDispatcher("/views/Result.jsp");
			if(isDeleted) {
				request.setAttribute("result", "Employee is deleted Successfully");
			}
			else {
				request.setAttribute("result", "Employee deletion failed");
			}
			rd.forward(request, response);
			
		} else if (uri.contains("/employeeGetAll")) {
			System.out.println("inside getall");
			List<Employee>employees = dao.getEmployees();
			request.setAttribute("employees", employees);
			rd = request.getRequestDispatcher("/views/Display.jsp");
			rd.forward(request, response);

		} else if (uri.contains("/employeeUpdate")) {
            System.out.println(request.getParameter("employeeId"));
            String empId = request.getParameter("employeeId");
            int employeeId = 0;
            if(empId!=null && empId.trim()!="") {
            	employeeId = Integer.parseInt(empId);
            }
            Employee employee = dao.getEmployee(employeeId);
            request.setAttribute("employee",employee );
            rd = request.getRequestDispatcher("/views/Update.jsp");
			rd.forward(request, response);
		} else if (uri.contains("/goToHome")) {
			rd = request.getRequestDispatcher("/views/HomePage.jsp");
			rd.forward(request, response);
		} else if (uri.contains("/updateSubmit")) {
			String empId = request.getParameter("employeeId");
			String name = request.getParameter("name");	
			String sal = request.getParameter("salary"); 
			String empAge = request.getParameter("age");
			int employeeId = Integer.parseInt(empId);
			boolean isUpdated =false;
			int age = 0;
			double salary = 0;
			if(sal!=null && sal.trim()!="") {
				salary = Double.parseDouble(sal);
			}
			if(empAge!=null && empAge.trim()!="") {
				age = Integer.parseInt(empAge);
			}
			Employee employee=new Employee(name, salary, age);
			employee.setEmployeeId(employeeId);
			if(!Validation.validateEmployee(employee)) {
				rd = request.getRequestDispatcher("/views/Update.jsp");
				request.setAttribute("invalidEntry", "Invalid Entry");
			}
			else {
				isUpdated = dao.updateEmployee(employee);
				rd = request.getRequestDispatcher("/views/Result.jsp");
				request.setAttribute("name", name);
			}
			if(isUpdated) {
				request.setAttribute("result", "Employee is Updated Successfully");
			}
			else {
				request.setAttribute("result", "Employee Updation failed");
			}
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
