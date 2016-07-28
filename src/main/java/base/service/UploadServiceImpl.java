package base.service;

import base.domain.Upload;
import base.repository.UploadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Service("uploadService")
@Transactional
public class UploadServiceImpl implements UploadService {

    private final String CUR_ROOT = new File("").getAbsolutePath();
    private final String WIN_DIR = "\\upload-dir";
    private final String WIN_HOME = "";
    private final String UNIX_DIR = "/upload-dir/";
    private final String UNIX_HOME = "/home/user/";

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
    public boolean exists(Integer id) {
        return uploadRepository.exists(id);
    }

    public boolean uploadFile(MultipartFile file, int id) {
        if (file.isEmpty()) return false;
        File checkedFile = new File(CUR_ROOT + UNIX_DIR + id);
        if (checkedFile.exists()) checkedFile.delete();
        try {
            Files.copy(file.getInputStream(), Paths.get(CUR_ROOT + UNIX_DIR, String.valueOf(id)));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean downloadFile(Upload upload) {
        try {
            InputStream is = new FileInputStream(CUR_ROOT + UNIX_DIR + upload.getId());
            OutputStream out = new BufferedOutputStream(new FileOutputStream(new File(UNIX_HOME + upload.getFilename())));
            byte[] outputByte = new byte[1024];
            while (is.read(outputByte, 0, 1024) != -1) {
                out.write(outputByte, 0, 1024);
            }
            is.close();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
