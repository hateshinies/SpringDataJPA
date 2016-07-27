package base.service;

import base.domain.Upload;
import base.repository.UploadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("uploadService")
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

}
