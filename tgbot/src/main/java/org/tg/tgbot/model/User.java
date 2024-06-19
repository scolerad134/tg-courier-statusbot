package org.tg.tgbot.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    private String id;
    private String name;
    private String nickName;

    @Builder.Default
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "id",cascade = CascadeType.ALL)
    private List<Order> orders = Collections.emptyList();
}
