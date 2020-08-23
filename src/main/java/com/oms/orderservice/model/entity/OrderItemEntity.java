package com.oms.orderservice.model.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ORDER_ITEM_ENTITY")
public class OrderItemEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String productCode;
    /*@Column
    private String productName;*/
    @Column
    private Integer quantity;

    // newly added
  /*  @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "order_id"), name = "order_id")
    private OrderEntity orderEntity;*/
}
