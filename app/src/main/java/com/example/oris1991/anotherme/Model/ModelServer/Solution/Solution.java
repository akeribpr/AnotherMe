package com.example.oris1991.anotherme.Model.ModelServer.Solution;

import com.example.oris1991.anotherme.Model.ModelServer.Task.PopUp;
import com.example.oris1991.anotherme.Model.ModelServer.Task.Task;
import com.example.oris1991.anotherme.Model.ModelServer.person.Person;
import com.example.oris1991.anotherme.Model.ModelServer.sms.SMS;

import java.io.Serializable;
import java.util.Date;


public class Solution implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// //string-> int
	public final static int Nothing = 1;
	public final static int Ask = 2;
	public final static int popUP = 3;
	public final static int Sms = 4;
	public final static int Ticket = 5;//movie
	public final static int Shoping = 6;//Shopping
	public final static int BabySiter = 7;//Babysitter
	public final static int Meeting = 8;//meeting
	public final static int TIME = 15;

	// /////////////////////////////
	Double idSolution;
	Task task;
	SMS sms;
	Date toDo;
	PopUp popUp;
	Person person;
	int action;
	int timeToArriving=TIME;


	public Solution(Double idSolution, Person person, Task task, SMS sms,
			PopUp popUp, int action) {
		this.idSolution = idSolution;
		this.person = person;
		this.task = task;
		this.sms = sms;
		this.popUp = popUp;
		this.action = action;
	}

	public void setPerson(Person person) {
		this.person = person;
		person.addSolution(this);
	}

	public Double getIdSolution() {
		return idSolution;
	}

	public void setIdSolution(Double idSolution) {
		this.idSolution = idSolution;
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public SMS getSms() {
		return sms;
	}

	public void setSms(SMS sms) {
		this.sms = sms;
	}

	public Date getToDo() {
		return toDo;
	}

	public void setToDo(Date toDo) {
		this.toDo = toDo;
	}

	public PopUp getPopUp() {
		return popUp;
	}

	public void setPopUp(PopUp popUp) {
		this.popUp = popUp;
	}

	public int getAction() {
		return action;
	}

	public void setAction(int action) {
		this.action = action;
	}

	public Person getPerson() {
		return person;
	}
	public int getTimeToArriving() {
		return timeToArriving;
	}

	public void setTimeToArriving(int timeToArriving) {
		this.timeToArriving = timeToArriving;
	}


}
