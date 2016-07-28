package base.controller;

import base.domain.Upload;
import base.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;
import java.util.Date;

@Controller
public class UploadController {

    @Autowired
    private UploadService uploadService;

    @RequestMapping(method = RequestMethod.POST, value = "/upload")
    public String handleFileUpload(@RequestParam("name") String name, @RequestParam("file") MultipartFile file) {
        if (name.isEmpty() || name.equals(" ")) return "showFail";
        Upload upload = new Upload();
        upload.setName(name);
        upload.setFilename(file.getOriginalFilename());
        upload.setFilesize(file.getSize());
        upload.setDate(new Timestamp(new Date().getTime()));
        uploadService.saveUpload(upload);
        boolean isUploaded = uploadService.uploadFile(file, upload.getId());
        if (!isUploaded) {
            return "showFail";
        }
        return "redirect:/upload/" + upload.getId();
    }
}
