package com.br.luizalabs.api.exceptions;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.inject.Singleton;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Singleton
@Provider
public class ConstraintViolationMapper implements ExceptionMapper<ConstraintViolationException> {

	@Override
	public Response toResponse(ConstraintViolationException e) {
		
		GenericExceptionEntity entity = new GenericExceptionEntity();
		entity.setCode(Status.BAD_REQUEST.getStatusCode());
		
		Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
		List<String> messages = new ArrayList<>();
		for (ConstraintViolation<?> violation : violations) {
			messages.add(violation.getMessage());
		}
		String result = String.join(", ", messages);
		entity.setReason(result);
		return Response.status(Status.BAD_REQUEST).entity(entity).build();
	}

}