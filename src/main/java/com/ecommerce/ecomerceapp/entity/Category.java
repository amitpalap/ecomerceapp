package com.ecommerce.ecomerceapp.entity;

import javax.persistence.*;
 

@Entity
@Table(name= "category")   
public class Category { 


	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private long ID;
	
	@Column(name = "name")
	private String name;
	
	
	public long getID() {return this.ID; }
	public String getName() { return this.name;}
	
	public void setID(long id) { this.ID = id;}
	public void setName(String value) { this.name = value;}
	
}
