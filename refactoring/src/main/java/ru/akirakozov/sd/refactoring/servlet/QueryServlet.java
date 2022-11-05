package ru.akirakozov.sd.refactoring.servlet;

import ru.akirakozov.sd.refactoring.dao.Dao;
import ru.akirakozov.sd.refactoring.entities.Product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author akirakozov
 */
public class QueryServlet extends AbstractProductService {

	private final Dao<Product> productDao;

	public QueryServlet(Dao<Product> productDao) {
		this.productDao = productDao;
	}

	@Override
	public void doGetImpl(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String command = request.getParameter("command");

		switch (command) {
			case "max": {
				response.getWriter().println("<h1>Product with max price: </h1>");
				Product product = productDao.findMax();
				response.getWriter().println(product.getName() + "\t" + product.getPrice() + "</br>");
				break;
			}
			case "min": {
				response.getWriter().println("<h1>Product with min price: </h1>");
				Product product = productDao.findMin();
				response.getWriter().println(product.getName() + "\t" + product.getPrice() + "</br>");
				break;
			}
			case "sum": {
				response.getWriter().println("Summary price: ");
				Integer result = productDao.findSum();
				if (result != null) {
					response.getWriter().println((int) result);
				}
				break;
			}
			case "count": {
				response.getWriter().println("Number of products: ");
				Integer result = productDao.findCount();
				if (result != null) {
					response.getWriter().println((int) result);
				}
				break;
			}
			default:
				response.getWriter().println("Unknown command: " + command);
				break;
		}
	}

}
