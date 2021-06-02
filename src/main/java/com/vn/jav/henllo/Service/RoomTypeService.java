package com.vn.jav.henllo.Service;

import com.vn.jav.henllo.Model.RoomType;
import com.vn.jav.henllo.Repository.RoomTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
