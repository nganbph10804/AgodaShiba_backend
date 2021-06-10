package com.vn.jav.henllo.Model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter

@Data
@Entity
public class Room implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_room;
    private String room_name;
    private int room_type;
    private int id_hotels;
    private Boolean status;
}
