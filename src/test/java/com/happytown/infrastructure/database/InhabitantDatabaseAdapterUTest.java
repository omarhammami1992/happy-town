package com.happytown.infrastructure.database;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.happytown.domain.entity.Inhabitant;
import com.happytown.domain.port.InhabitantPort;

@ExtendWith(MockitoExtension.class)
class InhabitantDatabaseAdapterUTest {

   private InhabitantPort inhabitantPort;

   @Mock
   private InhabitantJpaRepository inhabitantJpaRepository;

   @BeforeEach
   void setUp() {
      inhabitantPort = new InhabitantDatabaseAdapter(inhabitantJpaRepository);
   }

   @Test
   void findEligible_should_return_eligible_inhabitant() {
      // given
      String id = "5e18367a-1eb3-4b91-b87a-44cd210ef7ba";
      String email = "marie.carin@example.fr";
      LocalDate birthDay = LocalDate.of(2020, 1, 1);
      LocalDate arrivalDate = LocalDate.of(2020, 1, 2);

      InhabitantJpa inhabitantJpa = new InhabitantJpa(
            id,
            "Carin",
            "Marie",
            email,
            birthDay,
            arrivalDate,
            "12 rue des Lilas",
            null
      );

      LocalDate localDate = LocalDate.of(2022, 4, 26);
      when(inhabitantJpaRepository.findByArrivalDateLessThanEqualAndGiftIsNull(localDate)).thenReturn(List.of(inhabitantJpa));

      // when
      List<Inhabitant> inhabitants = inhabitantPort.findEligible(localDate);

      // then
      Inhabitant expectedInhabitant = new Inhabitant(id, email, birthDay, arrivalDate, null);
      assertThat(inhabitants).usingRecursiveFieldByFieldElementComparator().isEqualTo(List.of(expectedInhabitant));
   }

   @Test
   void save_should_persist_inhabitant() {
      // given
      String id = "5e18367a-1eb3-4b91-b87a-44cd210ef7ba";
      String email = "marie.carin@example.fr";
      LocalDate birthDay = LocalDate.of(2020, 1, 1);
      LocalDate arrivalDate = LocalDate.of(2020, 1, 2);
      String gift = "game";

      Inhabitant inhabitant = new Inhabitant(id, email, birthDay, arrivalDate, gift);

      // when
      inhabitantPort.save(inhabitant);

      // then
      ArgumentCaptor<InhabitantJpa> inhabitantJpaArgumentCaptor = ArgumentCaptor.forClass(InhabitantJpa.class);
      verify(inhabitantJpaRepository).save(inhabitantJpaArgumentCaptor.capture());
      InhabitantJpa inhabitantJpa = new InhabitantJpa(id, null, null, email, birthDay, arrivalDate, null, gift);
      assertThat(inhabitantJpaArgumentCaptor.getValue()).usingRecursiveComparison().isEqualTo(inhabitantJpa);
   }
}