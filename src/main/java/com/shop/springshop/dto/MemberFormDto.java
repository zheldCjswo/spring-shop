package com.shop.springshop.dto;

import com.shop.springshop.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@Setter
@NoArgsConstructor
public class MemberFormDto {

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
