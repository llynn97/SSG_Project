package project.PromotionInformationAPI.domain.dto.ServiceDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PromotionDeleteDTO {
    private boolean existPromotion;
    private boolean result;

    public PromotionDeleteDTO(boolean existPromotion){
        this.existPromotion=existPromotion;
    }
}
