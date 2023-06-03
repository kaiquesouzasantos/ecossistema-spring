package com.springwithgraphql.module.category;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.UUID;

@Controller
public class CategoryController {
    @Autowired private CategoryRepository categoryRepository;

    @MutationMapping
    public CategoryModel addCategory(@Argument CategoryInput category) {
        return categoryRepository.save(new CategoryModel(category.name));
    }

    @QueryMapping
    public CategoryModel categoryById(@Argument UUID id) {
        return categoryRepository.findById(id).orElse(null);
    }

    record CategoryInput(String name){}
}
