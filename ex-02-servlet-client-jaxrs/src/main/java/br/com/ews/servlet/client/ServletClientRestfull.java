package br.com.ews.servlet.client;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

/**
 * Servlet implementation class ServletClientRestfull
 */
@WebServlet("/client")
public class ServletClientRestfull extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	private Client client;
	
	@PostConstruct
	public void inicializa() {
		client = ClientBuilder.newClient();
	}

	@PreDestroy
	public void finaliza() {
		client.close();
	}
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletClientRestfull() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		final PrintWriter pw = response.getWriter();
		
		WebTarget wt = client.target("http://localhost:8080/ex-01-config-jaxrs/service/consulta/");
				
		// @Path("/saldo/{agencia}/{conta}") 
		String saldo = wt.path("saldo")
				.path("1011").path("47227-9") 
				//.path("{agencia}").path("{conta}")
				//.resolveTemplate("agencia", "1011").resolveTemplate("conta", "47227-9")
				.register(LogFilter.class) //registra Filter para interceptacao
				.request(MediaType.TEXT_HTML).get(String.class);
		
		
		pw.println("WS /consulta/saldo/ =============== " + saldo);
		
		// @Path("/create/cliente") 
		Cliente c = wt.path("create").path("cliente").request(MediaType.APPLICATION_XML)
				.post(Entity.xml(new Cliente(245, "Eduardo Wallace", "edubossa@gmail.com", "(011) 97672-7500")), Cliente.class);
		
		pw.println("WS /create/cliente/ =============== " + c);
		
		
		pw.flush();
		pw.close();
	}

}