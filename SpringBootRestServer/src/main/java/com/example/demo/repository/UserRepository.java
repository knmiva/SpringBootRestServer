package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("UserRepository")
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("FROM User us JOIN FETCH us.roles where us.username = :username")
    User findUserByUsername(@Param("username") String username);

    /*@Query("FROM User us JOIN FETCH us.roles")*/
    List<User> findAll();

    //@Query("FROM User u JOIN FETCH u.roles WHERE u.username= :username")
//    User findUserByUsername(@Param("username") String username);

    /*@Query("FROM User u JOIN FETCH u.roles")*/
    //List<User> getAllUsers();
}
