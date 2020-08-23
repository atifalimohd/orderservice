package com.oms.orderservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oms.orderservice.client.OrderItemsServiceClient;
import com.oms.orderservice.model.reponse.ItemResponse;
import com.oms.orderservice.model.createorder.CreateOrderItem;
import com.oms.orderservice.model.createorder.CreateOrderRequest;
import com.oms.orderservice.model.retrieveorder.RetrieveOrderItemsRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrderServiceControllerIntegrationTest {
	
	@Autowired
	MockMvc mockMvc;

	@InjectMocks
	OrderItemsServiceClient orderItemsServiceClient;
	
	@Test
	public void validateCreateOrder() throws Exception {

		when(orderItemsServiceClient.createOrderItems(Matchers.any(CreateOrderRequest.class))).thenReturn("SUCCESS");

		this.mockMvc.perform(
				post("/createOrder")
				.content(asJsonString(getCreateOrderRequest()))
        		.accept(MediaType.APPLICATION_JSON)
        		.contentType(MediaType.APPLICATION_JSON))
        		.andExpect(status().isOk())
        		.andExpect(MockMvcResultMatchers.jsonPath("$.message").exists());
		
	}

	@Test
	public void validateGetCall() throws Exception {

		ItemResponse itemResponse = new ItemResponse();
		itemResponse.setCode("RET200");
		when(orderItemsServiceClient.retrieveOrderItems(Matchers.any(RetrieveOrderItemsRequest.class))).thenReturn(itemResponse);
		this.mockMvc.perform(
				get("/retrieveOrder?orderId=abdefg",1)
		).
				andExpect(status().isOk());
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


	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
