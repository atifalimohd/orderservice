package com.oms.orderservice.model.createorder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderRequest {

    @NotBlank(message = "Please provide customer name")
    @Size(min=3,message="Customer name should contain minimum 3 characters")
    @Size(max=15,message="Customer name should contain maximum 15 characters")
    private String customerName;
    
    @NotBlank(message = "Please provide shipping address")
    @Size(min=5,message="Shipping Address should contain minimum 5 characters")
    @Size(max=20,message="Shipping Address should contain maximum 20 characters")
    private String shippingAddress;

    @Valid
    @NotEmpty(message="Please provide the order items list")
    private List<CreateOrderItem> orderItems;

    @NotNull(message="Please provide the amount")
    @Positive(message="Amount should be greater than 0")
    private Double totalAmount;
}
