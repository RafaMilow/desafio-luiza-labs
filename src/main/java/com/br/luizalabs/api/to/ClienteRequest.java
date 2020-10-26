package com.br.luizalabs.api.to;

public class ClienteRequest {

	private Integer id;
	private String nome;
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
