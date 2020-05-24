package com.example.demo.repository;

import com.example.demo.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

@EnableJpaRepositories
public interface RoleRepository extends JpaRepository<Role, Long> {

    List<Role> findAll();

    //@Query("SELECT DISTINCT u FROM Role ORDER BY RoleName")
    Role getRoleById(Long id);

    /*Role findByRole(@Param("role") String role);

    Role findById(@Param("id") long id);*/
}
