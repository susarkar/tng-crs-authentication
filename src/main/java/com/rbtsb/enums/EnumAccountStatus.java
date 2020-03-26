package com.rbtsb.enums;

public enum EnumAccountStatus implements EnumWebTemplate {
	ACTIVE("Active"), INACTIVE("Inactive"), LOCKED("Locked");
	// Text to be display in the UI
	private String text;

	EnumAccountStatus(String text) {
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
