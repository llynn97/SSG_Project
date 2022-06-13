package project.PromotionInformationAPI.repository.Promotion;

import project.PromotionInformationAPI.domain.entity.Promotion;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PromotionRepository {
    Promotion save(String PromotionNm, Long DiscountAmount, Float DiscountRate, LocalDate PromotionStartDate, LocalDate PromotionEndDate);
    Promotion findById(Long PromotionId);
    List<Promotion> findAvailablePromotion(List<Long> PromotionIds);
    boolean deletePromotion(Long PromotionId);
    Optional<Promotion> findByName(String PromotionNm);
    List<Promotion> findAll();
}
