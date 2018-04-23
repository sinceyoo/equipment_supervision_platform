package com.syo.platform.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="t_monitor_user")
public class User {
	
	@Id
	@GeneratedValue(generator="assignedGen")
	@GenericGenerator(name="assignedGen", strategy="assigned")
	@Column(length=50)
	private String account;
	
	@Column(length=50)
	private String password;
	
	@Column(length=50)
	private String name;
	
	@Column(length=20)
	private String telephone;
	
	@Column(length=20)
	private String identity;
	
	@Column(length=20)
	private String workNo;
	
	@Column(length=50)
	private String email;
	
	@Column(length=200)
	private String remarks;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public String getWorkNo() {
		return workNo;
	}

	public void setWorkNo(String workNo) {
		this.workNo = workNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	

}
