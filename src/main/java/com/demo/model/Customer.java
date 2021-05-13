package com.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "tbl_customer")
public class Customer {
	
		@Id // primary key unique
		@GeneratedValue(strategy = GenerationType.AUTO) // auto increment
		@Column(name = "id", updatable = false)
	private int id;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "address")
	private String address;
	
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
	
}


/**
 * create table tbl_customer(
 * 	id int not null auto_increment primary key,
 * 	name varchar(255) not null,
 * 	address varchar(255)
 * );
 * orm
 * 
 * */
