package project.PromotionInformationAPI.domain.dto.RequestDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ItemAvailableRequestDTO {
    @NotNull(message = "UserId를 입력해야함")
    private Long UserId;
}
