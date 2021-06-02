package com.vn.jav.henllo.Service;

import com.vn.jav.henllo.Model.Hotels;
import com.vn.jav.henllo.Repository.HotelsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class HotelsService {

    private final HotelsRepository hotelsRepository;

    @Autowired
    public HotelsService(HotelsRepository hotelsRepository){
        this.hotelsRepository = hotelsRepository;
    }

    public List<Hotels> getAllHotels(){
      return   hotelsRepository.findAll();
    }

    public List<Hotels> getHotelByUser(@PathVariable String phone){
        return hotelsRepository.findHotelByUser(phone);
    }
}
