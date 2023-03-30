package com.example.demo.products;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ProductService {

	private ProductRepository productRepository;

	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	List<Product> findAll() {
		return this.productRepository.findAll();
	}

	Product findById(Integer id) {
		return this.productRepository.findById(id);
	}

	Product save(Product product) {
		return this.productRepository.save(product);
	}
}
