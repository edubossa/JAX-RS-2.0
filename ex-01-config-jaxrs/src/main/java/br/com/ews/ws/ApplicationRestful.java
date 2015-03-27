package br.com.ews.ws;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/service")
public class ApplicationRestful extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		
		final Set<Class<?>> classes = new HashSet<Class<?>>();
		classes.add(ConsultaSaldoRestful.class);
		
		return classes;
	}

	
}
