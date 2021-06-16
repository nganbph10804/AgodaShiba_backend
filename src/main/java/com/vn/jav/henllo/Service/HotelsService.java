package com.vn.jav.henllo.Service;

import com.vn.jav.henllo.Model.Hotels;
import com.vn.jav.henllo.Repository.HotelsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HotelsService {

    private static final Path CURRENT_FOLDER = Paths.get(System.getProperty("user.dir"));
    private final HotelsRepository hotelsRepository;

    @Autowired
    public HotelsService(HotelsRepository hotelsRepository){
        this.hotelsRepository = hotelsRepository;
    }

    public Hotels addHotels(@Valid  @RequestBody Hotels hotels){
        return hotelsRepository.save(hotels);
    }

    public ResponseEntity<Hotels> updateHotels(@PathVariable int id, @RequestBody Hotels hotels){
        Hotels oldHt = hotelsRepository.findById(id).orElseThrow(()->new RuntimeException("Hotel not found"));

        oldHt.setName(hotels.getName());
        oldHt.setAddress(hotels.getAddress());
        oldHt.setStar_rate(hotels.getStar_rate());
        oldHt.setRoom_total(hotels.getRoom_total());

        Hotels newHt = hotelsRepository.save(oldHt);

        return ResponseEntity.ok(newHt);

    }
    public ResponseEntity<Map<String,Boolean>> deleteHotel(@PathVariable int id){
        Hotels hotels = hotelsRepository.findById(id).orElseThrow(()-> new RuntimeException("Room Not Found"));

        hotelsRepository.delete(hotels);
        Map<String,Boolean> resp = new HashMap<>();
        resp.put("Deleted",Boolean.TRUE);

        return ResponseEntity.ok(resp);
    }

    public List<Hotels> getAllHotels(){
      return   hotelsRepository.findAll();
    }

    public Hotels getHotelById(@PathVariable int id){
        Hotels ht = hotelsRepository.findById(id).orElseThrow(()-> new RuntimeException("Room Not Found"));

        return ht;

    }
    public void updateRoomtotal(int id){
         hotelsRepository.updateRoomTotal(id,id);
    }
}
