package com.pioneercoders.service;

import java.util.List;

import com.pioneercoders.dto.EmployeeDto;
import com.pioneercoders.exceptions.ServiceException;

public interface EmployeeService {
	
	public void addEmployee(EmployeeDto employee) throws ServiceException ;

	public List<EmployeeDto> getAllEmployees() throws ServiceException ;

	public Integer deleteEmployee(Integer employeeId) throws ServiceException ;

	public EmployeeDto getEmployee(int employeeid) throws ServiceException ;

	public EmployeeDto updateEmployee(EmployeeDto employee)throws ServiceException ;
}
