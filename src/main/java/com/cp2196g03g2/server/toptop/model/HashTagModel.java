package com.cp2196g03g2.server.toptop.model;

public class HashTagModel {
	private Long id;
	private String name;
	private Long view;

	public HashTagModel(Long id, String name, Long view) {
		this.id = id;
		this.name = name;
		this.view = view;
	}
	
	public HashTagModel(String name, Long view) {
		this.name = name;
		this.view = view;
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

	public Long getView() {
		return view;
	}

	public void setView(Long view) {
		this.view = view;
	}

	@Override
	public String toString() {
		return "HashTagModel [id=" + id + ", name=" + name + ", view=" + view + "]";
	}

	
}
