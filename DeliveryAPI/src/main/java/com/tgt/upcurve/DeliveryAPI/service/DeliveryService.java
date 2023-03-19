package com.tgt.upcurve.DeliveryAPI.service;

import com.tgt.upcurve.DeliveryAPI.entity.DeliveryEntity;
import com.tgt.upcurve.DeliveryAPI.mapper.DeliveryMapper;
import com.tgt.upcurve.DeliveryAPI.repository.DeliveryRepository;
import com.tgt.upcurve.DeliveryAPI.response.DeliveryResponse;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class DeliveryService{

    private final DeliveryRepository deliveryRepository;

    public DeliveryService(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    public DeliveryResponse fetchDeliveryByCustomerIdAndOrderId(Integer customerId, Integer orderId) {
        return DeliveryMapper.INSTANCE.toResponse(deliveryRepository.findDeliveryByCustomerIdAndOrderId(customerId, orderId));
    }

    public List<DeliveryResponse> fetchDeliveryByCustomerId(Integer customerId) {
        return DeliveryMapper.INSTANCE.toResponseList(deliveryRepository.findAllByCustomerId(customerId));
    }

    public DeliveryResponse saveDelivery(DeliveryEntity delivery) {
        DeliveryEntity saveDelivery = null;
        DeliveryEntity existingOrder = deliveryRepository.findDeliveryByCustomerIdAndOrderId(delivery.getCustomerId(), delivery.getOrderId());
        if(null == existingOrder){
            deliveryRepository.saveAndFlush(delivery);
        } else {
            deliveryRepository.save(delivery);
        }
        return fetchDeliveryByCustomerIdAndOrderId(delivery.getCustomerId(), delivery.getOrderId());
    }

    public List<DeliveryResponse> getDeliveryDetailsByDate(LocalDate pickupDate) {
        return DeliveryMapper.INSTANCE.toResponseList(deliveryRepository.findAllByPickupDate(pickupDate));
    }

    public List<DeliveryResponse> fetchDeliveryByImageId(Integer imageId) {
        return  DeliveryMapper.INSTANCE.toResponseList(deliveryRepository.findAllByImageId(imageId));
    }

    public void deleteDelivery(Integer customerId, Integer orderId) {
        DeliveryEntity existingDelivery = deliveryRepository.findDeliveryByCustomerIdAndOrderId(customerId, orderId);
        if(null != existingDelivery){
            deliveryRepository.delete(existingDelivery);
        }
    }


}
