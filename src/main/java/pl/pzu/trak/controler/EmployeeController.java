package pl.pzu.trak.controler;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.pzu.trak.domain.EmployeeContract;
import pl.pzu.trak.services.EmployeeCompanyDictionaryService;
import pl.pzu.trak.services.EmployeeContractsService;
import pl.pzu.trak.services.EmployeeService;
import pl.pzu.trak.services.EmployeeSystemsService;
import pl.pzu.trak.services.EmployeeTypeOfContractDictionaryService;




@Controller
@RequestMapping(value = "/employee")
public class EmployeeController {
	
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private EmployeeContractsService employeeContractsService;
	
	@Autowired
	private EmployeeSystemsService employeeSystemsService;
	
	@Autowired
	private EmployeeCompanyDictionaryService employeeCompanyDictionaryService;
	
	@Autowired
	private EmployeeTypeOfContractDictionaryService employeeTypeOfContractDictionaryService;
	
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
	
	@RequestMapping(value = "/contracts/add", method = RequestMethod.GET)
	public String getAddContract (Model model)
	{
		
	//	List<EmployeeCompanyDictionary> company = employeeCompanyDictionaryService.findAll();
		EmployeeContract newContract = new EmployeeContract();
		model.addAttribute("newContract", newContract);
		
		model.addAttribute("companyList", employeeCompanyDictionaryService.findAll());
		model.addAttribute("typeOfContractList", employeeTypeOfContractDictionaryService.findAll());
		
		return "/user/emp/addContract";
	}
	
	@RequestMapping(value = "/contracts/add", method = RequestMethod.POST)
	public String postAddContract (@ModelAttribute("newContract") EmployeeContract employeeContract, BindingResult bindingResult)
	{
		if (bindingResult.hasErrors())
		{
			return "redirect:/employee/all";
		} else
		{
			employeeContractsService.add(employeeContract);
			return "redirect:/employee/all";
		}
			
		
	}
}
