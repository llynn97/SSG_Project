package project.PromotionInformationAPI.repository.User;

import org.springframework.stereotype.Repository;
import project.PromotionInformationAPI.domain.entity.User;
import project.PromotionInformationAPI.domain.entity.enums.UserStat;
import project.PromotionInformationAPI.domain.entity.enums.UserType;

import java.util.*;

@Repository
public class UserJavaRepository implements UserRepository{
    private static Map<Long, User> MemberInformation = new HashMap<>();
    private static long sequence3 = 0L;

    @Override
    public User save(String UserName, UserType UserType) {
        User user=new User(++sequence3,UserName,UserType, UserStat.정상);
        MemberInformation.put(user.getUserId(),user);
        return MemberInformation.get(user.getUserId());
    }


    @Override
    public User findById(Long UserId) {
        return MemberInformation.get(UserId);
    }

    @Override
    public Optional<User> findByNameAndType(String UserName, UserType UserType, UserStat UserStat) {
        return MemberInformation.values().stream().filter(user -> user.getUserName().equals(UserName)).filter(user->user.getUserType().equals(UserType)).filter(user->user.getUserStat().equals(UserStat)).findAny();
    }



    @Override
    public boolean deleteUser(Long UserId) {
        User beforeUser=findById(UserId);
        User user=new User(beforeUser.getUserId(),beforeUser.getUserName(),beforeUser.getUserType(),UserStat.탈퇴);
        MemberInformation.put(beforeUser.getUserId(),user);
        if(findById(UserId).getUserStat().equals(UserStat.탈퇴)){
            return true;
        }
        return false;
    }

    public void clearInformation() {
        MemberInformation.clear();
    }

    public void saveUser(User user){
        MemberInformation.put(user.getUserId(),user);
    }
    public List<User> findAll(){
        return new ArrayList<>(MemberInformation.values()) ;
    }
}
