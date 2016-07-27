package base.repository;

import base.domain.Upload;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UploadRepository extends JpaRepository<Upload, Integer> {
    Upload findOne(Integer id);

    List<Upload> findAll();

    Upload save(Upload upload);

    void delete (Integer id);

    void deleteAll();

    boolean exists(Integer id);

}
