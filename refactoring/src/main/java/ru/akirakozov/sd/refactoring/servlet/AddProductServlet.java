package ru.akirakozov.sd.refactoring.servlet;

import ru.akirakozov.sd.refactoring.dao.Dao;
import ru.akirakozov.sd.refactoring.entities.Product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author akirakozov
 */
public class AddProductServlet extends AbstractProductService {

	private final Dao<Product> productDao;

	public AddProductServlet(Dao<Product> productDao) {
		this.productDao = productDao;
	}

	@Override
	public void doGetImpl(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String name = request.getParameter("name");
		long price = Long.parseLong(request.getParameter("price"));
		Product product = new Product(name, price);
		productDao.add(product);

		response.getWriter().println("OK");
	}
}
