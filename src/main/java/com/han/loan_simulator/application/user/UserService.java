package com.han.loan_simulator.application.user;

import com.han.loan_simulator.common.RESPONSE;
import com.han.loan_simulator.common.baseDto.BaseResponse;
import com.han.loan_simulator.domain.user.User;
import com.han.loan_simulator.domain.user.UserRepository;
import com.han.loan_simulator.web.user.dto.UserRequest;
import com.han.loan_simulator.web.user.dto.UserResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;

    /**
     * 회원가입은 닉네임으로만
     *
     * @param userRequest
     * @return
     */
    @Transactional
    public BaseResponse<Void> signUp(UserRequest userRequest) {
        try {
            User requestUser = userRequest.toEntity();

            if (!requestUser.nickNameValidate()) {
                return new BaseResponse<Void>().ofError(RESPONSE.NICK_NAME_VALIDATE_ERROR.code
                        , RESPONSE.NICK_NAME_VALIDATE_ERROR.mesage);
            }

            if (userRepository.isExist(requestUser)) {
                return new BaseResponse<Void>().ofError(RESPONSE.NICK_NAME_DUPLICATED_ERROR.code
                        , RESPONSE.NICK_NAME_DUPLICATED_ERROR.mesage);
            }

            userRepository.save(userRequest.toEntity());

            return new BaseResponse<Void>().ofSuccess();

        } catch (Exception e) {
            log.error("UserService :: signUp :: error :: {}", e.toString());
            return new BaseResponse<Void>().ofError();
        }
    }

    /**
     * 로그인은 닉네임으로만
     *
     * @param userRequest
     * @return
     */
    public BaseResponse<UserResponse> login(UserRequest userRequest) {
        try {
            if (!userRepository.isExist(userRequest.toEntity())) {
                return new BaseResponse<UserResponse>().ofError(RESPONSE.NO_EXIST_USER.code, RESPONSE.NO_EXIST_USER.mesage);
            }
            UserResponse response = new UserResponse(userRequest.getNickName());
            return new BaseResponse<UserResponse>().ofSuccess(response);
        } catch (Exception e) {
            log.error("UserService :: login :: error :: {}", e.toString());
            return new BaseResponse<UserResponse>().ofError();
        }
    }


    public boolean isExistUser(User user){
        if(userRepository.isExist(user)) return true;
        return false;
    }

    public boolean isExistUser(String nickName){
        if(userRepository.findbyNickName(nickName).isPresent()) return true;
        return false;
    }

    public Optional<User> getUserBy(String nickName){
       return userRepository.findbyNickName(nickName);
    }

}
