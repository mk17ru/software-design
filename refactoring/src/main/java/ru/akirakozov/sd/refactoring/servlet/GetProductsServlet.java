package ru.akirakozov.sd.refactoring.servlet;

import ru.akirakozov.sd.refactoring.dao.Dao;
import ru.akirakozov.sd.refactoring.entities.Product;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author akirakozov
 */
public class GetProductsServlet extends AbstractProductService {

	private final Dao<Product> productDao;

	public GetProductsServlet(Dao<Product> productDao) {
		this.productDao = productDao;
	}

	@Override
	public void doGetImpl(HttpServletRequest request, HttpServletResponse response) throws IOException {

		List<Product> products = productDao.getAll();

		for (Product product : products) {
			response.getWriter().println(product.getName() + "\t" + product.getPrice() + "</br>");
		}
	}
}
