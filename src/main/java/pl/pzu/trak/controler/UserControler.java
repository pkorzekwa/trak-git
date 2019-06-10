package pl.pzu.trak.controler;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import pl.pzu.trak.services.UserService;

@Controller
@RequestMapping(value = "/users")
public class UserControler
{
	@Autowired
	private UserService userService;

    @GetMapping("/all")
	public String showUserListDetail(Map<String, Object> model)
	{
		model.put("userList", userService.findAll());
		return "userlist";
	}	

}
