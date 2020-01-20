package pl.pzu.trak.controler;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import pl.pzu.trak.domain.Privilege;
import pl.pzu.trak.services.PrivilegeService;

@Controller
@RequestMapping(value = "/privileges")
public class PrivilegeController {

	@Autowired
	private PrivilegeService privilegeService;
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public String findAll(Map<String, Object> model)
	{
		model.put("privilegeListDictionary", privilegeService.findAll());
		return "user/upr/privileges";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String getAddNewPrivilegeForm(Model model)
	{
		Privilege newPrivilege = new Privilege();
		model.addAttribute("newPrivilege", newPrivilege);
		return "user/upr/addPrivilege";
	}
	
	@RequestMapping(value = "/add", params = { "save" }, method = RequestMethod.POST)
	public String processAddNewPrivilege(@ModelAttribute("newPrivilege") Privilege privilege, BindingResult bindingResult)
	{
		if (bindingResult.hasErrors())
		{
			return "addPrivilege";
		} else
		{
			privilegeService.add(privilege);
			return "redirect:/privileges/all";
		}
	}
	
	
}
