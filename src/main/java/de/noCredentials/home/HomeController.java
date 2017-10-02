package de.noCredentials.home;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;

@Controller
class HomeController {

    @GetMapping("/")
    String index(Model model) {
        model.addAttribute("now", LocalDateTime.now());
        return "index";
    }
/*
    @GetMapping("/base")
    String base(Model model) {
        model.addAttribute("now", LocalDateTime.now());
        return "base";
    }


        @GetMapping("/register")
        String base(Model model) {
            model.addAttribute("now", LocalDateTime.now());
            return "register";
        }
*/
    @GetMapping("/greeting")
    String greeting(Model model) {
        model.addAttribute("now", LocalDateTime.now());
        return "greeting";
    }

    @GetMapping("properties")
    @ResponseBody
    java.util.Properties properties() {
        return System.getProperties();
    }

}
