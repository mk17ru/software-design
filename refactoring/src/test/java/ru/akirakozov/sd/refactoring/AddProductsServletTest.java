package ru.akirakozov.sd.refactoring;

import org.junit.jupiter.api.Test;
import ru.akirakozov.sd.refactoring.servlet.AddProductServlet;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

import static org.mockito.Mockito.verify;

public class AddProductsServletTest extends BaseServletTest {

	@Test
	public void addProductTest() throws IOException {
		AddProductServlet servlet = new AddProductServlet(dao);
		initRequestMock(Map.of(
				"name", "shoes",
				"price", "999"
		));
		servlet.doGet(httpServletRequest, httpServletResponse);

		verify(httpServletResponse).setStatus(HttpServletResponse.SC_OK);
		verify(httpServletResponse.getWriter()).println("OK");
	}

}
