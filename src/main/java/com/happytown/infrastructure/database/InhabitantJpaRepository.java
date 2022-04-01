package com.happytown.infrastructure.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface InhabitantJpaRepository extends JpaRepository<InhabitantJpa, UUID> {
    List<InhabitantJpa> findByArrivalDateLessThanEqualAndGiftIsNull(LocalDate localDate);
}
