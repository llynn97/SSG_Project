package project.PromotionInformationAPI.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import project.PromotionInformationAPI.domain.dto.Result;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ErrorResponse {
    private  Result Result;
    private int Status;
    private String message;
}
