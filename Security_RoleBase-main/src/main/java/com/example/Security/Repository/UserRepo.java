package com.example.Security.Repository;


import com.example.Security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepo extends JpaRepository<User, Long> {


    @Query("select u from User u left join fetch u.roles where u.email = ?1")
    User findByEmail(String email);

//    @Query("select u from")
//    User findByEmail(String username);
}
