package net.ueye.cms.server;

import org.mortbay.jetty.Connector;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.bio.SocketConnector;
import org.mortbay.jetty.webapp.WebAppContext;

public class SecurityCenterJettyLauncherTool {

	private static final String CONTEXT = "/cms";

	private static Server server;

	public static final void startWebApp() throws Exception {

		long begin = System.currentTimeMillis();
		server = new Server();
		SocketConnector socketConnector = new SocketConnector();
		socketConnector.setPort(80);

		server.setConnectors(new Connector[] { socketConnector });
		WebAppContext context = new WebAppContext("src/main/webapp", CONTEXT);
		server.addHandler(context);
		server.setStopAtShutdown(true);
		server.setSendServerVersion(true);
		server.start();

		System.out.println("Jetty Server started in [" + (System.currentTimeMillis() - begin) + "] ms");
	}

	public static final void stopWebApp() throws Exception {
		if (server != null && server.isRunning()) {
			server.stop();
		}
	}

	public static void main(String[] args) throws Exception {
		startWebApp();
	}
}
