package project.PromotionInformationAPI.exception.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import project.PromotionInformationAPI.exception.ErrorCode;
@AllArgsConstructor
@Getter
public class ServerException extends RuntimeException{
    private final ErrorCode errorCode;
}
