package com.example.demo.repository;

import com.example.demo.bean.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by marta on 16.07.17.
 */
@Repository
@Transactional
public interface RoleRepository extends CrudRepository<Role, Long> {
    public Role getByName(@Param("roleName")String roleName);
}
