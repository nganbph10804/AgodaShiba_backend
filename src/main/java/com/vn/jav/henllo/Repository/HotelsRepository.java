package com.vn.jav.henllo.Repository;

import com.vn.jav.henllo.Model.Hotels;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface HotelsRepository extends JpaRepository<Hotels,Integer> {
    @Modifying
    @Transactional
    @Query(value = "UPDATE hotels SET room_total = (SELECT COUNT(id_room) FROM room where id_hotels=?1) where id_hotels=?2",nativeQuery = true)
    void updateRoomTotal(int id1,int id2);

}
