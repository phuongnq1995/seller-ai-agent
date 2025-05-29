package org.phuong.seller.dto;

import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetails {
  private String orderNumber;
  private String address;
  private String customerName;
  private BigDecimal totalPrice;
  private String totalAmountCurrency;
  private String status;
  private List<OrderItem> items;
}
