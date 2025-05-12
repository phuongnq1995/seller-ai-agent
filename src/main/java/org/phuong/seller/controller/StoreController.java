package org.phuong.seller.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.phuong.seller.service.RevenueService;
import org.phuong.seller.service.SellerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/store")
@RequiredArgsConstructor
public class StoreController {

  private final RevenueService revenueService;
  private final SellerService sellerService;

  @PostMapping("/assistant")
  public ResponseEntity<String> getAnswer(@RequestParam("q") String q) {
    return ResponseEntity.ok(sellerService.generateAnswer("1", q));
  }

  @GetMapping("/revenue")
  public ResponseEntity<List<String>> getAnswer() {
    return ResponseEntity.ok(revenueService.getRevenue());
  }
}