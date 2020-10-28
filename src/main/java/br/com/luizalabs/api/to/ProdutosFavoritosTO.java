package br.com.luizalabs.api.to;

public class ProdutosFavoritosTO {

	private Integer clientId;
	private Integer productId;
	private Integer softDelete;

	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getSoftDelete() {
		return softDelete;
	}

	public void setSoftDelete(Integer softDelete) {
		this.softDelete = softDelete;
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("ProdutosFavoritosTO [clientId=");
		stringBuilder.append(clientId);
		stringBuilder.append(", productId=");
		stringBuilder.append(productId);
		stringBuilder.append(", softDelete=");
		stringBuilder.append(softDelete);
		stringBuilder.append("]");
		return stringBuilder.toString();
	}

}
