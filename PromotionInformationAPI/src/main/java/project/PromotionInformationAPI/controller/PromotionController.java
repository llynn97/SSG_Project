package project.PromotionInformationAPI.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.PromotionInformationAPI.domain.dto.PromotionAvailableResponseDTO;
import project.PromotionInformationAPI.domain.dto.RequestDTO.PromotionAvailableRequestDTO;
import project.PromotionInformationAPI.domain.dto.RequestDTO.PromotionDeleteRequestDTO;
import project.PromotionInformationAPI.domain.dto.RequestDTO.PromotionInputRequestDTO;
import project.PromotionInformationAPI.domain.dto.ResponseDTO;
import project.PromotionInformationAPI.domain.dto.Result;
import project.PromotionInformationAPI.domain.dto.ServiceDTO.FindPromotionItemDTO;
import project.PromotionInformationAPI.domain.dto.ServiceDTO.PromotionDeleteDTO;
import project.PromotionInformationAPI.domain.dto.ServiceDTO.PromotionInputDTO;
import project.PromotionInformationAPI.domain.entity.Promotion;
import project.PromotionInformationAPI.exception.ErrorCode;
import project.PromotionInformationAPI.exception.Exception.AlreadySavedException;
import project.PromotionInformationAPI.exception.Exception.InvalidRequestException;
import project.PromotionInformationAPI.exception.Exception.NotFoundException;
import project.PromotionInformationAPI.exception.Exception.ServerException;
import project.PromotionInformationAPI.service.promotion.PromotionService;

import javax.validation.Valid;
@RestController
@RequiredArgsConstructor
@RequestMapping("/promotion")
public class PromotionController {
    private final PromotionService promotionService;

    @RequestMapping("/input") //프로모션 정보 입력
    public ResponseEntity<ResponseDTO> inputPromotion(@Valid @RequestBody PromotionInputRequestDTO promotionInputRequestDTO) throws Exception {
        if((promotionInputRequestDTO.getDiscountAmount()==null && promotionInputRequestDTO.getDiscountRate()==null) || (promotionInputRequestDTO.getDiscountRate()!=null && promotionInputRequestDTO.getDiscountAmount()!=null)){
            throw new InvalidRequestException(ErrorCode.INVALID_PROMOTION_REQUEST);
        }
        PromotionInputDTO promotionInputDTO=promotionService.input(promotionInputRequestDTO);
        if(promotionInputDTO.isDuplicated()){ //이미 존재하는 프로모션일 때
            throw new AlreadySavedException(ErrorCode.ALREADY_SAVED_PROMOTION);
        }
        Promotion promotion=promotionInputDTO.getPromotion();
        ResponseEntity<ResponseDTO> response;
        ResponseDTO responseDTO;
        if(promotion.equals(null)){ //저장 실패시
            throw new ServerException(ErrorCode.INTERNAL_SERVER_ERROR);

        }else{
            responseDTO = new ResponseDTO(Result.SUCCESS);
            response=new ResponseEntity<>(responseDTO, HttpStatus.OK);
        }
        return response;
    }

    @RequestMapping("/delete") //프로모션 정보 삭제
    public ResponseEntity<ResponseDTO> deletePromotion(@Valid @RequestBody PromotionDeleteRequestDTO promotionDeleteRequestDTO) throws Exception {
        PromotionDeleteDTO promotionDeleteDTO= promotionService.deletePromotion(promotionDeleteRequestDTO);
        ResponseEntity<ResponseDTO> response;
        ResponseDTO responseDTO;
        if(!promotionDeleteDTO.isExistPromotion()){
            throw new NotFoundException(ErrorCode.PROMOTION_NOT_FOUND);
        }else{
            if(!promotionDeleteDTO.isResult()){
                throw new ServerException(ErrorCode.INTERNAL_SERVER_ERROR);
            }else{
                responseDTO=new ResponseDTO(Result.SUCCESS);
                response=new ResponseEntity<>(responseDTO,HttpStatus.OK);
            }
        }
        return response;

    }

    @RequestMapping("/available")  //상품에 존재하는 프로모션 정보 반환
    public ResponseEntity<PromotionAvailableResponseDTO> findPromotionItem(@Valid @RequestBody PromotionAvailableRequestDTO promotionAvailableRequestDTO){
        FindPromotionItemDTO findPromotionItemDTO=promotionService.findPromotionItems(promotionAvailableRequestDTO);
        ResponseEntity<PromotionAvailableResponseDTO> response;
        PromotionAvailableResponseDTO promotionAvailableResponseDTO;
        if(findPromotionItemDTO.getItem()==null){ //ItemId에 해당하는 상품 정보 찾을 수 없을 때
            throw new NotFoundException(ErrorCode.ITEM_NOT_FOUND);
        }
        if(!findPromotionItemDTO.isApplicable()){ // 적용될 수 있는 프로모션이 없을 때
            promotionAvailableResponseDTO=new PromotionAvailableResponseDTO(Result.SUCCESS,findPromotionItemDTO.getItem(),"NO",null,null);
        }else{
            promotionAvailableResponseDTO=new PromotionAvailableResponseDTO(Result.SUCCESS,findPromotionItemDTO.getItem(),"YES",findPromotionItemDTO.getPromotionList(),findPromotionItemDTO.getDiscountPrice());

        }
        response=new ResponseEntity<>(promotionAvailableResponseDTO,HttpStatus.OK);
        return response;

    }
}
