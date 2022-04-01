package com.happytown.infrastructure.database;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class InhabitantJpaRepositoryITest {

    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    InhabitantJpaRepository inhabitantJpaRepository;

    @Test
    void findAll_should_return_all_inhabitants_of_happy_town() {
        // When
        List<InhabitantJpa> inhabitantJpas = inhabitantJpaRepository.findAll();

        // Then
        assertThat(inhabitantJpas).hasSize(3);
    }

    @Test
    void findByArrivalDateLessThanEqualAndGiftIsNull_should_return_inhabitant_arrived_in_happy_town_more_than_a_year_and_having_gift() {
        // Given
        InhabitantJpa inhabitant1 = testEntityManager.find(InhabitantJpa.class, "5e18367a-1eb3-4b91-b87a-44cd210ef7ba");
        InhabitantJpa inhabitant2 = testEntityManager.find(InhabitantJpa.class, "aebb21fa-b981-4baa-9668-52be5ea3ce90");
        LocalDate localDate = LocalDate.now().minusYears(1);

        // When
        List<InhabitantJpa> inhabitantJpas = inhabitantJpaRepository.findByArrivalDateLessThanEqualAndGiftIsNull(localDate);

        // Then
        assertThat(inhabitantJpas).hasSize(2);
        assertThat(inhabitantJpas.get(0)).usingRecursiveComparison().isEqualTo(inhabitant1);
        assertThat(inhabitantJpas.get(1)).usingRecursiveComparison().isEqualTo(inhabitant2);
    }

}