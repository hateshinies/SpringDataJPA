package base.repositories;

import base.domain.Upload;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UploadRepository extends CrudRepository<Upload, Integer> {
    Upload findOne(Integer id);
    List<Upload> findAll();
    Upload save(Upload upload);
}
