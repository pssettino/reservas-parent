package com.reservas.controllers;

import java.io.Serializable;

public class PasswordDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4824072409085576771L;
	private String oldPassword;
	private String inputPassword;
	private String inputPassword2;

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getInputPassword() {
		return inputPassword;
	}

	public void setInputPassword(String inputPassword) {
		this.inputPassword = inputPassword;
	}

	public String getInputPassword2() {
		return inputPassword2;
	}

	public void setInputPassword2(String inputPassword2) {
		this.inputPassword2 = inputPassword2;
	}

	public PasswordDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PasswordDTO(String oldPassword, String inputPassword, String inputPassword2) {
		super();
		this.oldPassword = oldPassword;
		this.inputPassword = inputPassword;
		this.inputPassword2 = inputPassword2;
	}

}
