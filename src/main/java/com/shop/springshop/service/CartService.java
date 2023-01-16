package com.shop.springshop.service;

import com.shop.springshop.dto.CartItemDto;
import com.shop.springshop.entity.Cart;
import com.shop.springshop.entity.CartItem;
import com.shop.springshop.entity.Item;
import com.shop.springshop.entity.Member;
import com.shop.springshop.repository.CartItemRepository;
import com.shop.springshop.repository.CartRepositoy;
import com.shop.springshop.repository.ItemRepository;
import com.shop.springshop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {

    private final ItemRepository itemRepository;
    private final MemberRepository memberRepository;
    private final CartRepositoy cartRepositoy;
    private final CartItemRepository cartItemRepository;

    public Long addCart(CartItemDto cartItemDto, String email){
        Item item = itemRepository.findById(cartItemDto.getItemId())
                .orElseThrow(EntityNotFoundException::new);
        Member member = memberRepository.findByEmail(email);

        Cart cart = cartRepositoy.findByMemberId(member.getId());
        if(cart == null){
            cart = Cart.createCart(member);
            cartRepositoy.save(cart);
        }

        CartItem savedCartItem = cartItemRepository.findByCartIdAndItemId(cart.getId(), item.getId());

        if(savedCartItem != null){
            savedCartItem.addCount(cartItemDto.getCount());
            return savedCartItem.getId();
        } else {
            CartItem cartItem = CartItem.createCartItem(cart, item, cartItemDto.getCount());
            cartItemRepository.save(cartItem);
            return cartItem.getId();
        }
    }
}
