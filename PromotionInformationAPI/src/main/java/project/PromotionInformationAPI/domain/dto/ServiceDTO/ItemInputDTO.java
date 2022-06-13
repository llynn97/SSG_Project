package project.PromotionInformationAPI.domain.dto.ServiceDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.PromotionInformationAPI.domain.entity.Item;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ItemInputDTO {

    Item item;
    boolean duplicated;


    public ItemInputDTO(boolean duplicated){
        this.duplicated=duplicated;
    }
}
