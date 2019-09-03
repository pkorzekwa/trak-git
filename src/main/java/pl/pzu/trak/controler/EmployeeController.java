package pl.pzu.trak.controler;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.pzu.trak.services.EmployeeService;



@Controller
@RequestMapping(value = "/employee")
public class EmployeeController {
	
	
	@Autowired
	private EmployeeService employeeService;
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public String findAll(Map<String, Object> model)
	{
		model.put("employeeList", employeeService.all());
		return "user/emp/employee";
	}
}
