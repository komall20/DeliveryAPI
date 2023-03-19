package com.tgt.upcurve.DeliveryAPI.mapper;

import com.tgt.upcurve.DeliveryAPI.entity.DeliveryEntity;
import com.tgt.upcurve.DeliveryAPI.response.DeliveryResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-09-16T14:12:34+0530",
    comments = "version: 1.4.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.5.jar, environment: Java 11.0.16.1 (Eclipse Adoptium)"
)
public class DeliveryMapperImpl implements DeliveryMapper {

    @Override
    public DeliveryResponse toResponse(DeliveryEntity delivery) {
        if ( delivery == null ) {
            return null;
        }

        DeliveryResponse deliveryResponse = new DeliveryResponse();

        deliveryResponse.setId( delivery.getId() );
        deliveryResponse.setCustomerId( delivery.getCustomerId() );
        deliveryResponse.setStoreId( delivery.getStoreId() );
        deliveryResponse.setOrderId( delivery.getOrderId() );
        deliveryResponse.setImageId( delivery.getImageId() );
        byte[] imageCode = delivery.getImageCode();
        if ( imageCode != null ) {
            deliveryResponse.setImageCode( Arrays.copyOf( imageCode, imageCode.length ) );
        }
        deliveryResponse.setPaymentStatus( delivery.getPaymentStatus() );
        deliveryResponse.setDeliveryStatus( delivery.getDeliveryStatus() );
        deliveryResponse.setPickupDate( delivery.getPickupDate() );

        return deliveryResponse;
    }

    @Override
    public List<DeliveryResponse> toResponseList(List<DeliveryEntity> deliveryEntityList) {
        if ( deliveryEntityList == null ) {
            return null;
        }

        List<DeliveryResponse> list = new ArrayList<DeliveryResponse>( deliveryEntityList.size() );
        for ( DeliveryEntity deliveryEntity : deliveryEntityList ) {
            list.add( toResponse( deliveryEntity ) );
        }

        return list;
    }
}
