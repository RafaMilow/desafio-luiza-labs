package com.br.luizalabs.api.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<GenericException> {
    
    @Override
    public Response toResponse(GenericException exception) {
	Response r = exception.getResponse();
//	return Response.status(r.getStatus()).entity("Code:" + r.getStatus() + ":" + getClass().getSimpleName())
//		.build();
	return r;
    }
    
}
