package com.dev3l.ws.helwoho.datastore;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.inject.Named;

import com.dev3l.ws.helwoho.bean.UserBean;

@Named
public class UserDataStore {
	private UserDataStore() {
	}

	private static Map<Long, UserBean> userBeanMap = new LinkedHashMap<Long, UserBean>();
	static {
		userBeanMap.put(1l, createUserBean(1, "admin", "admin@email.com", "ACT", "admin"));
		userBeanMap.put(2l, createUserBean(2, "user_one", "one@email.com", "ACT", "user_one"));
		userBeanMap.put(3l, createUserBean(3, "user_two", "two@email.com", "ACT", "user_two"));
		userBeanMap.put(4l, createUserBean(4, "user_three", "three@email.com", "ACT", "user_three"));
		userBeanMap.put(5l, createUserBean(5, "user_four", "four@email.com", "DEL", "user_four"));
		userBeanMap.put(6l, createUserBean(6, "user_five", "five@email.com", "ACT", "user_five"));
		userBeanMap.put(7l, createUserBean(7, "user_six", "six@email.com", "HOL", "user_six"));
		userBeanMap.put(8l, createUserBean(8, "user_seven", "seven@email.com", "ACT", "user_seven"));
	}

	private static UserBean createUserBean(final long id, final String userName, final String email, final String status,
			final String password) {
		final UserBean userBean = new UserBean();

		userBean.setEmail(email);
		userBean.setId(id);
		userBean.setPassword(password);
		userBean.setStatus(status);
		userBean.setUserName(userName);

		return userBean;
	}

	public static Map<Long, UserBean> getUserBeanMap() {
		return userBeanMap;
	}
}
