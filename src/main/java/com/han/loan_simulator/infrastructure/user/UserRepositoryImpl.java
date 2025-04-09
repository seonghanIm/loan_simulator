package com.han.loan_simulator.infrastructure.user;


import com.han.loan_simulator.domain.user.User;
import com.han.loan_simulator.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final UserJpaRepository jpaRepository;

    @Override
    public User save(User user) {
        return jpaRepository.save(user);
    }

    @Override
    public boolean isExist(User user) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withIgnorePaths("id")
                .withMatcher("nickName", ExampleMatcher.GenericPropertyMatcher::ignoreCase);
        Example<User> example = Example.of(user, matcher);
        return jpaRepository.exists(example);
    }
}
