package base.service;

import base.domain.Upload;
import base.repository.UploadRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UploadServiceImpl implements UploadService {

//    @Autowired
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
