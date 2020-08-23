package com.oms.orderservice.model.createorder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderItemsRequest {

    private List<CreateOrderItem> createOrderItems;
}
