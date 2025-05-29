package org.phuong.seller.service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import lombok.RequiredArgsConstructor;
import org.phuong.seller.dto.OrderDetails;
import org.phuong.seller.dto.OrderItem;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

  private final Map<String, BigDecimal> priceMap = Map.of(
    "Espresso", new BigDecimal("2.5"),
    "Cappuccino", new BigDecimal("2.3"),
    "Latte", new BigDecimal("2.7"),
    "Green Tea", new BigDecimal("1.5"),
    "Black Tea", new BigDecimal("1.2"),
    "Hot Chocolate", new BigDecimal("1.8"),
    "Iced Lemonade", new BigDecimal("2.0"),
    "Caramel Macchiato", new BigDecimal("2.5"),
    "Mocha Frappe", new BigDecimal("3")
  );

  private final ConcurrentMap<String, OrderDetails> orderDetailsMap = new ConcurrentHashMap<>();

  public OrderDetails getOrderDetails(String orderAddress, String customerName, String drinkNames) {
    List<OrderItem> items = Arrays.stream(drinkNames.split(","))
        .map(drinkName -> new OrderItem(drinkName, 1, priceMap.getOrDefault(drinkName, BigDecimal.valueOf(2.5f))))
        .toList();

    OrderDetails orderDetails = new OrderDetails();
    orderDetails.setOrderNumber(String.valueOf(new Random().nextInt()));
    orderDetails.setAddress(orderAddress);
    orderDetails.setCustomerName(customerName);
    orderDetails.setStatus("WaitForConfirm");
    orderDetails.setItems(items);
    orderDetails.setTotalPrice(items.stream().map(OrderItem::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add));
    orderDetails.setTotalAmountCurrency("USD");

    orderDetailsMap.put(orderDetails.getOrderNumber(), orderDetails);

    return orderDetails;
  }

  public void processOrder(String orderNumber) {
    OrderDetails orderDetails = orderDetailsMap.get(orderNumber);
    System.out.println(orderDetails);
  }

}
