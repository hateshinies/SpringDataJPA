package base.controller;

import base.domain.Upload;
import base.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

@Controller
public class UploadController {

    @Autowired
    private UploadService uploadService;
    private int currentId;

    @RequestMapping(method = RequestMethod.POST, value = "/upload")
    public String handleFileUpload(@RequestParam("name") String name, @RequestParam("file") MultipartFile file) throws IOException {
        Upload upload = new Upload();
        upload.setName(name);
        upload.setFilename(file.getOriginalFilename());
        upload.setFilesize(file.getSize());
        upload.setDate(new Timestamp(new Date().getTime()));
        uploadService.saveUpload(upload);
        currentId = upload.getId();
        boolean isUploaded = uploadService.uploadFile(file, currentId);
        if (!isUploaded) {
            return "showFail";
        }
        return "redirect:/success";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/success")
    public String showById(Model model) {
        Upload upload = uploadService.findUploadById(currentId);
        if (upload != null) {
            model.addAttribute("filename", upload.getFilename());
            model.addAttribute("filesize", upload.getFilesize());
            model.addAttribute("id", upload.getId());
            model.addAttribute("name", upload.getName());
            model.addAttribute("date", upload.getDate());
        }
        return "showOne";
    }


}
