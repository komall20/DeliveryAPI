package com.tgt.upcurve.DeliveryAPI.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
public class DeliveryResponse {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("customer_id")
    private Integer customerId;

    @JsonProperty("store_id")
    private Integer storeId;

    @JsonProperty("order_id")
    private Integer orderId;

    @JsonProperty("image_id")
    private Integer imageId;

    @JsonProperty("image_code")
    private byte[] imageCode;

    @JsonProperty("payment_status")
    private String paymentStatus;

    @JsonProperty("delivery_status")
    private String deliveryStatus;

    @JsonProperty("pickup_date")
    private LocalDateTime pickupDate;

}
