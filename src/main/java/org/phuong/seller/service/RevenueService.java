package org.phuong.seller.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.phuong.seller.dto.DraftBill;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RevenueService {

  private final List<String> revenue;

  public void addBill(DraftBill draftBill) {
    revenue.add(draftBill.getTotalAmount().toString());
  }

}
