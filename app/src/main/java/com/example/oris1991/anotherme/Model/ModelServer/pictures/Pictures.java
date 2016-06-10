package com.example.oris1991.anotherme.Model.ModelServer.pictures;

import com.example.oris1991.anotherme.Model.ModelServer.person.Person;

import java.util.Date;


public class Pictures {

	private Double idpicture;// id for pictures
	private String pictureName;
	private Person person;
	private Date datePic;
	
	
	public Pictures(Double idpicture,String pictureName,Person person,Date datePic){
		this.pictureName=pictureName;
		this.person=person;
		this.datePic=datePic;
		this.idpicture=idpicture;
				
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
	public Double getIdpicture() {
		return idpicture;
	}
	public void setIdpicture(Double idpicture) {
		this.idpicture = idpicture;
	}
	
}
