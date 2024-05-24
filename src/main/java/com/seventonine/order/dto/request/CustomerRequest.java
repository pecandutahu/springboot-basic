package com.seventonine.order.dto.request;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequest {
    @NotEmpty(message = "{customerName.required}")
    private String customerName;

    @NotEmpty(message = "{customerAddress.required}")
    private String customerAddress;

    @NotEmpty(message = "{customerCode.required}")
    private String customerCode;

    @NotEmpty(message = "{customerPhone.required}")
    private String customerPhone;

    private Integer isActive;

    private LocalDateTime lastOrderDate;
}
