package com.oms.orderservice.model.retrieveorder;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RetrieveOrderItem {

    private String productCode;
    private String productName;
    private Integer quantity;
}
