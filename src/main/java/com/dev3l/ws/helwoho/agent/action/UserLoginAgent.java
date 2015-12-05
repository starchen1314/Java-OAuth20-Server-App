package com.dev3l.ws.helwoho.agent.action;

import java.io.FileNotFoundException;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.apache.logging.log4j.Logger;

import com.dev3l.helwoho.data.entity.UserEntity;
import com.dev3l.helwoho.data.entity.manager.action.UserAction;
import com.dev3l.ws.helwoho.agent.UserAgent;
import com.dev3l.ws.helwoho.bean.UserBean;
import com.dev3l.ws.helwoho.bean.action.UserLoginRequestBean;

@Singleton
public class UserLoginAgent {
	private UserAction userAction;
	private UserAgent userAgent;
	private Logger logger;

	@Deprecated
	public UserLoginAgent() {
	}

	@Inject
	public UserLoginAgent(final UserAgent userAgent, final UserAction userAction, final Logger logger) {
		this.userAgent = userAgent;
		this.userAction = userAction;
		this.logger = logger;
	}

	public UserBean canUserLogin(final UserLoginRequestBean userLoginRequestBean) throws FileNotFoundException {
		final UserEntity userEntity = userAgent.getEntityByUserName(userLoginRequestBean.getUserName());

		if (!userAction.isValidPasswordForUser(userEntity, userLoginRequestBean.getPassword())) {
			logger.warn("Could not validate password for user: " + userLoginRequestBean.getUserName());
			return null;
		}

		return userAgent.mapEntityIntoBean(userEntity);
	}
}
