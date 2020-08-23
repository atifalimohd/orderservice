package com.oms.orderservice.model.retrieveorder;


import com.oms.orderservice.model.createorder.CreateOrderItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RetrieveOrder {

    private String orderNumber;
    private String customerName;
    private Date orderDate;
    private String shippingAddress;
    private List<RetrieveOrderItem> orderItems;
    private Double totalAmount;
}
