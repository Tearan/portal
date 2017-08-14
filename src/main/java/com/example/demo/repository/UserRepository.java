package com.example.demo.repository;

import com.example.demo.bean.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import javax.transaction.Transactional;

/**
 * Created by marta on 21.06.17.
 */
@Repository
@Transactional
public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(@Param("email") String email);
}
