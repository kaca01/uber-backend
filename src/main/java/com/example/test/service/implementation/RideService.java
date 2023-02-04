package com.example.test.service.implementation;

import com.example.test.domain.communication.Message;
import com.example.test.domain.communication.Rejection;
import com.example.test.domain.ride.FavoriteOrder;
import com.example.test.domain.ride.Ride;
import com.example.test.domain.user.Driver;
import com.example.test.domain.user.Passenger;
import com.example.test.domain.user.User;
import com.example.test.dto.AllDTO;
import com.example.test.dto.communication.PanicDTO;
import com.example.test.dto.communication.RejectionDTO;
import com.example.test.dto.ride.RideDTO;
import com.example.test.dto.user.UserDTO;
import com.example.test.enumeration.MessageType;
import com.example.test.enumeration.RideStatus;
import com.example.test.exception.BadRequestException;
import com.example.test.exception.NotFoundException;
import com.example.test.repository.communication.IMessageRepository;
import com.example.test.repository.communication.IRejectionRepository;
import com.example.test.repository.ride.IFavoriteOrderRepository;
import com.example.test.repository.ride.IRideRepository;
import com.example.test.repository.user.IPassengerRepository;
import com.example.test.service.interfaces.IRideService;
import com.example.test.service.interfaces.ISelectionDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.*;

@Service
public class RideService implements IRideService {

    @Autowired
    private IPassengerRepository passengerRepository;
    @Autowired
    private IRideRepository rideRepository;
    @Autowired
    private IMessageRepository messageRepository;
    @Autowired
    private IRejectionRepository rejectionRepository;
    @Autowired
    private IFavoriteOrderRepository favoriteOrderRepository;
    @Autowired
    private ISelectionDriver iSelectionDriver;

    @Transactional
    @Override
    public RideDTO insert(RideDTO rideDTO) throws ParseException {
        //Cannot create a ride while you have one already pending!
        Ride ride = new Ride(rideDTO);

        List<Passenger> passengers = new ArrayList<>();

        for (UserDTO u : rideDTO.getPassengers()) {
            Passenger p = passengerRepository.findByEmail(u.getEmail());
            passengers.add(p);
        }

        if(rideDTO.getPassengers().size() != 0) {
            List<Ride> rides = rideRepository.findRidesByStatusAndPassengers_email(RideStatus.PENDING, (rideDTO.getPassengers().get(rideDTO.getPassengers().size()-1).getEmail()));
            if(!rides.isEmpty()) {
                throw new BadRequestException("Cannot create a ride while you have one already pending!");
            }
        }
        ride.setPassengers(passengers);
        ride.setStatus(RideStatus.PENDING);
        ride.setLocations(rideDTO.getLocations());

        if(ride.getScheduledTime() == null) ride.setScheduledTime(new Date());
        Driver driver = findAvailableDriver(ride, rideDTO.getVehicleType());
        ride.setDriver(driver);
        if (driver != null) {
            ride.setVehicle(driver.getVehicle());
        }
        ride = rideRepository.save(ride);
        return new RideDTO(ride);
    }

    private Driver findAvailableDriver(Ride ride, String vehicleType) {
        return iSelectionDriver.findDriver(ride, vehicleType);
    }

    @Transactional
    @Override
    public RideDTO findDriversActiveRide(Long id) {
        Ride ride = rideRepository.findByStatusAndDriver_id(RideStatus.ACTIVE, id).orElseThrow(
                () -> new NotFoundException("Active ride does not exist!"));
        return new RideDTO(ride);
    }

    @Transactional
    @Override
    public RideDTO findPassengersActiveRide(Long id) {
        Ride ride = rideRepository.findByStatusAndPassengers_id(RideStatus.ACTIVE, id).orElseThrow(
                () -> new NotFoundException("Active ride does not exist!"));
        return new RideDTO(ride);
    }

    @Transactional
    @Override
    public RideDTO findOne(Long id) {
        Ride r = findRideById(id);
        return new RideDTO(r);
    }

    private Ride findRideById(Long id){
        return rideRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Ride does not exist!"));
    }

    //The passenger should have the possibility to cancel an existing ride before the driver has arrived at the destination
    //!ride.getLocations().stream().findFirst().get().getDeparture().equals(ride.getVehicle().getCurrentLocation()) &&
    @Override
    public RideDTO cancelExistingRide(Long id) {
        Ride ride = findRideById(id);
        if (ride.getStatus()==RideStatus.ACCEPTED || ride.getStatus()==RideStatus.PENDING){
            ride.setStatus(RideStatus.REJECTED);
            ride = rideRepository.save(ride);
            return new RideDTO(ride);
        }else throw new BadRequestException("Cannot cancel a ride that is not in status PENDING or STARTED!");
    }

