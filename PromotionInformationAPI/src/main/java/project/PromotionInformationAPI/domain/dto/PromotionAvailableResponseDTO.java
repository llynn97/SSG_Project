package project.PromotionInformationAPI.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.PromotionInformationAPI.domain.entity.Item;
import project.PromotionInformationAPI.domain.entity.Promotion;

import java.util.ArrayList;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PromotionAvailableResponseDTO {

    private Result result;
    private Item item;
    private String existPromotion; //할인적용 가능한 프로모션 존재 여부
    private List<Promotion> promotionList=new ArrayList<>();
    private Long discountedPrice;

}
