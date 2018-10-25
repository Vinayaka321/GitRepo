package com.saartha.dom.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.saartha.dom.application.Employee;
import com.saartha.dom.application.Validation;

public class EmployeeDAO implements IEmployeeDAO {
	private static EmployeeDAO instance;
	private Connection connection;
	private PreparedStatement statement;
	private ResultSet resultSet;
	private EmployeeDAO() {
		String url = "jdbc:postgresql://localhost:5432/employee";
		String userName = "postgres";
		String password = "root";
		try {
			Class.forName("org.postgresql.Driver");
			this.connection = DriverManager.getConnection(url, userName, password);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static EmployeeDAO getInstance() {
		if(instance == null) {
			instance = new EmployeeDAO();
		}
		return instance;
	}

	@Override
	public boolean addEmployee(Employee employee) {
		// TODO Auto-generated method stub
		boolean inserted = false;
		PreparedStatement ps = null;
		try {
			String queryString = "insert into employee(name,salary,age) values(?,?,?)";
			ps= this.connection.prepareStatement(queryString);
			ps.setString(1, employee.getName());
			ps.setDouble(2, employee.getSalary());
			ps.setInt(3, employee.getAge());
			int insert = ps.executeUpdate();
			if(insert!=0) {
				inserted = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return inserted;
	}

	@Override
	public Employee getEmployee(int empId) {
		// TODO Auto-generated method stub
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		Employee employee = null;
		try {
			String queryString = "select * from employee where \"employeeId\"=?";
			preparedStatement= this.connection.prepareStatement(queryString);
			preparedStatement.setInt(1, empId);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				int id = resultSet.getInt("employeeId");
				String name = resultSet.getString("name");
				double salary = resultSet.getDouble("salary");
				int age = resultSet.getInt("age");
				employee = new Employee(name, salary, age);
				employee.setEmployeeId(empId);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				resultSet.close();
				preparedStatement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return employee;
	}

	@Override
	public List<Employee> getEmployees() {
		// TODO Auto-generated method stub
		ResultSet resultSet = null;
		PreparedStatement preparedStatement = null;
		List<Employee> employees = new ArrayList<Employee>();
		try {
			String queryString = "select * from employee";
			preparedStatement= this.connection.prepareStatement(queryString);
			resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				int id = resultSet.getInt("employeeId");
				String name = resultSet.getString("name");
				double salary = resultSet.getDouble("salary");
				int age = resultSet.getInt("age");
				Employee employee = new Employee(name, salary, age);
				employee.setEmployeeId(id);
				employees.add(employee);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				resultSet.close();
				preparedStatement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return employees;
	}
	@Override
	public boolean deleteEmployees(List<String> employeeIds) {
		// TODO Auto-generated method stub
		boolean isDeleted = false;
		if(employeeIds!=null && !employeeIds.isEmpty()) {
			PreparedStatement ps = null;
			for(String employeeId : employeeIds)
			{
			try {
				String queryString = "delete from employee where \"employeeId\" = ?";
				ps= this.connection.prepareStatement(queryString);
				System.out.println(Integer.parseInt(employeeId));
				ps.setInt(1, Integer.parseInt(employeeId));
				int deleted = ps.executeUpdate();
				if(deleted!=0) {
					isDeleted = true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally {
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			}
		}
		return isDeleted;
	}
	@Override
	public boolean updateEmployee(Employee employee) {
		// TODO Auto-generated method stub
		boolean isUpdated = false;
		if(Validation.validateEmployee(employee)) {
			PreparedStatement ps = null;
			try {
				String queryString = "update employee set name = ?,salary = ?,age = ? where \"employeeId\" = ?";
				ps= this.connection.prepareStatement(queryString);
				ps.setString(1, employee.getName());
				ps.setDouble(2, employee.getSalary());
				ps.setInt(3, employee.getAge());
				ps.setInt(4, employee.getEmployeeId());
				int update = ps.executeUpdate();
				if(update!=0) {
					isUpdated = true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally {
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return isUpdated;
	}

}
