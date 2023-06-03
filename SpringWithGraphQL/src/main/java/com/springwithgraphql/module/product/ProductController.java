package com.springwithgraphql.module.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.util.UUID;

@Controller
public class ProductController {
    @Autowired private ProductRepository productRepository;

    @MutationMapping
    public ProductModel addProduct(@Argument ProductInput product){
        return productRepository.save(new ProductModel(product.name, product.price, product.categoryId));
    }

    @QueryMapping
    public Iterable<ProductModel> products() {
        return productRepository.findAll();
    }

    record ProductInput(String name, BigDecimal price, UUID categoryId){}
}
