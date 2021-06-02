package com.vn.jav.henllo.Repository;

import com.vn.jav.henllo.Model.Hotelsroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HotelsroomRepository extends JpaRepository<Hotelsroom,Long> {
    @Query(value = "select * from hotelsroom where hotels_id=?1",nativeQuery = true)
    List<Hotelsroom> getRoomByHotels(int hotelid);
}
