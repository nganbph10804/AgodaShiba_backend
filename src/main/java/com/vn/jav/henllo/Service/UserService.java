package com.vn.jav.henllo.Service;

import com.vn.jav.henllo.Model.Users;
import com.vn.jav.henllo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    private final UserRepository userRes;
    @Autowired
    public UserService(UserRepository userRes){
        this.userRes =userRes;
    }

    public List<Users> getAllUser(){
        return  userRes.findAll();

    }
    public Users createUser(@RequestBody Users user){
            return  userRes.save(user);
    }
    public ResponseEntity<Users> getUserbyPhone (@PathVariable String phone){
        Users us = userRes.findById(phone).orElseThrow(() -> new RuntimeException("User not found") );
        return ResponseEntity.ok(us);
    }
    public ResponseEntity<Users> getUserForRecover (@PathVariable String mail){
        Users us = userRes.findUser(mail);
        return ResponseEntity.ok(us);
    }
    public ResponseEntity<Users> getUserbyEmail (@PathVariable String mail,@PathVariable String pass){
        Users us = userRes.findUserByEmail(mail,pass);
        return ResponseEntity.ok(us);
    }

    public ResponseEntity<Users> updateUser (@PathVariable String phone, @RequestBody Users userDetails){
        Users us = userRes.findById(phone).orElseThrow(() -> new RuntimeException("User not found"));

        us.setEmail(userDetails.getEmail());
        us.setUsername(userDetails.getUsername());
        us.setPasswords(userDetails.getPasswords());

        Users updatedUser = userRes.save(us);

        return ResponseEntity.ok(updatedUser);
    }

    public ResponseEntity<Map<String ,Boolean>> deleteUser(@PathVariable String phone){
        Users us = userRes.findById(phone).orElseThrow(() -> new RuntimeException("User not found") );

        userRes.delete(us);
        Map<String,Boolean> response =new HashMap<>();
        response.put("deleted",Boolean.TRUE);

        return ResponseEntity.ok(response);
    }

}
