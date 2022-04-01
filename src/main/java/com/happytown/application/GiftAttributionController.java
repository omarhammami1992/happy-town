package com.happytown.application;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.happytown.domain.AttributeGift;

@RestController
@RequestMapping("/api/attribut/gift")
public class GiftAttributionController {

   private final AttributeGift attributeGift;

   public GiftAttributionController(AttributeGift attributeGift) {
      this.attributeGift = attributeGift;
   }

   @PostMapping
   public void attributeGift() {
      attributeGift.execute();
   }
}
