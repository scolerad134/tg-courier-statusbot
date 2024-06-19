package org.tg.tgbot.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.tg.tgbot.model.Courier;
import org.tg.tgbot.repository.CourierRepository;

import java.util.List;


@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Service
public class CourierService {

    CourierRepository courierRepository;

    public List<Courier> getCouriers() {
        return courierRepository.findAll();
    }

    public Courier getCourier(String nickName) {
        return courierRepository.findByNickName(nickName).orElse(null);
    }

    public void save(Courier courier) {
        courierRepository.save(courier);
    }
}
