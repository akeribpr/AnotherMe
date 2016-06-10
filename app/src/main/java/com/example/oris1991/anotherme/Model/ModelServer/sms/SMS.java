package com.example.oris1991.anotherme.Model.ModelServer.sms;

import com.example.oris1991.anotherme.Model.ModelServer.person.Person;

import java.io.Serializable;


import java.util.Date;


public class SMS implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Double idSMS;
	boolean SmsTamplates;
	String msg;
	Person senderId;// personId
	Date DateTimeSend;// if it send if not ->null
	Person personId;

	public SMS(Double idSMS, boolean SmsTamplates, String msg, Person senderId,
			Date DateTimeSend, Person personId) {
		this.idSMS = idSMS;
		this.SmsTamplates = SmsTamplates;
		this.msg = msg;
		this.senderId = senderId;
		this.DateTimeSend = DateTimeSend;
		this.personId = personId;
	}

	public void setPerson(Person personId) {
		this.personId = personId;
		personId.addSms(this);
	}

	public Double getIdSMS() {
		return idSMS;
	}

	public void setIdSMS(Double idSMS) {
		this.idSMS = idSMS;
	}

	public boolean isSmsTamplates() {
		return SmsTamplates;
	}

	public void setSmsTamplates(boolean smsTamplates) {
		SmsTamplates = smsTamplates;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Person getSenderId() {
		return senderId;
	}

	public void setSenderId(Person senderId) {
		this.senderId = senderId;
	}

	public Date getDateTimeSend() {
		return DateTimeSend;
	}

	public void setDateTimeSend(Date dateTimeSend) {
		DateTimeSend = dateTimeSend;
	}

	public Person getPerson() {
		return personId;
	}

}
