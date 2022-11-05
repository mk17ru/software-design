package ru.akirakozov.sd.refactoring.servlet;

import ru.akirakozov.sd.refactoring.dao.Dao;
import ru.akirakozov.sd.refactoring.dao.ProductDao;
import ru.akirakozov.sd.refactoring.entities.Product;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author akirakozov
 */
public class QueryServlet extends HttpServlet {

    private final Dao<Product> productDao;

    public QueryServlet(Dao<Product> productDao) {
        this.productDao = productDao;
    }
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String command = request.getParameter("command");

        if ("max".equals(command)) {
            response.getWriter().println("<html><body>");
            response.getWriter().println("<h1>Product with max price: </h1>");

            Product product = productDao.findMax();
            response.getWriter().println(product.getName() + "\t" + product.getPrice() + "</br>");

            response.getWriter().println("</body></html>");

        } else if ("min".equals(command)) {
            response.getWriter().println("<html><body>");
            response.getWriter().println("<h1>Product with min price: </h1>");

            Product product = productDao.findMin();
            response.getWriter().println(product.getName() + "\t" + product.getPrice() + "</br>");

            response.getWriter().println("</body></html>");
        } else if ("sum".equals(command)) {
            response.getWriter().println("<html><body>");
            response.getWriter().println("Summary price: ");

            Integer result = productDao.findSum();
            if (result != null) {
                response.getWriter().println((int)result);
            }

            response.getWriter().println("</body></html>");

        } else if ("count".equals(command)) {

            response.getWriter().println("<html><body>");
            response.getWriter().println("Number of products: ");

            Integer result = productDao.findCount();
            if (result != null) {
                response.getWriter().println((int)result);
            }

            response.getWriter().println("</body></html>");


        } else {
            response.getWriter().println("Unknown command: " + command);
        }

        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
    }

}
