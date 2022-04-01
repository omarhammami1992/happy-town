package com.happytown.infrastructure.database;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import com.happytown.domain.entity.Inhabitant;
import com.happytown.domain.port.InhabitantPort;

@Component
public class InhabitantDatabaseAdapter implements InhabitantPort {

   private final InhabitantJpaRepository inhabitantJpaRepository;

   public InhabitantDatabaseAdapter(InhabitantJpaRepository inhabitantJpaRepository) {
      this.inhabitantJpaRepository = inhabitantJpaRepository;
   }

   @Override
   public List<Inhabitant> findEligible(LocalDate localDate) {
      List<InhabitantJpa> inhabitantJpas = inhabitantJpaRepository.findByArrivalDateLessThanEqualAndGiftIsNull(localDate);
      return inhabitantJpas.stream()
            .map(InhabitantJpa::toDomain)
            .collect(Collectors.toList());
   }

   @Override
   public void save(Inhabitant inhabitant) {
      InhabitantJpa inhabitantJpa = new InhabitantJpa(
            inhabitant.getId(),
            null,
            null,
            inhabitant.getEmail(),
            inhabitant.getBirthDay(),
            inhabitant.getArrivalDate(),
            null,
            inhabitant.getGift()
      );
      inhabitantJpaRepository.save(inhabitantJpa);
   }
}
