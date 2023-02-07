package com.example.test.service;

import com.example.test.domain.communication.Rejection;
import com.example.test.domain.communication.Review;
import com.example.test.domain.ride.FavoriteOrder;
import com.example.test.domain.ride.Ride;
import com.example.test.domain.ride.Route;
import com.example.test.domain.user.Passenger;
import com.example.test.dto.communication.RejectionDTO;
import com.example.test.dto.ride.RideDTO;
import com.example.test.enumeration.RideStatus;
import com.example.test.enumeration.VehicleTypeName;
import com.example.test.exception.BadRequestException;
import com.example.test.exception.NotFoundException;
import com.example.test.repository.communication.IRejectionRepository;
import com.example.test.repository.ride.IFavoriteOrderRepository;
import com.example.test.repository.ride.IRideRepository;
import com.example.test.repository.user.IPassengerRepository;
import com.example.test.service.implementation.RideService;

import com.example.test.service.interfaces.ISelectionDriver;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

import org.assertj.core.api.Assertions;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
public class RideServiceTest {


    @Mock
    IRideRepository rideRepository;

    @Mock
    IFavoriteOrderRepository favoriteOrderRepository;

    @Mock
    IPassengerRepository passengerRepository;

    @Mock
    ISelectionDriver selectionDriver;

    @Autowired
    @InjectMocks
    RideService rideService;

    @Test
    @DisplayName("Should find ride by id")
    public void shouldFindRideById() {

        Ride ride = new Ride(123L, new Date(), new Date(), 450, 45, null, null,
                new ArrayList<Passenger>(), RideStatus.PENDING, null, false, false,
                new HashSet<Route>(), null, new Date());

        RideDTO expectedRide = new RideDTO(ride);

        Mockito.when(rideRepository.findById(123L)).thenReturn(Optional.of(ride));

        RideDTO actualRide = rideService.findOne(123L);

        verify(rideRepository, times(1)).findById(123L);

        Assertions.assertThat(actualRide.getId()).isEqualTo(expectedRide.getId());
        Assertions.assertThat(actualRide.getStartTime()).isEqualTo(expectedRide.getStartTime());
        Assertions.assertThat(actualRide.getEndTime()).isEqualTo(expectedRide.getEndTime());
        Assertions.assertThat(actualRide.getScheduledTime()).isEqualTo(expectedRide.getScheduledTime());
        Assertions.assertThat(actualRide.getTotalCost()).isEqualTo(expectedRide.getTotalCost());
        Assertions.assertThat(actualRide.getLocations()).isEqualTo(expectedRide.getLocations());
        Assertions.assertThat(actualRide.getPassengers()).isEqualTo(expectedRide.getPassengers());
        Assertions.assertThat(actualRide.getVehicleType()).isEqualTo(expectedRide.getVehicleType());
        Assertions.assertThat(actualRide.isBabyTransport()).isEqualTo(expectedRide.isBabyTransport());
        Assertions.assertThat(actualRide.isPetTransport()).isEqualTo(expectedRide.isPetTransport());
        Assertions.assertThat(actualRide.getEstimatedTimeInMinutes()).isEqualTo(expectedRide.getEstimatedTimeInMinutes());
        Assertions.assertThat(actualRide.getStatus()).isEqualTo(expectedRide.getStatus());
        Assertions.assertThat(actualRide.isPanic()).isEqualTo(expectedRide.isPanic());
        Assertions.assertThat(actualRide.getDriver()).isEqualTo(expectedRide.getDriver());
        Assertions.assertThat(actualRide.getRejection()).isEqualTo(expectedRide.getRejection());
    }

