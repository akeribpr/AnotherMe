package com.example.oris1991.anotherme.Model.ModelServer.person;

import java.io.Serializable;
import java.util.Date;

public  class ServerGpsSettings implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Double idSettings;
	String fullName;
	String phoneNumber;
	int age;
	String password;
	Date DateTimeRegister;
	String mail;
	 String personId;
	ServerPerson serverPerson;
	Boolean PopUps =true;
	Boolean Sms=false;
	Boolean Solution =true;
	Boolean gps=true;

	public ServerGpsSettings(Double idSettings, String phoneNumber,
							 String password, Date DateTimeRegister, String mail, String personId) {
		this.idSettings = idSettings;
		this.phoneNumber = phoneNumber;
		this.password = password;
		this.DateTimeRegister = DateTimeRegister;
		this.mail = mail;
		this.personId=personId;
		PopUps =true;
		Sms=false;
		Solution =true;
		gps=true;
	}

	public Double getIdSettings() {
		return idSettings;
	}

	public void setIdSettings(Double idSettings) {
		this.idSettings = idSettings;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getDateTimeRegister() {
		return DateTimeRegister;
	}

	public void setDateTimeRegister(Date dateTimeRegister) {
		DateTimeRegister = dateTimeRegister;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public ServerPerson getServerPerson() {
		return serverPerson;
	}

	public void setServerPerson(ServerPerson serverPerson) {
		this.serverPerson = serverPerson;
		serverPerson.setServerGpsSettings(this);
	}

	public Boolean getPopUps() {
		return PopUps;
	}

	public void setPopUps(Boolean popUps) {
		PopUps = popUps;
	}

	public Boolean getSms() {
		return Sms;
	}

	public void setSms(Boolean sms) {
		Sms = sms;
	}

	public Boolean getSolution() {
		return Solution;
	}

	public void setSolution(Boolean solution) {
		Solution = solution;
	}

	public Boolean getGps() {
		return gps;
	}

	public void setGps(Boolean gps) {
		this.gps = gps;
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

}
