package com.br.luizalabs.api.exceptions;

import javax.ws.rs.core.Response;

import com.br.luizalabs.api.to.ResponseGeneric;

public class GenericException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private Response response;

	public GenericException(Response response) {
		this.response = response;
	}

	public GenericException(String messge) {
		ResponseGeneric detailResponse = new ResponseGeneric();
		detailResponse.setInError(true);
		detailResponse.setMessage(messge);

		Response response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(detailResponse).build();
		this.response = response;
	}

	public GenericException(Response.Status status, String messge) {
		ResponseGeneric detailResponse = new ResponseGeneric();
		detailResponse.setInError(true);
		detailResponse.setMessage(messge);

		Response response = Response.status(status).entity(detailResponse).build();
		this.response = response;
	}

	public Response getResponse() {
		return response;
	}
}
