package com.springwithgraphql.module.product;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface ProductRepository extends CrudRepository<ProductModel, UUID> {
}
