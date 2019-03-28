package com.centraprise.hrmodule.model;

public class UserRegistration {

	private String username;
	private String password;
	private String emailid;
	private String phone;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmailid() {
		return emailid;
	}

	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "UserRegistration [username=" + username + ", password=" + password + ", emailid=" + emailid + ", phone="
				+ phone + "]";
	}

}
