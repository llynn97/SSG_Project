package project.PromotionInformationAPI.repository.Item;

import org.springframework.stereotype.Repository;
import project.PromotionInformationAPI.domain.entity.Item;
import project.PromotionInformationAPI.domain.entity.enums.ItemType;
import project.PromotionInformationAPI.domain.entity.enums.UserType;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;


@Repository
public class ItemJavaRepository implements ItemRepository{
    private static Map<Long, Item> ItemInformation = new HashMap<>();
    private static long sequence = 0L;
    @Override
    public Item save(String ItemName, ItemType ItemType, Long ItemPrice, LocalDate ItemDisplayStartDate, LocalDate ItemDisplayEndDate) {
        Item item=new Item(++sequence, ItemName,ItemType, ItemPrice,ItemDisplayStartDate, ItemDisplayEndDate);
        ItemInformation.put(item.getItemId(),item);

        return ItemInformation.get(item.getItemId());
    }

    @Override
    public Item findById(Long ItemId) {
        return ItemInformation.get(ItemId);
    }

    @Override
    public List<Item> findAvailableAll(UserType UserType) { // UserType 에 따른 전시기간 안에 있는 상품정보들 반환
        Iterator<Long> keys=ItemInformation.keySet().iterator();
        List <Item> itemList=new ArrayList<>();
        LocalDate now=LocalDate.now();
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formatedNow=now.format(formatter);
        List<Item> all=findAll();
        for(Item item:all){
            if(UserType.equals(UserType.일반)){
                if(item.getItemType().equals(ItemType.기업회원상품)){ //일반회원일 때 기업회원상품은 조회 할 수 없음
                    continue;
                }
            }
            int compare1=formatedNow.compareTo(item.getItemDisplayStartDate().format(formatter));
            int compare2=formatedNow.compareTo(item.getItemDisplayEndDate().format(formatter));

            if(compare1>=0 && compare2<=0){ //전시기간 안에 있는지 확인
                itemList.add(item);

            }
        }
        return itemList;
    }

    @Override
    public List<Item> findAll() {
        return new ArrayList<>(ItemInformation.values());
    }

    @Override
    public boolean deleteItem(Long itemId) {
        ItemInformation.remove(itemId);
        if(ItemInformation.containsKey(itemId)){ //삭제되지 않았다면 false 리턴
            return false;
        }
        return true;
    }

    @Override
    public Optional<Item> findByNameAneType(String ItemName, ItemType ItemType) {
        return ItemInformation.values().stream().filter(item -> item.getItemName().equals(ItemName)).filter(item->item.getItemType().equals(ItemType)).findAny();

    }

    public void clearInformation() {
        ItemInformation.clear();
    }

    public void saveItem(Item item){
        ItemInformation.put(item.getItemId(),item);
    }
}
