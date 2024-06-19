package org.tg.tgbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tg.tgbot.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByName(String name);
    Optional<User> findByNickName(String nickname);
}