    //the user will be used from the token
    @Override
    public PanicDTO setPanic(PanicDTO reason, Long id, User sender)
    {
        Ride ride = findRideById(id);
        ride.setStatus(RideStatus.REJECTED);
        ride = rideRepository.save(ride);
        String msg;
        if (reason != null) msg= reason.getReason(); else msg = "";
        Message panic = new Message(sender, null, msg, new Date(), MessageType.PANIC, ride);
        panic = messageRepository.save(panic);
        return new PanicDTO(panic);
    }

    @Override
    public RideDTO acceptRide(Long id) {
        Ride ride = findRideById(id);
        if (ride.getStatus()!= RideStatus.PENDING) throw new BadRequestException("Cannot accept a ride that is not in status PENDING!");
        ride.setStatus(RideStatus.ACCEPTED);
        ride = rideRepository.save(ride);
        return new RideDTO(ride);
    }

    @Override
    public RideDTO endRide(Long id) {
        Ride ride = findRideById(id);
        if (ride.getStatus()!= RideStatus.ACTIVE) throw new BadRequestException("Cannot end a ride that is not in status ACTIVE!");
        ride.setStatus(RideStatus.FINISHED);
        ride.setEndTime(new Date());
        ride = rideRepository.save(ride);
        return new RideDTO(ride);
    }

    //perspective of driver
    @Override
    public RideDTO cancelRide(RejectionDTO reason, Long id) {
        Ride ride = findRideById(id);
        if (ride.getStatus()!= RideStatus.PENDING && ride.getStatus()!= RideStatus.ACCEPTED) throw new BadRequestException("Cannot cancel a ride that is not in status PENDING or ACCEPTED!");
        ride.setStatus(RideStatus.REJECTED);
        String msg;
        if (reason == null) msg = ""; else msg = reason.getReason();;
        Rejection rejection = new Rejection(msg, ride.getDriver(), new Date());
        ride.setRejection(rejection);
        rejectionRepository.save(rejection);
        ride = rideRepository.save(ride);
        return new RideDTO(ride);
    }

    @Override
    public RideDTO startRide(Long id) {
        Ride ride = findRideById(id);
        ride.setStartTime(new Date());
        if (ride.getStatus()!= RideStatus.ACCEPTED) throw new BadRequestException("Cannot start a ride that is not in status ACCEPTED!");
        ride.setStatus(RideStatus.ACTIVE);
        ride = rideRepository.save(ride);
        return new RideDTO(ride);
    }

    @Transactional
    @Override
    public FavoriteOrder insertFavoriteOrder(FavoriteOrder favoriteOrder, String email) {
        Passenger passengerT = passengerRepository.findByEmail(email);
        List<FavoriteOrder> fo = favoriteOrderRepository.findByPassenger_Id(passengerT.getId());
        if(fo.size() >=10) throw new BadRequestException("Number of favorite rides cannot exceed 10!");
        Set<Passenger> passengers = new HashSet<>();
        for (Passenger p : favoriteOrder.getPassengers())
        {
            Passenger passenger = passengerRepository.findById(p.getId()).orElse(null);
            passengers.add(passenger);
        }
        favoriteOrder.setPassengers(passengers);
        favoriteOrder.setPassenger(passengerT);
        return favoriteOrderRepository.save(favoriteOrder);
    }

    @Transactional
    @Override
    public AllDTO<FavoriteOrder> getFavoriteOrdersByPassenger(Passenger p) {
        List<FavoriteOrder> orders = favoriteOrderRepository.findByPassenger_Id(p.getId());
        return new AllDTO<FavoriteOrder>(orders.size(), orders);
    }

    @Override
    public void deleteFavoriteLocation(Long id, Passenger p) {
        FavoriteOrder order = favoriteOrderRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Favorite location does not exist!"));
        if (Objects.equals(order.getPassenger().getId(), p.getId())){
            favoriteOrderRepository.delete(order);
        }
    }

    @Override
    @Transactional
    public RideDTO getPendingRide(Long id) {
        Passenger passenger = passengerRepository.findById(id).orElse(null);
        if(passenger != null) {
            Ride ride = rideRepository.findByStatusAndPassengers_id(RideStatus.PENDING, id).orElseThrow(
                    () -> new NotFoundException("The passenger doesn't have an accepted ride!"));
            return new RideDTO(ride);
        }
        else {
            List<Ride> rides = rideRepository.findRidesByStatusAndDriver_Id(RideStatus.PENDING, id);
            if(rides.isEmpty()) throw new NotFoundException("The driver doesn't have a ride");
            return new RideDTO(rides.get(0));
        }
    }
}
