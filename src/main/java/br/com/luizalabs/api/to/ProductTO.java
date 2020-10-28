package br.com.luizalabs.api.to;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ProductTO {

	private Integer id;
	private String name;
	private BigDecimal price;
	private String image;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Product [id=");
		stringBuilder.append(id);
		stringBuilder.append(", name=");
		stringBuilder.append(name);
		stringBuilder.append(", price=");
		stringBuilder.append(price);
		stringBuilder.append(", image=");
		stringBuilder.append(image);
		stringBuilder.append("]");
		return stringBuilder.toString();
	}

}
