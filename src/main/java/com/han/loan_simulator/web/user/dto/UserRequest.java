package com.han.loan_simulator.web.user.dto;

import com.han.loan_simulator.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserRequest {
    String nickName;

    public User toEntity(){
        return User.builder()
                .nickName(nickName)
                .build();
    }
}
