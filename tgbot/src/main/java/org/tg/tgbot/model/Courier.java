package org.tg.tgbot.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "courier")
public class Courier {

    @Id
    @Builder.Default
    private String id = UUID.randomUUID().toString();
    private String name;
    private String nickName;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "id",cascade = CascadeType.ALL)
    private List<Order> orders;

    @Override
    public String toString() {
        return "ник в tg: " + nickName
                + "\nимя курьера: " + nickName
                + "\nзаказы курьера: " + orders
                + "\n";
    }
}
