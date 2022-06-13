package project.PromotionInformationAPI.domain.dto.ServiceDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.PromotionInformationAPI.domain.entity.Item;
import project.PromotionInformationAPI.domain.entity.enums.UserStat;

import java.util.ArrayList;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FindAvailableItemDTO {

    private boolean existUser;
    private List<Item> itemList=new ArrayList<>();
    private UserStat userStat;

    public  FindAvailableItemDTO(boolean existUser){
        this.existUser=existUser;
    }



    public  FindAvailableItemDTO(boolean existUser, UserStat userStat){
        this.existUser=existUser;
        this.userStat=userStat;
    }
}
