package ru.akirakozov.sd.refactoring.dao;

import ru.akirakozov.sd.refactoring.database.DatabaseProvider;
import ru.akirakozov.sd.refactoring.entities.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDao implements Dao<Product> {

	private DatabaseProvider databaseProvider;

	public ProductDao(DatabaseProvider databaseProvider) {
		this.databaseProvider = databaseProvider;
	}

	private static List<Product> extractAllFromResultSet(ResultSet resultSet) {
		List<Product> products = new ArrayList<>();
		try {
			while (resultSet.next()) {
				products.add(extractProduct(resultSet));
			}
			resultSet.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return products;
	}

	private static Product extractProduct(ResultSet resultSet) {
		try {
			String name = resultSet.getString("name");
			int price = resultSet.getInt("price");
			return new Product(name, price);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}


	public List<Product> getAll() {
		return databaseProvider.query("SELECT * FROM PRODUCT",
				ProductDao::extractAllFromResultSet
		);
	}

	public void add(Product product) {
		String sql = String.format("INSERT INTO PRODUCT " +
				"(NAME, PRICE) VALUES (\"%s\", %d)", product.getName(), product.getPrice());
		databaseProvider.update(sql);
	}

	@Override
	public Product findMax() {

		return databaseProvider.query("SELECT * FROM PRODUCT ORDER BY PRICE DESC LIMIT 1",
				(stm) -> extractAllFromResultSet(stm).get(0)
		);

	}

	@Override
	public Product findMin() {
		return databaseProvider.query("SELECT * FROM PRODUCT ORDER BY PRICE LIMIT 1",
				(resultSet) -> extractAllFromResultSet(resultSet).get(0)
		);
	}

	@Override
	public Integer findSum() {
		return databaseProvider.query("SELECT SUM(price) FROM PRODUCT",
				(rs) -> {
					try {
						if (rs.next()) {
							return rs.getInt(1);
						}
					} catch (SQLException e) {
						throw new RuntimeException(e);
					}
					return null;
				}
		);
	}

	@Override
	public Integer findCount() {
		return databaseProvider.query("SELECT COUNT(*) FROM PRODUCT",
				(rs) -> {
					try {
						if (rs.next()) {
							return rs.getInt(1);
						}
					} catch (SQLException e) {
						throw new RuntimeException(e);
					}
					return null;
				}
		);
	}

}
