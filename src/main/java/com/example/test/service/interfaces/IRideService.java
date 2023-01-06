package com.example.test.service.interfaces;

import com.example.test.domain.ride.FavoriteOrder;
import com.example.test.domain.user.Passenger;
import com.example.test.dto.AllDTO;
import com.example.test.dto.communication.PanicDTO;
import com.example.test.dto.ride.RideDTO;

public interface IRideService {

    RideDTO insert(RideDTO vehicleType);

    RideDTO findDriversActiveRide(Long id);

    RideDTO findPassengersActiveRide(Long id);

    RideDTO findOne(Long id);

    RideDTO cancelExistingRide(Long id);

    PanicDTO setPanic(PanicDTO reason, Long id);

    RideDTO acceptRide(Long id);

    RideDTO endRide(Long id);

    RideDTO cancelRide(PanicDTO reason, Long id);

    RideDTO startRide(Long id);

    FavoriteOrder insertFavoriteOrder(FavoriteOrder favoriteOrder, Passenger p);

    AllDTO<FavoriteOrder> getFavoriteOrdersByPassenger(Passenger p);

    boolean deleteFavoriteLocation(Long id, Passenger p);
}
