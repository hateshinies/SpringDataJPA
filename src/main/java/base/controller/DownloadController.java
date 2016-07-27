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
public class DownloadController {

    @Autowired
    private UploadService uploadService;

    @RequestMapping(method = RequestMethod.GET, value = "/uploads")
    public String showAll(Model model) {
        if (uploadService.findUploads().isEmpty()) return "showEmp";
        model.addAttribute("allUploads", uploadService.findUploads());
        return "showAll";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/download/{id}")
    public String handleFileDownload(@PathVariable("id") Integer id) {
        Upload upload = uploadService.findUploadById(id);
        if (!uploadService.downloadFile(upload, )) return "showFail";
        return "redirect:/uploads";
    }
}
