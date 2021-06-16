package com.vn.jav.henllo.Model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;


@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class RoomDomain {
    private int id_room;
    private String room_name;
    private String type_name;
    private Double price;
    private int bed;
    private Double area;
    private Boolean status;
    private String img;


}
