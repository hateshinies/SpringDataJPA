package base.controllers;

import base.domain.Upload;
import base.repositories.UploadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.*;

@Controller
public class DownloadController {
    @Autowired
    private UploadRepository uploadRepository;

    private String uploadId = null;

    @RequestMapping(method = RequestMethod.GET, value = "/uploads")
    public String showAll(Model model) {
        model.addAttribute("allUploads", uploadRepository.findAll());
        return "showAll";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/download/{id}") //value = "/download/{id:[\\d]+}")
    public String handleFileDownload(@PathVariable("id") Integer id) throws IOException {
        try {
            Upload upload = uploadRepository.findOne(id);
            String filename = upload.getFilename();
            InputStream is = new FileInputStream(new File("upload-dir/" + id));
            OutputStream out = new BufferedOutputStream(new FileOutputStream(new File("/home/user/" + filename)));
            byte[] outputByte = new byte[1024];
            while (is.read(outputByte, 0, 1024) != -1) {
                out.write(outputByte, 0, 1024);
            }
            is.close();
            out.close();

        } catch (FileNotFoundException e) {
            return "Such file not found at  server\n" + e.getMessage();
        }
        return "redirect:/uploads";
    }
}
