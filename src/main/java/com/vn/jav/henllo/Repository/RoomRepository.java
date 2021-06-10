package com.vn.jav.henllo.Repository;

import com.vn.jav.henllo.Model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room,Integer> {
    @Query(value = "select * from room where id_hotels=?1",nativeQuery = true)
    List<Room> getRoomsById_hotels(int id);




}
