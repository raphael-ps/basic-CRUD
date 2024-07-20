package io.github.raphael.basic_CRUD.domain.product;

import jakarta.persistence.*;
import lombok.*;


@Table(name="product")
@Entity(name="product")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")

public class Product {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private Integer price_in_cents;
    private Integer amount;
    private Boolean active;

    public Product(RequestProductPostDTO product){
        this.name = product.name();
        this.price_in_cents = product.price_in_cents();
        this.active = true;
        this.amount = product.amount();
    }
}
