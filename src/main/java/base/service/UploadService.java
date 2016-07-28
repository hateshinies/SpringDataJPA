package base.service;

import base.domain.Upload;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UploadService {

    Upload findUploadById(Integer id);

    List<Upload> findUploads();

    void saveUpload(Upload upload);

    void removeUpload(Integer id);

    boolean exists(Integer id);

    boolean uploadFile(MultipartFile file, int id);

    boolean downloadFile(Upload upload);
}
