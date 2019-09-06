package pl.pzu.trak.controler;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import pl.pzu.trak.services.EmployeeContractsService;
import pl.pzu.trak.services.EmployeeService;
import pl.pzu.trak.services.EmployeeSystemsService;




@Controller
@RequestMapping(value = "/employee")
public class EmployeeController {
	
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private EmployeeContractsService employeeContractsService;
	
	@Autowired
	private EmployeeSystemsService employeeSystemsService;
	
//	@Autowired
//	private EmployeeTypeOfContractDictionaryService employeeTypeOfContractDictionaryService;
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public String findAll(Map<String, Object> model)
	{
		model.put("employeeList", employeeService.all());
		return "user/emp/employee";
	}
	
	@RequestMapping(value = "/contracts/{id_employee}", method = RequestMethod.GET)
	public String findContracts (Model model, @PathVariable(value = "id_employee") Long id_employee)
	{
		model.addAttribute("listContracts", employeeContractsService.findContracts(id_employee));
		model.addAttribute("employeeOne", employeeService.findOne(id_employee));
		return "/user/emp/contracts";
	}
	
	@RequestMapping(value = "/systems/{id_employee}", method = RequestMethod.GET)
	public String findSystems (Model model, @PathVariable(value = "id_employee") Long id_employee)
	{
		model.addAttribute("listSystems", employeeSystemsService.findSystems(id_employee));
		model.addAttribute("employeeOne", employeeService.findOne(id_employee));
		return "/user/emp/systems";
	}
}
