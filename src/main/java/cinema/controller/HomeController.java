package cinema.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/home")
public class HomeController {
	
	@RequestMapping
	public String home(Model model){
		return "home";
	}
        
        @RequestMapping("/operationResult")
        public String operationResult(){
            return "/operationResult";
        }
}
