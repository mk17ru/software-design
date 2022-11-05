package ru.akirakozov.sd.refactoring.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.function.Function;

public interface DatabaseProvider {

	<E, F> F executeQuery(Function<Statement, E> sql, Function<E, F> resultConverter);

	int update(String sql);

	<R> R query(String sql, Function<ResultSet, R> resultConverter);
}
