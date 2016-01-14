package rocks.huwi.liberatepdf.war;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import rocks.huwi.liberatepdf.ejb.RemoteEchoInterface;

@WebServlet("/echo")
public class EchoServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
	private static final long serialVersionUID = 769430204708549626L;

	@EJB
	RemoteEchoInterface remoteEchoInterface;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");

		if ((name == null) || (name.length() == 0)) {
			name = "anonymous";
		}

		response.getWriter().write(this.remoteEchoInterface.echo(name));
	}
}
