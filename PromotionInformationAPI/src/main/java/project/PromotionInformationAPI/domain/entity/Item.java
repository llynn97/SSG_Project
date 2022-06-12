package project.PromotionInformationAPI.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import project.PromotionInformationAPI.domain.entity.enums.ItemType;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Item {


    private Long ItemId;
    private String ItemName;
    private ItemType ItemType;
    private Long ItemPrice;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate ItemDisplayStartDate;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate ItemDisplayEndDate;
}
