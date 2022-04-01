package com.happytown.application;

import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.happytown.domain.AttributeGift;

@ExtendWith(MockitoExtension.class)
class GiftAttributionControllerUTest {

   private GiftAttributionController giftAttributionController;

   @Mock
   private AttributeGift attributeGift;

   @BeforeEach
   void setUp() {
      giftAttributionController = new GiftAttributionController(attributeGift);
   }

   @Test
   void attributeGift_should_call_attributeGift_attribute_gift() {
      // when
      giftAttributionController.attributeGift();

      // then
      verify(attributeGift).execute();
   }
}