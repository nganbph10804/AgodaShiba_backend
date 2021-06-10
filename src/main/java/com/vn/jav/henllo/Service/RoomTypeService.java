package com.vn.jav.henllo.Service;

import com.vn.jav.henllo.Model.Room;
import com.vn.jav.henllo.Model.RoomType;
import com.vn.jav.henllo.Repository.RoomTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoomTypeService {

    private final RoomTypeRepository  roomTypeRes;

    @Autowired
    public RoomTypeService(RoomTypeRepository roomTypeRes){
        this.roomTypeRes = roomTypeRes;
    }

    public List<RoomType> getAllRoomType(){
        return roomTypeRes.findAll();
    }
    public RoomType getRoomTypeById(int id){

        return roomTypeRes.findById(id).orElseThrow(()-> new RuntimeException("not found"));


    }
    public RoomType addRoomType(@RequestBody RoomType roomType){
        return roomTypeRes.save(roomType);
    }
    public ResponseEntity<RoomType> updateRoomType(@PathVariable int id, @RequestBody RoomType roomType){
        RoomType roomType1 = roomTypeRes.findById(id).orElseThrow(()-> new RuntimeException("Room not found"));

        roomType1.setType_name(roomType.getType_name());
        roomType1.setArea(roomType.getArea());
        roomType1.setBed(roomType.getBed());
        roomType1.setPrice(roomType.getPrice());

        RoomType updateRoom = roomTypeRes.save(roomType1);

        return ResponseEntity.ok(updateRoom);
    }
    public ResponseEntity<Map<String,Boolean>> deleteRoomType(@PathVariable int id){
        RoomType roomType = roomTypeRes.findById(id).orElseThrow(()-> new RuntimeException("Room Not Found"));

        roomTypeRes.delete(roomType);
        Map<String,Boolean> resp = new HashMap<>();
        resp.put("Deleted",Boolean.TRUE);

        return ResponseEntity.ok(resp);
    }
}
