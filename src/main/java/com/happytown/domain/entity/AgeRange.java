package com.happytown.domain.entity;

public class AgeRange {
    private final Integer minAge;
    private final Integer maxAge;

    public AgeRange(Integer minAge, Integer maxAge) {
        this.minAge = minAge;
        this.maxAge = maxAge;
    }

    public Integer getMinAge() {
        return minAge;
    }

    public Integer getMaxAge() {
        return maxAge;
    }
}
