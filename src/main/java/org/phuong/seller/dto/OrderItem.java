package org.phuong.seller.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderItem {
  private String name;
  private int quantity;
  private BigDecimal price;
}
