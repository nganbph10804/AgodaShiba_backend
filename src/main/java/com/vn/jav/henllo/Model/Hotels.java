package com.vn.jav.henllo.Model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter

@Data
@Entity
public class Hotels implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id_hotels;
    @NotBlank
    private String name;
    @NotBlank
    private String address;
    @Max(value = 5)
    private int star_rate;

    private  int room_total;


    private String img;
}
