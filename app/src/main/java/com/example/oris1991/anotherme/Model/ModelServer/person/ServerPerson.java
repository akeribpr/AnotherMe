package com.example.oris1991.anotherme.Model.ModelServer.person;

import com.example.oris1991.anotherme.Model.ModelServer.GPS.ServerGps;
import com.example.oris1991.anotherme.Model.ModelServer.Solution.ServerSolution;
import com.example.oris1991.anotherme.Model.ModelServer.Task.ServerPopUp;
import com.example.oris1991.anotherme.Model.ModelServer.Task.ServerTask;
import com.example.oris1991.anotherme.Model.ModelServer.sms.ServerSMS;

import java.io.Serializable;
import java.util.List;



public  class ServerPerson implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String personId;// like mail
	ServerGpsSettings serverGpsSettings;
	private List<ServerTask> serverTask;
	private List<ServerGps> gps;
	private List<ServerSMS> sms;
	private List<ServerPopUp> serverPopUp;
	private List<ServerSolution> serverSolution;

	public ServerPerson(String personId, ServerGpsSettings serverGpsSettings) {
		this.personId = personId;
		this.serverGpsSettings = serverGpsSettings;
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public void addGps(ServerGps gps) {
		this.gps.add(gps);
	}

	public void addtask(ServerTask serverTask) {
		this.serverTask.add(serverTask);
	}

	public void addSms(ServerSMS serverSms) {
		this.sms.add(serverSms);
	}

	public void addSolution(ServerSolution serverSolution) {
		this.serverSolution.add(serverSolution);
	}

	public void addpopUp(ServerPopUp serverPopUp) {
		this.serverPopUp.add(serverPopUp);
	}

	public ServerGpsSettings getServerGpsSettings() {
		return serverGpsSettings;
	}

	public void setServerGpsSettings(ServerGpsSettings serverGpsSettings) {
		this.serverGpsSettings = serverGpsSettings;
	}

	public List<ServerTask> getServerTask() {
		return serverTask;
	}

	public void setServerTask(List<ServerTask> serverTask) {
		serverTask = serverTask;
	}

	public List<ServerGps> getGps() {
		return gps;
	}

	public void setGps(List<ServerGps> gps) {
		this.gps = gps;
	}

	public List<ServerSMS> getSms() {
		return sms;
	}

	public void setSms(List<ServerSMS> sms) {
		this.sms = sms;
	}

	public List<ServerPopUp> getServerPopUp() {
		return serverPopUp;
	}

	public void setServerPopUp(List<ServerPopUp> serverPopUp) {
		this.serverPopUp = serverPopUp;
	}

	public List<ServerSolution> getServerSolution() {
		return serverSolution;
	}

	public void setServerSolution(List<ServerSolution> serverSolution) {
		this.serverSolution = serverSolution;
	}

}
