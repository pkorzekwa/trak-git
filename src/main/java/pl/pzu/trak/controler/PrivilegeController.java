package pl.pzu.trak.controler;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
	@RequestMapping(value = "/delete/{Id}", method = RequestMethod.GET)
	public String deletePrivilege(@PathVariable(value = "Id") Long Id)
	{
		privilegeService.remove(Id);;
		return "redirect:/privileges/all";
	}
	
	@RequestMapping(value = "/edit/{Id}", method = RequestMethod.GET)
	public String editPrivilege(Model model, @PathVariable(value = "Id") Long Id)
	{
		model.addAttribute("privilege", privilegeService.findOne(Id));
		return "user/upr/editPrivileges";
	}
	
	@RequestMapping(value = "/edit/{Id}", params = { "save" }, method = RequestMethod.POST)
	public String updatePrivilege(@Valid @ModelAttribute("privilege") Privilege privilege, BindingResult bindingResult,
			RedirectAttributes attributes, Model model)
	{
		if (bindingResult.hasErrors())
		{
			return "user/upr/privileges";
		} else
		{
			privilegeService.updatePrivilege(privilege.getId(), privilege.getName());
			return "redirect:/privileges/all";
		}
	}
}
