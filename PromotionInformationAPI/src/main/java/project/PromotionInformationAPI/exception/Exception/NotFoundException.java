package project.PromotionInformationAPI.exception.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import project.PromotionInformationAPI.exception.ErrorCode;
@AllArgsConstructor
@Getter
public class NotFoundException extends NullPointerException {
    private final ErrorCode errorCode;
}
