package com.seventonine.order.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "items")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Items {

    @Id
    @Column(name = "items_id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int itemsId;
    
    @Column(name = "items_name")
    private String itemsName;

    @Column(name = "items_code")
    private String itemsCode;

    @Column(name = "stock")
    private int stock;

    @Column(name = "price")
    private int price;

    @Column(name = "is_available")
    private int isAvailable;

    @Column(name = "last_re_stock")
    private int lastReStock;
}
