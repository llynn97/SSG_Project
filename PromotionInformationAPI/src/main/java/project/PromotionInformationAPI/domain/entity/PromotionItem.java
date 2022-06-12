package project.PromotionInformationAPI.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PromotionItem {

    private Long PromotionItemId;
    private Long PromotionId;
    private Long ItemId;
}
