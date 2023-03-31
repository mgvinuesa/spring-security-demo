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
package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.service.SecuredService;

@Controller
public class IndexController {

	private SecuredService securedService;

	public IndexController(SecuredService securedService) {
		this.securedService = securedService;
	}

	@GetMapping("/")
	public String index() {
		return "index";
	}

	@GetMapping("/secured")
	public String secured(@RequestParam(name = "method", required = false) boolean method) {
		if (method) {
			this.securedService.invoke();
		}
		return "secured";
	}

}