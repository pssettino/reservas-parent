package com.reservas.model;

import java.sql.Date;

/**
 * @author pablo gabriel settino
 * Fecha: 2017-07-22 
 * Copyright 2017
 */
public class PersistentTokenBO {
	
	private UsuarioBO usuario;
	
	private String series;

	private String token;
	
	private Date tokenDate;
	
	private String ipAddress;
	
	public UsuarioBO getUsuario() {
		return usuario;
	}

	public String getToken() {
		return token;
	}

	public Date getTokenDate() {
		return tokenDate;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUsuario(UsuarioBO usuario) {
		this.usuario = usuario;
	}

	public void setToken(String token) {
		this.token = token;
	}

	private String userAgent;
	
	public String getTokenValue(){
		return "";
	}
	
	public UsuarioBO getUser(){
		return new UsuarioBO();
	}
	
	public void setSeries(String dato){
		this.series = dato;
	}
	
	public String getSeries(){
		return series;
	}
	
	public void setUser(UsuarioBO user){
		this.usuario = user;
		
	}
	
	public void setTokenValue(String tokenValue){
		this.token = tokenValue;
	}
	
	public void setTokenDate(Date fecha){
		this.tokenDate = fecha;
	}
	
	public void setIpAddress(String ipAddress){
		this.ipAddress = ipAddress;
	}
	
	public void setUserAgent(String user){
		this.userAgent = user;
	}
	
}

