package com.example.oris1991.anotherme.Model.ModelServer.pictures;

import com.example.oris1991.anotherme.Model.ModelServer.person.Person;

import java.util.Date;


public class SharePictures {

	
	private Double idPictures;// id for pictures
	private String pictureName;
	private Person person;
	private Date datePic;
	private Person withPerson;
	private String txt;
	private Boolean sendToPerson;
	
	public SharePictures(Double idPictures,String pictureName,Person person,Date datePic,Person withPerson,String txt,Boolean sendToPerson){
		this.idPictures=idPictures;
		this.pictureName=pictureName;
		this.person=person;
		this.datePic=datePic;
		this.withPerson=withPerson;
		this.txt=txt;
		this.sendToPerson=sendToPerson;
	}
	public Double getIdPictures() {
		return idPictures;
	}
	public void setIdPictures(Double idPictures) {
		this.idPictures = idPictures;
	}
	public String getPictureName() {
		return pictureName;
	}
	public void setPictureName(String pictureName) {
		this.pictureName = pictureName;
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public Date getDatePic() {
		return datePic;
	}
	public void setDatePic(Date datePic) {
		this.datePic = datePic;
	}
	public Person getWithPerson() {
		return withPerson;
	}
	public void setWithPerson(Person withPerson) {
		this.withPerson = withPerson;
	}
	public String getTxt() {
		return txt;
	}
	public void setTxt(String txt) {
		this.txt = txt;
	}
	public Boolean getSendToPerson() {
		return sendToPerson;
	}
	public void setSendToPerson(Boolean sendToPerson) {
		this.sendToPerson = sendToPerson;
	}
	
	
}
