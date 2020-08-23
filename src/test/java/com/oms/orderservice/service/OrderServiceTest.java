package com.oms.orderservice.service;

import com.oms.orderservice.client.OrderItemsServiceClient;
import com.oms.orderservice.model.createorder.CreateOrderItem;
import com.oms.orderservice.model.createorder.CreateOrderRequest;
import com.oms.orderservice.model.entity.OrderEntity;
import com.oms.orderservice.repository.OrderServiceRepo;
import com.oms.orderservice.serivce.OrderServiceImpl;
import com.oms.orderservice.util.OrderServiceUtility;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class OrderServiceTest {
	
	@InjectMocks
	OrderServiceImpl orderServiceImpl;

	@Mock
	OrderItemsServiceClient orderItemsServiceClient;

	@Mock
	OrderServiceRepo orderServiceRepo;

	@Mock
	OrderServiceUtility orderServiceUtility;
	
	@Test
	public void createOrderTest(){
		when(orderItemsServiceClient.createOrderItems(Mockito.any(CreateOrderRequest.class))).thenReturn("SUCCESS");
		when(orderServiceUtility.convertModelToEntity(Mockito.any(CreateOrderRequest.class))).thenReturn(getEntity());
		when(orderServiceRepo.save(Mockito.any())).thenReturn(getEntity());
		String orderNumber = orderServiceImpl.createOrder(getCreateOrderRequest());
		Assert.assertNotNull(orderNumber);
		verify(orderItemsServiceClient,times(1)).createOrderItems(Mockito.any(CreateOrderRequest.class));
		verify(orderServiceRepo,times(1)).save(Mockito.any(OrderEntity.class));
	}


	private CreateOrderRequest getCreateOrderRequest() {

		CreateOrderRequest createOrderRequest = new CreateOrderRequest();
		CreateOrderItem createOrderItem = new CreateOrderItem();
		createOrderItem.setProductCode("pc001");
		createOrderItem.setQuantity(5);
		createOrderRequest.setTotalAmount(25.80);
		List<CreateOrderItem> createOrderItemList = new ArrayList<>();
		createOrderItemList.add(createOrderItem);

		createOrderRequest.setCustomerName("abcdef");
		createOrderRequest.setShippingAddress("address1,city1");
		createOrderRequest.setOrderItems(createOrderItemList);
		return createOrderRequest;
	}

	private OrderEntity getEntity(){
		return new OrderEntity();
	}
}
