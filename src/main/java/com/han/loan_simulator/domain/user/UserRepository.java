package com.han.loan_simulator.domain.user;

import java.util.Optional;

public interface UserRepository {
    User save(User user);
    boolean isExist(User user);
    Optional<User> findbyNickName(String nickName);
}
