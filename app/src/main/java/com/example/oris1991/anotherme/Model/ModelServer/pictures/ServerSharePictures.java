package com.example.oris1991.anotherme.Model.ModelServer.pictures;

import com.example.oris1991.anotherme.Model.ModelServer.person.ServerPerson;

import java.util.Date;


public class ServerSharePictures {

	
	private Double idPictures;// id for pictures
	private String pictureName;
	private ServerPerson serverPerson;
	private Date datePic;
	private ServerPerson withServerPerson;
	private String txt;
	private Boolean sendToPerson;
	
	public ServerSharePictures(Double idPictures, String pictureName, ServerPerson serverPerson, Date datePic, ServerPerson withServerPerson, String txt, Boolean sendToPerson){
		this.idPictures=idPictures;
		this.pictureName=pictureName;
		this.serverPerson = serverPerson;
		this.datePic=datePic;
		this.withServerPerson = withServerPerson;
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
	public ServerPerson getWithServerPerson() {
		return withServerPerson;
	}
	public void setWithServerPerson(ServerPerson withServerPerson) {
		this.withServerPerson = withServerPerson;
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
