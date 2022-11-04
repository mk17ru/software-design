package ru.akirakozov.sd.refactoring;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.akirakozov.sd.refactoring.servlet.AddProductServlet;
import ru.akirakozov.sd.refactoring.servlet.GetProductsServlet;
import ru.akirakozov.sd.refactoring.servlet.QueryServlet;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.verify;

public class QueryProductsServletTest extends BaseServletTest {

	@Test
	public void queryProductTest() throws IOException {
		AddProductServlet addServlet = new AddProductServlet();
		addRequest(addServlet, "kappa", "1001");
		addRequest(addServlet, "puma", "2001");
		addRequest(addServlet, "yum", "9");

		verify(httpServletResponse).setStatus(HttpServletResponse.SC_OK);

		QueryServlet servlet = new QueryServlet();

		queryRequest(servlet, "max", Integer.MIN_VALUE, List.of(
				"<h1>Product with max price: </h1>",
				"puma\t2001</br>"
		));

		queryRequest(servlet, "min", Integer.MIN_VALUE, List.of(
				"<h1>Product with min price: </h1>",
				"yum\t9</br>"
		));

		queryRequest(servlet, "sum", 3011, List.of(
				"Summary price: "
		));

		queryRequest(servlet, "count", 3, List.of(
				"Number of products: "
		));

	}


	private void addRequest(AddProductServlet addServlet, String name, String price) throws IOException {
		initRequestMock(Map.of(
				"name", name,
				"price", price
		));

		addServlet.doGet(httpServletRequest, httpServletResponse);

		verify(httpServletResponse).setStatus(HttpServletResponse.SC_OK);
		verify(httpServletResponse.getWriter()).println("OK");
	}

	private void queryRequest(QueryServlet servlet, String command, int answer, List<String> expected) throws IOException {
		initRequestMock(Map.of(
				"command", command
		));

		servlet.doGet(httpServletRequest, httpServletResponse);

		verify(httpServletResponse.getWriter()).println("<html><body>");

		for (String str : expected) {
			verify(httpServletResponse.getWriter()).println(str);
		}

		if (answer != Integer.MIN_VALUE) {
			verify(httpServletResponse.getWriter()).println(answer);
		}

		verify(httpServletResponse.getWriter()).println("</body></html>");
	}

}
