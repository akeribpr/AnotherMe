package com.example.oris1991.anotherme.Model.ModelServer.Task;

import com.example.oris1991.anotherme.Model.Entities.Task;
import com.example.oris1991.anotherme.Model.ModelServer.Solution.ServerSolution;
import com.example.oris1991.anotherme.Model.ModelServer.person.ServerPerson;

import java.io.Serializable;
import java.util.Date;



public  class ServerTask implements Serializable {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	// //string-> int
	public final static int Nothing = 1;
	public final static int Ask = 2;
	public final static int PopUp = 3;
	public final static int Sms = 4;
	public final static int Ticket = 5;
	public final static int Shoping = 6;//Shopping
	public final static int BabySiter = 7;//Babysitter
	public final static int Meeting = 8;//meeting
	public final static int CALENDARTASK = 9;
	public final static int APPTASK = 10;

	// /////////////////////////////

	Double idTask;
	ServerPerson serverPerson;
	String taskText;
	Date start;
	Date end;
	String address;
	int whatToDo;
	int platform;// which platform the task coming from

	ServerPerson withServerPerson;
	ServerSolution serverSolution;


	public ServerTask(Double idTask, ServerPerson serverPerson, String taskText, Date start,
					  Date end, String address, int whatToDo, int platform) {
		this.idTask = idTask;
		this.serverPerson = serverPerson;
		this.taskText = taskText;
		this.start = start;
		this.end = end;
		this.address = address;
		this.whatToDo = whatToDo;
		this.platform = platform;
	}

	public Double getIdTask() {
		return idTask;
	}

	public void setIdTask(Double idTask) {
		this.idTask = idTask;
	}

	public ServerPerson getServerPerson() {
		return serverPerson;
	}

	public void setServerPerson(ServerPerson serverPerson) {
		this.serverPerson = serverPerson;
		serverPerson.addtask(this);
	}

	public String getTaskText() {
		return taskText;
	}

	public void setTaskText(String taskText) {
		this.taskText = taskText;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getWhatToDo() {
		return whatToDo;
	}

	public void setWhatToDo(int whatToDo) {
		this.whatToDo = whatToDo;
	}

	public int getPlatform() {
		return platform;
	}

	public void setPlatform(int platform) {
		this.platform = platform;
	}

	public ServerPerson getWithServerPerson() {
		return withServerPerson;
	}

	public void setWithServerPerson(ServerPerson withServerPerson) {
		this.withServerPerson = withServerPerson;
	}

	public ServerSolution getServerSolution() {
		return serverSolution;
	}

	public void setServerSolution(ServerSolution serverSolution) {
		this.serverSolution = serverSolution;
	}

	public Task convertServerTask(){
		Task task = new Task(1,getTaskText(),getStart().getTime(),getEnd().getTime(),getAddress());
		task.setSolution(getServerSolution().convertServerSolution());
		return task;

	}
}
