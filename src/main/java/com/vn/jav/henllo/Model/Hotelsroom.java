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
public class Hotelsroom implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private int room_id;
    private int hotels_id;
    private Boolean status;
}
