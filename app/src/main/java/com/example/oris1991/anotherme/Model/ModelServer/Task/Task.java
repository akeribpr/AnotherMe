package com.example.oris1991.anotherme.Model.ModelServer.Task;

import com.example.oris1991.anotherme.Model.ModelServer.Solution.Solution;
import com.example.oris1991.anotherme.Model.ModelServer.person.Person;

import java.io.Serializable;
import java.util.Date;



public class Task implements Serializable {
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
	Person person;
	String taskText;
	Date start;
	Date end;
	String address;
	int whatToDo;
	int platform;// which platform the task coming from

	Person withPerson;
	Solution solution;

	public Task(Double idTask, Person person, String taskText, Date start,
			Date end, String address, int whatToDo, int platform) {
		this.idTask = idTask;
		this.person = person;
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

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
		person.addtask(this);
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

	public Person getWithPerson() {
		return withPerson;
	}

	public void setWithPerson(Person withPerson) {
		this.withPerson = withPerson;
	}

	public Solution getSolution() {
		return solution;
	}

	public void setSolution(Solution solution) {
		this.solution = solution;
	}

}
