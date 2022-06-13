package project.PromotionInformationAPI.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.PromotionInformationAPI.domain.dto.RequestDTO.UserDeleteRequestDTO;
import project.PromotionInformationAPI.domain.dto.RequestDTO.UserInputRequestDTO;
import project.PromotionInformationAPI.domain.dto.ResponseDTO;
import project.PromotionInformationAPI.domain.dto.ResponseDTOs.UserInputResponseDTO;
import project.PromotionInformationAPI.domain.dto.Result;
import project.PromotionInformationAPI.domain.dto.ServiceDTO.UserDeleteDTO;
import project.PromotionInformationAPI.domain.entity.User;
import project.PromotionInformationAPI.exception.ErrorCode;
import project.PromotionInformationAPI.exception.Exception.InvalidRequestException;
import project.PromotionInformationAPI.exception.Exception.NotFoundException;
import project.PromotionInformationAPI.exception.Exception.ServerException;
import project.PromotionInformationAPI.service.user.UserService;

import javax.validation.Valid;
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @RequestMapping("/input") // 사용자 입력 API
    public ResponseEntity<UserInputResponseDTO> inputUser(@Valid @RequestBody UserInputRequestDTO userInputRequestDTO) throws Exception {

        if(!userInputRequestDTO.getUserType().toString().equals("기업회원") && !userInputRequestDTO.getUserType().toString().equals("일반")){
            throw new InvalidRequestException(ErrorCode.INVALID_USERTYPE_REQUEST);
        }
        User user=userService.input(userInputRequestDTO);

        ResponseEntity<UserInputResponseDTO> response;
        UserInputResponseDTO responseDTO;
        if(user==null){ //저장 실패시
            throw new ServerException(ErrorCode.INTERNAL_SERVER_ERROR);
        }else{
            responseDTO = new UserInputResponseDTO(Result.SUCCESS,user.getUserId());
            response=new ResponseEntity<>(responseDTO, HttpStatus.OK);
        }
        return response;
    }

    @RequestMapping("/delete")  //사용자 삭제 API
    public ResponseEntity<ResponseDTO> deleteUser(@Valid @RequestBody UserDeleteRequestDTO userDeleteRequestDTO){
        UserDeleteDTO userDeleteDTO=userService.deleteUser(userDeleteRequestDTO);
        if(!userDeleteDTO.isExistUser()){
            throw new NotFoundException(ErrorCode.USER_NOT_FOUND);
        }
        if(!userDeleteDTO.isDeleted()){
            throw new ServerException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
        ResponseDTO responseDTO = new ResponseDTO(Result.SUCCESS);
        ResponseEntity<ResponseDTO> response =new ResponseEntity<>(responseDTO,HttpStatus.OK);
        return response;
    }
}
