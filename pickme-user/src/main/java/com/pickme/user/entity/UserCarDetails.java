package com.pickme.user.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCarDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long carId;

    @NotNull(message = "Registration number can't be empty")
    private String registrationNumber;

    @NotNull(message = "Model Name can't be empty")
    private String modelName;

    @NotNull(message = "Colour can't be empty")
    private String color;

    @NotNull(message = "Seat limit can't be empty")
    private long seatLimit;

    @NotNull
    private boolean isCarActive;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserDetails userDetails;
}
