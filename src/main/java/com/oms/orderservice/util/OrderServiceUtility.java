package com.oms.orderservice.util;


import com.oms.orderservice.model.createorder.CreateOrderRequest;
import com.oms.orderservice.model.reponse.ItemResponse;
import com.oms.orderservice.model.retrieveorder.RetrieveOrder;
import com.oms.orderservice.model.entity.OrderEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class OrderServiceUtility {

    @Autowired
    ModelMapper modelMapper;

    public OrderEntity convertModelToEntity(CreateOrderRequest createOrderRequest){

        return  modelMapper.map(createOrderRequest,OrderEntity.class);
    }

    public RetrieveOrder converEntityToModel(OrderEntity orderEntity) {

        return modelMapper.map(orderEntity, RetrieveOrder.class);
    }

    public RetrieveOrder mapOrderItems(RetrieveOrder retrieveOrder, ItemResponse<String,Map<String,String>> itemResponse) {

        // mapping product code with product name
        retrieveOrder.getOrderItems().forEach(x -> x.setProductName(itemResponse.getData().get(x.getProductCode())));

        return retrieveOrder;
    }

  
}
