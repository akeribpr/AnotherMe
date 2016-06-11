package com.example.oris1991.anotherme.Model.ModelServer.pictures;

import com.example.oris1991.anotherme.Model.ModelServer.person.ServerPerson;

import java.util.Date;


public class ServerPictures {

	private Double idpicture;// id for pictures
	private String pictureName;
	private ServerPerson serverPerson;
	private Date datePic;
	
	
	public ServerPictures(Double idpicture, String pictureName, ServerPerson serverPerson, Date datePic){
		this.pictureName=pictureName;
		this.serverPerson = serverPerson;
		this.datePic=datePic;
		this.idpicture=idpicture;
				
	}
	public String getPictureName() {
		return pictureName;
	}


	public void setPictureName(String pictureName) {
		this.pictureName = pictureName;
	}


	public ServerPerson getServerPerson() {
		return serverPerson;
	}


	public void setServerPerson(ServerPerson serverPerson) {
		this.serverPerson = serverPerson;
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
