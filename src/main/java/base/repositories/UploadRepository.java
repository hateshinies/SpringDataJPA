package base.repositories;


import base.domain.Upload;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface UploadRepository extends CrudRepository<Upload, Integer>{
    Page<Upload> findAll(Pageable pageable);
}
