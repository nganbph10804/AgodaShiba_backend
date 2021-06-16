package com.vn.jav.henllo.Service;


import com.vn.jav.henllo.Model.Hotels;
import com.vn.jav.henllo.Model.Room;
import com.vn.jav.henllo.Repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RoomService {

    private final RoomRepository roomRes;

    @Autowired
    public RoomService(RoomRepository roomRes){
        this.roomRes=roomRes;
    }
    public List<Room> getAllRoom(){
        return roomRes.findAll();
    }

    public List<Room> getRoomsByHotel(@PathVariable int id){
        return roomRes.getRoomsById_hotels(id);
    }

    public Room addRoom(@RequestBody Room room){
        return roomRes.save(room);
    }
    public ResponseEntity<Room> updateRoom(@PathVariable int id, @RequestBody Room room){
        Room oldRoom = roomRes.findById(id).orElseThrow(()-> new RuntimeException("Room not Found"));
        oldRoom.setRoom_name(room.getRoom_name());
        oldRoom.setRoom_type(room.getRoom_type());
        
        Room newRoom = roomRes.save(oldRoom);

        return   ResponseEntity.ok(newRoom);

    }
    public ResponseEntity<Map<String,Boolean>> deleteRoom(@PathVariable int id){
        Room room = roomRes.findById(id).orElseThrow(()-> new RuntimeException("Room Not Found"));

        roomRes.delete(room);
        Map<String,Boolean> resp = new HashMap<>();
        resp.put("Deleted",Boolean.TRUE);

        return ResponseEntity.ok(resp);
    }
    public void updateRented(int id){
        roomRes.updateRoomRented(id);
    }
    public void updateRestore(int id){
        roomRes.updateRoomRestore(id);
    }
}
