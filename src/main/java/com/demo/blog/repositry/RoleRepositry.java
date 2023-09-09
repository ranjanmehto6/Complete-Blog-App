package com.demo.blog.repositry;

import com.demo.blog.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepositry extends JpaRepository<Role,Long> {

    Optional<Role> findByName(String name);


}
