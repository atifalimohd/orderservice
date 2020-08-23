package com.oms.orderservice.model.retrieveorder;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RetrieveOrderItemsRequest {

    private List<String> productCodes;
}
