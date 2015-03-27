package br.com.ews.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

@Path("/consulta")
public class ConsultaSaldoRestful {
	
	@Path("/saldo/{agencia}/{conta}")
	@GET
	@Produces({MediaType.TEXT_HTML})
	public String saldo(@PathParam("agencia") String agencia, @PathParam("conta") String conta) {
		System.out.println("======== /consulta/saldo/{agencia}/{conta} ========== ");
		System.out.println(agencia);
		System.out.println(conta);
		
		return "R$ 1,325.45";
	}
	
	
	@Path("/formulario")
	@POST
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.TEXT_HTML})
	public String fieldsForm(@FormParam("nome") String nome, @FormParam("email") String email, 
			@FormParam("telefone") String telefone  ) {
		
		System.out.println(nome);
		System.out.println(email);
		System.out.println(telefone);
		
		return "OK";
	}
	
	@Path("/formulario/all")
	@POST
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED})
	@Produces({MediaType.TEXT_HTML}) 
	public String allFieldsForm(MultivaluedMap<String, String> formParams) {
		
		for(String key : formParams.keySet()) {
			System.out.println("Campo: " + key + " Valor: " + formParams.getFirst(key)); 
		}
		
		return "OK";
	}
	
	@Path("/create/cliente")
	@POST
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Cliente create(Cliente cliente) {
			
		Cliente c = cliente;
		System.out.println("======== /consulta/create/cliente ========== ");
		System.out.println(c);
		return c;
	}
	
	@Path("/async/{value}")
	@DELETE
	@Consumes(MediaType.TEXT_HTML)
	@Produces(MediaType.TEXT_HTML)
	public String asyncMetodo(@PathParam("value") String value) {
		
		System.out.println("======== /consulta/async/{value} ========== ");
		System.out.println(value);
		
		try {
			Thread.sleep(1000*10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return "OK";
	}
	
}