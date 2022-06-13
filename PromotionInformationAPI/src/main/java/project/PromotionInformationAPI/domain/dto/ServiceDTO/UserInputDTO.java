package project.PromotionInformationAPI.domain.dto.ServiceDTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.PromotionInformationAPI.domain.entity.User;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserInputDTO {
    boolean duplicated;
    User user;

    public UserInputDTO(boolean duplicated){
        this.duplicated=duplicated;
    }
}
