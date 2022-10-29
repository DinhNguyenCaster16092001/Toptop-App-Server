package com.cp2196g03g2.server.toptop.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_role")
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name", length = 225, nullable = false, unique = true)
	private String name;
	
	@Column(name = "alias", length = 255, nullable = false)
	private String alias;
	
	
	@Column(name = "description" , columnDefinition = "TEXT")
	private String description;

	public Role() {
	}

	

	public Role(Long id, String name, String alias, String description) {
		this.id = id;
		this.name = name;
		this.alias = alias;
		this.description = description;
	}

	

	public Role(String name, String alias, String description) {
		this.name = name;
		this.alias = alias;
		this.description = description;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + ", alias=" + alias + ", description=" + description + "]";
	}

		
}
