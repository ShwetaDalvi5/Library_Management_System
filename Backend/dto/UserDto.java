package com.library.dto;

public class UserDto {
	
	int uid;
	String username;
	String password;
	String email;
	String contactNo;
	
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public UserDto(int uid, String username, String password, String email, String contactNo) {
		
		this.uid = uid;
		this.username = username;
		this.password = password;
		this.email = email;
		this.contactNo = contactNo;
		
	}
	
	public UserDto() {	
	}
	
	@Override
	public String toString() {
		return "UserDto [uid=" + uid + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", contactNo=" + contactNo + "]";
	}
}
