package project.PromotionInformationAPI.domain.dto.ServiceDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ItemDeleteDTO {
    private boolean existItem;
    private boolean result;

    public ItemDeleteDTO(boolean existItem){
        this.existItem=existItem;
    }
}
