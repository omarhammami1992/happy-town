package com.happytown.domain;

import java.time.Clock;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Component;
import com.happytown.domain.entity.AgeRange;
import com.happytown.domain.entity.Gift;
import com.happytown.domain.entity.Inhabitant;
import com.happytown.domain.entity.Notification;
import com.happytown.domain.port.GiftPort;
import com.happytown.domain.port.InhabitantPort;
import com.happytown.domain.port.NotificationPort;

@Component
public class AttributeGift {

    private final InhabitantPort inhabitantPort;
    private final GiftPort giftPort;
    private final NotificationPort notificationPort;
    private final Clock clock;

    public AttributeGift(InhabitantPort inhabitantPort, GiftPort giftPort, NotificationPort notificationPort, Clock clock) {
        this.inhabitantPort = inhabitantPort;
        this.giftPort = giftPort;
        this.notificationPort = notificationPort;
        this.clock = clock;
    }

    public void execute() {
        LocalDate now = LocalDate.now(clock);
        final List<Inhabitant> inhabitants = inhabitantPort.findEligible(now.minusYears(1));

        final Map<AgeRange, Gift> giftsByRange = giftPort.findByAgeRage();

        inhabitants.forEach(inhabitant -> {
            final int inhabitantAge = Period.between(inhabitant.getBirthDay(), now).getYears();
            final Optional<AgeRange> ageRangeOptional = fetchSuitableAgeRange(giftsByRange, inhabitantAge);

            if (ageRangeOptional.isPresent()) {
                String giftName = giftsByRange.get(ageRangeOptional.get()).getName();
                inhabitant.attributeGift(giftName);
                inhabitantPort.save(inhabitant);
                notificationPort.notify(new Notification(inhabitant.getEmail(),"subject", "body"));
            }
        });
    }

    private Optional<AgeRange> fetchSuitableAgeRange(Map<AgeRange, Gift> giftsByRange, int inhabitantAge) {
        return giftsByRange.keySet().stream()
                .filter(ageRange -> ageRange.getMinAge() <= inhabitantAge && inhabitantAge < ageRange.getMaxAge())
                .findFirst();
    }
}
