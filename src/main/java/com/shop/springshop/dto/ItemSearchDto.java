package com.shop.springshop.dto;

import com.shop.springshop.constant.ItemSellSatus;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ItemSearchDto {

    private String searchDateType;

    private ItemSellSatus searchSellStatus;

    private String searchBy;

    private String searchQuery = "";
}
