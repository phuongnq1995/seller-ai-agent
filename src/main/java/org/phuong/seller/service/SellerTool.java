package org.phuong.seller.service;

import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.phuong.seller.dto.OrderDetails;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SellerTool {

  private final OrderService orderService;

  @Tool(description = "Get order details")
  public OrderDetails getOrderDetails(String orderAddress, String customerName, String drinkNames) throws IOException {
    System.out.println("Get order details: " + drinkNames);
    OrderDetails orderDetails = orderService.getOrderDetails(orderAddress, customerName, drinkNames);
    System.out.println(orderDetails);
    return orderDetails;
  }

  @Tool(description = "Process order")
  public void processOrder(String orderAddress, String customerName, String orderNumber) throws IOException {
    System.out.println("Send notification to process order...");
    System.out.println("Process order with orderNumber: " +orderNumber+ ", orderAddress: " + orderAddress + " and totalAmount: " + customerName);
    orderService.processOrder(orderAddress);
  }
}
