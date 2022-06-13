package project.PromotionInformationAPI.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    INVALID_REQUEST(400, "요청 데이터값 확인필요."),
    INVALID_PROMOTION_REQUEST(400,"DiscountAmount ,DiscountRate 값 확인 필요"),
    INVALID_USERTYPE_REQUEST(400,"UserType 은 기업회원/일반 둘 중 하나여야함"),
    INVALID_ITEMTYPE_REQUEST(400,"ItemType 은 일반/기업회원상품 둘 중 하나여야함"),

    //404 NOT_FOUND 잘못된 리소스 접근
    ITEM_NOT_FOUND(404, "ItemId에 해당하는 상품 정보를 찾을 수 없음."),
    PROMOTION_NOT_FOUND(404, "PromotionId에 해당하는 프로모션 정보를 찾을 수 없음."),
    USER_NOT_FOUND(404, "UserId에 해당하는 사용자 정보를 찾을 수 없음"),


    //409 CONFLICT 중복된 리소스
    ALREADY_SAVED_PROMOTION(409, "이미 존재하는 프로모션임."),
    ALREADY_SAVED_ITEM(409, "이미 존재하는 상품임."),

    //500 INTERNAL SERVER ERROR
    INTERNAL_SERVER_ERROR(500, "서버 에러로 실패"),

    //403 FORBIDDEN 서버가 허용하지 않는 웹 페이지나 미디어를 사용자가 요청
    SECESSION_ERROR(403,"탈퇴한 회원은 조회 할 수 없음");

    private final int status;
    private final String message;
}
