package project.PromotionInformationAPI.repository.User;

import project.PromotionInformationAPI.domain.entity.User;
import project.PromotionInformationAPI.domain.entity.enums.UserStat;
import project.PromotionInformationAPI.domain.entity.enums.UserType;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User save(String UserName, UserType UserType);
    User findById(Long UserId);
    Optional<User> findByNameAndType(String UserName, UserType UserType, UserStat UserStat);
    boolean deleteUser(Long UserId);
    List<User> findAll();
}
