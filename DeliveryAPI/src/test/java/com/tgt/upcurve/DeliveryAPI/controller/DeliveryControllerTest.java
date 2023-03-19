package com.tgt.upcurve.DeliveryAPI.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.tgt.upcurve.DeliveryAPI.entity.DeliveryEntity;
import com.tgt.upcurve.DeliveryAPI.utility.JsonUtility;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class DeliveryControllerTest {
    @Autowired
    MockMvc mockMvc;

    private static final String URI_FETCH_CUSTOMER_ID_ORDER_ID = "/delivery_api/v1/fetch_delivery_by_id/customer_id/{customer_id}/order_id/{order_id}";
    private static final String URI_FETCH_CUSTOMER_ID = "/delivery_api/v1/fetch_delivery_by_customer_id/{customer_id}";
    private static final String URI_FETCH_IMAGE_ID = "/delivery_api/v1/fetch_delivery_by_image_id/{image_id}";
    private static final String URI_DELETE_CUSTOMER_ID_ORDER_ID = "/delivery_api/v1/customer_id/{customer_id}/order_id/{order_id}";
    private static final String URI_SAVE = "/delivery_api/v1/";
    private static final String DELIVERY_JSON_FILE_PATH = "/deliveryData.json";

    @Test
    public void testFetchByCustomerIdAndOrderId() throws Exception {
        String orderString = JsonUtility.getResourceAsString(DELIVERY_JSON_FILE_PATH);
        MvcResult responseSave = mockMvc.perform(post(URI_SAVE)
                        .content(orderString)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
        String savedResponse = responseSave.getResponse().getContentAsString();
        MvcResult responseFetch = mockMvc.perform(get(URI_FETCH_CUSTOMER_ID_ORDER_ID, 100, 10)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
        String fetchedResponse = responseFetch.getResponse().getContentAsString();

        DeliveryEntity savedOrder = JsonUtility.readValue(savedResponse, DeliveryEntity.class);
        DeliveryEntity fetchedOrder = JsonUtility.readValue(fetchedResponse, DeliveryEntity.class);
        Assertions.assertEquals(savedOrder.getOrderId(), fetchedOrder.getOrderId());
        Assertions.assertEquals(savedOrder.getCustomerId(), fetchedOrder.getCustomerId());
        Assertions.assertEquals(savedOrder.getStoreId(), fetchedOrder.getStoreId());
    }

    @Test
    public void testFetchDeliveryByCustomerId() throws Exception {
        String deliveryString = JsonUtility.getResourceAsString(DELIVERY_JSON_FILE_PATH);
        MvcResult responseSave = mockMvc.perform(post(URI_SAVE)
                        .content(deliveryString)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
        String savedResponse = responseSave.getResponse().getContentAsString();
        MvcResult responseFetch = mockMvc.perform(get(URI_FETCH_CUSTOMER_ID, 100)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
        String fetchedResponse = responseFetch.getResponse().getContentAsString();
        DeliveryEntity savedDelivery = JsonUtility.readValue(savedResponse, DeliveryEntity.class);
        List<DeliveryEntity> fetchedDelivery = JsonUtility.readValue(fetchedResponse, new TypeReference<List<DeliveryEntity>>() {
        });

        Assertions.assertEquals(savedDelivery.getOrderId(), fetchedDelivery.get(0).getOrderId());
        Assertions.assertEquals(savedDelivery.getCustomerId(), fetchedDelivery.get(0).getCustomerId());
        Assertions.assertEquals(savedDelivery.getStoreId(), fetchedDelivery.get(0).getStoreId());
    }

    @Test
    public void testFetchDeliveryByImageId() throws Exception {
        String deliveryString = JsonUtility.getResourceAsString(DELIVERY_JSON_FILE_PATH);
        MvcResult responseSave = mockMvc.perform(post(URI_SAVE)
                        .content(deliveryString)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
        String savedResponse = responseSave.getResponse().getContentAsString();
        MvcResult responseFetch = mockMvc.perform(get(URI_FETCH_IMAGE_ID, 24)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
        String fetchedResponse = responseFetch.getResponse().getContentAsString();
        DeliveryEntity savedDelivery = JsonUtility.readValue(savedResponse, DeliveryEntity.class);
        List<DeliveryEntity> fetchedDelivery = JsonUtility.readValue(fetchedResponse, new TypeReference<List<DeliveryEntity>>() {
        });

        Assertions.assertEquals(savedDelivery.getOrderId(), fetchedDelivery.get(0).getOrderId());
        Assertions.assertEquals(savedDelivery.getCustomerId(), fetchedDelivery.get(0).getCustomerId());
        Assertions.assertEquals(savedDelivery.getStoreId(), fetchedDelivery.get(0).getStoreId());
    }

    @Test
    public void testDeleteDelivery() throws Exception {
        String orderString = JsonUtility.getResourceAsString(DELIVERY_JSON_FILE_PATH);
        MvcResult responseSave = mockMvc.perform(post(URI_SAVE)
                        .content(orderString)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
        String savedResponse = responseSave.getResponse().getContentAsString();

        MvcResult responseFetch = mockMvc.perform(get(URI_FETCH_CUSTOMER_ID_ORDER_ID, 100, 10)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
        String fetchedResponse = responseFetch.getResponse().getContentAsString();
        DeliveryEntity fetchedDelivery = JsonUtility.readValue(fetchedResponse, DeliveryEntity.class);
        assert fetchedDelivery != null;

        MvcResult responseDelete = mockMvc.perform(delete(URI_DELETE_CUSTOMER_ID_ORDER_ID, 100, 10)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andReturn();

        MvcResult responseFetch1 = mockMvc.perform(get(URI_FETCH_CUSTOMER_ID_ORDER_ID, 100, 10)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
        String fetchedResponse1 = responseFetch1.getResponse().getContentAsString();

        assert fetchedResponse1.isEmpty();
    }
}
