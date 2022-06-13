package project.PromotionInformationAPI.domain.dto.ResponseDTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.PromotionInformationAPI.domain.dto.Result;
import project.PromotionInformationAPI.domain.entity.Item;

import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemAvailableResponseDTO {
    private Result result;
    private String existItem; //구매가능한 상품정보 존재 여부
    private List<Item> itemList=new ArrayList<>();
}
