package project.PromotionInformationAPI.domain.dto.ResponseDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.PromotionInformationAPI.domain.dto.Result;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInputResponseDTO {
    private Result result;  // 입력 성공, 실패 여부
    private Long UserId;
}
