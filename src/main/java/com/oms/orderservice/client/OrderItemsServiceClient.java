package com.oms.orderservice.client;

import com.oms.orderservice.exception.OrderServiceException;
import com.oms.orderservice.model.createorder.CreateOrderItemsRequest;
import com.oms.orderservice.model.createorder.CreateOrderRequest;
import com.oms.orderservice.model.reponse.ItemResponse;
import com.oms.orderservice.model.retrieveorder.RetrieveOrderItemsRequest;
import com.oms.orderservice.util.OrderServiceConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class OrderItemsServiceClient {

    @Autowired
    RestTemplate restTemplate;

    @Value("${orderitemservice.createorderitems}")
    String  orderItemServiceUrl;

    @Value("${orderitemservice.retrieveorderitems}")
    String orderItemsRetrieveUrl;

    public String createOrderItems(CreateOrderRequest order) {

        ResponseEntity<ItemResponse> response;

        CreateOrderItemsRequest createOrderItemsRequest = new CreateOrderItemsRequest();

        createOrderItemsRequest.setCreateOrderItems(order.getOrderItems());

        try {
            response = restTemplate.postForEntity(orderItemServiceUrl, createOrderItemsRequest, ItemResponse.class);

            if (response != null && response.getBody().getCode().equals("CRTD200")) {
                return OrderServiceConstants.SUCCESS;
            } else {

                return (String) response.getBody().getData();
            }

        }catch (Exception ex){
            throw new RuntimeException(ex.getMessage());
        }

    }

    public ItemResponse retrieveOrderItems(RetrieveOrderItemsRequest retrieveOrderItemsRequest){


       ResponseEntity<ItemResponse> response = null;

       try {
            response = restTemplate.postForEntity(orderItemsRetrieveUrl, retrieveOrderItemsRequest, ItemResponse.class);

            //
            if(response!=null && !response.getBody().getCode().equals("RET200")){

              throw new OrderServiceException((String) response.getBody().getCode());

            }else{
                return response.getBody();
            }

        }catch (RuntimeException ex){
            throw new RuntimeException((String) response.getBody().getCode());
        }

    }

}
