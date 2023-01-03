package com.example.test.repository.ride;

import com.example.test.domain.ride.FavoriteOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFavoriteOrderRepository extends JpaRepository<FavoriteOrder, Long> {
}
