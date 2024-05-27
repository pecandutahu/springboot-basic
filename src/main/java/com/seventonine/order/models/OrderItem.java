package com.seventonine.order.models;

import java.util.List;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "order_items")

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class OrderItem {
    @Id
    @Column(name = "order_item_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer orderItemId;
    
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Orders order;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Items item;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price")
    private int price;

    @Column(name = "subtotal")
    private int subtotal;

    public OrderItem(Items item, int quantity, Orders order) {
        this.item = item;
        this.quantity = quantity;
        this.order = order;
    }
}
