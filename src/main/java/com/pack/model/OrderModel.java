package com.pack.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Entity
@Table(name="orders")
public class OrderModel {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@Valid
	private UserModel user;
	@OneToOne
	private TopingModel toping;
	@NotEmpty
	private String status;
	private Date orderReceivedDate;
	private Date orderDeliveryDate;
	private float totalPrice;
	
	
	
	
	public OrderModel(UserModel user, TopingModel toping, String status, Date orderReceivedDate, Date orderDeliveryDate,
			float totalPrice) {
		super();
		this.user = user;
		this.toping = toping;
		this.status = status;
		this.orderReceivedDate = orderReceivedDate;
		this.orderDeliveryDate = orderDeliveryDate;
		this.totalPrice = totalPrice;
	}

	


	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public UserModel getUser() {
		return user;
	}



	public void setUser(UserModel user) {
		this.user = user;
	}



	public TopingModel getToping() {
		return toping;
	}



	public void setToping(TopingModel toping) {
		this.toping = toping;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}



	public Date getOrderReceivedDate() {
		return orderReceivedDate;
	}



	public void setOrderReceivedDate(Date orderReceivedDate) {
		this.orderReceivedDate = orderReceivedDate;
	}



	public Date getOrderDeliveryDate() {
		return orderDeliveryDate;
	}



	public void setOrderDeliveryDate(Date orderDeliveryDate) {
		this.orderDeliveryDate = orderDeliveryDate;
	}



	public float getTotalPrice() {
		return totalPrice;
	}



	public void setTotalPrice(float totalPrice) {
		this.totalPrice = totalPrice;
	}



	public OrderModel() {
		super();
	}

		@Override
	public String toString() {
		return "OrderModel [id=" + id + ", user=" + user + ", toping=" + toping + ", status=" + status
				+ ", orderReceivedDate=" + orderReceivedDate + ", orderDeliveryDate=" + orderDeliveryDate
				+ ", totalPrice=" + totalPrice + "]";
	}




		public OrderModel(int id, UserModel user, TopingModel toping, String status, Date orderReceivedDate,
				Date orderDeliveryDate, float totalPrice) {
			super();
			this.id = id;
			this.user = user;
			this.toping = toping;
			this.status = status;
			this.orderReceivedDate = orderReceivedDate;
			this.orderDeliveryDate = orderDeliveryDate;
			this.totalPrice = totalPrice;
		}

		
	
}
