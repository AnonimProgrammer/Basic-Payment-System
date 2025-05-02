package com.docker.orderserviceapp.service.order;

import com.docker.orderserviceapp.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
