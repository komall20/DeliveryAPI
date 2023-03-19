package com.tgt.upcurve.DeliveryAPI.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Date;


@Data
@NoArgsConstructor
@Entity
@Table(name="order_delivery")
public class DeliveryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonAlias("id")
    private Integer id;

    @Column(name = "customer_id")
    @JsonProperty("customer_id")
    @JsonAlias("customerId")
    private Integer customerId;

    @Column(name="store_id")
    @JsonProperty("store_id")
    @JsonAlias("storeId")
    private Integer storeId;

    @Column(name="order_id")
    @JsonProperty("order_id")
    @JsonAlias("orderId")
    private Integer orderId;

    @Column(name="image_id")
    @JsonProperty("image_id")
    @JsonAlias("imageId")
    private Integer imageId;

    @Lob
    @Type(type = "org.hibernate.type.BinaryType")
    @Column(name="image_code",length=10000)
    @JsonProperty("image_code")
    @JsonAlias("imageCode")
    private byte[] imageCode;

    @Column(name="payment_status")
    @JsonProperty("payment_status")
    @JsonAlias("paymentStatus")
    private String paymentStatus;

    @Column(name = "delivery_status")
    @JsonProperty("delivery_status")
    @JsonAlias("deliveryStatus")
    private String deliveryStatus;

    @Column(name = "pickup_date")
    @JsonProperty("pickup_date")
    @JsonAlias("pickupDate")
    private LocalDateTime pickupDate;

}
