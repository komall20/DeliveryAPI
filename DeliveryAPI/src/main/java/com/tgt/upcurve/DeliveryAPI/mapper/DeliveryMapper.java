package com.tgt.upcurve.DeliveryAPI.mapper;

import com.tgt.upcurve.DeliveryAPI.entity.DeliveryEntity;
import com.tgt.upcurve.DeliveryAPI.response.DeliveryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface DeliveryMapper {

    DeliveryMapper INSTANCE = Mappers.getMapper(DeliveryMapper.class);
    DeliveryResponse toResponse(DeliveryEntity delivery);
    List<DeliveryResponse> toResponseList(List<DeliveryEntity> deliveryEntityList);

}
