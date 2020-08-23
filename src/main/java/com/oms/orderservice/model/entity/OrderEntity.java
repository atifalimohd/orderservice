package com.oms.orderservice.model.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "ORDER_ENTITY")
public class OrderEntity implements Serializable {

    @Id
    @GeneratedValue
    private Long orderId;
    @Column
    private String orderNumber;
    @Column
    private String customerName;
    @Column
    private Date orderDate;
    @Column
    private String shippingAddress;
    @Column
    private Double totalAmount;

    //@OneToMany(mappedBy = "orderEntity") // newly added
    //@Cascade(value = org.hibernate.annotations.CascadeType.ALL)
    //@OneToMany(targetEntity = OrderItemEntity.class,mappedBy = "orderEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="fid",referencedColumnName = "orderNumber")
    private List<OrderItemEntity> orderItems;

}
