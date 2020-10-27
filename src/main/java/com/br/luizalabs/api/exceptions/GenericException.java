package com.br.luizalabs.api.exceptions;

import javax.ws.rs.core.Response;

public class GenericException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private Response response;

	public GenericException(Response response) {
		this.response = response;
	}

	public GenericException(String reason) {
		GenericExceptionEntity entity = new GenericExceptionEntity();
		entity.setCode(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
		entity.setReason(Response.Status.INTERNAL_SERVER_ERROR.getReasonPhrase());
		Response response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(entity).build();
		this.response = response;
	}

	public GenericException(Response.Status status, String reason) {
		GenericExceptionEntity entity = new GenericExceptionEntity();
		entity.setCode(status.getStatusCode());
		entity.setReason(reason);
		Response response = Response.status(status).entity(entity).build();
		this.response = response;
	}

	public Response getResponse() {
		return response;
	}
}
