package project.PromotionInformationAPI.service.item;

import project.PromotionInformationAPI.domain.dto.RequestDTO.ItemAvailableRequestDTO;
import project.PromotionInformationAPI.domain.dto.RequestDTO.ItemDeleteRequestDTO;
import project.PromotionInformationAPI.domain.dto.RequestDTO.ItemInputRequestDTO;
import project.PromotionInformationAPI.domain.dto.ServiceDTO.FindAvailableItemDTO;
import project.PromotionInformationAPI.domain.dto.ServiceDTO.ItemDeleteDTO;
import project.PromotionInformationAPI.domain.dto.ServiceDTO.ItemInputDTO;
import project.PromotionInformationAPI.domain.entity.Item;

public interface ItemService {

    Item findItem(Long ItemId);
    ItemDeleteDTO deleteItem(ItemDeleteRequestDTO itemDeleteRequestDTO);
    FindAvailableItemDTO findAvailableItem(ItemAvailableRequestDTO itemAvailableRequestDTO);
    ItemInputDTO input(ItemInputRequestDTO itemInputRequestDTO);
}
