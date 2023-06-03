package com.springwithgraphql.module.category;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@Entity
@Table(name = "category")
public class CategoryModel {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;

    public CategoryModel(String name) {
        this.name = name;
    }
}
