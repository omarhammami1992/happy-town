package com.happytown.domain.port;

import java.util.Map;
import com.happytown.domain.entity.AgeRange;
import com.happytown.domain.entity.Gift;
import com.happytown.domain.entity.GiftException;

public interface GiftPort {
    Map<AgeRange, Gift> findByAgeRage()  throws GiftException;
}
