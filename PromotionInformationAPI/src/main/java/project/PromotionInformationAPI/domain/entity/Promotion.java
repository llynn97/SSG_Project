package project.PromotionInformationAPI.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Promotion {
    private Long PromotionId;
    private String PromotionNm;
    private Long DiscountAmount;
    private Float DiscountRate;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate PromotionStartDate;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate PromotionEndDate;
}
