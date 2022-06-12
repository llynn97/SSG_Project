package project.PromotionInformationAPI.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import project.PromotionInformationAPI.domain.entity.enums.UserStat;
import project.PromotionInformationAPI.domain.entity.enums.UserType;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Long UserId;
    private String UserName;
    private UserType UserType;
    private UserStat UserStat;
}
