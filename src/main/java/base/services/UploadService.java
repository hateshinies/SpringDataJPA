package base.services;

import base.domain.Upload;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UploadService {

    Upload findUploadById(Integer id);

    List<Upload> findUploads();

    void saveUpload(Upload upload);

}
