package project.PromotionInformationAPI.domain.dto.ServiceDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class UserDeleteDTO {

    boolean existUser;
    boolean isDeleted;

    public UserDeleteDTO(boolean existUser){
        this.existUser=existUser;
    }

}
