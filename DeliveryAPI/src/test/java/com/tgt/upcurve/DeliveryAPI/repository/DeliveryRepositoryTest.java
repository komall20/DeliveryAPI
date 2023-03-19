package com.tgt.upcurve.DeliveryAPI.repository;

import com.tgt.upcurve.DeliveryAPI.DeliveryApiApplication;
import com.tgt.upcurve.DeliveryAPI.entity.DeliveryEntity;
import com.tgt.upcurve.DeliveryAPI.utility.JsonUtility;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = DeliveryApiApplication.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class DeliveryRepositoryTest {
    @Autowired
    DeliveryRepository deliveryRepository;

    private static final String DELIVERY_JSON_FILE_PATH = "/deliveryData.json";
    @Test
    public void testFindByImageId() throws Exception {
        DeliveryEntity delivery = JsonUtility.getDeliveryRequest(DELIVERY_JSON_FILE_PATH);
        DeliveryEntity savedDelivery = deliveryRepository.save(delivery);
        DeliveryEntity fetchedDelivery = deliveryRepository.findByImageId(24);
        assert fetchedDelivery != null;
    }

    @Test
    public void testFindByCustomerIdAndOrderId() throws Exception {
        DeliveryEntity delivery = JsonUtility.getDeliveryRequest(DELIVERY_JSON_FILE_PATH);
        DeliveryEntity savedDelivery = deliveryRepository.save(delivery);
        DeliveryEntity fetchedDelivery =deliveryRepository.findDeliveryByCustomerIdAndOrderId(100, 10);
        assert fetchedDelivery != null;
    }

    @Test
    public void testSaveDelivery() throws Exception {
       DeliveryEntity delivery = JsonUtility.getDeliveryRequest(DELIVERY_JSON_FILE_PATH);
        DeliveryEntity savedDelivery = deliveryRepository.save(delivery);
        Assertions.assertNotNull(savedDelivery.getId());
    }

    @Test
    public void testFetchDeliveryByCustomerId() throws Exception{
        DeliveryEntity delivery = JsonUtility.getDeliveryRequest(DELIVERY_JSON_FILE_PATH);
        DeliveryEntity savedDelivery = deliveryRepository.save(delivery);
        List<DeliveryEntity> fetchedDeliveriesByCustomerId = deliveryRepository.findAllByCustomerId(delivery.getCustomerId());
        assert fetchedDeliveriesByCustomerId.size() > 0;
    }

    @Test
    public  void testDeleteDelivery() throws Exception{
        DeliveryEntity delivery = JsonUtility.getDeliveryRequest(DELIVERY_JSON_FILE_PATH);
        DeliveryEntity savedDelivery = deliveryRepository.save(delivery);
        DeliveryEntity fetchedDelivery = deliveryRepository.findDeliveryByCustomerIdAndOrderId(100,10);
        assert fetchedDelivery != null;
        deliveryRepository.delete(savedDelivery);
        DeliveryEntity fetchedDelivery1 = deliveryRepository.findDeliveryByCustomerIdAndOrderId(100,10);
        assert fetchedDelivery1 == null;
    }


}
