package com.han.loan_simulator.domain.user;

public interface UserRepository {
    User save(User user);
    boolean isExist(User user);
}
