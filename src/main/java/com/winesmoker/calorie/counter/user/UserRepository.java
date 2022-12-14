package com.winesmoker.calorie.counter.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Override
    <S extends User> S save(S entity);

    @Override
    void deleteById(Long aLong);

    boolean existsByTgId(Long tgId);

    Optional<Long> findIdByTgId(Long tgId);
}
