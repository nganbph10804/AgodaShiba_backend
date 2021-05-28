package com.vn.jav.henllo.Repository;

import com.vn.jav.henllo.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users ,String> {
    @Query(value = "select  * from users u where u.email= ?1 and u.password=?2",nativeQuery = true)
    Users findUserByEmail(String email,String pass);

    @Query(value = "select  * from users u where u.email= ?1 ",nativeQuery = true)
    Users findUser(String email);
}
