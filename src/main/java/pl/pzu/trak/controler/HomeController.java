package pl.pzu.trak.controler;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import pl.pzu.trak.domain.User;
import pl.pzu.trak.services.UserService;

@Controller
public class HomeController {
	
	@Autowired
	private UserService userService;

	@GetMapping("/")
    public String root() {
        return "user/index";
    }

    @GetMapping("/user")
    public String userIndex() {
        return "user/index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/access-denied")
    public String accessDenied() {
        return "/error/access-denied";
    }
    
    @GetMapping("/userlist")
	public String showUserListDetail(Map<String, Object> model)
	{
		model.put("userList", userService.findAll());
		return "userlist";
	}
    
      
    
	@RequestMapping(value = "user/edit/{id}", method = RequestMethod.GET)
	public String editUser(Model model, @PathVariable(value = "id") Long id)
	{
		model.addAttribute("editUser", userService.findOne(id));
		return "editUser";
	}
	
	
	@RequestMapping(value = "/edit/{id}", params = { "save" }, method = RequestMethod.POST)
	public String updateUser(@Valid @ModelAttribute("kontrakt") User user, BindingResult bindingResult, RedirectAttributes attributes, Model model)
	{
		if (bindingResult.hasErrors())
		{
			return "editUser";
		} else
		{
			userService.save(user);
			return "redirect:/userlist";
		}
	}	
}
