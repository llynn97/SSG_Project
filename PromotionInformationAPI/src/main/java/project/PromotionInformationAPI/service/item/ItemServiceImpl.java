package project.PromotionInformationAPI.service.item;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import project.PromotionInformationAPI.domain.dto.RequestDTO.ItemAvailableRequestDTO;
import project.PromotionInformationAPI.domain.dto.RequestDTO.ItemDeleteRequestDTO;
import project.PromotionInformationAPI.domain.dto.RequestDTO.ItemInputRequestDTO;
import project.PromotionInformationAPI.domain.dto.ServiceDTO.FindAvailableItemDTO;
import project.PromotionInformationAPI.domain.dto.ServiceDTO.ItemDeleteDTO;
import project.PromotionInformationAPI.domain.dto.ServiceDTO.ItemInputDTO;
import project.PromotionInformationAPI.domain.entity.Item;
import project.PromotionInformationAPI.domain.entity.User;
import project.PromotionInformationAPI.domain.entity.enums.UserStat;
import project.PromotionInformationAPI.repository.Item.ItemRepository;
import project.PromotionInformationAPI.repository.Promotion.PromotionRepository;
import project.PromotionInformationAPI.repository.PromotionItem.PromotionItemRepository;
import project.PromotionInformationAPI.repository.User.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ItemServiceImpl implements ItemService{
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final PromotionItemRepository promotionItemRepository;
    private final PromotionRepository promotionRepository;


    public ItemInputDTO input(ItemInputRequestDTO itemInputRequestDTO) { //상품 입력
        if(itemRepository.findByNameAneType(itemInputRequestDTO.getItemName(),itemInputRequestDTO.getItemType()).isPresent()){
            return new ItemInputDTO(true);
        }
        Item item=itemRepository.save(itemInputRequestDTO.getItemName(),itemInputRequestDTO.getItemType(),itemInputRequestDTO.getItemPrice(),itemInputRequestDTO.getItemDisplayStartDate(),itemInputRequestDTO.getItemDisplayEndDate());
        if(item!=null){ //상품 저장에 오류가 없다면
            return new ItemInputDTO(item,false);
        }
        return new ItemInputDTO(null,false);
    }


    public Item findItem(Long ItemId) {
        return itemRepository.findById(ItemId);

    }


    public ItemDeleteDTO deleteItem(ItemDeleteRequestDTO itemDeleteRequestDTO) {

        if(itemRepository.findById(itemDeleteRequestDTO.getItemId())==null){ //ItemId에 해당하는 상품이 존재하지 않을 때
            return new ItemDeleteDTO(false);
        }
        if(!itemRepository.deleteItem(itemDeleteRequestDTO.getItemId())){//상품 삭제 오류시
            return new ItemDeleteDTO(true,false);
        }
        if(!promotionItemRepository.deletePromotionItem(itemDeleteRequestDTO.getItemId())){//프로모션상품 삭제 오류시
            return new ItemDeleteDTO(true,false);
        }
        return new ItemDeleteDTO(true,true);


    }


    public FindAvailableItemDTO findAvailableItem(ItemAvailableRequestDTO itemAvailableRequestDTO) {
        User user=userRepository.findById(itemAvailableRequestDTO.getUserId());
        List<Item> itemList=new ArrayList<>();
        if(user==null){ //UserId에 해당하는 회원정보가 없다면
            return new FindAvailableItemDTO(false); // existUser: false
        }else{
            if(user.getUserStat().equals(UserStat.탈퇴)){ //탈퇴 회원이라면
                return new FindAvailableItemDTO(true,user.getUserStat());
            }
            else{
                itemList=itemRepository.findAvailableAll(user.getUserType()); //전시기간 사이에 있는 상품 정보
                return new FindAvailableItemDTO(true,itemList,user.getUserStat());
            }

        }

    }
}
