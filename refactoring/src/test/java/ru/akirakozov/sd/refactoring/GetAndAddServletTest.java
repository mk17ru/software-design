package ru.akirakozov.sd.refactoring;

import org.junit.jupiter.api.Test;
import ru.akirakozov.sd.refactoring.servlet.AddProductServlet;
import ru.akirakozov.sd.refactoring.servlet.GetProductsServlet;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

import static org.mockito.Mockito.verify;

public class GetAndAddServletTest extends BaseServletTest {

	@Test
	public void addAndGetProductTest() throws IOException {
		AddProductServlet addServlet = new AddProductServlet();
		addRequest(addServlet, "kappa", "1001");
		addRequest(addServlet, "puma", "2001");
		addRequest(addServlet, "yum", "9");

		GetProductsServlet getServlet = new GetProductsServlet();
		initRequestMock(Map.of());

		getServlet.doGet(httpServletRequest, httpServletResponse);

		verify(httpServletResponse).setStatus(HttpServletResponse.SC_OK);
		verify(httpServletResponse.getWriter()).println("<html><body>");
		verify(httpServletResponse.getWriter()).println("kappa\t1001</br>");
		verify(httpServletResponse.getWriter()).println("puma\t2001</br>");
		verify(httpServletResponse.getWriter()).println("yum\t9</br>");
		verify(httpServletResponse.getWriter()).println("</body></html>");
	}

	private void addRequest(AddProductServlet addServlet, String name, String price) throws IOException {
		initRequestMock(Map.of(
				"name", name,
				"price", price
		));
		initResponseMock();

		addServlet.doGet(httpServletRequest, httpServletResponse);

		verify(httpServletResponse).setStatus(HttpServletResponse.SC_OK);
		verify(httpServletResponse.getWriter()).println("OK");
	}


}
