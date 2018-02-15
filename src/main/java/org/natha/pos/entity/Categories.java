package org.natha.pos.entity;

public class Categories {
	private String categoriesId;
	private String name;
	private String parentid;
//	image bytea
	private String texttip;
	private Boolean catshowname;
	
	public Categories() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getCategoriesId() {
		return categoriesId;
	}

	public void setCategoriesId(String id) {
		this.categoriesId = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	public String getTexttip() {
		return texttip;
	}

	public void setTexttip(String texttip) {
		this.texttip = texttip;
	}

	public Boolean getCatshowname() {
		return catshowname;
	}

	public void setCatshowname(Boolean catshowname) {
		this.catshowname = catshowname;
	}
	
}
