package com.pioneercoders.dto;

public class DetailsDto {
	private int id;
	private String name;
	private long mobileNo;
	private String emailId;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(long mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public DetailsDto(int id,String name, long mobileNo, String emailId) {
		super();
		this.id = id;
		this.name = name;
		this.mobileNo = mobileNo;
		this.emailId = emailId;
	}
	
}
