package com.seventonine.order.models;

import java.io.Serializable;
import java.security.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@IdClass(OrdersId.class)
public class Orders implements Serializable{

    // @Id
    // @Column(name = "order_id", unique = true, nullable = false)
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    // private int orderId;

    @Column(name = "order_code")
    private String orderCode;

    @Column(name = "order_date")
    private Timestamp orderDate;

    @Column(name = "total_price")
    private Double totalPrice;

    @Id
    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    private Customers customers;

    @Id
    @ManyToOne
    @JoinColumn(name = "items_id", referencedColumnName = "items_id")
    private Items items;

    @Column(name = "quantity")
    private int quantity;

}
