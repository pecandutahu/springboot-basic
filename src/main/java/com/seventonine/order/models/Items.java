package com.seventonine.order.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    
    @NotEmpty(message = "Item name cannot be null.")
    @Size(min = 2, max = 200, message = ("Item name is must between 2 and 100 character"))
    @Column(name = "items_name")
    private String itemsName;

    @Column(name = "items_code")
    private String itemsCode;

    @NotNull(message = "numericField: positive number value is required")
    @Min(value=0, message="numericField: positive number, min 0 is required")
    @Column(name = "stock")
    private int stock;

    
    @NotNull(message = "numericField: positive number value is required")
    @Min(value=0, message="numericField: positive number, min 0 is required")
    @Column(name = "price")
    private int price;

    @Column(name = "is_available")
    private int isAvailable;

    @Column(name = "last_re_stock")
    private int lastReStock;
}
