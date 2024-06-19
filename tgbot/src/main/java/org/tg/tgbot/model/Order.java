package org.tg.tgbot.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    private String id;
    private String name;
    private String status;
    private String type;
    private String price;
    private String quantity;

    @ManyToOne
    @JoinColumn(name = "courier_id",referencedColumnName="id")
    private Courier courier;

    @ManyToOne
    @JoinColumn(name = "user_id",referencedColumnName="id")
    private User user;

    @Override
    public String toString() {
        return id;
    }
}