    @Test
    @DisplayName("Should not find ride by id. Also should throw exception Not Found")
    public void shouldNotFindRideById() {
        Mockito.when(rideRepository.findById(123L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> rideService.findOne(123L));

        verify(rideRepository, times(1)).findById(123L);
    }

    @Test
    @DisplayName("Should find drivers active ride")
    public void shouldFindDriversActiveRide() {
        Ride ride = new Ride(123L, new Date(), new Date(), 450, 45, null, null,
                new ArrayList<Passenger>(), RideStatus.ACTIVE, null, false, false,
                new HashSet<Route>(), null, new Date());
        RideDTO expectedRide = new RideDTO(ride);
        Mockito.when(rideRepository.findByStatusAndDriver_id(RideStatus.ACTIVE, 123L)).thenReturn(Optional.of(ride));

        RideDTO actualRide = rideService.findDriversActiveRide(123L);
        Assertions.assertThat(actualRide.getId()).isEqualTo(expectedRide.getId());
        Assertions.assertThat(actualRide.getStartTime()).isEqualTo(expectedRide.getStartTime());
        Assertions.assertThat(actualRide.getEndTime()).isEqualTo(expectedRide.getEndTime());
        Assertions.assertThat(actualRide.getScheduledTime()).isEqualTo(expectedRide.getScheduledTime());
        Assertions.assertThat(actualRide.getTotalCost()).isEqualTo(expectedRide.getTotalCost());
        Assertions.assertThat(actualRide.getLocations()).isEqualTo(expectedRide.getLocations());
        Assertions.assertThat(actualRide.getPassengers()).isEqualTo(expectedRide.getPassengers());
        Assertions.assertThat(actualRide.getVehicleType()).isEqualTo(expectedRide.getVehicleType());
        Assertions.assertThat(actualRide.isBabyTransport()).isEqualTo(expectedRide.isBabyTransport());
        Assertions.assertThat(actualRide.isPetTransport()).isEqualTo(expectedRide.isPetTransport());
        Assertions.assertThat(actualRide.getEstimatedTimeInMinutes()).isEqualTo(expectedRide.getEstimatedTimeInMinutes());
        Assertions.assertThat(actualRide.getStatus()).isEqualTo(expectedRide.getStatus());
        Assertions.assertThat(actualRide.isPanic()).isEqualTo(expectedRide.isPanic());
        Assertions.assertThat(actualRide.getDriver()).isEqualTo(expectedRide.getDriver());
        Assertions.assertThat(actualRide.getRejection()).isEqualTo(expectedRide.getRejection());

        verify(rideRepository, times(1)).findByStatusAndDriver_id(RideStatus.ACTIVE, 123L);
    }

    @Test
    @DisplayName("Should not find driver active ride")
    public void shouldNotFindDriverActiveRide() {
        Mockito.when(rideRepository.findByStatusAndDriver_id(RideStatus.ACTIVE, 123L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> rideService.findDriversActiveRide(123L));

        verify(rideRepository, times(1)).findByStatusAndDriver_id(RideStatus.ACTIVE, 123L);
    }

    @Test
    @DisplayName("Should find drivers accepted ride")
    public void shouldFindDriversAcceptedRide() {
        Ride ride = new Ride(123L, new Date(), new Date(), 450, 45, null, null,
                new ArrayList<Passenger>(), RideStatus.ACCEPTED, null, false, false,
                new HashSet<Route>(), null, new Date());
        RideDTO expectedRide = new RideDTO(ride);
        Mockito.when(rideRepository.findByStatusAndDriver_id(RideStatus.ACCEPTED, 123L)).thenReturn(Optional.of(ride));

        RideDTO actualRide = rideService.findDriversAcceptedRide(123L);
        Assertions.assertThat(actualRide.getId()).isEqualTo(expectedRide.getId());
        Assertions.assertThat(actualRide.getStartTime()).isEqualTo(expectedRide.getStartTime());
        Assertions.assertThat(actualRide.getEndTime()).isEqualTo(expectedRide.getEndTime());
        Assertions.assertThat(actualRide.getScheduledTime()).isEqualTo(expectedRide.getScheduledTime());
        Assertions.assertThat(actualRide.getTotalCost()).isEqualTo(expectedRide.getTotalCost());
        Assertions.assertThat(actualRide.getLocations()).isEqualTo(expectedRide.getLocations());
        Assertions.assertThat(actualRide.getPassengers()).isEqualTo(expectedRide.getPassengers());
        Assertions.assertThat(actualRide.getVehicleType()).isEqualTo(expectedRide.getVehicleType());
        Assertions.assertThat(actualRide.isBabyTransport()).isEqualTo(expectedRide.isBabyTransport());
        Assertions.assertThat(actualRide.isPetTransport()).isEqualTo(expectedRide.isPetTransport());
        Assertions.assertThat(actualRide.getEstimatedTimeInMinutes()).isEqualTo(expectedRide.getEstimatedTimeInMinutes());
        Assertions.assertThat(actualRide.getStatus()).isEqualTo(expectedRide.getStatus());
        Assertions.assertThat(actualRide.isPanic()).isEqualTo(expectedRide.isPanic());
        Assertions.assertThat(actualRide.getDriver()).isEqualTo(expectedRide.getDriver());
        Assertions.assertThat(actualRide.getRejection()).isEqualTo(expectedRide.getRejection());

        verify(rideRepository, times(1)).findByStatusAndDriver_id(RideStatus.ACCEPTED, 123L);
    }

    @Test
    @DisplayName("Should not find driver accepted ride")
    public void shouldNotFindAcceptedRide() {
        Mockito.when(rideRepository.findByStatusAndDriver_id(RideStatus.ACCEPTED, 123L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> rideService.findDriversAcceptedRide(123L));

        verify(rideRepository, times(1)).findByStatusAndDriver_id(RideStatus.ACCEPTED, 123L);
    }

    @Test
    @DisplayName("Should start ride")
    public void shouldStartRide() {
        Ride ride = new Ride(123L, new Date(), new Date(), 450, 45, null, null,
                new ArrayList<Passenger>(), RideStatus.ACCEPTED, null, false, false,
                new HashSet<Route>(), new HashSet<Review>(), new Date());

        Mockito.when(rideRepository.findById(123L)).thenReturn(Optional.of(ride));
        Mockito.when(rideRepository.save(ride)).thenReturn(ride);

        RideDTO actualRide = rideService.startRide(123L);
        ride.setStatus(RideStatus.ACTIVE);

        RideDTO expectedRide = new RideDTO(ride);
        Assertions.assertThat(actualRide.getId()).isEqualTo(expectedRide.getId());
        Assertions.assertThat(actualRide.getStartTime()).isEqualTo(expectedRide.getStartTime());
        Assertions.assertThat(actualRide.getEndTime()).isEqualTo(expectedRide.getEndTime());
        Assertions.assertThat(actualRide.getScheduledTime()).isEqualTo(expectedRide.getScheduledTime());
        Assertions.assertThat(actualRide.getTotalCost()).isEqualTo(expectedRide.getTotalCost());
        Assertions.assertThat(actualRide.getLocations()).isEqualTo(expectedRide.getLocations());
        Assertions.assertThat(actualRide.getPassengers()).isEqualTo(expectedRide.getPassengers());
        Assertions.assertThat(actualRide.getVehicleType()).isEqualTo(expectedRide.getVehicleType());
        Assertions.assertThat(actualRide.isBabyTransport()).isEqualTo(expectedRide.isBabyTransport());
        Assertions.assertThat(actualRide.isPetTransport()).isEqualTo(expectedRide.isPetTransport());
        Assertions.assertThat(actualRide.getEstimatedTimeInMinutes()).isEqualTo(expectedRide.getEstimatedTimeInMinutes());
        Assertions.assertThat(actualRide.getStatus()).isEqualTo(expectedRide.getStatus());
        Assertions.assertThat(actualRide.isPanic()).isEqualTo(expectedRide.isPanic());
        Assertions.assertThat(actualRide.getDriver()).isEqualTo(expectedRide.getDriver());
        Assertions.assertThat(actualRide.getRejection()).isEqualTo(expectedRide.getRejection());

        verify(rideRepository, times(1)).save(ride);
    }

    @ParameterizedTest
    @EnumSource(value=RideStatus.class, names = {"PENDING", "ACTIVE", "FINISHED", "REJECTED"})
    @DisplayName("Should not start a ride. Wrong ride status")
    public void wrongRideStatusStartRide(RideStatus type) {
        Ride ride = new Ride(123L, new Date(), new Date(), 450, 45, null, null,
                new ArrayList<Passenger>(), RideStatus.PENDING, null, false, false,
                new HashSet<Route>(), new HashSet<Review>(), new Date());
        ride.setStatus(type);
        Mockito.when(rideRepository.findById(123L)).thenReturn(Optional.of(ride));

        assertThrows(BadRequestException.class, () -> rideService.startRide(123L));

        verify(rideRepository, times(0)).save(ride);
    }

    @Test
    @DisplayName("Should not start a ride. Wrong ride id")
    public void wrongRideIdStartRide() {
        Mockito.when(rideRepository.findById(123L)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> rideService.startRide(123L));
    }

    @Test
    @DisplayName("Should end a ride")
    public void shouldEndRide() {
        Ride ride = new Ride(123L, new Date(), new Date(), 450, 45, null, null,
                new ArrayList<Passenger>(), RideStatus.ACTIVE, null, false, false,
                new HashSet<Route>(), new HashSet<Review>(), new Date());

        Mockito.when(rideRepository.findById(123L)).thenReturn(Optional.of(ride));
        Mockito.when(rideRepository.save(ride)).thenReturn(ride);

        RideDTO actualRide = rideService.endRide(123L);
        ride.setStatus(RideStatus.FINISHED);

        RideDTO expectedRide = new RideDTO(ride);
        Assertions.assertThat(actualRide.getId()).isEqualTo(expectedRide.getId());
        Assertions.assertThat(actualRide.getStartTime()).isEqualTo(expectedRide.getStartTime());
        Assertions.assertThat(actualRide.getEndTime()).isEqualTo(expectedRide.getEndTime());
        Assertions.assertThat(actualRide.getScheduledTime()).isEqualTo(expectedRide.getScheduledTime());
        Assertions.assertThat(actualRide.getTotalCost()).isEqualTo(expectedRide.getTotalCost());
        Assertions.assertThat(actualRide.getLocations()).isEqualTo(expectedRide.getLocations());
        Assertions.assertThat(actualRide.getPassengers()).isEqualTo(expectedRide.getPassengers());
        Assertions.assertThat(actualRide.getVehicleType()).isEqualTo(expectedRide.getVehicleType());
        Assertions.assertThat(actualRide.isBabyTransport()).isEqualTo(expectedRide.isBabyTransport());
        Assertions.assertThat(actualRide.isPetTransport()).isEqualTo(expectedRide.isPetTransport());
        Assertions.assertThat(actualRide.getEstimatedTimeInMinutes()).isEqualTo(expectedRide.getEstimatedTimeInMinutes());
        Assertions.assertThat(actualRide.getStatus()).isEqualTo(expectedRide.getStatus());
        Assertions.assertThat(actualRide.isPanic()).isEqualTo(expectedRide.isPanic());
        Assertions.assertThat(actualRide.getDriver()).isEqualTo(expectedRide.getDriver());
        Assertions.assertThat(actualRide.getRejection()).isEqualTo(expectedRide.getRejection());

        verify(rideRepository, times(1)).save(ride);
    }

    @ParameterizedTest
    @EnumSource(value=RideStatus.class, names = {"PENDING", "ACCEPTED", "FINISHED", "REJECTED"})
    @DisplayName("Should not end a ride. Wrong ride status")
    public void wrongRideStatusEndRide(RideStatus type) {
        Ride ride = new Ride(123L, new Date(), new Date(), 450, 45, null, null,
                new ArrayList<Passenger>(), RideStatus.PENDING, null, false, false,
                new HashSet<Route>(), new HashSet<Review>(), new Date());
        ride.setStatus(type);

        Mockito.when(rideRepository.findById(123L)).thenReturn(Optional.of(ride));

        assertThrows(BadRequestException.class, () -> rideService.endRide(123L));

        verify(rideRepository, times(0)).save(ride);
    }

    @Test
    @DisplayName("Should not end a ride. Wrong ride id")
    public void wrongRideIdEndRide() {
        Mockito.when(rideRepository.findById(123L)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> rideService.endRide(123L));
    }

    @Test
    @DisplayName("Should insert favorite order")
    public void shouldInsertFavoriteOrder() {
        FavoriteOrder favoriteOrder = new FavoriteOrder(123L, "name", VehicleTypeName.STANDARD,
                null, new HashSet<>(), false, false, new HashSet<>());
        Passenger passenger = new Passenger(150L, "name", "surname", "sadas", "123465",
                "ana@gmail.com", "address", "pass", false, true);

        List<FavoriteOrder> favoriteOrders = new ArrayList<>();

        Mockito.when(passengerRepository.findByEmail("ana@gmail.com")).thenReturn(passenger);
        Mockito.when(favoriteOrderRepository.findByPassenger_Id(150L)).thenReturn(favoriteOrders);
        Mockito.when(favoriteOrderRepository.save(favoriteOrder)).thenReturn(favoriteOrder);

        FavoriteOrder actualFavOrder = rideService.insertFavoriteOrder(favoriteOrder, "ana@gmail.com");

        Assertions.assertThat(actualFavOrder.getId()).isEqualTo(favoriteOrder.getId());
        Assertions.assertThat(actualFavOrder.getFavoriteName()).isEqualTo(favoriteOrder.getFavoriteName());
        Assertions.assertThat(actualFavOrder.getVehicleType()).isEqualTo(favoriteOrder.getVehicleType());
        Assertions.assertThat(actualFavOrder.getPassenger()).isEqualTo(favoriteOrder.getPassenger());
        Assertions.assertThat(actualFavOrder.getPassengers()).isEqualTo(favoriteOrder.getPassengers());
        Assertions.assertThat(actualFavOrder.isBabyTransport()).isEqualTo(favoriteOrder.isBabyTransport());
        Assertions.assertThat(actualFavOrder.isPetTransport()).isEqualTo(favoriteOrder.isPetTransport());
        Assertions.assertThat(actualFavOrder.getLocations()).isEqualTo(favoriteOrder.getLocations());

        verify(favoriteOrderRepository, times(1)).save(favoriteOrder);
    }

    @Test
    @DisplayName("Should not insert favorite ride.Number of favorite rides cannot exceed 10")
    public void tooBigNumberOfFavoriteOrders() {
        FavoriteOrder favoriteOrder = new FavoriteOrder(123L, "name", VehicleTypeName.STANDARD,
                null, new HashSet<>(), false, false, new HashSet<>());
        FavoriteOrder favoriteOrder2 = new FavoriteOrder(124L, "name", VehicleTypeName.STANDARD,
                null, new HashSet<>(), false, false, new HashSet<>());
        FavoriteOrder favoriteOrder3 = new FavoriteOrder(125L, "name", VehicleTypeName.STANDARD,
                null, new HashSet<>(), false, false, new HashSet<>());
        FavoriteOrder favoriteOrder4 = new FavoriteOrder(126L, "name", VehicleTypeName.STANDARD,
                null, new HashSet<>(), false, false, new HashSet<>());
        FavoriteOrder favoriteOrder5 = new FavoriteOrder(127L, "name", VehicleTypeName.STANDARD,
                null, new HashSet<>(), false, false, new HashSet<>());
        FavoriteOrder favoriteOrder6 = new FavoriteOrder(128L, "name", VehicleTypeName.STANDARD,
                null, new HashSet<>(), false, false, new HashSet<>());
        FavoriteOrder favoriteOrder7 = new FavoriteOrder(129L, "name", VehicleTypeName.STANDARD,
                null, new HashSet<>(), false, false, new HashSet<>());
        FavoriteOrder favoriteOrder8 = new FavoriteOrder(130L, "name", VehicleTypeName.STANDARD,
                null, new HashSet<>(), false, false, new HashSet<>());
        FavoriteOrder favoriteOrder9 = new FavoriteOrder(131L, "name", VehicleTypeName.STANDARD,
                null, new HashSet<>(), false, false, new HashSet<>());
        FavoriteOrder favoriteOrder10 = new FavoriteOrder(132L, "name", VehicleTypeName.STANDARD,
                null, new HashSet<>(), false, false, new HashSet<>());
        FavoriteOrder favoriteOrder11 = new FavoriteOrder(133L, "name", VehicleTypeName.STANDARD,
                null, new HashSet<>(), false, false, new HashSet<>());

        Passenger passenger = new Passenger(150L, "name", "surname", "sadas", "123465",
                "ana@gmail.com", "address", "pass", false, true);

        List<FavoriteOrder> favoriteOrders = new ArrayList<>();
        favoriteOrders.add(favoriteOrder2);
        favoriteOrders.add(favoriteOrder3);
        favoriteOrders.add(favoriteOrder4);
        favoriteOrders.add(favoriteOrder5);
        favoriteOrders.add(favoriteOrder6);
        favoriteOrders.add(favoriteOrder7);
        favoriteOrders.add(favoriteOrder8);
        favoriteOrders.add(favoriteOrder9);
        favoriteOrders.add(favoriteOrder10);
        favoriteOrders.add(favoriteOrder11);

        Mockito.when(passengerRepository.findByEmail("ana@gmail.com")).thenReturn(passenger);
        Mockito.when(favoriteOrderRepository.findByPassenger_Id(150L)).thenReturn(favoriteOrders);

        assertThrows(BadRequestException.class, () -> rideService.insertFavoriteOrder(favoriteOrder,
                                                "ana@gmail.com"));

        verify(favoriteOrderRepository, times(0)).save(favoriteOrder);
    }

    @Test
    @DisplayName("Should delete favorite order")
    public void shouldDeleteFavoriteOrder() {
        Passenger passenger = new Passenger(150L, "name", "surname", "sadas", "123465",
                "ana@gmail.com", "address", "pass", false, true);

        FavoriteOrder favoriteOrder = new FavoriteOrder(123L, "name", VehicleTypeName.STANDARD,
                passenger, new HashSet<>(), false, false, new HashSet<>());

        Mockito.when(favoriteOrderRepository.findById(123L)).thenReturn(Optional.of(favoriteOrder));

        rideService.deleteFavoriteLocation(123L, passenger);

        verify(favoriteOrderRepository, times(1)).delete(favoriteOrder);
    }

    @Test
    @DisplayName("Should not delete favorite order. Wrong passenger")
    public void shouldNotDeleteFavoriteOrder() {
        Passenger passenger = new Passenger(150L, "name", "surname", "sadas", "123465",
                "ana@gmail.com", "address", "pass", false, true);

        FavoriteOrder favoriteOrder = new FavoriteOrder(123L, "name", VehicleTypeName.STANDARD,
                passenger, new HashSet<>(), false, false, new HashSet<>());

        Passenger passenger1 = new Passenger(151L, "name", "surname", "sadas", "123465",
                "ana@gmail.com", "address", "pass", false, true);

        Mockito.when(favoriteOrderRepository.findById(123L)).thenReturn(Optional.of(favoriteOrder));

        rideService.deleteFavoriteLocation(123L, passenger1);

        verify(favoriteOrderRepository, times(0)).delete(favoriteOrder);
    }

    @Test
    @DisplayName("Should not delete favorite order. Not existing favorite order")
    public void notExistingFavoriteOrderDelete() {
        Passenger passenger = new Passenger(150L, "name", "surname", "sadas", "123465",
                "ana@gmail.com", "address", "pass", false, true);

        FavoriteOrder favoriteOrder = new FavoriteOrder(123L, "name", VehicleTypeName.STANDARD,
                passenger, new HashSet<>(), false, false, new HashSet<>());

        Mockito.when(favoriteOrderRepository.findById(123L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> rideService.deleteFavoriteLocation(123L, passenger));

        verify(favoriteOrderRepository, times(0)).delete(favoriteOrder);
    }

    @Test
    @DisplayName("Should find passengers active ride")
    public void shouldFindPassengersActiveRide() {
        Ride ride = new Ride(123L, new Date(), new Date(), 450, 45, null, null,
                new ArrayList<Passenger>(), RideStatus.ACTIVE, null, false, false,
                new HashSet<Route>(), null, new Date());
        RideDTO expectedRide = new RideDTO(ride);
        Mockito.when(rideRepository.findByStatusAndPassengers_id(RideStatus.ACTIVE, 123L)).thenReturn(Optional.of(ride));

        RideDTO actualRide = rideService.findPassengersActiveRide(123L);

        Assertions.assertThat(actualRide.getId()).isEqualTo(expectedRide.getId());
        Assertions.assertThat(actualRide.getStartTime()).isEqualTo(expectedRide.getStartTime());
        Assertions.assertThat(actualRide.getEndTime()).isEqualTo(expectedRide.getEndTime());
        Assertions.assertThat(actualRide.getScheduledTime()).isEqualTo(expectedRide.getScheduledTime());
        Assertions.assertThat(actualRide.getTotalCost()).isEqualTo(expectedRide.getTotalCost());
        Assertions.assertThat(actualRide.getLocations()).isEqualTo(expectedRide.getLocations());
        Assertions.assertThat(actualRide.getPassengers()).isEqualTo(expectedRide.getPassengers());
        Assertions.assertThat(actualRide.getVehicleType()).isEqualTo(expectedRide.getVehicleType());
        Assertions.assertThat(actualRide.isBabyTransport()).isEqualTo(expectedRide.isBabyTransport());
        Assertions.assertThat(actualRide.isPetTransport()).isEqualTo(expectedRide.isPetTransport());
        Assertions.assertThat(actualRide.getEstimatedTimeInMinutes()).isEqualTo(expectedRide.getEstimatedTimeInMinutes());
        Assertions.assertThat(actualRide.getStatus()).isEqualTo(expectedRide.getStatus());
        Assertions.assertThat(actualRide.isPanic()).isEqualTo(expectedRide.isPanic());
        Assertions.assertThat(actualRide.getDriver()).isEqualTo(expectedRide.getDriver());
        Assertions.assertThat(actualRide.getRejection()).isEqualTo(expectedRide.getRejection());

        verify(rideRepository, times(1)).findByStatusAndPassengers_id(RideStatus.ACTIVE, 123L);
    }

    @Test
    @DisplayName("Should not find passenger active ride")
    public void shouldNotFindPassengersActiveRide() {
        Mockito.when(rideRepository.findByStatusAndPassengers_id(RideStatus.ACTIVE, 123L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> rideService.findPassengersActiveRide(123L));

        verify(rideRepository, times(1)).findByStatusAndPassengers_id(RideStatus.ACTIVE, 123L);
    }

    @ParameterizedTest
    @EnumSource(value=RideStatus.class, names = {"PENDING", "ACCEPTED"})
    @DisplayName("Should cancel ride (perspective of driver)")
    public void shouldCancelRideAsDriver(RideStatus type) {
        Ride ride = new Ride(123L, new Date(), new Date(), 450, 45, null, null,
                new ArrayList<Passenger>(), RideStatus.ACCEPTED, null, false, false,
                new HashSet<Route>(), new HashSet<Review>(), new Date());
        ride.setStatus(type);
        Rejection reason = new Rejection("No passengers at destination", null, new Date());
        RejectionDTO rejection = new RejectionDTO(reason);

        Mockito.when(rideRepository.findById(123L)).thenReturn(Optional.of(ride));
        Mockito.when(rideRepository.save(ride)).thenReturn(ride);

        RideDTO actualRide = rideService.cancelRide(rejection , 123L);
        ride.setStatus(RideStatus.REJECTED);
        ride.setRejection(reason);

        RideDTO expectedRide = new RideDTO(ride);
        Assertions.assertThat(actualRide.getId()).isEqualTo(expectedRide.getId());
        Assertions.assertThat(actualRide.getStartTime()).isEqualTo(expectedRide.getStartTime());
        Assertions.assertThat(actualRide.getEndTime()).isEqualTo(expectedRide.getEndTime());
        Assertions.assertThat(actualRide.getScheduledTime()).isEqualTo(expectedRide.getScheduledTime());
        Assertions.assertThat(actualRide.getTotalCost()).isEqualTo(expectedRide.getTotalCost());
        Assertions.assertThat(actualRide.getLocations()).isEqualTo(expectedRide.getLocations());
        Assertions.assertThat(actualRide.getPassengers()).isEqualTo(expectedRide.getPassengers());
        Assertions.assertThat(actualRide.getVehicleType()).isEqualTo(expectedRide.getVehicleType());
        Assertions.assertThat(actualRide.isBabyTransport()).isEqualTo(expectedRide.isBabyTransport());
        Assertions.assertThat(actualRide.isPetTransport()).isEqualTo(expectedRide.isPetTransport());
        Assertions.assertThat(actualRide.getEstimatedTimeInMinutes()).isEqualTo(expectedRide.getEstimatedTimeInMinutes());
        Assertions.assertThat(actualRide.getStatus()).isEqualTo(expectedRide.getStatus());
        Assertions.assertThat(actualRide.isPanic()).isEqualTo(expectedRide.isPanic());
        Assertions.assertThat(actualRide.getDriver()).isEqualTo(expectedRide.getDriver());
        Assertions.assertThat(actualRide.getRejection()).isEqualTo(expectedRide.getRejection());

        verify(rideRepository, times(1)).save(ride);
        verify(rideRepository, times(1)).findById(123L);
    }

    @ParameterizedTest
    @EnumSource(value=RideStatus.class, names = {"ACTIVE", "FINISHED", "REJECTED"})
    @DisplayName("Should not cancel ride (perspective of driver) if not in status pending or accepted")
    public void shouldNotCancelRideAsDriver(RideStatus type) {
        Ride ride = new Ride(123L, new Date(), new Date(), 450, 45, null, null,
                new ArrayList<Passenger>(), RideStatus.ACCEPTED, null, false, false,
                new HashSet<Route>(), new HashSet<Review>(), new Date());
        ride.setStatus(type);
        Rejection reason = new Rejection("No passengers at destination", null, new Date());
        RejectionDTO rejection = new RejectionDTO(reason);

        Mockito.when(rideRepository.findById(123L)).thenReturn(Optional.of(ride));
        //Mockito.doReturn(reason).when(rejectionRepository.save(reason));

        assertThrows(BadRequestException.class, () -> rideService.cancelRide(rejection, 123L));

        verify(rideRepository, times(1)).findById(123L);
    }

    @ParameterizedTest
    @EnumSource(value=RideStatus.class, names = {"PENDING", "ACCEPTED"})
    @DisplayName("Should cancel ride (perspective of passenger)")
    public void shouldCancelRideAsPasenger(RideStatus type) {
        Ride ride = new Ride(123L, new Date(), new Date(), 450, 45, null, null,
                new ArrayList<Passenger>(), RideStatus.ACCEPTED, null, false, false,
                new HashSet<Route>(), new HashSet<Review>(), new Date());
        ride.setStatus(type);

        Mockito.when(rideRepository.findById(123L)).thenReturn(Optional.of(ride));
        Mockito.when(rideRepository.save(ride)).thenReturn(ride);

        RideDTO actualRide = rideService.cancelExistingRide(123L);
        ride.setStatus(RideStatus.REJECTED);

        RideDTO expectedRide = new RideDTO(ride);
        Assertions.assertThat(actualRide.getId()).isEqualTo(expectedRide.getId());
        Assertions.assertThat(actualRide.getStartTime()).isEqualTo(expectedRide.getStartTime());
        Assertions.assertThat(actualRide.getEndTime()).isEqualTo(expectedRide.getEndTime());
        Assertions.assertThat(actualRide.getScheduledTime()).isEqualTo(expectedRide.getScheduledTime());
        Assertions.assertThat(actualRide.getTotalCost()).isEqualTo(expectedRide.getTotalCost());
        Assertions.assertThat(actualRide.getLocations()).isEqualTo(expectedRide.getLocations());
        Assertions.assertThat(actualRide.getPassengers()).isEqualTo(expectedRide.getPassengers());
        Assertions.assertThat(actualRide.getVehicleType()).isEqualTo(expectedRide.getVehicleType());
        Assertions.assertThat(actualRide.isBabyTransport()).isEqualTo(expectedRide.isBabyTransport());
        Assertions.assertThat(actualRide.isPetTransport()).isEqualTo(expectedRide.isPetTransport());
        Assertions.assertThat(actualRide.getEstimatedTimeInMinutes()).isEqualTo(expectedRide.getEstimatedTimeInMinutes());
        Assertions.assertThat(actualRide.getStatus()).isEqualTo(expectedRide.getStatus());
        Assertions.assertThat(actualRide.isPanic()).isEqualTo(expectedRide.isPanic());
        Assertions.assertThat(actualRide.getDriver()).isEqualTo(expectedRide.getDriver());
        Assertions.assertThat(actualRide.getRejection()).isEqualTo(expectedRide.getRejection());

        verify(rideRepository, times(1)).save(ride);
        verify(rideRepository, times(1)).findById(123L);
    }

    @ParameterizedTest
    @EnumSource(value=RideStatus.class, names = {"ACTIVE", "FINISHED", "REJECTED"})
    @DisplayName("Should not cancel ride (perspective of passenger) if not in status pending or accepted")
    public void shouldNotCancelRideAsPassenger(RideStatus type) {
        Ride ride = new Ride(123L, new Date(), new Date(), 450, 45, null, null,
                new ArrayList<Passenger>(), RideStatus.ACCEPTED, null, false, false,
                new HashSet<Route>(), new HashSet<Review>(), new Date());
        ride.setStatus(type);

        Mockito.when(rideRepository.findById(123L)).thenReturn(Optional.of(ride));

        assertThrows(BadRequestException.class, () -> rideService.cancelExistingRide(123L));

        verify(rideRepository, times(1)).findById(123L);
    }
}
