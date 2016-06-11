package com.example.oris1991.anotherme.Model.ModelServer.sms;

import com.example.oris1991.anotherme.Model.ModelServer.person.ServerPerson;

import java.io.Serializable;


import java.util.Date;


public class ServerSMS implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Double idSMS;
	boolean SmsTamplates;
	String msg;
	ServerPerson senderId;// serverPersonId
	Date DateTimeSend;// if it send if not ->null
	ServerPerson serverPersonId;

	public ServerSMS(Double idSMS, boolean SmsTamplates, String msg, ServerPerson senderId,
					 Date DateTimeSend, ServerPerson serverPersonId) {
		this.idSMS = idSMS;
		this.SmsTamplates = SmsTamplates;
		this.msg = msg;
		this.senderId = senderId;
		this.DateTimeSend = DateTimeSend;
		this.serverPersonId = serverPersonId;
	}

	public void setPerson(ServerPerson serverPersonId) {
		this.serverPersonId = serverPersonId;
		serverPersonId.addSms(this);
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

	public ServerPerson getSenderId() {
		return senderId;
	}

	public void setSenderId(ServerPerson senderId) {
		this.senderId = senderId;
	}

	public Date getDateTimeSend() {
		return DateTimeSend;
	}

	public void setDateTimeSend(Date dateTimeSend) {
		DateTimeSend = dateTimeSend;
	}

	public ServerPerson getPerson() {
		return serverPersonId;
	}

}
