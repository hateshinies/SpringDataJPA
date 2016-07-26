package base.service;

import base.domain.Upload;

import java.util.List;

public interface UploadService {

    Upload findUploadById(Integer id);

    List<Upload> findUploads();

    void saveUpload(Upload upload);

}
