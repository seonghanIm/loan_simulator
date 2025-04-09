package com.han.loan_simulator.infrastructure.user;

import com.han.loan_simulator.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserJpaRepository extends JpaRepository<User, Long> {
    Optional<User> findByNickName(String nickName);
}
