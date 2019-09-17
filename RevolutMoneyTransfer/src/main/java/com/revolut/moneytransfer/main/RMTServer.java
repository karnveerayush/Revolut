package com.revolut.moneytransfer.main;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import com.revolut.moneytransfer.apis.RevolutMoneyTransferController;

public class RMTServer {

	public static void main(String[] args) throws Exception {
		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
		context.setContextPath("/");

		Server server = new Server(8080);
		server.setHandler(context);

		ServletHolder jerseyServlet = context.addServlet(org.glassfish.jersey.servlet.ServletContainer.class, "/*");
		jerseyServlet.setInitOrder(0);

		jerseyServlet.setInitParameter("jersey.config.server.provider.classnames",
				RevolutMoneyTransferController.class.getCanonicalName());

		try {
			server.start();
			server.join();
		} finally {
			server.destroy();
		}
	}
}
