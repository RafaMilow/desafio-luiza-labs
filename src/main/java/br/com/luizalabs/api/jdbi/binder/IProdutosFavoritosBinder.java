package br.com.luizalabs.api.jdbi.binder;

import java.math.BigDecimal;
import java.util.List;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import br.com.luizalabs.api.jdbi.mapper.ProdutosFavoritosMapper;
import br.com.luizalabs.api.to.ProductTO;

@RegisterMapper(ProdutosFavoritosMapper.class)
public abstract class IProdutosFavoritosBinder {

	@SqlQuery("SELECT product_id, name, price, image FROM produtos_favoritos where client_id = :clientId")
	public abstract List<ProductTO> getListaDoCliente(@Bind("clientId") Integer clientId);

	@SqlUpdate("DELETE FROM produtos_favoritos WHERE client_id = :clientId and product_id = :productId")
	public abstract int deleteOneProduct(@Bind("clientId") Integer clientId, @Bind("productId") Integer productId);

	@SqlUpdate("DELETE FROM produtos_favoritos WHERE client_id = :clientId")
	public abstract int deleteAllProducts(@Bind("clientId") Integer clientId);

	@SqlUpdate("insert into produtos_favoritos (client_id, product_id, name, price, image) "
			+ "values (:clientId, :productId, :name, :price, :image)")
	public abstract int insertOnList(@Bind("clientId") Integer clientId, 
			@Bind("productId") Integer productId,
			@Bind("name") String name,
			@Bind("price") BigDecimal price,
			@Bind("image") String image);

	@SqlUpdate("insert into produtos_favoritos (client_id, product_id, name, price, image) "
			+ "values (:clientId, :product.id, :product.name, :product.price, :product.image)")
	public abstract int insertBeanOnList(@Bind("clientId") Integer clientId, 
			@BindBean("product") ProductTO product);
}
