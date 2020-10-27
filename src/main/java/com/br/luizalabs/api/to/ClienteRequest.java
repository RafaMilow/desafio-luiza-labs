package com.br.luizalabs.api.to;

import javax.validation.constraints.Pattern;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.NotBlank;

@XmlRootElement
public class ClienteRequest {

	private Integer id;
	
	@NotBlank(message = "Nome não pode ser vazio!")
	private String nome;

	@Pattern(message = "Endereço de e-mail invalido.", regexp = "^[a-zA-Z0-9_!#$%&�*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
	private String email;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("ClienteRequest [id=");
		stringBuilder.append(id);
		stringBuilder.append(", nome=");
		stringBuilder.append(nome);
		stringBuilder.append(", email=");
		stringBuilder.append(email);
		stringBuilder.append("]");
		return stringBuilder.toString();
	}

}
