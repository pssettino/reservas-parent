package com.reservas.utils;

import java.io.Serializable;
import java.util.List;


/**
 * @author pablo gabriel settino
 * Fecha: 2017-07-22 
 * Copyright 2017
 */
public class JsonResponse<T extends Serializable> {

	private Boolean success;
	private String message;
	private T item;
	private List<T> rows;

	public JsonResponse() {

	}

	public JsonResponse(T item) {
		this.item = item;
		this.rows = null;
		this.success = true;
		this.message = "";
	}

	public JsonResponse(List<T> rows) {
		this.item = null;
		this.rows = rows;
		this.success = true;
		this.message = "";
	}

	public JsonResponse(Boolean success, String message) {
		this.success = success;
		this.message = message;
	}

	public JsonResponse(String message, T item) {
		this.success = true;
		this.message = message;
		this.item = item;
		this.rows = null;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public void setSuccessFully(String message) {
		this.setSuccess(Boolean.TRUE);
		this.setMessage(message);
	}

	public void setNotSuccessFully(String message) {
		this.setSuccess(Boolean.FALSE);
		this.setMessage(message);
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getItem() {
		return item;
	}

	public void setItem(T item) {
		this.item = item;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}
}
