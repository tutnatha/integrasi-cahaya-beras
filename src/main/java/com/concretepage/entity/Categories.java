package com.concretepage.entity;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="categories")
public class Categories implements Serializable{
//public class Categories {
/*	
	id character varying NOT NULL,
  	name character varying NOT NULL,
  	parentid character varying,
  	image bytea,
  	texttip character varying,
  	catshowname boolean NOT NULL DEFAULT true,
  	CONSTRAINT categories_pkey PRIMARY KEY (id),
  	CONSTRAINT categories_fk_1 FOREIGN KEY (parentid)
*/	
	private static final long serialVersionUID = 1L;
	
	@Id 
//	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id", unique = true, nullable = false)
//	private UUID categoriesId;
	private String categoriesId;
	@Column(name="name")
	private String name;
	@Column(name="parentid")
	private String parentid;
//	image bytea
	@Column(name="texttip")
	private String texttip;
	@Column(name="catshowname")
	private Boolean catshowname;
	
	public Categories(String id, String name, String parentid, String texttip, Boolean catshowname) {
		super();
		this.categoriesId = id;
		this.name = name;
		this.parentid = parentid;
		this.texttip = texttip;
		this.catshowname = catshowname;
	}
	
	public Categories() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getCategoriesId() {
//	public UUID getCategoriesId() {
		return categoriesId;
	}
	
	public void setCategoriesId(String id) {
//	public void setCategoriesId(UUID id) {
		id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		name = name;
	}
	public String getParentid() {
		return parentid;
	}
	public void setParentid(String parentid) {
		parentid = parentid;
	}
	public String getTexttip() {
		return texttip;
	}
	public void setTexttip(String texttip) {
		texttip = texttip;
	}
	public Boolean getCatshowname() {
		return catshowname;
	}
	public void setCatshowname(Boolean catshowname) {
		catshowname = catshowname;
	}
}
