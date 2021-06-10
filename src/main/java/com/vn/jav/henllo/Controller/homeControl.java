package com.vn.jav.henllo.Controller;

import com.vn.jav.henllo.Model.*;
import com.vn.jav.henllo.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
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
    private final BillService billService;

    @Autowired
    public homeControl(UserService userService,
                       EmailService emailService,
                       RoomTypeService roomTypeService,
                       HotelsService hotelsRepository,
                       RoomService roomService,
                       BillService billService
                       ){
        this.userService =userService;
        this.emailService = emailService;
        this.roomTypeService = roomTypeService;
        this.hotelsService= hotelsRepository;
        this.roomService =roomService;
        this.billService = billService;
    }
    //For custom Query
    @GetMapping("/room/domain/{id}")
    public List<RoomDomain> getById(@PathVariable int id){
        List<RoomDomain> lst = new ArrayList<RoomDomain>();
      List<Room> lstRoom = roomService.getRoomsByHotel(id);
        for (int i=0; i<lstRoom.size();i++) {
        RoomDomain roomDM = new RoomDomain();
        roomDM.setRoom_name(lstRoom.get(i).getRoom_name());
        roomDM.setStatus(lstRoom.get(i).getStatus());
        roomDM.setId_room(lstRoom.get(i).getId_room());
        RoomType roomTypes = roomTypeService.getRoomTypeById(lstRoom.get(i).getRoom_type());

        roomDM.setType_name(roomTypes.getType_name());
        roomDM.setPrice(roomTypes.getPrice());
        roomDM.setBed(roomTypes.getBed());
        roomDM.setArea(roomTypes.getArea());
        lst.add(roomDM);

        }
        return lst;
    }
    @PutMapping("/update-total/{id}")
    public void updateRoom(@PathVariable int id){
        hotelsService.updateRoomtotal(id);
    }


    // For User Entity
    @GetMapping("/user")
    public List<Users> getAllUser(){
        return  userService.getAllUser();

    }
    @PostMapping("/user")
    public Users createUser(@Valid @RequestBody Users  user) {


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

    @PostMapping("/roomtype")
    public RoomType createRoomType(@RequestBody RoomType roomType){return roomTypeService.addRoomType(roomType);}
    @PutMapping("/roomtype/{id}")
    public ResponseEntity<RoomType> updateRoomType(@PathVariable int id, @RequestBody RoomType roomType){
        return roomTypeService.updateRoomType(id,roomType);
    }
    @DeleteMapping("/roomtype/{id}")
    public ResponseEntity<Map<String,Boolean>> deleteRoomType(@PathVariable int id){
        return roomTypeService.deleteRoomType(id);
    }



    // Hotels
    @GetMapping("/hotels")
    public List<Hotels> getAllHotels(){
        return  hotelsService.getAllHotels();
    }

    @GetMapping("/hotels/{id}")
    public  Hotels getHotelsById(@PathVariable int id){
        return hotelsService.getHotelById(id);
    }
    @PostMapping("/hotels")
    public Hotels createHotels(@Valid @RequestBody Hotels hotels){
        return  hotelsService.addHotels(hotels);
    }

    @PutMapping("/hotels/{id}")
    public ResponseEntity<Hotels> updateHotels(@PathVariable int id,@RequestBody Hotels hotels){
        return hotelsService.updateHotels(id,hotels);
    }
    @DeleteMapping("/hotels/{id}")
    public ResponseEntity<Map<String,Boolean>> deleteHotels(@PathVariable int id){
        return hotelsService.deleteHotel(id);
    }




    //Room
    @GetMapping("/room")
    public List<Room> getAllRoom(){
        return roomService.getAllRoom();
    }

    @GetMapping("/room/{id}")
    public List<Room> getRoomsByHotels(@PathVariable int id){
        return roomService.getRoomsByHotel(id);
    }

    @PostMapping("/room")
    public Room createRoom(@RequestBody Room room){return roomService.addRoom(room);}
    @PutMapping("/room/{id}")
    public ResponseEntity<Room> updateRoom(@PathVariable int id, @RequestBody Room room){
        return roomService.updateRoom(id,room);
    }
    @DeleteMapping("/room/{id}")
    public ResponseEntity<Map<String,Boolean>> deleteRoom(@PathVariable int id){
        return roomService.deleteRoom(id);
    }





    //Bill
    @GetMapping("/bill")
    public List<Bill> getAllBill(){
        return billService.getAllBill();
    }

    @PostMapping("/bill")
    public Bill createBill(@RequestBody Bill bill){
        return billService.addBill(bill);
    }
    @GetMapping("/bill/{phone}")
    public List<Bill> getBillByPhone(@PathVariable String phone){
        return billService.getBillByUser(phone);
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
