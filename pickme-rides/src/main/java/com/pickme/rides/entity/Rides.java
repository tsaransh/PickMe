package com.pickme.rides.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Rides {

    @Id
    private String rideId;

    private String rideRegisterByUserId;

    @Column(name = "starting_point", nullable = false)
    private String startingPoint;

    @Column(name = "ending_point", nullable = false)
    private String endingPoint;

    private Date rideOnDate;

    @CreatedDate
    private Date rideRegisterDate;

    @LastModifiedDate
    private Date lastUpdateDate;

    private String carRegistrationNumber;

    private double rideAmount;

    @ElementCollection
    @CollectionTable(name = "ride_booked_users", joinColumns = @JoinColumn(name = "ride_id"))
    @Column(name = "booked_by_user_id")
    private List<String> bookedByUserId = new ArrayList<>();

    private String rideStatus;

    @PrePersist
    private void ensureId() {
        if (this.rideId == null) {
            this.rideId = generateRideId();
        }
    }

    private String generateRideId() {
        return UUID.randomUUID().toString();
    }
}
