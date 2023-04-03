package com.example.demo.cart;

import java.io.Serializable;

public class Item implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3223908988864441977L;
	private String name;

	public Item(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
