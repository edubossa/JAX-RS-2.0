package br.com.ews.servlet.client;

import java.io.IOException;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;
import javax.ws.rs.ext.Provider;

/**
 * Filtro responsavel por interceptar a Consulta do Saldo
 * 
 * @author Eduardo Wallace
 *
 */
@Provider
public class LogFilter implements ClientRequestFilter, ClientResponseFilter {
	
	/**
	 * Intercepta a request
	 */
	public void filter(ClientRequestContext requestContext) throws IOException {
		
		System.out.println("Interceptando ClientRequestFilter");		
	}

	/**
	 * Intercepta o Response
	 */
	public void filter(ClientRequestContext requestContext,
			ClientResponseContext responseContext) throws IOException {
		
		System.out.println("Interceptando ClientResponseFilter");
	}

}