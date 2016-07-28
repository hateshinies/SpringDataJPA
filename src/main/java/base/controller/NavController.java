package base.controller;

import base.domain.Upload;
import base.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class NavController {
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
        Upload upload = uploadService.findUploadById(id);
        if (upload != null)
            model.addAttribute(upload);
        return "showOne";
    }
}
