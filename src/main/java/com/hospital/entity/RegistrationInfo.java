package com.hospital.entity;

import java.util.Objects;

public class RegistrationInfo {
	
	private String firstname;
	private String lastname;
	private String login;
	private String password;
	private long roleId;


	public RegistrationInfo() {
	}

	public RegistrationInfo(String firstname, String lastname, String login, String password, long roleId) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.login = login;
		this.password = password;
		this.roleId = roleId;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
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

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	@Override
	public boolean equals(Object o) {

		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		RegistrationInfo that = (RegistrationInfo) o;
		return roleId == that.roleId && Objects.equals(firstname, that.firstname) && Objects.equals(lastname, that.lastname) && Objects.equals(login, that.login) && Objects.equals(password, that.password);
	}

	@Override
	public int hashCode() {
		return Objects.hash(firstname, lastname, login, password, roleId);
	}

	@Override
	public String toString() {
		return "RegistrationInfo{" +
				"firstname='" + firstname + '\'' +
				", lastname='" + lastname + '\'' +
				", login='" + login + '\'' +
				", password='" + password + '\'' +
				", roleId=" + roleId +
				'}';
	}
}
