package com.syo.platform.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="t_monitor_databaseconfig")
public class DataBaseMonitorConfig {

	@Id
	@GeneratedValue(generator="uuidGen")
	@GenericGenerator(name="uuidGen", strategy="uuid")
	@Column(length=50)
	private String id;
	
	@Column(length=150)
	private String name;//名称
	
	@Column(length=20)
	private String ipAddress;//ip地址
	
	private Integer dbPort;//端口
	
	@Column(length=50)
	private String dbName;//数据库名，实例名
	
	@Column(length=50)
	private String dbUser;
	
	@Column(length=50)
	private String dbPassword;
	
	@Column(length=20)
	private String cron;//执行时间表达式
	
	private int errorContinued = 3;//连续多少次失败认为故障
	
	@Column(length=50)
	private String dbDriver;//数据库驱动
	
	@Column(length=20)
	private String dbVendor;//数据库类型
	
	@Column(length=20)
	private String taskType;
	
	@Column(length=500)
	private String taskHours;
	
	@Column(length=200)
	private String taskWeeks;
	
	@Column(length=200)
	private String taskDays;
	
	@Column(length=1000)
	private String emailReceivers;
	
	@Column(length=1000)
	private String smsReceivers;
	
	@Column(length=200)
	private String remarks;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="create_time")
	private Date createTime = new Date();
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="last_time")
	private Date lastTime;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="next_time")
	private Date nextTime;
	
	@Column(length=10)
	private String status = "未运行";
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastUpdateTime;
	
	@Column(length=10)
	private String lastResult;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public Integer getDbPort() {
		return dbPort;
	}

	public void setDbPort(Integer dbPort) {
		this.dbPort = dbPort;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getDbUser() {
		return dbUser;
	}

	public void setDbUser(String dbUser) {
		this.dbUser = dbUser;
	}

	public String getDbPassword() {
		return dbPassword;
	}

	public void setDbPassword(String dbPassword) {
		this.dbPassword = dbPassword;
	}

	public String getCron() {
		return cron;
	}

	public void setCron(String cron) {
		this.cron = cron;
	}

	public int getErrorContinued() {
		return errorContinued;
	}

	public void setErrorContinued(int errorContinued) {
		this.errorContinued = errorContinued;
	}

	public String getDbDriver() {
		return dbDriver;
	}

	public void setDbDriver(String dbDriver) {
		this.dbDriver = dbDriver;
	}

	public String getDbVendor() {
		return dbVendor;
	}

	public void setDbVendor(String dbVendor) {
		this.dbVendor = dbVendor;
	}

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	public String getTaskHours() {
		return taskHours;
	}

	public void setTaskHours(String taskHours) {
		this.taskHours = taskHours;
	}

	public String getTaskWeeks() {
		return taskWeeks;
	}

	public void setTaskWeeks(String taskWeeks) {
		this.taskWeeks = taskWeeks;
	}

	public String getTaskDays() {
		return taskDays;
	}

	public void setTaskDays(String taskDays) {
		this.taskDays = taskDays;
	}

	public String getEmailReceivers() {
		return emailReceivers;
	}

	public void setEmailReceivers(String emailReceivers) {
		this.emailReceivers = emailReceivers;
	}

	public String getSmsReceivers() {
		return smsReceivers;
	}

	public void setSmsReceivers(String smsReceivers) {
		this.smsReceivers = smsReceivers;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getLastTime() {
		return lastTime;
	}

	public void setLastTime(Date lastTime) {
		this.lastTime = lastTime;
	}

	public Date getNextTime() {
		return nextTime;
	}

	public void setNextTime(Date nextTime) {
		this.nextTime = nextTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLastResult() {
		return lastResult;
	}

	public void setLastResult(String lastResult) {
		this.lastResult = lastResult;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	@Override
	public String toString() {
		return "DataBaseMonitorConfig [id=" + id + ", name=" + name + ", ipAddress=" + ipAddress + ", dbPort=" + dbPort
				+ ", dbName=" + dbName + ", dbUser=" + dbUser + ", dbPassword=" + dbPassword + ", cron=" + cron
				+ ", errorContinued=" + errorContinued + ", dbDriver=" + dbDriver + ", dbVendor=" + dbVendor
				+ ", taskType=" + taskType + ", taskHours=" + taskHours + ", taskWeeks=" + taskWeeks + ", taskDays="
				+ taskDays + ", emailReceivers=" + emailReceivers + ", smsReceivers=" + smsReceivers + ", remarks="
				+ remarks + ", createTime=" + createTime + ", lastTime=" + lastTime + ", nextTime=" + nextTime
				+ ", status=" + status + ", lastUpdateTime=" + lastUpdateTime + ", lastResult=" + lastResult + "]";
	}
	
	
}
