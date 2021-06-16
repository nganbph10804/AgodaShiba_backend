package com.vn.jav.henllo.Model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter

@Data
@Entity
public class Bill implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bill_id;
    private int room_id;
    private String phone_user;
    @Temporal(TemporalType.TIMESTAMP)
    private Date time_in;
    @Temporal(TemporalType.TIMESTAMP)
    private Date time_out;
    private Double total_money;
    private Boolean confirm;
}
