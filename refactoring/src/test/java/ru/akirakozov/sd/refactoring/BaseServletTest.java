package ru.akirakozov.sd.refactoring;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BaseServletTest {

	@Mock
	protected HttpServletRequest httpServletRequest;

	protected HttpServletResponse httpServletResponse;

	@BeforeEach
	public void openMockito() throws SQLException {
		MockitoAnnotations.openMocks(this);
		try(Connection c = DriverManager.getConnection("jdbc:sqlite:test.db")) {
			String sql = "DELETE FROM PRODUCT WHERE TRUE";
			Statement stmt = c.createStatement();
			stmt.executeUpdate(sql);
			stmt.close();
		}
	}

	protected void initRequestMock(Map<String, String> values) throws IOException {
		for (Map.Entry<String, String> entry : values.entrySet()) {
			when(httpServletRequest.getParameter(entry.getKey())).thenReturn(entry.getValue());
		}
		initResponseMock();
	}

	protected void initResponseMock() throws IOException {
		PrintWriter printWriter = Mockito.mock(PrintWriter.class);
		httpServletResponse = mock(HttpServletResponse.class);
		when(httpServletResponse.getWriter()).thenReturn(printWriter);
	}

}
