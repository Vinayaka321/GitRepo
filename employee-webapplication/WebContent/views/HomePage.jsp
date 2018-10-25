<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employee Maintenance Application</title>
</head>
<body>
<h2>Employee Maintenance Application</h2>
<div align="center">
<form action="employeeSave.do" method="post">
<label><b>Enter the Name of the Employee</b></label>
<input type="text" name="name">
<br/>
<label><b>Enter the Salary of the Employee(Rs)</b></label>
<input type="number" name="salary">
<br/>
<label><b>Enter the Age of the Employee(years)</b></label>
<input type="number" name="age">
<br/>
<input type="submit" value="Save">
</form>
<b style="color:red">${invalidEntry}</b>
<br/>
<br/>
<h3>Show the list of employees</h3>
<form action="employeeGetAll.do" method="post">
<input type="submit" value="List"/>
</form>
</div>
</body>
</html>