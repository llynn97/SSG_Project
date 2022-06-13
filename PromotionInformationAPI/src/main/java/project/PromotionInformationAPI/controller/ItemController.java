package project.PromotionInformationAPI.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.PromotionInformationAPI.domain.dto.RequestDTO.ItemAvailableRequestDTO;
import project.PromotionInformationAPI.domain.dto.RequestDTO.ItemDeleteRequestDTO;
import project.PromotionInformationAPI.domain.dto.RequestDTO.ItemInputRequestDTO;
import project.PromotionInformationAPI.domain.dto.ResponseDTO;
import project.PromotionInformationAPI.domain.dto.ResponseDTOs.ItemAvailableResponseDTO;
import project.PromotionInformationAPI.domain.dto.Result;
import project.PromotionInformationAPI.domain.dto.ServiceDTO.FindAvailableItemDTO;
import project.PromotionInformationAPI.domain.dto.ServiceDTO.ItemDeleteDTO;
import project.PromotionInformationAPI.domain.dto.ServiceDTO.ItemInputDTO;
import project.PromotionInformationAPI.domain.entity.Item;
import project.PromotionInformationAPI.domain.entity.enums.UserStat;
import project.PromotionInformationAPI.exception.ErrorCode;
import project.PromotionInformationAPI.exception.Exception.*;
import project.PromotionInformationAPI.service.item.ItemService;

import javax.validation.Valid;
@RestController
@RequiredArgsConstructor
@RequestMapping("/item")
public class ItemController {
    private final ItemService itemService;

    @RequestMapping("/input") // 상품 입력
    public ResponseEntity<ResponseDTO> inputItem(@Valid @RequestBody ItemInputRequestDTO itemInputRequestDTO) throws Exception {
        if(!itemInputRequestDTO.getItemType().toString().equals("일반") && !itemInputRequestDTO.getItemType().toString().equals("기업회원상품")){
            throw new InvalidRequestException(ErrorCode.INVALID_ITEMTYPE_REQUEST);
        }
        ItemInputDTO itemInputDTO=itemService.input(itemInputRequestDTO);
        if(itemInputDTO.isDuplicated()){ // 이미 존재하는 상품이 있을 때
            throw new AlreadySavedException(ErrorCode.ALREADY_SAVED_ITEM);
        }
        Item item=itemInputDTO.getItem();
        ResponseEntity<ResponseDTO> response;
        ResponseDTO responseDTO;
        if(item==null){ //저장 실패시

            throw new ServerException(ErrorCode.INTERNAL_SERVER_ERROR);

        }else{
            responseDTO = new ResponseDTO(Result.SUCCESS);
            response=new ResponseEntity<>(responseDTO, HttpStatus.OK);
        }
        return response;
    }

    @RequestMapping("/delete") //상품 삭제
    public ResponseEntity<ResponseDTO> deleteItem(@Valid @RequestBody ItemDeleteRequestDTO itemDeleteRequestDTO ) throws Exception {
        ItemDeleteDTO itemDeleteDTO=itemService.deleteItem(itemDeleteRequestDTO);
        ResponseEntity<ResponseDTO> response;
        if(!itemDeleteDTO.isExistItem()){ //ItemId에 해당하는 상품 정보를 찾을 수 없음

            throw new NotFoundException(ErrorCode.ITEM_NOT_FOUND);


        }else{
            if(!itemDeleteDTO.isResult()){ //상품 정보 삭제 실패함

                throw new ServerException(ErrorCode.INTERNAL_SERVER_ERROR);


            }else{
                ResponseDTO responseDTO=new ResponseDTO(Result.SUCCESS);
                response=new ResponseEntity<>(responseDTO,HttpStatus.OK);

            }
        }
        return response;
    }

    @RequestMapping("/available")  //사용자가 구매할 수 있는 상품 정보 반환
    public ResponseEntity<ItemAvailableResponseDTO> findAvailableItem(@Valid @RequestBody ItemAvailableRequestDTO itemAvailableRequestDTO) throws Exception {
        FindAvailableItemDTO findAvailableItemDTO=itemService.findAvailableItem(itemAvailableRequestDTO);
        ResponseEntity<ItemAvailableResponseDTO> response;
        ItemAvailableResponseDTO itemAvailableResponseDTO;

        if(!findAvailableItemDTO.isExistUser()){ //userId에 해당하는 사용자 정보를 찾을 수 없을 때
            throw new NotFoundException(ErrorCode.USER_NOT_FOUND);

        }else{
            if(findAvailableItemDTO.getUserStat().equals(UserStat.탈퇴)){ //탈퇴한 사용자일 경우
                throw new SecessionException(ErrorCode.SECESSION_ERROR);

            }else{
                if(findAvailableItemDTO.getItemList()==null){ // 구매할 수 있는 상품이 없을 때
                    itemAvailableResponseDTO=new ItemAvailableResponseDTO(Result.SUCCESS,"NO",null);

                }else{
                    itemAvailableResponseDTO=new ItemAvailableResponseDTO(Result.SUCCESS,"YES",findAvailableItemDTO.getItemList());
                }
                response=new ResponseEntity<>(itemAvailableResponseDTO,HttpStatus.OK);
            }
        }
        return response;

    }

}
