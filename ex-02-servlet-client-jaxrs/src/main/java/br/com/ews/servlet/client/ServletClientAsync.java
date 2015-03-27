package br.com.ews.servlet.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.Future;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.InvocationCallback;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

/**
 * Servlet implementation class ServletClientAsync
 */
@WebServlet("/clientAsync")
public class ServletClientAsync extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	private Client client;
	
	@PostConstruct
	public void inicializa() {
		client = ClientBuilder.newClient();
		System.out.println("=========== PostConstruct =============");
	}

	@PreDestroy
	public void finaliza() {
		client.close();
		System.out.println("=========== PreDestroy =============");
	}
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletClientAsync() {
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
		
		Future<String> retorno = wt.path("async").path("ETX6654784221412")
					.request(MediaType.TEXT_HTML)
					.async()
					.delete(new InvocationCallback<String>() {

						public void completed(String retorno) {
							
							System.out.println("WS /async/{value} =============== " + retorno);
						}

						public void failed(Throwable throwable) {
							System.out.println(throwable.getMessage());
						}
						
					});
		 
		System.out.println(retorno); 
		
		for(int i = 0; i <=100; i++) {
			pw.println("Aguardando chamada assincrona ["+i+"]");
		}
		
		pw.flush();
		pw.close();
	}

}
