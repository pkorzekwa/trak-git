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
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editRole(Model model, @PathVariable(value = "id") Long id)
	{
		model.addAttribute("role", roleService.findOne(id));
		return "user/upr/editRole";
	}


	@RequestMapping(value = "/edit/{id}", params = { "save" }, method = RequestMethod.POST)
	public String updateRole(@Valid @ModelAttribute("role") Role role, BindingResult bindingResult, RedirectAttributes attributes, Model model)
	{
		if (bindingResult.hasErrors())
		{
			return "user/upr/editRole";
		} else
		{
			
			roleService.updateRole(role.getId(), role.getName());
			return "redirect:/roles/all";
		}
	}	
}
