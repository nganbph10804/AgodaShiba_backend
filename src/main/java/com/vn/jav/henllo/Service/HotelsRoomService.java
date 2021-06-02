package com.vn.jav.henllo.Service;


import com.vn.jav.henllo.Model.Hotelsroom;
import com.vn.jav.henllo.Repository.HotelsroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class HotelsRoomService {

    private final HotelsroomRepository hotelsroomRes;

    @Autowired
    public HotelsRoomService(HotelsroomRepository hotelsroomRes){
        this.hotelsroomRes = hotelsroomRes;
    }

    public List<Hotelsroom> getAllHotelsRoom(){
        return hotelsroomRes.findAll();
    }
    public List<Hotelsroom> getHotelsRoomByHotels(@PathVariable int hotel){
        return hotelsroomRes.getRoomByHotels(hotel);

    }
}
