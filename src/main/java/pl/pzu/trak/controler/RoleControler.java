package pl.pzu.trak.controler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pl.pzu.trak.domain.Privilege;
import pl.pzu.trak.domain.PrivilegeListDto;
import pl.pzu.trak.domain.Role;

import pl.pzu.trak.services.PrivilegeService;
import pl.pzu.trak.services.RoleService;


@Controller
@RequestMapping(value = "/roles")
public class RoleControler
{
	@Autowired
	private RoleService roleService;

	@Autowired
	private PrivilegeService privilegeService;


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
	public String updateRole(@Valid @ModelAttribute("role") Role role, BindingResult bindingResult,
			RedirectAttributes attributes, Model model)
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

	@RequestMapping(value = "/editprivileges/{id}", method = RequestMethod.GET)
	public String editRolePrivileges(Model model, @PathVariable(value = "id") Long id)
	{
		Role role = new Role();
		role = roleService.findOne(id);

		List<Privilege> privileges = new ArrayList<>();
		role.getPrivileges().iterator().forEachRemaining(privileges::add);
		model.addAttribute("privilegeForm", new PrivilegeListDto(privileges));
		
//		PrivilegeListDto privilegeForm = new PrivilegeListDto();
//		for (Privilege priv: role.getPrivileges())
//		{
//			privilegeForm.addPrivilege(priv);
//		}	
//		model.addAttribute("privilegeForm", privilegeForm);

		List<Privilege> allprivileges = privilegeService.ListAllPrivilegesRoleList(id);
		model.addAttribute("allprivileges", allprivileges);
	
		
		model.addAttribute("allprivilegesDictionary", privilegeService.findAll());
		model.addAttribute("role", roleService.findOne(id));
		
		
		return "user/upr/editRolePrivileges";
	}

/*	@RequestMapping(value = "/editprivileges/{id}", params = { "save" }, method = RequestMethod.POST)
	public String updateRolePrivileges(@Valid @ModelAttribute("privilegeForm") PrivilegeListDto privilegeForm,
			BindingResult bindingResult, RedirectAttributes attributes, Model model, @PathVariable(value = "id") Long roleId)
	{
		if (bindingResult.hasErrors())
		{
			return "user/upr/editRolePrivileges";
		} else
		{
			Role role = new Role();
			role = roleService.findOne(roleId);
			role.setPrivileges(new ArrayList<>());
			for (Privilege priv: privilegeForm.getPrivileges())
			{
				role.getPrivileges().add(priv);
				priv.setRoles(new ArrayList<>());
				priv.getRoles().add(role);
				//privilegeService.save(priv);
			}
			//roleService.save(role);
			
			System.out.println(role.getId());
			System.out.println(role.getPrivileges());
			System.out.println(role.getName());
			
			return "redirect:/roles/all";
		}
		
	}*/
	
	@RequestMapping(value = "/editprivileges/{id}", params = { "save" }, method = RequestMethod.POST)
	public String updateRolePrivileges(@Valid @ModelAttribute("privilegeForm") Role role,
			BindingResult bindingResult, RedirectAttributes attributes, Model model, @PathVariable(value = "id") Long roleId)
	{
		if (bindingResult.hasErrors())
		{
			return "user/upr/editRolePrivileges";
		} else
		{

			roleService.save(role);
			
			System.out.println(role.getId());
			System.out.println(role.getPrivileges());

			
			return "redirect:/roles/all";
		}
		
	}
	
	
	
	@RequestMapping(value = "/editprivileges/{id}", params = { "cancel" }, method = RequestMethod.POST)
	public String RolePrivilegesCancel(@Valid @ModelAttribute("privilegeForm") PrivilegeListDto privilegeForm, BindingResult bindingResult, RedirectAttributes attributes, Model model, @PathVariable(value = "id") Long roleId)
	{
		if (bindingResult.hasErrors())
		{
			return "/user/upr/editRolePrivileges";
		} else
		{		
			model.addAttribute("roleList", roleService.findAll());
			return "redirect:/roles/all";
		}
	}
	
	@RequestMapping(value = "/editprivileges/1/JsonServlet", method = RequestMethod.POST)
	public ResponseEntity<String> update(@RequestBody String elist) {

	        System.out.println(elist);
	 
	    return new ResponseEntity<String>(elist, HttpStatus.OK);
	}

	@RequestMapping(value = "/delete/{Id}", method = RequestMethod.GET)
	public String deletePrivilege(@PathVariable(value = "Id") Long Id)
	{
		roleService.remove(Id);;
		return "redirect:/roles/all";
	}
	
}
