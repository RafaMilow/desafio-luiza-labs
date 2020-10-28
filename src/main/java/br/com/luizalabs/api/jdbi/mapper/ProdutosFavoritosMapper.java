package br.com.luizalabs.api.jdbi.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import br.com.luizalabs.api.to.ProductTO;

public class ProdutosFavoritosMapper implements ResultSetMapper<ProductTO> {

	@Override
	public ProductTO map(int index, ResultSet rs, StatementContext sc) throws SQLException {
		ProductTO product = new ProductTO();
		product.setId(rs.getInt("product_id"));
		product.setName(rs.getString("name"));
		product.setPrice(rs.getBigDecimal("price"));
		product.setImage(rs.getString("image"));
		return product;
	}
	
}
