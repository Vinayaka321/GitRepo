<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.List,com.saartha.dom.application.Employee"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update Employee</title>
</head>
<body>
<%Employee employee = (Employee)request.getAttribute("employee");
if(employee==null){
	RequestDispatcher rd=request.getRequestDispatcher("/goToHome.do");
	rd.forward(request, response);
}
request.setAttribute("employeeId", employee.getEmployeeId());
%>
<div align="center">
<h2>Update Employee Details</h2>
<form action="updateSubmit.do" method="post">
<label><b>EmployeeId</b></label>
<input type="hidden" name="employeeId" value=<%=employee.getEmployeeId() %>></input>
<label><b>Name</b></label>
<br/>
<input type="text" name="name" value=<%=employee.getName() %>></input>
<br/>
<label><b>Salary</b></label>
<br/>
<input type="number" name="salary" value=<%=employee.getSalary()%>></input>
<br/>
<label><b>Age</b></label>
<br/>
<input type="number" name="age" value=<%=employee.getAge()%>></input>
<br/>
<input type="submit" value="Update"/>
</form>
</div>
</body>
</html>