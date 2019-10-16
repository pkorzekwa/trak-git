package pl.pzu.trak.controler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import pl.pzu.trak.services.CsvToolsService;


@Controller
public class HomeController {

	@Autowired
	private CsvToolsService csvToolsService;	
	
	
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

    @GetMapping("/csv")
    @ResponseBody
    public String csv() {
    	return csvToolsService.LoadFileCsv("c:\\TEMP\\20191010_023404_001_PC_OBS_aktywnosci_20191009_20191009.csv");    	
    }

}
