package com.shop.springshop.repository;

import com.shop.springshop.dto.ItemSearchDto;
import com.shop.springshop.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ItemRepositoryCustom {

    Page<Item> getAdminItemPage(ItemSearchDto itemSearchDto, Pageable pageable);
}
