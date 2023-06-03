package com.springwithgraphql.module.category;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface CategoryRepository extends CrudRepository<CategoryModel, UUID> {

}
