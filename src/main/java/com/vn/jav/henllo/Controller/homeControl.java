package com.vn.jav.henllo.Controller;

import com.vn.jav.henllo.Model.*;
import com.vn.jav.henllo.Repository.HotelsRepository;
import com.vn.jav.henllo.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(value ="*")
@RestController
@RequestMapping("/api/v1/")
public class homeControl {


    private final UserService userService;
    private final EmailService emailService;
    private final RoomTypeService roomTypeService;
    private final HotelsService hotelsService;
    private final RoomService roomService;
    private final HotelsRoomService hotelsRoomService;
    private final BillService billService;
    @Autowired
    public homeControl(UserService userService,
                       EmailService emailService,
                       RoomTypeService roomTypeService,
                       HotelsService hotelsRepository,
                       RoomService roomService,
                       HotelsRoomService hotelsRoomService,
                       BillService billService
                       ){
        this.userService =userService;
        this.emailService = emailService;
        this.roomTypeService = roomTypeService;
        this.hotelsService= hotelsRepository;
        this.roomService =roomService;
        this.hotelsRoomService =hotelsRoomService;
        this.billService = billService;
    }

    // For User Entity
    @GetMapping("/user")
    public List<Users> getAllUser(){

        return  userService.getAllUser();

    }
    @PostMapping("/user")
    public Users createUser(@Valid @RequestParam Users  user) {


        return userService.createUser(user);
    }
    @PostMapping("/user/upload/{phone}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Users> updateImg(@PathVariable String phone,@RequestParam MultipartFile image ) throws IOException{
        return userService.updateImage(phone,image);
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

    // RoomType
    @GetMapping("/roomtype")
    public List<RoomType> getAllRoomType(){
        return roomTypeService.getAllRoomType();
    }




    // Hotels
    @GetMapping("/hotels")
    public List<Hotels> getAllHotels(){
        return  hotelsService.getAllHotels();
    }

    @GetMapping("/hotels/{phone}")
    public  List<Hotels> getHotelsByUser(@PathVariable String phone){
        return hotelsService.getHotelByUser(phone);
    }



    //Room
    @GetMapping("/room")
    public List<Room> getAllRoom(){
        return roomService.getAllRoom();
    }

    //Hotelsroom
    @GetMapping("/hotelsroom")
    public List<Hotelsroom> getAllHotelsRoom(){
        return hotelsRoomService.getAllHotelsRoom();
    }
    @GetMapping("/hotelsroom/{id}")
    public List<Hotelsroom> getRoomByHotels(@PathVariable int id){
        return hotelsRoomService.getHotelsRoomByHotels(id);
    }


    //Bill
    @GetMapping("/bill")
    public List<Bill> getAllBill(){
        return billService.getAllBill();
    }





    // Bind list error validation
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }




}
