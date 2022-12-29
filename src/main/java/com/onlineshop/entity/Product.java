package com.onlineshop.entity;

import java.util.Date;
import java.util.Locale.Category;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.onlineshop.model.ProductDetail;

@Entity
@Table(name = "Products")
public class Product {
	
	@Id
	@Column(name="Code", nullable = false, length = 5)
	private String code;
	
	@Column(name="Name", length = 255, nullable = false)
	private String name;
	
	@Column(name="Price", nullable = false, precision = 10, scale = 1)
	private double price;
	
	@Column(name="Quantity", nullable = false)
	private int quantity;
	
	@Lob
	@Column(name="Image", length = Integer.MAX_VALUE, nullable = true)
	private byte[] image;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="Create_Day", nullable = false)
	private Date createDate;
	
	@ManyToOne
	@JoinColumn(name="CATEGORY_ID", foreignKey = @ForeignKey(name = "CATEGORY_PRO_FK"))
	private Category category;
	
	@ManyToOne
	@JoinColumn(name="PRODUCER_ID", foreignKey = @ForeignKey(name = "PRODUCER_PRO_FK"))
	private Producer producer;
	
	@OneToOne(mappedBy = "product", cascade = CascadeType.ALL)
	private ProductDetail detail;
	
	public Product(String code, String name, double price, int quantity, byte[] image, Date createDate,
			Category category, Producer producer) {
		super();
		this.code = code;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.image = image;
		this.createDate = createDate;
		this.category = category;
		this.producer = producer;
	}
	
	public Product() {
		super();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Producer getProducer() {
		return producer;
	}

	public void setProducer(Producer producer) {
		this.producer = producer;
	}

	public ProductDetail getDetail() {
		return detail;
	}

	public void setDetail(ProductDetail detail) {
		this.detail = detail;
	}
}
