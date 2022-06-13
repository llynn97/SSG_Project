package project.PromotionInformationAPI.repository.PromotionItem;

import project.PromotionInformationAPI.domain.entity.PromotionItem;

import java.util.List;

public interface PromotionItemRepository {

    boolean save(Long PromotionId, List<Long> PromotionItems);
    PromotionItem findById(Long PromotionId);
    List<Long> findPromotionIds(Long ItemId);
    boolean deletePromotionItem(Long Id);
    PromotionItem saveOnePromotion(Long promotionId, Long itemId);
    void savePromotionItem(Long PromotionId,List<Long>ItemIds);
    List<PromotionItem> findAll();
}
