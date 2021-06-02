package com.vn.jav.henllo.Model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter

@Data
@Entity
public class Hotels implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id_hotels;

    private String name;

    private String address;

    private int star_rate;

    private  int room_total;


    private String phone;

    private String img;
}
