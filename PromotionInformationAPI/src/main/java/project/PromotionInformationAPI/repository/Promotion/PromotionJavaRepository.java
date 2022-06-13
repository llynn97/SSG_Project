package project.PromotionInformationAPI.repository.Promotion;

import org.springframework.stereotype.Repository;
import project.PromotionInformationAPI.domain.entity.Promotion;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Repository
public class PromotionJavaRepository implements PromotionRepository{
    private static Map<Long, Promotion> PromotionInformation = new HashMap<>();
    private static long sequence2 = 0L;
    @Override
    public Promotion save(String PromotionNm, Long DiscountAmount, Float DiscountRate, LocalDate PromotionStartDate, LocalDate PromotionEndDate) {
        Promotion promotion=new Promotion(++sequence2, PromotionNm, DiscountAmount, DiscountRate, PromotionStartDate, PromotionEndDate);
        PromotionInformation.put(promotion.getPromotionId(),promotion);
        return PromotionInformation.get(promotion.getPromotionId());
    }

    @Override
    public Promotion findById(Long promotionId) {
        return PromotionInformation.get(promotionId);
    }

    @Override
    public List<Promotion> findAvailablePromotion(List<Long> PromotionIds) {
        List<Promotion> promotionList=new ArrayList<>();
        LocalDate now=LocalDate.now();
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formatedNow=now.format(formatter);
        for(Long promotionId:PromotionIds){
            Promotion promotion=PromotionInformation.get(promotionId);
            int compare1=formatedNow.compareTo(promotion.getPromotionStartDate().format(formatter));
            int compare2=formatedNow.compareTo(promotion.getPromotionEndDate().format(formatter));

            if(compare1>=0 && compare2<=0){
                promotionList.add(promotion);
            }
        }

        return promotionList;
    }

    @Override
    public boolean deletePromotion(Long PromotionId) {
        Promotion promotion=PromotionInformation.get(PromotionId);
        if(promotion.equals(null)){//아이디에 해당하는 프로모션 정보 없으면
            return false;
        }else{
            PromotionInformation.remove(PromotionId);
            if(PromotionInformation.containsKey(PromotionId)==false){
                return true;
            }
            return false;
        }
    }

    @Override
    public Optional<Promotion> findByName(String PromotionNm) {
        return PromotionInformation.values().stream().filter(promotion -> promotion.getPromotionNm().equals(PromotionNm)).findAny();
    }

    @Override
    public List<Promotion> findAll() {
        return new ArrayList<>(PromotionInformation.values());
    }

    public void clearInformation() {
        PromotionInformation.clear();
    }
    public void savePromotion(Promotion promotion){ PromotionInformation.put(promotion.getPromotionId(),promotion); }


}
