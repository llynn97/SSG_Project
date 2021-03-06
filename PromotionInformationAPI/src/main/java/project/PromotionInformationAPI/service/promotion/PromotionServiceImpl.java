package project.PromotionInformationAPI.service.promotion;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import project.PromotionInformationAPI.domain.dto.RequestDTO.PromotionAvailableRequestDTO;
import project.PromotionInformationAPI.domain.dto.RequestDTO.PromotionDeleteRequestDTO;
import project.PromotionInformationAPI.domain.dto.RequestDTO.PromotionInputRequestDTO;
import project.PromotionInformationAPI.domain.dto.ServiceDTO.FindPromotionItemDTO;
import project.PromotionInformationAPI.domain.dto.ServiceDTO.PromotionDeleteDTO;
import project.PromotionInformationAPI.domain.dto.ServiceDTO.PromotionInputDTO;
import project.PromotionInformationAPI.domain.entity.Item;
import project.PromotionInformationAPI.domain.entity.Promotion;
import project.PromotionInformationAPI.repository.Item.ItemRepository;
import project.PromotionInformationAPI.repository.Promotion.PromotionRepository;
import project.PromotionInformationAPI.repository.PromotionItem.PromotionItemRepository;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
@Slf4j
public class PromotionServiceImpl implements PromotionService {
    private final PromotionItemRepository promotionItemRepository;
    private final PromotionRepository promotionRepository;
    private final ItemRepository itemRepository;

    public PromotionInputDTO input(PromotionInputRequestDTO promotionInputRequestDTO) {
        if(promotionRepository.findByName(promotionInputRequestDTO.getPromotionNm()).isPresent()){
            return new PromotionInputDTO(true);
        }
        return new PromotionInputDTO(promotionRepository.save(promotionInputRequestDTO.getPromotionNm(),promotionInputRequestDTO.getDiscountAmount(),promotionInputRequestDTO.getDiscountRate(),promotionInputRequestDTO.getPromotionStartDate(),promotionInputRequestDTO.getPromotionEndDate()),false);
    }


    public FindPromotionItemDTO findPromotionItems(PromotionAvailableRequestDTO promotionAvailableRequestDTO) {
        Long ItemId=promotionAvailableRequestDTO.getItemId();
        Item item=itemRepository.findById(ItemId);
        List<Promotion> promotionsList=new ArrayList<>();
        if(item==null){
            //???????????? itemId??? ???????????? item??? ?????? ??? ??????
            return new FindPromotionItemDTO(null,false);
        }else{
            Long itemPrice=item.getItemPrice();
            List<Long> promotionIds= promotionItemRepository.findPromotionIds(ItemId); //itemId??? ????????? ??? ?????? promotionId??? ?????????
            if(promotionIds==null){ //promotionId??? ????????? ??? ?????? ??????????????? ?????????

                return new FindPromotionItemDTO(item,false);

            }else{
                List<Promotion> promotionList= promotionRepository.findAvailablePromotion(promotionIds); //?????? ???????????? ???????????? ???????????? ?????????
                if(promotionList==null){ //??????????????? ???????????? ??????????????? ?????????
                    return new FindPromotionItemDTO(item,false);

                }else{
                    for(Promotion promotion:promotionList){
                        Long discountPrice=item.getItemPrice();
                        if(promotion.getDiscountAmount()!=null){
                            discountPrice-=promotion.getDiscountAmount();

                        }else{
                            float discount=discountPrice*promotion.getDiscountRate();
                            long discount2=(long)discount;
                            discountPrice-=discount2;
                        }
                        if(discountPrice>0L && discountPrice<=itemPrice){
                            if(discountPrice.equals(itemPrice) && discountPrice<item.getItemPrice()){
                                promotionsList.add(promotion);
                            }
                            if(discountPrice<itemPrice){
                                promotionsList.clear();
                                promotionsList.add(promotion);
                                itemPrice=discountPrice;
                            }



                        }
                    }

                    if(itemPrice.equals(item.getItemPrice())){ //??????????????? ???????????? ?????????(???????????? ????????? ????????? ?????? 0???????????????)
                        return new FindPromotionItemDTO(item,false);
                    }
                    return new FindPromotionItemDTO(item,itemPrice,promotionsList,true);

                }
            }
        }

    }

    @Override
    public PromotionDeleteDTO deletePromotion(PromotionDeleteRequestDTO promotionDeleteRequestDTO) {
        Long promotionId=promotionDeleteRequestDTO.getPromotionId();
        if(promotionRepository.findById(promotionId)==null){
            return new PromotionDeleteDTO(false);
        }
        boolean check=promotionRepository.deletePromotion(promotionId);
        if(check==false){
            return new PromotionDeleteDTO(true,false);
        }
        boolean check2=promotionItemRepository.deletePromotionItem(promotionId);
        if(check2==false){
            return new PromotionDeleteDTO(true,false);
        }
        return new PromotionDeleteDTO(true,true);
    }

}
