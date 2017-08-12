package com.example.demo.repository;

import com.example.demo.bean.Attachment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by marta on 21.06.17.
 */
@Repository
public interface AttachmentRepository extends CrudRepository<Attachment, Long> {

}
