package com.shop.springshop.dto;

import com.shop.springshop.entity.ItemImg;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class ItemImgDto {

    private Long id;

    private String imgName;

    private String oriImgName;

    private String imgUrl;

    private String repimgYn;


    public static ItemImgDto of(ItemImg itemImg){
        ItemImgDto itemImgDto = new ItemImgDto();
        itemImgDto.setId(itemImg.getId());
        itemImgDto.setImgName(itemImg.getImgName());
        itemImgDto.setOriImgName(itemImg.getImgName());
        itemImgDto.setImgUrl(itemImg.getImgUrl());
        itemImgDto.setRepimgYn(itemImg.getRepimgYn());
        return itemImgDto;
    }
}
