package com.br.luizalabs.api.to;

public class ResponseGeneric {

    private Boolean inError;
    private String message;

    public Boolean getInError() {
	return inError;
    }

    public void setInError(Boolean inError) {
	this.inError = inError;
    }

    public String getMessage() {
	return message;
    }

    public void setMessage(String message) {
	this.message = message;
    }

    @Override
    public String toString() {
	return "ResponseGeneric [inError=" + inError + ", message=" + message + "]";
    }

}
