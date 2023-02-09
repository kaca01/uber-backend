package com.example.test.service;

import com.example.test.domain.communication.Message;
import com.example.test.domain.communication.Rejection;
import com.example.test.domain.communication.Review;
import com.example.test.domain.ride.FavoriteOrder;
import com.example.test.domain.ride.Ride;
import com.example.test.domain.ride.Route;
import com.example.test.domain.user.Passenger;
import com.example.test.dto.AllDTO;
import com.example.test.dto.communication.PanicDTO;
import com.example.test.dto.communication.RejectionDTO;
import com.example.test.dto.ride.RideDTO;
import com.example.test.dto.user.UserDTO;
import com.example.test.enumeration.RideStatus;
import com.example.test.enumeration.VehicleTypeName;
import com.example.test.exception.BadRequestException;
import com.example.test.exception.NotFoundException;
import com.example.test.repository.communication.IMessageRepository;
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
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.assertj.core.api.Assertions;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
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
    IMessageRepository messageRepository;

    @Mock
    IRejectionRepository rejectionRepository;

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

    @ParameterizedTest
    @NullSource
    @ValueSource(longs = {123L})
    @DisplayName("Should not find ride by id. Also should throw exception Not Found")
    public void shouldNotFindRideById(Long name) {
        Mockito.when(rideRepository.findById(name)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> rideService.findOne(name));

        verify(rideRepository, times(1)).findById(name);
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

    @ParameterizedTest
    @NullSource
    @ValueSource(longs = {123L})
    @DisplayName("Should not find driver active ride")
    public void shouldNotFindDriverActiveRide(Long name) {
        Mockito.when(rideRepository.findByStatusAndDriver_id(RideStatus.ACTIVE, name)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> rideService.findDriversActiveRide(name));

        verify(rideRepository, times(1)).findByStatusAndDriver_id(RideStatus.ACTIVE, name);
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

    @ParameterizedTest
    @NullSource
    @ValueSource(longs = {123L})
    @DisplayName("Should not find driver accepted ride")
    public void shouldNotFindAcceptedRide(Long name) {
        Mockito.when(rideRepository.findByStatusAndDriver_id(RideStatus.ACCEPTED, name)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> rideService.findDriversAcceptedRide(name));

        verify(rideRepository, times(1)).findByStatusAndDriver_id(RideStatus.ACCEPTED, name);
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
    @EnumSource(value = RideStatus.class, names = {"PENDING", "ACTIVE", "FINISHED", "REJECTED"})
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

    @ParameterizedTest
    @NullSource
    @ValueSource(longs = {123L})
    @DisplayName("Should not start a ride. Wrong ride id")
    public void wrongRideIdStartRide(Long name) {
        Mockito.when(rideRepository.findById(name)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> rideService.startRide(name));

        verify(rideRepository, times(1)).findById(name);
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
    @EnumSource(value = RideStatus.class, names = {"PENDING", "ACCEPTED", "FINISHED", "REJECTED"})
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

    @ParameterizedTest
    @NullSource
    @ValueSource(longs = {123L})
    @DisplayName("Should not end a ride. Wrong ride id")
    public void wrongRideIdEndRide(Long name) {
        Mockito.when(rideRepository.findById(name)).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> rideService.endRide(name));

        verify(rideRepository, times(1)).findById(name);
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

    @ParameterizedTest
    @NullSource
    @ValueSource(longs = {123L})
    @DisplayName("Should not delete favorite order. Not existing favorite order")
    public void notExistingFavoriteOrderDelete(Long name) {
        Passenger passenger = new Passenger(150L, "name", "surname", "sadas", "123465",
                "ana@gmail.com", "address", "pass", false, true);

        FavoriteOrder favoriteOrder = new FavoriteOrder(name, "name", VehicleTypeName.STANDARD,
                passenger, new HashSet<>(), false, false, new HashSet<>());

        Mockito.when(favoriteOrderRepository.findById(name)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> rideService.deleteFavoriteLocation(name, passenger));

        verify(favoriteOrderRepository, times(0)).delete(favoriteOrder);
    }

    @Test
    @DisplayName("Should find passenger's favorite orders")
    public void shouldFindFavoriteOrders() {
        FavoriteOrder favoriteOrder = new FavoriteOrder(133L, "name", VehicleTypeName.STANDARD,
                null, new HashSet<>(), false, false, new HashSet<>());
        Passenger passenger = new Passenger(150L, "name", "surname", "sadas", "123465",
                "ana@gmail.com", "address", "pass", false, true);
        favoriteOrder.setPassenger(passenger);
        List<FavoriteOrder> favoriteOrders = new ArrayList<>();
        favoriteOrders.add(favoriteOrder);

        Mockito.when(favoriteOrderRepository.findByPassenger_Id(150L)).thenReturn(favoriteOrders);

        AllDTO<FavoriteOrder> actualOrders = rideService.getFavoriteOrdersByPassenger(passenger);
        Assertions.assertThat(actualOrders.getTotalCount()).isEqualTo(favoriteOrders.size());

        Assertions.assertThat(favoriteOrders.get(0).getId()).isEqualTo(actualOrders.getResults().get(0).getId());
        Assertions.assertThat(favoriteOrders.get(0).getLocations()).isEqualTo(actualOrders.getResults().get(0).getLocations());
        Assertions.assertThat(favoriteOrders.get(0).getPassengers()).isEqualTo(actualOrders.getResults().get(0).getPassengers());
        Assertions.assertThat(favoriteOrders.get(0).getVehicleType()).isEqualTo(actualOrders.getResults().get(0).getVehicleType());
        Assertions.assertThat(favoriteOrders.get(0).isBabyTransport()).isEqualTo(actualOrders.getResults().get(0).isBabyTransport());
        Assertions.assertThat(favoriteOrders.get(0).isPetTransport()).isEqualTo(actualOrders.getResults().get(0).isPetTransport());

        verify(favoriteOrderRepository, times(1)).findByPassenger_Id(150L);
    }

    @ParameterizedTest
    @NullSource
    @ValueSource(longs = {150L})
    @DisplayName("Should not find passenger's favorite orders")
    public void shouldNotFindFavoriteOrders(Long name) {
        Passenger passenger = new Passenger(name, "name", "surname", "sadas", "123465",
                "ana@gmail.com", "address", "pass", false, true);

        Mockito.when(favoriteOrderRepository.findByPassenger_Id(name)).thenReturn(new ArrayList<>());

        AllDTO<FavoriteOrder> actualOrders = rideService.getFavoriteOrdersByPassenger(passenger);
        Assertions.assertThat(actualOrders.getTotalCount()).isEqualTo(0);

        verify(favoriteOrderRepository, times(1)).findByPassenger_Id(name);
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

    @ParameterizedTest
    @NullSource
    @ValueSource(longs = {123L})
    @DisplayName("Should not find passenger active ride")
    public void shouldNotFindPassengersActiveRide(Long name) {
        Mockito.when(rideRepository.findByStatusAndPassengers_id(RideStatus.ACTIVE, name)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> rideService.findPassengersActiveRide(name));

        verify(rideRepository, times(1)).findByStatusAndPassengers_id(RideStatus.ACTIVE, name);
    }

    @ParameterizedTest
    @EnumSource(value = RideStatus.class, names = {"PENDING", "ACCEPTED"})
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
        Mockito.when(rejectionRepository.save(any())).thenReturn(reason);

        RideDTO actualRide = rideService.cancelRide(rejection, 123L);
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
    @EnumSource(value = RideStatus.class, names = {"ACTIVE", "FINISHED", "REJECTED"})
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
        verify(rideRepository, times(0)).save(ride);
    }

    @Test
    @DisplayName("Should not cancel ride (perspective of driver) if no parameters have been sent")
    public void shouldNotCancelRideAsDriverWhenNoParameter() {
        assertThrows(NotFoundException.class, () -> rideService.cancelRide(null, null));
        verify(rideRepository, times(1)).findById(null);
    }

    @ParameterizedTest
    @EnumSource(value = RideStatus.class, names = {"PENDING", "ACCEPTED"})
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
    @EnumSource(value = RideStatus.class, names = {"ACTIVE", "FINISHED", "REJECTED"})
    @DisplayName("Should not cancel ride (perspective of passenger) if not in status pending or accepted")
    public void shouldNotCancelRideAsPassenger(RideStatus type) {
        Ride ride = new Ride(123L, new Date(), new Date(), 450, 45, null, null,
                new ArrayList<Passenger>(), RideStatus.ACCEPTED, null, false, false,
                new HashSet<Route>(), new HashSet<Review>(), new Date());
        ride.setStatus(type);

        Mockito.when(rideRepository.findById(123L)).thenReturn(Optional.of(ride));

        assertThrows(BadRequestException.class, () -> rideService.cancelExistingRide(123L));

        verify(rideRepository, times(1)).findById(123L);
        verify(rideRepository, times(0)).save(ride);
    }

    @Test
    @DisplayName("Should not cancel ride (perspective of passenger) if no parameters have been sent")
    public void shouldNotCancelRideAsPassengerWhenNoParameter() {
        assertThrows(NotFoundException.class, () -> rideService.cancelExistingRide(null));
        verify(rideRepository, times(1)).findById(null);
    }

    @Test
    @DisplayName("Should insert ride")
    public void shouldInsertRide() throws ParseException {
        Ride ride = new Ride(123L, new Date(), new Date(), 450, 45, null, null,
                new ArrayList<Passenger>(), RideStatus.PENDING, null, false, false,
                new HashSet<Route>(), new HashSet<Review>(), new Date());

        Mockito.when(rideRepository.save(any())).thenReturn(ride);
        RideDTO expectedRide = new RideDTO(ride);

        RideDTO actualRide = rideService.insert(expectedRide);

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

        verify(rideRepository, times(1)).save(any());
    }

    @Test
    @DisplayName("Should not insert ride. Ride status pending")
    public void shouldNotInsertRidePendingStatus() {
        Passenger passenger = new Passenger(111L, "Pera", "Peric", "sadsadas", "456879", "email", "address", "pass",
                false, true);

        List<Passenger> passengers = new ArrayList<>();
        passengers.add(passenger);

        Ride ride = new Ride(123L, new Date(), new Date(), 450, 45, null, null,
                passengers, RideStatus.PENDING, null, false, false,
                new HashSet<Route>(), new HashSet<Review>(), new Date());

        List<Ride> rides = new ArrayList<>();
        rides.add(ride);
        Mockito.when(rideRepository.findRidesByStatusAndPassengers_email(RideStatus.PENDING, "email")).thenReturn(rides);

        RideDTO expectedRide = new RideDTO(ride);
        assertThrows(BadRequestException.class, () -> rideService.insert(expectedRide));

        verify(rideRepository, times(0)).save(any());
    }

    @Test
    @DisplayName("Should not insert ride. Null ride")
    public void shouldNotInsertNullRide() {
        RideDTO ride = null;
        assertThrows(NullPointerException.class, () -> rideService.insert(ride));
    }

    @Test
    @DisplayName("Should send panic")
    public void shouldSendPanic() {
        Passenger passenger = new Passenger(111L, "Pera", "Peric", "sadsadas", "456879", "email", "address", "pass",
                false, true);
        Ride ride = new Ride(123L, new Date(), new Date(), 450, 45, null, null,
                new ArrayList<Passenger>(), RideStatus.PENDING, null, false, false,
                new HashSet<Route>(), new HashSet<Review>(), new Date());

        PanicDTO panicDTO = new PanicDTO("some reason");
        Date now = new Date();

        Message message = new Message();
        message.setId(100L);
        message.setRide(ride);
        message.setMessage("some reason");
        message.setSender(passenger);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        String time = format.format(now);
        message.setTimeOfSending(now);

        Mockito.when(rideRepository.findById(123L)).thenReturn(Optional.of(ride));
        Mockito.when(messageRepository.save(any())).thenReturn(message);

        panicDTO.setId(100L);
        RideDTO rideDTO = new RideDTO(ride);
        rideDTO.setPanic(true);
        panicDTO.setRide(rideDTO);
        UserDTO userDTO = new UserDTO(passenger);
        panicDTO.setUser(userDTO);
        panicDTO.setTime(time);
        PanicDTO actualPanic = rideService.setPanic(panicDTO, 123L
                , passenger);

        PanicDTO expectedPanic = new PanicDTO("some reason", 100L, userDTO, rideDTO, time);

        Assertions.assertThat(actualPanic.getRide()).isEqualTo(expectedPanic.getRide());
        Assertions.assertThat(actualPanic.getId()).isEqualTo(expectedPanic.getId());
        Assertions.assertThat(actualPanic.getReason()).isEqualTo(expectedPanic.getReason());
        Assertions.assertThat(actualPanic.getUser()).isEqualTo(expectedPanic.getUser());
        Assertions.assertThat(actualPanic.getTime()).isEqualTo(expectedPanic.getTime());

        verify(rideRepository, times(1)).save(any());
        verify(messageRepository, times(1)).save(any());
    }

    @Test
    @DisplayName("Should not send panic")
    public void shouldNotSendPanic() {
        Passenger passenger = new Passenger(111L, "Pera", "Peric", "sadsadas", "456879", "email", "address", "pass",
                false, true);
        Mockito.when(rideRepository.findById(123L)).thenReturn(Optional.empty());

        PanicDTO panic = new PanicDTO("some reason");

        assertThrows(NotFoundException.class, () -> rideService.setPanic(panic, 123L, passenger));

        verify(rideRepository, times(0)).save(any());
        verify(messageRepository, times(0)).save(any());
    }

    @Test
    @DisplayName("Panic sending null...")
    public void shouldNotSendPanicNull() {
        Passenger passenger = new Passenger(111L, "Pera", "Peric", "sadsadas", "456879", "email", "address", "pass",
                false, true);
        Ride ride = new Ride(123L, new Date(), new Date(), 450, 45, null, null,
                new ArrayList<Passenger>(), RideStatus.PENDING, null, false, false,
                new HashSet<Route>(), new HashSet<Review>(), new Date());

        Mockito.when(rideRepository.findById(123L)).thenReturn(Optional.of(ride));

        assertThrows(NullPointerException.class, () -> rideService.setPanic(null, 123L, passenger));

    }
}