package com.han.loan_simulator.user;

import com.han.loan_simulator.application.user.UserService;
import com.han.loan_simulator.common.RESPONSE;
import com.han.loan_simulator.common.baseDto.BaseResponse;
import com.han.loan_simulator.domain.user.User;
import com.han.loan_simulator.domain.user.UserRepository;
import com.han.loan_simulator.web.user.dto.UserRequest;
import com.han.loan_simulator.web.user.dto.UserResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class UserServiceTest {
    private UserRepository userRepository;
    private UserService userService;
    @BeforeEach
    void setUp(){
        userRepository = mock(UserRepository.class);
        userService = new UserService(userRepository);
    }


    @Test
    void signup_정상회원가입(){
        UserRequest request = new UserRequest("testNickName");

        when(userRepository.save(any(User.class))).thenReturn(request.toEntity());
        when(userRepository.isExist(any(User.class))).thenReturn(false);

        BaseResponse<Void> response = userService.signUp(request);

        assertThat(response.isSuccess()).isTrue();

    }

    @Test
    void signup_잘못된문자회원가입(){
        UserRequest request = new UserRequest("@!@#!@");

        when(userRepository.save(any(User.class))).thenReturn(request.toEntity());
        when(userRepository.isExist(any(User.class))).thenReturn(false);

        BaseResponse<Void> response = userService.signUp(request);

        assertThat(response.isSuccess()).isFalse();
        assertThat(response.getCode()).isEqualTo(RESPONSE.NICK_NAME_VALIDATE_ERROR.code);
    }

    @Test
    void signup_긴닉네임회원가입(){
        UserRequest request = new UserRequest("AAAAAAAAAAAAAAAAAAAAA");

        when(userRepository.save(any(User.class))).thenReturn(request.toEntity());
        when(userRepository.isExist(any(User.class))).thenReturn(false);

        BaseResponse<Void> response = userService.signUp(request);

        assertThat(response.isSuccess()).isFalse();
        assertThat(response.getCode()).isEqualTo(RESPONSE.NICK_NAME_VALIDATE_ERROR.code);
    }

    @Test
    void signup_중복된닉네임회원가입(){
        UserRequest request = new UserRequest("test");

        when(userRepository.save(any(User.class))).thenReturn(request.toEntity());
        when(userRepository.isExist(any(User.class))).thenReturn(true);

        BaseResponse<Void> response = userService.signUp(request);

        assertThat(response.isSuccess()).isFalse();
        assertThat(response.getCode()).isEqualTo(RESPONSE.NICK_NAME_DUPLICATED_ERROR.code);
    }


    @Test
    void login_정상로그인(){
        UserRequest request = new UserRequest("test");

        when(userRepository.isExist(any(User.class))).thenReturn(true);

        BaseResponse<UserResponse> response = userService.login(request);

        assertThat(response.isSuccess()).isTrue();
    }

    @Test
    void login_없는닉네임로그인(){
        UserRequest request = new UserRequest("test");

        when(userRepository.isExist(any(User.class))).thenReturn(false);

        BaseResponse<UserResponse> response = userService.login(request);

        assertThat(response.isSuccess()).isFalse();
        assertThat(response.getCode()).isEqualTo(RESPONSE.NO_EXIST_NICKNAME.code);
    }



}
