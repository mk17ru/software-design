package ru.akirakozov.sd.refactoring.entities;

public class Product {
	private final String name;

	private final long price;

	public Product(String name, long price) {
		this.name = name;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public long getPrice() {
		return price;
	}
}
