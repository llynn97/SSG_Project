package project.PromotionInformationAPI.service.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import project.PromotionInformationAPI.domain.dto.RequestDTO.UserDeleteRequestDTO;
import project.PromotionInformationAPI.domain.dto.RequestDTO.UserInputRequestDTO;
import project.PromotionInformationAPI.domain.dto.ServiceDTO.UserDeleteDTO;
import project.PromotionInformationAPI.domain.entity.User;
import project.PromotionInformationAPI.repository.User.UserRepository;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;


    public User input(UserInputRequestDTO userInputRequestDTO) {

        return userRepository.save(userInputRequestDTO.getUserName(),userInputRequestDTO.getUserType());
    }


    public User findUser(Long UserId) {
        return userRepository.findById(UserId);
    }


    public UserDeleteDTO deleteUser(UserDeleteRequestDTO userDeleteRequestDTO) {
        Long userId=userDeleteRequestDTO.getUserId();
        if(userRepository.findById(userId)==null){
            return new UserDeleteDTO(false);
        }
        if(!userRepository.deleteUser(userId)){
            return new UserDeleteDTO(true,false);
        }
        return new UserDeleteDTO(true,true);

    }
}
