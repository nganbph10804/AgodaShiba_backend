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

public class RoomType implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private Double price;
    private  Double area;
    private int bed;
    private String img;

}
