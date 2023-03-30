package com.example.demo.products;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;

public interface ProductRepository extends JpaRepository<Product, Long> {

	@Override
	@PostFilter("hasPermission(filterObject, 'READ')")
	List<Product> findAll();

	@PostAuthorize("hasPermission(returnObject, 'READ')")
	Product findById(Integer id);

	@Override
	@SuppressWarnings("unchecked")
	@PreAuthorize("hasPermission(#product, 'WRITE')")
	Product save(@Param("product") Product product);

}
