package project.PromotionInformationAPI.domain.dto.RequestDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PromotionInputRequestDTO {

    @NotEmpty(message = "상품이름을 입력해야함")
    private String PromotionNm;

    private Long DiscountAmount;
    private Float DiscountRate;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate PromotionStartDate;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate PromotionEndDate;
}
