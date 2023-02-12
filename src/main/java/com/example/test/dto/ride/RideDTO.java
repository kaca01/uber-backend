package com.example.test.dto.ride;

import com.example.test.domain.ride.Ride;
import com.example.test.domain.ride.Route;
import com.example.test.domain.user.Passenger;
import com.example.test.dto.user.UserDTO;
import com.example.test.dto.communication.RejectionDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@NoArgsConstructor
@Data
public class RideDTO {
    private Long id;
    private String startTime;
    private String endTime;
    private boolean isPanic;
    @Min(0)
    private double totalCost;
    @NotNull
    @NotEmpty
    private Set<Route> locations;
    @NotNull
    private List<UserDTO> passengers;
    @NotEmpty
    @NotNull
    private String vehicleType;
    @NotNull
    private boolean babyTransport;
    @NotNull
    private boolean petTransport;
    @Min(0)
    private double estimatedTimeInMinutes;
    private String status;
    private UserDTO driver;
    private RejectionDTO rejection;
    private String scheduledTime;

    public RideDTO(Ride ride) {
        this.id = ride.getId();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        if(ride.getStartTime() != null) this.startTime = format.format(ride.getStartTime());
        if(ride.getEndTime() != null) this.endTime = format.format(ride.getEndTime());
        this.totalCost = ride.getTotalCost();
        this.locations = ride.getLocations();
        this.passengers = convertPassengersToUsersDTO(ride);
        if (ride.getVehicle() != null) this.vehicleType = ride.getVehicle().getType().getName().toString();
        this.babyTransport = ride.isBabyTransport();
        this.petTransport = ride.isPetTransport();
        this.estimatedTimeInMinutes = ride.getEstimatedTimeInMinutes();
        this.status = ride.getStatus().toString();
        this.isPanic = ride.isPanic();
        this.scheduledTime = format.format(ride.getScheduledTime());
        if(ride.getDriver() != null) this.driver = new UserDTO(ride.getDriver());
        if (ride.getRejection() != null) this.rejection = new RejectionDTO(ride.getRejection());
    }

    // request
    public RideDTO(Set<Route> locations, List<UserDTO> passengers, String vehicleType,
                   boolean babyTransport, boolean petTransport, String scheduledTime) {
        this.locations = locations;
        this.passengers = passengers;
        this.vehicleType = vehicleType;
        this.babyTransport = babyTransport;
        this.isPanic = false;
        this.petTransport = petTransport;
        this.scheduledTime = scheduledTime;
    }

    // response
    public RideDTO(Long id, String startTime, String endTime, double totalCost, Set<Route> locations,
                   List<UserDTO> passengers, String vehicleType, boolean babyTransport,
                   boolean petTransport, double estimatedTimeInMinutes, String status, UserDTO driver,
                   RejectionDTO rejection, String scheduledTime) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.totalCost = totalCost;
        this.locations = locations;
        this.passengers = passengers;
        this.vehicleType = vehicleType;
        this.babyTransport = babyTransport;
        this.petTransport = petTransport;
        this.estimatedTimeInMinutes = estimatedTimeInMinutes;
        this.status = status;
        this.driver = driver;
        this.rejection = rejection;
        this.scheduledTime = scheduledTime;
        this.isPanic = false;
    }

    private List<UserDTO> convertPassengersToUsersDTO(Ride ride) {
        List<UserDTO> users = new ArrayList<>();
        for (Passenger passenger: ride.getPassengers()) {
            users.add(new UserDTO(passenger.getId(), passenger.getEmail()));
        }
        return users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RideDTO)) return false;

        RideDTO rideDTO = (RideDTO) o;

        if (isPanic != rideDTO.isPanic) return false;
        if (Double.compare(rideDTO.totalCost, totalCost) != 0) return false;
        if (babyTransport != rideDTO.babyTransport) return false;
        if (petTransport != rideDTO.petTransport) return false;
        if (Double.compare(rideDTO.estimatedTimeInMinutes, estimatedTimeInMinutes) != 0) return false;
        if (!Objects.equals(id, rideDTO.id)) return false;
        if (!Objects.equals(startTime, rideDTO.startTime)) return false;
        if (!Objects.equals(endTime, rideDTO.endTime)) return false;
        if (!locations.equals(rideDTO.locations)) return false;
        if (!Objects.equals(passengers, rideDTO.passengers)) return false;
        if (!vehicleType.equals(rideDTO.vehicleType)) return false;
        if (!Objects.equals(status, rideDTO.status)) return false;
        if (!Objects.equals(driver, rideDTO.driver)) return false;
        if (!Objects.equals(rejection, rideDTO.rejection)) return false;
        return scheduledTime.equals(rideDTO.scheduledTime);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id != null ? id.hashCode() : 0;
        result = 31 * result + (startTime != null ? startTime.hashCode() : 0);
        result = 31 * result + (endTime != null ? endTime.hashCode() : 0);
        result = 31 * result + (isPanic ? 1 : 0);
        temp = Double.doubleToLongBits(totalCost);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + locations.hashCode();
        result = 31 * result + (passengers != null ? passengers.hashCode() : 0);
        result = 31 * result + vehicleType.hashCode();
        result = 31 * result + (babyTransport ? 1 : 0);
        result = 31 * result + (petTransport ? 1 : 0);
        temp = Double.doubleToLongBits(estimatedTimeInMinutes);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (driver != null ? driver.hashCode() : 0);
        result = 31 * result + (rejection != null ? rejection.hashCode() : 0);
        result = 31 * result + scheduledTime.hashCode();
        return result;
    }
}