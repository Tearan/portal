package com.example.demo.repository;

import com.example.demo.bean.Advertisement;
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
public interface AdvertisementRepository extends CrudRepository<Advertisement, Long> {
    List<Advertisement> findByCreationDate(@Param("roleName")String date);
    List<Advertisement> findByAuthorId(@Param("author_id")String author_id);
    Advertisement findById(@Param("id")Long id);
    List<Advertisement> findAll();

}
