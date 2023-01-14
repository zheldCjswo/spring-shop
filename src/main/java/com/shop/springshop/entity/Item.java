package com.shop.springshop.entity;

import com.shop.springshop.constant.ItemSellSatus;
import com.shop.springshop.dto.ItemFormDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="item")
@Getter
@Setter
@ToString
public class Item extends BaseEntity {

    @Id
    @Column(name="item_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; //상품 코드

    @Column(nullable = false, length = 50)
    private String itemNm; //상품명

    @Column(name="price", nullable = false)
    private int price; //가격

    @Column(nullable = false)
    private int stockNumber; //재고수량

    @Lob
    @Column(nullable = false)
    private String itemDetail; //상품 상세 설명

    @Enumerated(EnumType.STRING)
    private ItemSellSatus itemSellSatus; //상품 판매 상태

    //private LocalDateTime regTime; //등록 시간

    //private LocalDateTime updateTime; //수정 시간

    public void updateItem(String itemNm, int price, int stockNumber, String itemDetail, ItemSellSatus itemSellSatus){
        this.itemNm = itemNm;
        this.price = price;
        this.stockNumber = stockNumber;
        this.itemDetail = itemDetail;
        this.itemSellSatus = itemSellSatus;
    }
}
