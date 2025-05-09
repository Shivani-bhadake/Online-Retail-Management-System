package com.techhub.demo.Model;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class PaymentModel {
    private int paymentId;
    private int orderId;
    private String paymentMethod;
    private BigDecimal amount;
    private String status;
}
