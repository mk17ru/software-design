package ru.akirakozov.sd.refactoring;

import org.junit.jupiter.api.Test;
import ru.akirakozov.sd.refactoring.dao.ProductDao;
import ru.akirakozov.sd.refactoring.servlet.AddProductServlet;
import ru.akirakozov.sd.refactoring.servlet.GetProductsServlet;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

import static org.mockito.Mockito.verify;

public class GetProductsServletTest extends BaseServletTest {

	@Test
	public void getProductTest() throws IOException {
		GetProductsServlet servlet = new GetProductsServlet(dao);
		initRequestMock(Map.of());

		servlet.doGet(httpServletRequest, httpServletResponse);

		verify(httpServletResponse).setStatus(HttpServletResponse.SC_OK);
		verify(httpServletResponse.getWriter()).println("<html><body>");
		verify(httpServletResponse.getWriter()).println("</body></html>");
	}


}
