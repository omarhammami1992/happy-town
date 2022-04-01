package com.happytown.infrastructure.file;

import com.happytown.domain.entity.AgeRange;
import com.happytown.domain.entity.Gift;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class GiftFileAdapterITest {

    @Autowired
    private GiftFileAdapter giftPort;


    @Test
    void findByAgeRage_should_get_gifts_by_age_rage_from_file() {
        // when
        final Map<AgeRange, Gift> ageRangeGiftMap = giftPort.findByAgeRage();

        // then
        assertThat(ageRangeGiftMap).usingRecursiveComparison().isEqualTo(Map.of(
                new AgeRange(0, 10), new Gift("Maracas"),
                new AgeRange(10, 30), new Gift("Pâte à modeler party tube"),
                new AgeRange(30, 100), new Gift("Domino mickey top départ")
        ));

    }
}