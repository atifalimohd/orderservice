package com.oms.orderservice.serivce;

import com.oms.orderservice.client.OrderItemsServiceClient;
import com.oms.orderservice.exception.OrderServiceException;
import com.oms.orderservice.model.createorder.CreateOrderRequest;
import com.oms.orderservice.model.reponse.ItemResponse;
import com.oms.orderservice.model.retrieveorder.RetrieveOrder;
import com.oms.orderservice.model.retrieveorder.RetrieveOrderItemsRequest;
import com.oms.orderservice.model.entity.OrderEntity;
import com.oms.orderservice.repository.OrderServiceRepo;
import com.oms.orderservice.util.OrderServiceConstants;
import com.oms.orderservice.util.OrderServiceUtility;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderServiceImpl implements OrderService{

    @Autowired
    OrderItemsServiceClient orderItemsServiceClient;

    @Autowired
    OrderServiceRepo orderServiceRepo;

    @Autowired
    OrderServiceUtility orderServiceUtility;

    @Override
    public String createOrder(CreateOrderRequest createOrderRequest) {

        String orderNumber;
        String status = orderItemsServiceClient.createOrderItems(createOrderRequest);

        if(!status.equals(OrderServiceConstants.SUCCESS)){
           throw new OrderServiceException(status);
        }
        orderNumber = RandomString.make().toUpperCase();
        OrderEntity orderEntity = orderServiceUtility.convertModelToEntity(createOrderRequest);
        orderEntity.setOrderNumber(orderNumber);
        orderEntity.setOrderDate(new Date());
        orderServiceRepo.save(orderEntity);
        return orderNumber;

    }


    public RetrieveOrder retrieveOrder(String orderNumber) {

        OrderEntity orderEntity = orderServiceRepo.findByOrderNumber(orderNumber);

        if(orderEntity==null){
            throw new OrderServiceException(OrderServiceConstants.INVALID_ORDER_ID+orderNumber);
        }

        RetrieveOrder retrieveOrder = orderServiceUtility.converEntityToModel(orderEntity);

        // extracting product codes from the entity
        List<String> productCodes = retrieveOrder.getOrderItems().stream().map(x-> x.getProductCode()).collect(Collectors.toList());
        RetrieveOrderItemsRequest retrieveOrderItemsRequest = new RetrieveOrderItemsRequest();
        retrieveOrderItemsRequest.setProductCodes(productCodes);
        ItemResponse retrieveOrderItems = orderItemsServiceClient.retrieveOrderItems(retrieveOrderItemsRequest);

        return orderServiceUtility.mapOrderItems(retrieveOrder,retrieveOrderItems);

    }
}
