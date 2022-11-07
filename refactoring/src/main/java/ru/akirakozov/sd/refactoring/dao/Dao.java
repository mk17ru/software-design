package ru.akirakozov.sd.refactoring.dao;

import ru.akirakozov.sd.refactoring.entities.Product;

import java.util.List;

public interface Dao<E> {

	List<E> getAll();

	void add(E product);

	E findMax();

	E findMin();

	Integer findSum();

	Integer findCount();

}
