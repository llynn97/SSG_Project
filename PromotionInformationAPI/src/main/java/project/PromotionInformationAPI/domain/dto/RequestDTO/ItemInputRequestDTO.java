package project.PromotionInformationAPI.domain.dto.RequestDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import project.PromotionInformationAPI.domain.entity.enums.ItemType;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemInputRequestDTO {

    @NotEmpty
    private String ItemName;

    @NotNull
    private ItemType ItemType;

    @NotNull
    private Long ItemPrice;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate ItemDisplayStartDate;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate ItemDisplayEndDate;
}
