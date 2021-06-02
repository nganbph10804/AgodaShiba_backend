package com.vn.jav.henllo.Repository;

import com.vn.jav.henllo.Model.Hotels;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HotelsRepository extends JpaRepository<Hotels,Long> {

    @Query(value = "select * from hotels where phone=?1",nativeQuery = true)
    List<Hotels> findHotelByUser(String userPhone);

}
