package org.phuong.seller.controller;

import lombok.RequiredArgsConstructor;
import org.phuong.seller.service.SellerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/store")
@RequiredArgsConstructor
public class StoreController {

  private final SellerService sellerService;

  @PostMapping("/assistant")
  public ResponseEntity<String> getAnswer(@RequestParam("q") String q, @RequestParam("chatId") String chatId) {
    return ResponseEntity.ok(sellerService.generateAnswer(chatId, q));
  }
}