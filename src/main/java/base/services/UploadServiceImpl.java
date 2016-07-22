package base.services;

import base.domain.Upload;
import base.repositories.UploadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Component("uploadService")
//@Service
@Transactional
public class UploadServiceImpl implements UploadService {

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

}
