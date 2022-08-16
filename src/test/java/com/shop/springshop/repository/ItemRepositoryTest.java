package com.shop.springshop.repository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.shop.springshop.constant.ItemSellSatus;
import com.shop.springshop.entity.Item;
import com.shop.springshop.entity.QItem;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.TestPropertySource;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;


@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class ItemRepositoryTest {

    @Autowired
    ItemRepository itemRepository;
    
    //영속성 컨텍스트 사용을 위한 설정
    @PersistenceContext
    EntityManager em;

    @Test
    @DisplayName("상품 저장 리스트")
    public void creteItemTest(){
        Item item = new Item();
        item.setItemNm("테스트 상품");
        item.setPrice(10000);
        item.setItemDetail("테스트 상품 상세 설명");
        item.setItemSellSatus(ItemSellSatus.SELL);
        item.setStockNumber(100);
        item.setRegTime(LocalDateTime.now());
        item.setUpdateTime(LocalDateTime.now());
        Item savedItem = itemRepository.save(item);
        System.out.println("savedItem = " + savedItem);
    }

    public void createItemList(){
        for (int i = 0; i < 11; i++) {
            Item item = new Item();
            item.setItemNm("테스트 상품" +  i);
            item.setPrice(10000+i);
            item.setItemDetail("테스트 상품 상세 내용" + i);
            item.setItemSellSatus(ItemSellSatus.SELL);
            item.setStockNumber(100);
            item.setRegTime(LocalDateTime.now());
            item.setUpdateTime(LocalDateTime.now());
            Item savedItem = itemRepository.save(item);
        }
    }

    @Test
    @DisplayName("상품명 조회 테스트")
    public void findByItemNmTest(){
        this.createItemList();
        List<Item> itemList = itemRepository.findByItemNm("테스트 상품1");
        for(Item item : itemList){
            System.out.println("item.toString() = " + item.toString());
        }
    }
    
    @Test
    @DisplayName("상품명, 상품상세설명 or 테스트")
    public void findByItemNmOrItemDetailTest(){
        this.createItemList();
        List<Item> itemList = itemRepository.findByItemNmOrItemDetail("테스트 상품1","테스트 상품 상세 내용5");
        for(Item item : itemList){
            System.out.println("item.toString() = " + item.toString());
        }
    }
    
    @Test
    @DisplayName("가격 LessThan 테스트")
    public void findByPriceLessThanTest() {
        this.createItemList();
        List<Item> itemList = itemRepository.findByPriceLessThan(100000);
        for(Item item : itemList){
            System.out.println("item.toString() = " + item.toString());
        }
    }
    
    @Test
    @DisplayName("가격 내림차순 조회 테스트")
    public void findByPriceLessThanOrderByPriceDesc(){
        this.createItemList();
        List<Item> itemList = itemRepository.findByPriceLessThanOrderByPriceDesc(10005);
        for(Item item : itemList){
            System.out.println("item.toString() = " + item.toString());
        }
    }
    
    @Test
    @DisplayName("@Query를 이용한 상품 조회 테스트")
    public void findByItemDetailTest(){
        this.createItemList();
        List<Item> itemList = itemRepository.findByItemDetail("테스트 상품");
        for(Item item : itemList){
            System.out.println("item.toString() = " + item.toString());
        }
    }

    @Test
    @DisplayName("nativeQuery 속성을 이용한 상품 조회 리스트")
    public void findByItemDetailByNative(){
        this.createItemList();
        List<Item> itemList = itemRepository.findByItemDetail("테스트 상품");
        for(Item item : itemList){
            System.out.println("item.toString() = " + item.toString());
        }
    }
    
    @Test
    @DisplayName("querydsl 조회 테스트1")
    public void queryDslTest(){
        this.createItemList();
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(em); //Factory를 사용하여 쿼리 생성
        QItem qItem = QItem.item; //자동생성된 QItem 사용
        JPAQuery<Item> query = jpaQueryFactory.selectFrom(qItem)
                .where(qItem.itemSellSatus.eq(ItemSellSatus.SELL))
                .where(qItem.itemDetail.like("%"+"테스트 상품"+"%"))
                .orderBy(qItem.price.desc());
        List<Item> itemList = query.fetch();
        
        for(Item item : itemList){
            System.out.println("item.toString() = " + item.toString());
        }
    }

    public void createItemList2(){
        for (int i = 0; i < 6; i++) {
            Item item = new Item();
            item.setItemNm("테스트 상품" +  i);
            item.setPrice(10000+i);
            item.setItemDetail("테스트 상품 상세 내용" + i);
            item.setItemSellSatus(ItemSellSatus.SELL);
            item.setStockNumber(100);
            item.setRegTime(LocalDateTime.now());
            item.setUpdateTime(LocalDateTime.now());
            Item savedItem = itemRepository.save(item);
        }

        for (int i = 6; i < 11; i++) {
            Item item = new Item();
            item.setItemNm("테스트 상품" +  i);
            item.setPrice(10000+i);
            item.setItemDetail("테스트 상품 상세 내용" + i);
            item.setItemSellSatus(ItemSellSatus.SOLD_OUT);
            item.setStockNumber(0);
            item.setRegTime(LocalDateTime.now());
            item.setUpdateTime(LocalDateTime.now());
            Item savedItem = itemRepository.save(item);
        }
    }

    @Test
    @DisplayName("상품 Querydsl 조회 테스트 2")
    public void queryDslTest2(){
        this.createItemList2();
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        QItem item = QItem.item;

        String itemDetail = "테스트 상품";
        int price = 10003;
        String itemSellStat = "SELL";

        booleanBuilder.and(item.itemDetail.like("%"+itemDetail+"%"));
        booleanBuilder.and(item.price.gt(price));

        if(StringUtils.equals(itemSellStat, ItemSellSatus.SELL.toString())){
            booleanBuilder.and(item.itemSellSatus.eq(ItemSellSatus.SELL));
        }

        Pageable pageable = PageRequest.of(0,5);
        Page<Item> itemPagingResult = itemRepository.findAll(booleanBuilder, pageable);

        System.out.println("itemPagingResult.getTotalElements() = " + itemPagingResult.getTotalElements());
    
        List<Item> resultItemList = itemPagingResult.getContent();
        for(Item resultItem : resultItemList){
            System.out.println("resultItem.toString() = " + resultItem.toString());
        }
    }
}