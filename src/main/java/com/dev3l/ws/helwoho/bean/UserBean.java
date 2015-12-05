package com.dev3l.ws.helwoho.bean;

import javax.xml.bind.annotation.XmlRootElement;

import com.dev3l.helwoho.ws.ObjectAbstract;

@XmlRootElement
public class UserBean extends ObjectAbstract {
	private Long id;
	private String userName;
	private String email;
	private String status;
	private String password;

	/**
	 * @return the id
	 */
	public final Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public final void setId(final Long id) {
		this.id = id;
	}

	/**
	 * @return the userName
	 */
	public final String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public final void setUserName(final String userName) {
		this.userName = userName;
	}

	/**
	 * @return the email
	 */
	public final String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public final void setEmail(final String email) {
		this.email = email;
	}

	/**
	 * @return the status
	 */
	public final String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public final void setStatus(final String status) {
		this.status = status;
	}

	/**
	 * @return the password
	 */
	public final String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public final void setPassword(final String password) {
		this.password = password;
	}

}