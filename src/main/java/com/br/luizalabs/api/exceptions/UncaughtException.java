package com.br.luizalabs.api.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.br.luizalabs.api.app.Log;

@Provider
public class UncaughtException extends Throwable implements ExceptionMapper<Throwable> {
	private static final long serialVersionUID = 1L;

	@Override
	public Response toResponse(Throwable exception) {

		GenericExceptionEntity entity = new GenericExceptionEntity();
		entity.setCode(Response.Status.BAD_REQUEST.getStatusCode());
		entity.setReason("Uncaught Exception " + exception.getClass() + " : " + exception.getMessage());
		Log.error("Uncaught Exception " + exception.getClass() + " : " + exception.getMessage(), exception);
		return Response.status(Response.Status.OK).entity(entity).build();

	}
}