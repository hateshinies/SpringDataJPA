package base.controller;

import base.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.File;


@Controller
public class RemoveController {

    @Autowired
    private UploadService uploadService;

    @RequestMapping(method = RequestMethod.GET, value = "/remove/{id}")
    public String removeUpload(@PathVariable("id") Integer id) {
        boolean uploadExist = uploadService.exists(id);
        if (!uploadExist) return "uploadForm";
        uploadService.removeUpload(id);
        File upload = new File("upload-dir/" + id);
        if(upload.exists()) upload.delete();
        return "redirect:/uploads";
    }
}