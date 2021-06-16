package com.vn.jav.henllo.Repository;

import com.vn.jav.henllo.Model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room,Integer> {
    @Query(value = "select * from room where id_hotels=?1",nativeQuery = true)
    List<Room> getRoomsById_hotels(int id);

    @Modifying
    @Transactional
    @Query(value = "update room set status=1 where id_room=?1",nativeQuery = true)
    void updateRoomRented(int id);


    @Modifying
    @Transactional
    @Query(value = "update room set status=0 where id_room=?1",nativeQuery = true)
    void updateRoomRestore(int id);



}
