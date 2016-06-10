package com.example.oris1991.anotherme.Model.ModelServer.Task;

import com.example.oris1991.anotherme.Model.ModelServer.person.Person;

import java.io.Serializable;


import java.util.Date;


public class PopUp implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Double idPopUp;
	String text;
	boolean popUpTamplates;
	Date DateTimeShow; // if it show if not ->null
	Person senderId;// send popUp to other user
	Person personId;

	public PopUp(Double idPopUp, String text, boolean popUpTamplates,Person senderId,
			Person personId) {
		this.idPopUp = idPopUp;
		this.text = text;
		this.popUpTamplates = popUpTamplates;
		this.personId = personId;
		this.senderId=senderId;
	}

	public void setPerson(Person person) {
		this.personId = person;
		person.addpopUp(this);
	}

	public Double getIdPopUp() {
		return idPopUp;
	}

	public void setIdPopUp(Double idPopUp) {
		this.idPopUp = idPopUp;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean isPopUpTamplates() {
		return popUpTamplates;
	}

	public void setPopUpTamplates(boolean popUpTamplates) {
		this.popUpTamplates = popUpTamplates;
	}

	public Date getDateTimeShow() {
		return DateTimeShow;
	}

	public void setDateTimeShow(Date dateTimeShow) {
		DateTimeShow = dateTimeShow;
	}

	public Person getSenderId() {
		return senderId;
	}

	public void setSenderId(Person senderId) {
		this.senderId = senderId;
	}

	public Person getPersonId() {
		return personId;
	}

	public void setPersonId(Person personId) {
		this.personId = personId;
	}

}
