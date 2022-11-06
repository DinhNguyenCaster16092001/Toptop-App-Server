package com.cp2196g03g2.server.toptop.enums;

public enum NotificationType {
	LIKE(1), 
	COMMENT(2), 
	REPLY_COMMENT(3), 
	FOLLOW(4), 
	MESSAGE(5);

	private int value;

	private NotificationType(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
