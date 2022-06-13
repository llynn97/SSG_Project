package project.PromotionInformationAPI.domain.dto.RequestDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDeleteRequestDTO {

    @NotNull(message = "ItemId를 입력해야함")
    private Long ItemId;
}
