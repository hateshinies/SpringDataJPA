package base.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class NavController {

    @RequestMapping("/")
    public String front() {
        return "uploadForm";
    }
}
