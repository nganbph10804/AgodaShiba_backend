package com.vn.jav.henllo.Controller;

import com.vn.jav.henllo.Model.Users;
import com.vn.jav.henllo.Service.EmailService;
import com.vn.jav.henllo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/")
public class homeControl {

    private final UserService userService;
    private final EmailService emailService;
    @Autowired
    public homeControl(UserService userService, EmailService emailService){
        this.userService =userService;
        this.emailService = emailService;
    }


    @GetMapping("/user")
    public List<Users> getAllUser(){

        return  userService.getAllUser();

    }
    @PostMapping("/user")
    public Users createUser(@RequestBody Users user){
            return userService.createUser(user);
    }

    @GetMapping("/user/{phone}")
    public ResponseEntity<Users> getUserById(@PathVariable String phone){
        return userService.getUserbyPhone(phone);
    }
    @GetMapping("/user/getmail/{mail}")
    public ResponseEntity<Users> getUserForRecover(@PathVariable String mail){
        return userService.getUserForRecover(mail);

    }
    @GetMapping("/user/get/{mail}/{pass}")
    public ResponseEntity<Users> getUserByEmail(@PathVariable String mail, @PathVariable String pass){
        return userService.getUserbyEmail(mail,pass);
    }

    @PutMapping("/user/{phone}")
    public ResponseEntity<Users> updateUser(@PathVariable String phone,@RequestBody Users Userdetails){
        return userService.updateUser(phone,Userdetails);
    }

    @DeleteMapping("/user/{phone}")
    public ResponseEntity<Map<String,Boolean>> deleteUser(@PathVariable String phone){
        return userService.deleteUser(phone);
    }
    // send mail

    @RequestMapping("/user/recovery/{to}/{pass}")
    public  void sendMailForRecovery(@PathVariable String to,@PathVariable String pass) throws MessagingException {
        emailService.sendMail(to,pass);

    }
}
