package com.pioneercoders.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pioneercoders.exceptions.DBException;
import com.pioneercoders.model.Employee;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO {

	@Autowired
	private SessionFactory sessionFactory;

	private Logger logger = LoggerFactory.getLogger(EmployeeDAOImpl.class);

	public void addEmployee(Employee employee) throws DBException {
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(employee);
		} catch (Exception e) {
			if (logger.isErrorEnabled()) {
				logger.error("Exception Occred while creating Employee  Record ["
						+ employee.getEmail()
						+ "] ["
						+ employee.getAddress()
						+ "]");
				logger.error(e.getMessage());
			}
			throw new DBException(e.getMessage(), e);

		}
		if (logger.isDebugEnabled()) {
			logger.debug("Employee Record inserted with id ["
					+ employee.getId() + "]");
		}
	}

	@SuppressWarnings("unchecked")
	public List<Employee> getAllEmployees() throws DBException {
		try {
			Session session = sessionFactory.getCurrentSession();
			Query query = session.createQuery("from Employee");
			return query.list();
		} catch (Exception e) {
			if (logger.isErrorEnabled()) {
				logger.error("Exception occured while executing EmployeesList ");
				logger.error(e.getMessage());
			}
			throw new DBException(e.getMessage(), e);

		}
	}

	@Override
	public Integer deleteEmployee(Integer employeeId) throws DBException{
		int result=0;
		try{
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("delete from Employee where id="+employeeId);
		result = query.executeUpdate();
		}catch(Exception e){
			if(logger.isErrorEnabled()){
				logger.error("Exception occured while deleting EmployeeRecord by id ["+employeeId+"]");
				logger.error(e.getMessage());
			}
			throw new DBException(e.getMessage(), e);
		}

		if(logger.isDebugEnabled()){
			logger.debug("Employee record deleted successfully  with id ["+employeeId+"]" );
		}
		return result;

	}

	public Employee getEmployee(int empid) {
		return (Employee) sessionFactory.getCurrentSession().get(
				Employee.class, empid);
	}

	@Override
	public Employee updateEmployee(Employee employee) throws DBException {
		try {
			Session session = sessionFactory.getCurrentSession();
			session.update(employee);
			return employee;

		} catch (Exception e) {
			if (logger.isErrorEnabled()) {
				logger.error("Exception occured while executing Update Employee Methode  by  ["
						+ employee.getId() + "] [" + employee.getEmail() + "]");
				logger.error(e.getMessage());
			}
			throw new DBException(e.getMessage(), e);

		}// TODO Auto-generated method stub
	}

}