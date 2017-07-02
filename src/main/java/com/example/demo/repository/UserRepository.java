package com.example.demo.repository;

import com.example.demo.bean.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by marta on 21.06.17.
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(@Param("email") String email);
}
