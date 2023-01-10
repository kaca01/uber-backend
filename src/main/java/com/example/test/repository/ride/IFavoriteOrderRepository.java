package com.example.test.repository.ride;

import com.example.test.domain.ride.FavoriteOrder;
import com.example.test.domain.user.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IFavoriteOrderRepository extends JpaRepository<FavoriteOrder, Long> {

    public List<FavoriteOrder> findByPassenger_Id(Long id);


}
