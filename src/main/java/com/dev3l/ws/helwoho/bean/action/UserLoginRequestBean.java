package com.dev3l.ws.helwoho.bean.action;

import javax.xml.bind.annotation.XmlRootElement;

import com.dev3l.helwoho.ws.ObjectAbstract;

@XmlRootElement
public class UserLoginRequestBean extends ObjectAbstract {
	private String userName;
	private String password;

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