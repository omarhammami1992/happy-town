package com.happytown.infrastructure.file;

import com.happytown.domain.entity.AgeRange;
import com.happytown.domain.entity.Gift;
import com.happytown.domain.entity.GiftException;
import com.happytown.domain.port.GiftPort;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@Component
public class GiftFileAdapter implements GiftPort {

    @Value("${file.giftsByAgeRange.path}")
    private String giftFilePath;

    private static final String DASH = "-";
    private static final String COMMA = ",";

    @Override
    public Map<AgeRange, Gift> findByAgeRage() throws GiftException {
        Map<AgeRange, Gift> giftsByAgeRange = new HashMap<>();
        try {
            Files.lines(Paths.get(giftFilePath))
                    .skip(1)
                    .forEach(
                            line -> addLine(line, giftsByAgeRange)
                    );
        } catch (IOException e) {
            throw new GiftException("Error while reading file content", e);
        }
        return giftsByAgeRange;
    }

    private void addLine(String line, Map<AgeRange, Gift> giftsByAgeRange) {
        String[] giftData = line.split(COMMA);
        String giftName = giftData[0];
        String[] ageRangeInfos = giftData[1].split(DASH);
        AgeRange ageRange = new AgeRange(Integer.valueOf(ageRangeInfos[0]), Integer.valueOf(ageRangeInfos[1]));

        giftsByAgeRange.put(ageRange, new Gift(giftName));
    }
}
