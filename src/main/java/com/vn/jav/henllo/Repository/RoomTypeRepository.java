package com.vn.jav.henllo.Repository;

import com.vn.jav.henllo.Model.RoomType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoomTypeRepository extends JpaRepository<RoomType,Integer> {

}
