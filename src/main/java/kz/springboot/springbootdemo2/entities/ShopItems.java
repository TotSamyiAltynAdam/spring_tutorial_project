package kz.springboot.springbootdemo2.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="t_items")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name",length=200)
    private String name;

    @Column(name = "price")
    private int price;

    @Column(name = "amount")
    private int amount;

}
