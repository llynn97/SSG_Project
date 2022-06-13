package project.PromotionInformationAPI.service.promotion;

import project.PromotionInformationAPI.domain.dto.RequestDTO.PromotionAvailableRequestDTO;
import project.PromotionInformationAPI.domain.dto.RequestDTO.PromotionDeleteRequestDTO;
import project.PromotionInformationAPI.domain.dto.RequestDTO.PromotionInputRequestDTO;
import project.PromotionInformationAPI.domain.dto.ServiceDTO.FindPromotionItemDTO;
import project.PromotionInformationAPI.domain.dto.ServiceDTO.PromotionDeleteDTO;
import project.PromotionInformationAPI.domain.dto.ServiceDTO.PromotionInputDTO;

public interface PromotionService {
    PromotionInputDTO input(PromotionInputRequestDTO promotionInputRequestDTO);
    FindPromotionItemDTO findPromotionItems(PromotionAvailableRequestDTO promotionAvailableRequestDTO);
    PromotionDeleteDTO deletePromotion(PromotionDeleteRequestDTO promotionDeleteRequestDTO);
}
