package org.example.poti_fullstack.plans.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class PlansDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Res {
        private Long idx;
        private String name;
        private int monthlyPrice;
        private int yearlyPrice;
        private List<String> benefits;

        public static Res from(Plans entity) {
            return Res.builder()
                    .idx(entity.getIdx())
                    .name(entity.getName())
                    .monthlyPrice(entity.getMonthlyPrice())
                    .yearlyPrice(entity.getYearlyPrice())
                    .benefits(entity.getBenefits())
                    .build();
        }
    }
}