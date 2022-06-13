package project.PromotionInformationAPI.domain.dto.ServiceDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.PromotionInformationAPI.domain.entity.Item;
import project.PromotionInformationAPI.domain.entity.Promotion;

import java.util.ArrayList;
import java.util.List;
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FindPromotionItemDTO {
    private Item item;
    private Long discountPrice;
    private List<Promotion> promotionList=new ArrayList<>();
    private boolean applicable;


    public FindPromotionItemDTO(Item item,Boolean applicable){
        this.item=item;
        this.applicable=applicable;
    }
}
