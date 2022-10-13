package com.cp2196g03g2.server.toptop.dto;

public class ObjectKey {
	private String target;
	private String id;
	

	public ObjectKey() {
	}

	public ObjectKey(String target, String id) {
		this.target = target;
		this.id = id;
	}



	public String getTarget() {
		return target;
	}



	public void setTarget(String target) {
		this.target = target;
	}



	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}


	@Override
	public String toString() {
		return "ObjectKey [target=" + target + ", id=" + id + "]";
	}
	
	
}
