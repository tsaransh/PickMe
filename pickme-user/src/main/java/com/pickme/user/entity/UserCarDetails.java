package com.pickme.user.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCarDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long carId;

    private String registerNumber;
    private String modelName;
    private String color;
    private long seatLimit;

    @ManyToOne
    @JoinColumn(name = "user_uid", nullable = false)
    private UserDetails userDetails;
}
