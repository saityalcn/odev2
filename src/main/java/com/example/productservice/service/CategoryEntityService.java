package com.example.productservice.service;

import com.example.productservice.dao.CategoryRepository;
import com.example.productservice.service.generic.GenericEntityService;
import org.springframework.stereotype.Service;

@Service
public class CategoryEntityService extends GenericEntityService<Category, CategoryRepository> {

    protected CategoryEntityService(CategoryRepository repository) {
        super(repository);
    }

}
