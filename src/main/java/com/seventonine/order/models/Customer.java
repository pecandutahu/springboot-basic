package com.seventonine.order.models;

import java.time.LocalDateTime;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name="customers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity

public class Customer {
    @Id
    @Column(name="customer_id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerId;

    @NotEmpty(message = "Name is required")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    @Column(name = "customer_name", nullable = false)
    private String customerName;

    @NotEmpty(message = "Address is required")
    @Size(min = 2, max = 100, message = "Address must be between 2 and 100 characters")
    @Column(name = "customer_address")
    private String customerAddress;

    @Column(name = "customer_code")
    private String customerCode;

    @NotEmpty(message = "Phone is required")
    @Size(min = 8, max = 16, message = "Phone must be between 2 and 16 characters")
    @Column(name = "customer_phone")
    private String customerPhone;

    @Column(name = "is_active")
    private int isActive;

    @Column(name = "last_order_date")
    private LocalDateTime lastOrderDate;

    @Column(name = "pic")
    private int pic;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.lastOrderDate = LocalDateTime.now();
        this.createdAt = LocalDateTime.now();
        this.isActive = 1;
    }

    @PreUpdate
    protected void onUpdate() {
        this.lastOrderDate = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
}
