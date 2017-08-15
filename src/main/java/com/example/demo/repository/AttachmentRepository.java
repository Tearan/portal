package com.example.demo.repository;

import com.example.demo.bean.Attachment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by marta on 21.06.17.
 */
@Repository
@Transactional
public interface AttachmentRepository extends CrudRepository<Attachment, Long> {
    List<Attachment> findByAdvertisement_Id(@Param("advertisement_id") String advertisement_id);
}
