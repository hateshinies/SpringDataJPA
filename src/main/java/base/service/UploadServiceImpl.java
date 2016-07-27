package base.service;

import base.domain.Upload;
import base.repository.UploadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Service("uploadService")
@Transactional
public class UploadServiceImpl implements UploadService {

    private final String ROOT = new File("").getAbsolutePath() + "\\upload-dir";

    @Autowired
    private UploadRepository uploadRepository;

    @Override
    public Upload findUploadById(Integer id) {
        return uploadRepository.findOne(id);
    }

    @Override
    public List<Upload> findUploads() {
        return uploadRepository.findAll();
    }

    @Override
    public void saveUpload(Upload upload) {
        uploadRepository.save(upload);
    }

    @Override
    public void removeUpload(Integer id) {
        uploadRepository.delete(id);
    }

    @Override
    public void removeUploads() {
        uploadRepository.deleteAll();
    }

    @Override
    public boolean exists(Integer id) {
        return uploadRepository.exists(id);
    }

    @Override
    public boolean uploadFile(MultipartFile file, int id) {
        if (file.isEmpty()) return false;
        try {
            Files.copy(file.getInputStream(), Paths.get(ROOT, String.valueOf(id)));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean downloadFile(Upload upload, HttpServletResponse response) {
        try {
            InputStream is = new FileInputStream(ROOT + upload.getId());
            org.apache.commons.io.IOUtils.copy(is, response.getOutputStream());
            response.flushBuffer();
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
