package com.shop.springshop.dto;

import com.shop.springshop.entity.Member;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@Setter
@RequiredArgsConstructor
public class MemberFormDto {

    private final PasswordEncoder passwordEncoder;
    private String name;

    private String email;

    private String password;

    private String address;

    public Member createMember(PasswordEncoder passwordEncoder){
        return Member.builder()
                .name(this.getName())
                .email(this.getEmail())
                .password(passwordEncoder.encode(this.getPassword()))
                .address(this.getAddress())
                .build();
    }
}
