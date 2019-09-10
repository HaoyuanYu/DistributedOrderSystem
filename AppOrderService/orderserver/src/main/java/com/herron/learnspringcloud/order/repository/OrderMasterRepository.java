package com.herron.learnspringcloud.order.repository;

import com.herron.learnspringcloud.order.DO.OrderMaster;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {
}
