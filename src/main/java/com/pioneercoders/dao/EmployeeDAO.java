package com.pioneercoders.dao;

import java.util.List;

import com.pioneercoders.exceptions.DBException;
import com.pioneercoders.model.Employee;

public interface EmployeeDAO {

	public void addEmployee(Employee employee) throws DBException;

	public List<Employee> getAllEmployees() throws DBException;

	public Integer deleteEmployee(Integer employeeId) throws DBException;

	public Employee updateEmployee(Employee employee) throws DBException;

	public Employee getEmployee(int employeeid)throws DBException;
}
