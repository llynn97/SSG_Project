package project.PromotionInformationAPI.domain.dto.RequestDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.PromotionInformationAPI.domain.entity.enums.UserType;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInputRequestDTO {

    @NotEmpty(message="UserName 을 입력해야함 ")
    private String UserName;

    @NotNull(message = "UserType 을 입력해야함")
    private UserType UserType;
}
