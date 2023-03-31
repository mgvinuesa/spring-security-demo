package com.example.demo.cart;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CartStore {

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
