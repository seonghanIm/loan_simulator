package com.han.loan_simulator.web.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@AllArgsConstructor
@Getter
public class UserResponse {
    String nickName;
}
