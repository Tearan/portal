package com.example.demo.repository;

import com.example.demo.bean.Attachment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by marta on 21.06.17.
 */
@Repository
@Transactional
public interface AttachmentRepository extends CrudRepository<Attachment, Long> {

}
