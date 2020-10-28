package br.com.luizalabs.api.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import br.com.luizalabs.api.app.Log;
import br.com.luizalabs.api.to.GenericResponseEntity;

@Provider
public class UncaughtException extends Throwable implements ExceptionMapper<Throwable> {
	private static final long serialVersionUID = 1L;

	@Override
	public Response toResponse(Throwable exception) {

		GenericResponseEntity entity = new GenericResponseEntity();
		entity.setCode(Response.Status.BAD_REQUEST.getStatusCode());
		entity.setReason("Uncaught Exception " + exception.getClass() + " : " + exception.getMessage());
		Log.error("Uncaught Exception " + exception.getClass() + " : " + exception.getMessage(), exception);
		return Response.status(Response.Status.OK).entity(entity).build();

	}
}
