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


import pl.pzu.trak.domain.Syst;
import pl.pzu.trak.services.SystService;

@Controller
@RequestMapping(value = "/systems")
public class SystemController {

	@Autowired
	private SystService systService;
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public String findAll(Map<String, Object> model) {
		
		model.put("systList", systService.findAll());
		return "user/system/systems";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String addNewSystem(Model model) {
		
		Syst newSyst = new Syst();
		model.addAttribute("newSyst", newSyst);
		return "user/system/addSystem";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String processAddNewSystem(@ModelAttribute("newSyst") Syst syst, BindingResult bindingResult)
	{
		if (bindingResult.hasErrors())
		{
			return "addSystem";
		} else
		{
			systService.add(syst);
			return "redirect:/systems/all";
		}
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public String deleteSystem(@PathVariable(value = "id") Short systemId)
	{
		systService.remove(systemId);
		return "redirect:/systems/all";
	}
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public String editSystem(Model model, @PathVariable(value = "id") Short systemId)
	{
		model.addAttribute("system", systService.findOne(systemId));
		return "user/system/editSystem";
	}
	
	@RequestMapping(value = "/edit/{id}", params = { "save" }, method = RequestMethod.POST)
	public String updateSystem(@Valid @ModelAttribute("system") Syst syst, BindingResult bindingResult,
			RedirectAttributes attributes, Model model)
	{
		if (bindingResult.hasErrors())
		{
			return "user/upr/editSystem";
		} else
		{
			systService.updateSystem(syst.getSystemId(), syst.getSystemName());
			return "redirect:/systems/all";
		}
	}
}
