package project.PromotionInformationAPI.domain.dto.ServiceDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.PromotionInformationAPI.domain.entity.Promotion;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PromotionInputDTO {

    Promotion promotion;
    boolean duplicated;

    public PromotionInputDTO(boolean duplicated){
        this.duplicated=duplicated;
    }
}
