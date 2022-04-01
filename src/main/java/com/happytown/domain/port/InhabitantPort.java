package com.happytown.domain.port;

import java.time.LocalDate;
import java.util.List;
import com.happytown.domain.entity.Inhabitant;

public interface InhabitantPort {
    List<Inhabitant> findEligible(LocalDate localDate);

    void save(Inhabitant inhabitant);
}
