package com.herron.learnspringcloud.order.repository;

import com.herron.learnspringcloud.order.DO.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {
}
