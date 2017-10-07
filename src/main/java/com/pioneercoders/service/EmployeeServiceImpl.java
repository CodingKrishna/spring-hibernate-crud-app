package com.pioneercoders.service;

import java.util.List;

import org.dozer.DozerBeanMapperSingletonWrapper;
import org.dozer.MappingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pioneercoders.dao.EmployeeDAO;
import com.pioneercoders.dto.EmployeeDto;
import com.pioneercoders.exceptions.DBException;
import com.pioneercoders.exceptions.ServiceException;
import com.pioneercoders.model.Employee;
import com.pioneercoders.util.CollectionMapper;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeDAO employeeDAO;

	@Override
	@Transactional
	public void addEmployee(EmployeeDto employeeDto) throws ServiceException {
		try {
			Employee employee = DozerBeanMapperSingletonWrapper.getInstance()
					.map(employeeDto, Employee.class);
			employeeDAO.addEmployee(employee);
		} catch (DBException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	@Override
	@Transactional
	public List<EmployeeDto> getAllEmployees() throws ServiceException {
		try {
			List<Employee> entityList = employeeDAO.getAllEmployees();
			List<EmployeeDto> dtoList = CollectionMapper.getMappingList(
					entityList.iterator(), EmployeeDto.class);
			return dtoList;
		} catch (DBException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	@Override
	@Transactional
	public Integer deleteEmployee(Integer employeeId) throws ServiceException {
		int result = 0;
		try {

			result = employeeDAO.deleteEmployee(employeeId);

		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;

	}

	public EmployeeDto getEmployee(int empid) throws ServiceException {
		Employee employee = null;
		try {
			employee = employeeDAO.getEmployee(empid);
		} catch (MappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return DozerBeanMapperSingletonWrapper.getInstance().map(employee,
				EmployeeDto.class);
	}

	public EmployeeDto updateEmployee(EmployeeDto employeeDto)
			throws ServiceException {
		try {
			Employee employee = DozerBeanMapperSingletonWrapper.getInstance()
					.map(employeeDto, Employee.class);
			return DozerBeanMapperSingletonWrapper.getInstance().map(
					employeeDAO.updateEmployee(employee), EmployeeDto.class);
		} catch (DBException e) {
			throw new ServiceException(e.getMessage(), e);
		}
	}

	public void setEmployeeDAO(EmployeeDAO employeeDAO) throws ServiceException {
		this.employeeDAO = employeeDAO;
	}

}
