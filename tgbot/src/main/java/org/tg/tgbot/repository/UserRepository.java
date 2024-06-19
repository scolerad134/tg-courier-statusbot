package org.tg.tgbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tg.tgbot.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByName(String name);
    User findByNickName(String nickname);
}
