package base.controller;

import base.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class NavigationController {
    @Autowired
    private UploadService uploadService;

    @RequestMapping("/")
    public String uploadForm() {
        return "uploadForm";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/uploads")
    public String showAll(Model model) {
        if (uploadService.findUploads().isEmpty()) return "showEmp";
        model.addAttribute("allUploads", uploadService.findUploads());
        return "showAll";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/upload/{id}")
    public String showById(Model model, @PathVariable("id") Integer id) {
            model.addAttribute(uploadService.findUploadById(id));
        return "showOne";
    }
}
