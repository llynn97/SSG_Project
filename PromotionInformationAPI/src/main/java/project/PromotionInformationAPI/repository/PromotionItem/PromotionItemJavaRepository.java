package project.PromotionInformationAPI.repository.PromotionItem;

import org.springframework.stereotype.Repository;
import project.PromotionInformationAPI.domain.entity.Promotion;
import project.PromotionInformationAPI.domain.entity.PromotionItem;
import project.PromotionInformationAPI.repository.Promotion.PromotionRepository;

import java.time.LocalDate;
import java.util.*;

@Repository
public class PromotionItemJavaRepository implements PromotionItemRepository {

    private static Map<Long, PromotionItem> PromotionItemInformation = new HashMap<>();
    private static long sequence4 = 0L;

    @Override
    public boolean save(Long PromotionId, List<Long> PromotionItems) {
        int num1=PromotionItems.size();
        List<Long> idList=new ArrayList<>();
        int num2=0;
        if(PromotionItems!=null){
            for(int i=0; i<PromotionItems.size(); i++){
                PromotionItem promotionItem=new PromotionItem(++sequence4,PromotionId,PromotionItems.get(i));
                PromotionItemInformation.put(promotionItem.getPromotionItemId(),promotionItem);
                idList.add(promotionItem.getPromotionItemId());
            }
        }
        if(idList!=null){
            for(int i=0; i<idList.size(); i++){
                if(PromotionItemInformation.get(idList.get(i)).getPromotionId()==PromotionId){
                    num2++;
                }
            }
            if(num1==num2){
                return true;
            }
        }


        return false;

    }



    @Override
    public PromotionItem findById(Long PromotionItemId) {
        return PromotionItemInformation.get(PromotionItemId);
    }

    @Override
    public List<Long> findPromotionIds(Long ItemId) { //ItemId이 같은 value 값 중 promotionId 값을 리스트에 담아 리턴

        List <Long> promotionIds=new ArrayList<>();
        Set<Map.Entry<Long,PromotionItem>> entrySet=PromotionItemInformation.entrySet();
        for(Map.Entry<Long,PromotionItem> entry:entrySet){
            if(entry.getValue().getItemId().equals(ItemId)){
                promotionIds.add(entry.getValue().getPromotionId());
            }
        }

        return promotionIds;
    }


    @Override
    public boolean deletePromotionItem(Long ItemId) {
        List<Long> deleteId=new ArrayList<>();
        Iterator<Long> keys = PromotionItemInformation.keySet().iterator();
        while(keys.hasNext()){
            Long key= keys.next();
            if(findById(key).getItemId().equals(ItemId)){
                deleteId.add(key);
            }
        }
        for(Long id:deleteId){
            PromotionItemInformation.remove(id);
            if(PromotionItemInformation.containsKey(id)){
                return false;
            }
        }

        return true;
    }

    public void clearInformation() {
        PromotionItemInformation.clear();
    }

    public void savePromotionItem(PromotionItem promotionItem){
        PromotionItemInformation.put(promotionItem.getPromotionItemId(),promotionItem);

    }

    public void savePromotionItems(Long promotionId, List<Long>idList){
        for(Long id:idList){
            PromotionItem promotionItem=new PromotionItem(++sequence4,promotionId,id);
            PromotionItemInformation.put(promotionItem.getPromotionItemId(),promotionItem);
        }

    }
    public PromotionItem saveOnePromotion(Long promotionId,Long itemId){
        PromotionItem promotionItem=new PromotionItem(++sequence4,promotionId,itemId);
        PromotionItemInformation.put(promotionItem.getPromotionItemId(),promotionItem);
        return PromotionItemInformation.get(promotionItem.getPromotionItemId());
    }

    @Override
    public void savePromotionItem(Long PromotionId,List<Long>ItemIds) {

    }

    @Override
    public List<PromotionItem> findAll() {
        return new ArrayList<>(PromotionItemInformation.values());
    }
}
