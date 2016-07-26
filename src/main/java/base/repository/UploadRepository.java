package base.repository;

import base.domain.Upload;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UploadRepository extends CrudRepository<Upload, Integer> {
    Upload findOne(Integer id);
    List<Upload> findAll();
    Upload save(Upload upload);
}
