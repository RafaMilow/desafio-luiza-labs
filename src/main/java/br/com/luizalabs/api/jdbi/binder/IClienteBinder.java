package br.com.luizalabs.api.jdbi.binder;

import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.GetGeneratedKeys;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import br.com.luizalabs.api.jdbi.mapper.ClienteMapper;
import br.com.luizalabs.api.to.ClienteRequest;

public abstract class IClienteBinder {

	@SqlQuery("select id, nome, email from clientes where flagDelete = 0")
	@RegisterMapper(ClienteMapper.class)
	public abstract List<ClienteRequest> getClientesColletcion();

	@SqlQuery("select id, nome, email from clientes where id = :clientId")
	@RegisterMapper(ClienteMapper.class)
	public abstract ClienteRequest getCliente(@Bind("clientId") Integer clientId);

	@SqlUpdate("update clientes set nome = :nome, email = :email where id = :id")
	public abstract int update(@Bind("id") Integer id, @Bind("nome") String nome, @Bind("email") String email);
	
	@SqlUpdate("insert into clientes (nome, email) values (:nome, :email)")
	@GetGeneratedKeys
	public abstract int insertClient(@Bind("nome") String nome, @Bind("email") String email);
	
	@SqlUpdate("update clientes set flagDelete = 1 where id = :id")
	public abstract int softDelete(@Bind("id") Integer id);

}
