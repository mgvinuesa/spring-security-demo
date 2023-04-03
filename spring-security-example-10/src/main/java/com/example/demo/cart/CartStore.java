package com.example.demo.cart;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CartStore implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7049535591383574917L;

	private List<Item> items;

	public CartStore() {
		items = new ArrayList<>();
		items.add(new Item(UUID.randomUUID().toString()));
		items.add(new Item(UUID.randomUUID().toString()));
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

}
