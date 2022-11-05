package ru.akirakozov.sd.refactoring.database;

import java.sql.*;
import java.util.function.Function;

public class DatabaseProviderImpl implements DatabaseProvider {

	@Override
	public <E, F> F executeQuery(Function<Statement, E> sqlFunction, Function<E, F> resultConverter) {
		try {
			try (Connection c = DriverManager.getConnection("jdbc:sqlite:test.db")) {
				Statement stmt = c.createStatement();
				F result = resultConverter.apply(sqlFunction.apply(stmt));
				stmt.close();
				return result;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int update(String sql) {

		return executeQuery(stm ->
		{
			try {
				return stm.executeUpdate(sql);
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}, Function.identity());
	}


	@Override
	public <R> R query(String sql, Function<ResultSet, R> resultConverter) {
		return executeQuery(stm ->
		{
			try {
				return stm.executeQuery(sql);
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}, resultConverter);
	}
}
