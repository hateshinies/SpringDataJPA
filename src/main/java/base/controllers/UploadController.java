package base.controllers;

import base.domain.Upload;
import base.services.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

@Controller
public class UploadController {

    @Autowired
    private UploadService uploadService;
    private int currentId;

    @RequestMapping(method = RequestMethod.POST, value = "/upload")
    public String handleFileUpload(@RequestParam("name") String name,
                                   @RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                String filename = file.getOriginalFilename();
                long filesize = file.getSize();
                Timestamp ts = new Timestamp(new Date().getTime());
                Upload upload = new Upload();
                upload.setName(name);
                upload.setFilename(filename);
                upload.setFilesize(filesize);
                upload.setDate(ts);
                uploadService.saveUpload(upload);
                currentId = upload.getId();
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File("upload-dir/" + currentId)));
                stream.write(bytes);
                stream.close();
                return "redirect:/success";
            } catch (IOException e) {
                return "File is too large!\n" + e.getMessage();
            }
        }
        return "front";
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
