package com.oms.orderservice.repository;

import com.oms.orderservice.model.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderServiceRepo extends JpaRepository<OrderEntity,String> {



    OrderEntity findByOrderNumber(String orderNumber);
}
