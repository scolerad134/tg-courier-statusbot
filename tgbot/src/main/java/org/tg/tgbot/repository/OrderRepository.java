package org.tg.tgbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.tg.tgbot.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
