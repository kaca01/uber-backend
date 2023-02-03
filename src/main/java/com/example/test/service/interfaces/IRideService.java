package com.example.test.service.interfaces;

import com.example.test.domain.ride.FavoriteOrder;
import com.example.test.domain.user.Passenger;
import com.example.test.domain.user.User;
import com.example.test.dto.AllDTO;
import com.example.test.dto.communication.PanicDTO;
import com.example.test.dto.communication.RejectionDTO;
import com.example.test.dto.ride.RideDTO;

import java.text.ParseException;

public interface IRideService {

    RideDTO insert(RideDTO vehicleType) throws ParseException;

    RideDTO findDriversActiveRide(Long id);

    RideDTO findPassengersActiveRide(Long id);

    RideDTO findOne(Long id);

    RideDTO cancelExistingRide(Long id);

    PanicDTO setPanic(PanicDTO reason, Long id, User sender);

    RideDTO acceptRide(Long id);

    RideDTO endRide(Long id);

    RideDTO cancelRide(RejectionDTO reason, Long id);

    RideDTO startRide(Long id);

    FavoriteOrder insertFavoriteOrder(FavoriteOrder favoriteOrder, String p);

    AllDTO<FavoriteOrder> getFavoriteOrdersByPassenger(Passenger p);

    void deleteFavoriteLocation(Long id, Passenger p);

    RideDTO getPendingRide(Long id);

    RideDTO getAcceptedRide(Long id);
}
