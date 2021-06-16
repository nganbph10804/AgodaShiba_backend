package com.vn.jav.henllo.Service;

import com.vn.jav.henllo.Model.Users;
import com.vn.jav.henllo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.data.domain.Example;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService  {

    // get current folder
    private static final Path CURRENT_FOLDER = Paths.get(System.getProperty("user.dir"));
    private final UserRepository userRes;
    @Autowired
    public UserService(UserRepository userRes){
        this.userRes =userRes;
    }

    public List<Users> getAllUser(){
        return  userRes.findAll();

    }
    public Users createUser(@Valid  @RequestBody  Users user) {

        if(userRes.existsById(user.getPhone())){
            throw new RuntimeException(" User already exist!");

        }
        return  userRes.save(user);
    }
    // Update image
    public ResponseEntity<Users> updateImage(@PathVariable String phone ,@RequestParam MultipartFile image  ) throws IOException{
        Users us = userRes.findById(phone).orElseThrow(() -> new RuntimeException("User not found"));
        Path staticPath = Paths.get("static");
        Path imagePath = Paths.get("images");
        if (!Files.exists(CURRENT_FOLDER.resolve(staticPath).resolve(imagePath))) {
            Files.createDirectories(CURRENT_FOLDER.resolve(staticPath).resolve(imagePath));
        }
        Path file = CURRENT_FOLDER.resolve(staticPath)
                .resolve(imagePath).resolve(image.getOriginalFilename());
        try (OutputStream os = Files.newOutputStream(file)) {
            os.write(image.getBytes());
        }
        us.setImg(imagePath.resolve(image.getOriginalFilename()).toString());

//        us.setImg(UploadImage.UploadFile(image));


        Users updatedUser = userRes.save(us);

        return ResponseEntity.ok(updatedUser);
    }

    public ResponseEntity<Users> getUserbyPhone (@PathVariable String phone){
        Users us = userRes.findById(phone).orElseThrow(() -> new RuntimeException("User not found") );
        return ResponseEntity.ok(us);
    }
    // get user by mail
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
