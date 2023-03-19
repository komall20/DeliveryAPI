package com.tgt.upcurve.DeliveryAPI.repository;

import com.tgt.upcurve.DeliveryAPI.entity.DeliveryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.time.LocalDate;
import java.util.List;

@Repository
public interface DeliveryRepository extends JpaRepository<DeliveryEntity,Integer> {

    DeliveryEntity findDeliveryByCustomerIdAndOrderId(Integer customerId, Integer orderId);

    List<DeliveryEntity> findAllByCustomerId(Integer customerId);

   List<DeliveryEntity> findAllByPickupDate(LocalDate pickupDate);

    List<DeliveryEntity> findAllByImageId(Integer imageId);

    DeliveryEntity findByImageId(int i);
}
