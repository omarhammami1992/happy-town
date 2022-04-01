package com.happytown.application;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import com.happytown.domain.AttributeGift;

@ExtendWith(SpringExtension.class)
@WebMvcTest(GiftAttributionController.class)
class GiftAttributionControllerITest {

   @Autowired
   MockMvc mockMvc;

   @MockBean
   AttributeGift attributeGift;

   @Test
   void attribute_gift_should_return_200() throws Exception {
      // When
      mockMvc.perform(post("/api/attribut/gift"))
            .andExpect(status().isOk());
   }
}