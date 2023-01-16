package com.shop.springshop.repository;

import com.shop.springshop.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepositoy extends JpaRepository<Cart, Long> {

    Cart findByMemberId(Long memberId);
}
