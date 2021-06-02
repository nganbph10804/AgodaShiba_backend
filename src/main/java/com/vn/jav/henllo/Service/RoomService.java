package com.vn.jav.henllo.Service;


import com.vn.jav.henllo.Model.Room;
import com.vn.jav.henllo.Repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
