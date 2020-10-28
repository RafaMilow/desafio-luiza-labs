package br.com.luizalabs.api.jdbi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import br.com.luizalabs.api.jdbi.DAOUtils;
import br.com.luizalabs.api.to.ClienteRequest;

public class ClienteMapper implements ResultSetMapper<ClienteRequest> {

	@Override
	public ClienteRequest map(int index, ResultSet rs, StatementContext sc) throws SQLException {
		ClienteRequest cliente = new ClienteRequest();
		cliente.setId(DAOUtils.getIntValue(rs, "id"));
		cliente.setNome(DAOUtils.getStringValue(rs, "nome"));
		cliente.setEmail(DAOUtils.getStringValue(rs, "email"));
		return cliente;
	}

}
