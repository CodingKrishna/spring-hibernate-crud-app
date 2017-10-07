package com.pioneercoders.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.pioneercoders.dto.DetailsDto;
import com.pioneercoders.dto.EmployeeDto;
import com.pioneercoders.exceptions.ServiceException;
import com.pioneercoders.model.Employee;
import com.pioneercoders.service.EmployeeService;

@Controller
public class EmployeeController {

	private static final Logger logger = Logger
			.getLogger(EmployeeController.class);

	public EmployeeController() {
		System.out.println("EmployeeController()");
	}

	@Autowired
	private EmployeeService employeeService;

	@RequestMapping(value = "/")
	public ModelAndView listEmployee(ModelAndView model) throws IOException {
		List<EmployeeDto> listEmployee;
		try {
			listEmployee = employeeService.getAllEmployees();
			model.addObject("listEmployee", listEmployee);
			model.setViewName("home");
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return model;
	}

	@RequestMapping(value = "/newEmployee", method = RequestMethod.GET)
	public ModelAndView newContact(ModelAndView model) {
		Employee employee = new Employee();
		model.addObject("employee", employee);
		model.setViewName("EmployeeForm");
		return model;
	}

	@RequestMapping(value = "/saveEmployee", method = RequestMethod.POST)
	public ModelAndView saveEmployee(@ModelAttribute EmployeeDto employee) {
		try {
			if (employee.getId() == 0) { // if employee id is 0 then creating
											// the
				// employee other updating the employee
				employeeService.addEmployee(employee);
			} else {
				employeeService.updateEmployee(employee);
			}
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new ModelAndView("redirect:/");
	}

	@RequestMapping(value = "/deleteEmployee", method = RequestMethod.GET)
	public ModelAndView deleteEmployee(HttpServletRequest request) {

		try {
			int employeeId = Integer.parseInt(request.getParameter("id"));
			employeeService.deleteEmployee(employeeId);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ModelAndView("redirect:/");
	}

	@RequestMapping(value = "/editEmployee", method = RequestMethod.GET)
	public ModelAndView editContact(HttpServletRequest request) {

		EmployeeDto employee = null;
		try {
			int employeeId = Integer.parseInt(request.getParameter("id"));
			employee = employeeService.getEmployee(employeeId);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ModelAndView model = new ModelAndView("EmployeeForm");
		model.addObject("employee", employee);

		return model;
	}
	@RequestMapping(value="data", method=RequestMethod.POST)
	@ResponseBody
	public DetailsDto getString(@RequestBody DetailsDto detailsDto) {
			System.out.println(detailsDto);
			
		return detailsDto;
		
	}
	

}