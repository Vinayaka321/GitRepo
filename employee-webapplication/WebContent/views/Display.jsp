<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.List,com.saartha.dom.application.Employee"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employee Maintenance Application</title>
</head>
<body>
<div align="center">
<form action="employeeDelete.do" method="post">
<table border="2px">
<tr>
<th>
Id
</th>
<th>
Name
</th>
<th>
Salary(Rs)
</th>
<th>
Age(years)
</th>
<th>
Select
</th>
</tr>
<% List<Employee>employees = (List<Employee>)request.getAttribute("employees");
for(Employee emp : employees){%>
	<tr>
	<td>
	<%= emp.getEmployeeId()%>
	</td>
	<td>
	<%String href = "employeeUpdate.do?employeeId="+emp.getEmployeeId(); %>
	<a href=<%=href%> name="employeeId"><%= emp.getName()%></a>
	</td>
	<td>
	<%= emp.getSalary()%>
	</td>
	<td>
	<%= emp.getAge()%>
	</td>
	<td><input type="checkbox" name ="employeeIds" value=<%=emp.getEmployeeId() %> />&nbsp;</td>
	</tr>
<% }
%>
</table>
<input type="submit" value="Delete the Selected Employees"></input>
</form>
<br/>
<a href="goToHome.do">Click here to go back</a>
</div>
</body>
</html>