package com.happytown.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Clock;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.happytown.domain.entity.AgeRange;
import com.happytown.domain.entity.Gift;
import com.happytown.domain.entity.Inhabitant;
import com.happytown.domain.entity.Notification;
import com.happytown.domain.port.GiftPort;
import com.happytown.domain.port.InhabitantPort;
import com.happytown.domain.port.NotificationPort;

@ExtendWith(MockitoExtension.class)
class AttributeGiftUTest {

    private AttributeGift attributeGift;

    @Mock
    private InhabitantPort inhabitantPort;

    @Mock
    private GiftPort giftPort;

    @Mock
    private NotificationPort notificationPort;

    private Clock clock;

    private final LocalDate TODAY_2022_04_26 = LocalDate.of(2022, 4, 26);

    @BeforeEach
    void setUp() {
        clock = Clock.fixed(TODAY_2022_04_26.atStartOfDay(ZoneId.systemDefault()).toInstant(), ZoneId.of("Europe/Paris"));
        attributeGift = new AttributeGift(inhabitantPort, giftPort, notificationPort, clock);
    }

    @Test
    void execute_should_attribute_gifts() {
        // given
        final Inhabitant inhabitant = new Inhabitant("5e18367a-1eb3-4b91-b87a-44cd210ef7ba", "marie.carin@example.fr", LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1), null);
        when(inhabitantPort.findEligible(TODAY_2022_04_26.minusYears(1))).thenReturn(List.of(inhabitant));

        when(giftPort.findByAgeRage()).thenReturn(Map.of(
                new AgeRange(0, 3), new Gift("gift 1"),
                new AgeRange(3, 6), new Gift("gift 2")
        ));

        // when
        attributeGift.execute();

        // then
        final Inhabitant inhabitantToSave = new Inhabitant("5e18367a-1eb3-4b91-b87a-44cd210ef7ba", "marie.carin@example.fr", LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1), "gift 1");
        ArgumentCaptor<Inhabitant> inhabitantArgumentCaptor = ArgumentCaptor.forClass(Inhabitant.class);
        verify(inhabitantPort).save(inhabitantArgumentCaptor.capture());
        assertThat(inhabitantArgumentCaptor.getValue()).usingRecursiveComparison().isEqualTo(inhabitantToSave);
    }

    @Test
    void execute_should_send_email_to_inhabitant() {
        // given
        final Inhabitant inhabitant = new Inhabitant("5e18367a-1eb3-4b91-b87a-44cd210ef7ba", "marie.carin@example.fr", LocalDate.of(2020, 1, 1), LocalDate.of(2020, 1, 1), null);
        when(inhabitantPort.findEligible(TODAY_2022_04_26.minusYears(1))).thenReturn(List.of(inhabitant));

        when(giftPort.findByAgeRage()).thenReturn(Map.of(
              new AgeRange(0, 3), new Gift("gift 1"),
              new AgeRange(3, 6), new Gift("gift 2")
        ));

        // when
        attributeGift.execute();

        // then
        final Notification notification = new Notification("marie.carin@example.fr", "subject", "body");
        ArgumentCaptor<Notification> notificationArgumentCaptor = ArgumentCaptor.forClass(Notification.class);
        verify(notificationPort).notify(notificationArgumentCaptor.capture());
        assertThat(notificationArgumentCaptor.getValue()).usingRecursiveComparison().isEqualTo(notification);
    }
}