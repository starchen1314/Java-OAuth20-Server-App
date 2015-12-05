package com.dev3l.ws.helwoho.agent;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.dev3l.helwoho.data.entity.UserEntity;
import com.dev3l.helwoho.data.entity.manager.AuthenticationTokenManager;
import com.dev3l.helwoho.data.entity.manager.UserManager;
import com.dev3l.ws.helwoho.bean.UserBean;
import com.dev3l.ws.helwoho.datastore.UserDataStore;
import com.dev3l.ws.helwoho.mapper.UserBeanMapper;

@Singleton
public class UserAgent {
	private static final Logger logger = LogManager.getLogger();

	private AuthenticationTokenManager authenticationTokenManager;
	private UserManager userManager;
	private UserBeanMapper userBeanMapper;

	@Deprecated
	public UserAgent() {
	}

	@Inject
	public UserAgent(final AuthenticationTokenManager authenticationTokenManager, final UserManager userManager,
			final UserBeanMapper userBeanMapper) {
		this.authenticationTokenManager = authenticationTokenManager;
		this.userManager = userManager;
		this.userBeanMapper = userBeanMapper;
	}

	public UserBean mapEntityIntoBean(final UserEntity userEntity) {
		return userBeanMapper.map(userEntity);
	}

	public List<UserBean> get() {
		if (authenticationTokenManager != null) {
			authenticationTokenManager.create(new Long(1));
		}

		final List<UserBean> userBeans = new ArrayList<UserBean>();

		for (final UserEntity userEntity : userManager.read()) {
			userBeans.add(mapEntityIntoBean(userEntity));
		}

		return userBeans;
	}

	public UserEntity getEntityByUserName(final String userName) {
		return userManager.getByUserName(userName);
	}

	public UserBean getByUserName(final String userName) {
		return mapEntityIntoBean(getEntityByUserName(userName));
	}

	public UserBean get(final long id) {
		return mapEntityIntoBean(userManager.get(id));
	}

	public UserBean add(final UserBean userBean) {
		if (userBean == null) {
			logger.warn("Cannot add a null user");
			return null;
		}

		if (userBean.getId() != null) {
			logger.warn("Cannot add user that already exists... user should not have an id value.");
			return null;
		}

		long maxId = 0;

		for (final Long id : UserDataStore.getUserBeanMap().keySet()) {
			if (id > maxId) {
				maxId = id;
			}
		}

		userBean.setId(++maxId);
		UserDataStore.getUserBeanMap().put(userBean.getId(), userBean);

		return userBean;
	}

	public boolean update(final UserBean userBean) {
		if (userBean == null) {
			logger.warn("Cannot update a null user");
			return false;
		}

		UserDataStore.getUserBeanMap().put(userBean.getId(), userBean);
		return true;
	}

	public boolean delete(final long userBeanId) {
		if (UserDataStore.getUserBeanMap().get(userBeanId) != null) {
			UserDataStore.getUserBeanMap().remove(userBeanId);
			return true;
		}

		logger.warn("Could not delete user: " + userBeanId);
		return false;
	}
}
