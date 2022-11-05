package ru.akirakozov.sd.refactoring.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class AbstractProductService extends HttpServlet {

	protected abstract void doGetImpl(HttpServletRequest request, HttpServletResponse response) throws IOException;
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.getWriter().println("<html><body>");
		doGetImpl(request, response);
		response.getWriter().println("</body></html>");
		response.setContentType("text/html");
		response.setStatus(HttpServletResponse.SC_OK);
	}



}
