package org.tg.tgbot.service;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.tg.tgbot.model.Order;
import org.tg.tgbot.repository.OrderRepository;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Service
public class OrderService {

    OrderRepository repository;

    public Order save(Order order) {
        return repository.save(order);
    }

    public Order findById(Long id) {
        return repository.findById(id).orElse(null);
    }
    public List<Order> findAll() {
        return repository.findAll();
    }

}
