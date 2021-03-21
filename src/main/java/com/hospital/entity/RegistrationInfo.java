package com.hospital.entity;

import java.util.Objects;

public class RegistrationInfo {
	
	private String name;
	private String surname;
	private String login;
	private String password;


	public RegistrationInfo() {
	}

	public RegistrationInfo(String name, String surname, String login, String password) {
		this.name = name;
		this.surname = surname;
		this.login = login;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		RegistrationInfo that = (RegistrationInfo) o;
		return Objects.equals(name, that.name) && Objects.equals(surname, that.surname) && Objects.equals(login, that.login) && Objects.equals(password, that.password);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, surname, login, password);
	}

	@Override
	public String toString() {
		return "RegistrationInfo{" +
				"name='" + name + '\'' +
				", surname='" + surname + '\'' +
				", login='" + login + '\'' +
				", password='" + password + '\'' +
				'}';
	}
}
