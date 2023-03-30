/*
 * Copyright 2002-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.demo.products;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

	private ProductService securedService;

	public ProductController(ProductService securedService) {
		this.securedService = securedService;
	}

	@GetMapping("/products")
	List<Product> findAll() {
		return this.securedService.findAll();
	}

	@GetMapping("/products/{id}")
	Product findById(@PathVariable("id") Integer id) {
		return this.securedService.findById(id);
	}

	@PostMapping("/products")
	Product save(@RequestBody Product product) {
		return this.securedService.save(product);
	}

}
