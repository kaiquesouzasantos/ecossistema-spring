package com.springwithgraphql.module.product;

import com.springwithgraphql.module.category.CategoryModel;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "product")
public class ProductModel {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "category_id", insertable = false, updatable = false)
    private CategoryModel category;

    @Column(name = "category_id")
    private UUID categoryId;

    public ProductModel(String name, BigDecimal price, UUID categoryId) {
        this.name = name;
        this.price = price;
        this.categoryId = categoryId;
    }
}
