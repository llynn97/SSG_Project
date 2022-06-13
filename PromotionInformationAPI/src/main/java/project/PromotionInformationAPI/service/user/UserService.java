package project.PromotionInformationAPI.service.user;

import project.PromotionInformationAPI.domain.dto.RequestDTO.UserDeleteRequestDTO;
import project.PromotionInformationAPI.domain.dto.RequestDTO.UserInputRequestDTO;
import project.PromotionInformationAPI.domain.dto.ServiceDTO.UserDeleteDTO;
import project.PromotionInformationAPI.domain.entity.User;

public interface UserService {
    User input(UserInputRequestDTO userInputRequestDTO);
    User findUser(Long UserId);
    UserDeleteDTO deleteUser(UserDeleteRequestDTO userDeleteRequestDTO);
}
