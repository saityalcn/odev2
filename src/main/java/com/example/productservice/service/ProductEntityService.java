package com.example.productservice.service;

import com.example.productservice.dao.ProductRepository;
import com.example.productservice.service.generic.GenericEntityService;
import org.springframework.stereotype.Service;

@Service
public class ProductEntityService extends GenericEntityService<Product, ProductRepository> {
    protected ProductEntityService(ProductRepository repository) {
        super(repository);
    }
}
