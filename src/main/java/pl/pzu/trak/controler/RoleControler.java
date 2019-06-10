package pl.pzu.trak.controler;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.pzu.trak.domain.Role;
import pl.pzu.trak.services.RoleService;

@Controller
@RequestMapping(value = "/roles")
public class RoleControler
{
	@Autowired
	private RoleService roleService;

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public String findAll(Map<String, Object> model)
	{
		model.put("roleList", roleService.findAll());
		return "user/upr/roles";
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String getAddNewRoleForm(Model model)
	{
		Role newRole = new Role();
		model.addAttribute("newRole", newRole);
		return "user/upr/addRole";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String processAddNewRole(@ModelAttribute("newRole") Role role, BindingResult bindingResult)
	{
		if (bindingResult.hasErrors())
		{
			return "addRole";
		} else
		{
			roleService.add(role);
			return "redirect:/roles/all";
		}
	}	
}
