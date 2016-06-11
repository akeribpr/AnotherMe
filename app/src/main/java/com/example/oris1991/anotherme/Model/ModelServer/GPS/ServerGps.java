package com.example.oris1991.anotherme.Model.ModelServer.GPS;

import com.example.oris1991.anotherme.Model.ModelServer.person.ServerPerson;

import java.io.Serializable;
import java.util.Date;


public class ServerGps implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Double idGps;// id for gps
	Date gpsDate;
	Double x;
	Double y;
	ServerPerson serverPersonId;

	public ServerGps(Double idGps, Double x, Double y, Date gpsDate, ServerPerson serverPersonId) {
		this.idGps = idGps;
		this.x = x;
		this.y = y;
		this.serverPersonId = serverPersonId;
		this.gpsDate = gpsDate;
	}

	public void setPerson(ServerPerson serverPerson) {
		this.serverPersonId = serverPerson;
		serverPerson.addGps(this);
	}

	public Double getIdGps() {
		return idGps;
	}

	public void setIdGps(Double idGps) {
		this.idGps = idGps;
	}

	public Date getGpsDate() {
		return gpsDate;
	}

	public void setGpsDate(Date gpsDate) {
		this.gpsDate = gpsDate;
	}

	public Double getX() {
		return x;
	}

	public void setX(Double x) {
		this.x = x;
	}

	public Double getY() {
		return y;
	}

	public void setY(Double y) {
		this.y = y;
	}

	public ServerPerson getPerson() {
		return serverPersonId;
	}

}