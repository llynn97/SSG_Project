package project.PromotionInformationAPI.repository.Item;

import project.PromotionInformationAPI.domain.entity.Item;
import project.PromotionInformationAPI.domain.entity.enums.ItemType;
import project.PromotionInformationAPI.domain.entity.enums.UserType;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ItemRepository {

    Item save(String ItemName, ItemType ItemType, Long ItemPrice, LocalDate ItemDisplayStartDate, LocalDate ItemDisplayEndDate);
    Item findById(Long ItemId);
    List<Item> findAvailableAll(UserType UserType);
    List<Item> findAll();
    boolean deleteItem(Long ItemId);
    Optional<Item> findByNameAneType(String ItemName, ItemType ItemType);
}
