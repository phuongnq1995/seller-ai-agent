package org.phuong.seller.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.phuong.seller.dto.DraftBill;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SellerTool {

  private final RevenueService revenueService;

  @Tool(description = "Make a draft bill")
  public DraftBill makeDraftBill(String drinkNames) throws IOException {
    System.out.println("Make a draft bill for: " + drinkNames);
    DraftBill draftBill = new DraftBill(BigDecimal.valueOf(10.3f));
    return draftBill;
  }

  @Tool(description = "Process order")
  public void processOrder(String confirmation, DraftBill draftBill) throws IOException {
    System.out.println("Send notification to process order...");
    System.out.println("Process order with confirmation: " + confirmation + " and totalAmount: " + draftBill);
    revenueService.addBill(draftBill);
  }

  @Tool(description = "Get today weather")
  public String getTodayWeather() throws IOException {
    System.out.println("Get today weather");
    int i = new Random().nextInt(2);
    String weather = switch (i) {
      case 0 -> "Sunny";
      case 1 -> "Cloudy";
      case 2 -> "Rain";
      default -> throw new IllegalStateException("Unexpected value: " + i);
    };
    return new ObjectMapper().writeValueAsString(weather);
  }
}
