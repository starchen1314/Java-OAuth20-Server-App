package com.dev3l.ws.helwoho.mapper;

import javax.inject.Inject;

import com.dev3l.helwoho.data.entity.UserEntity;
import com.dev3l.ws.helwoho.bean.UserBean;

public class UserBeanMapper {
	@Inject
	public UserBeanMapper() {
	}

	public UserBean map(final UserEntity userEntity) {
		if (userEntity == null) {
			return null;
		}

		final UserBean userBean = new UserBean();
		userBean.setEmail(userEntity.getEmail());
		userBean.setId(userEntity.getUserId());
		userBean.setPassword(userEntity.getPassword());
		userBean.setStatus(userEntity.getStatus());
		userBean.setUserName(userEntity.getUserName());
		return userBean;
	}
}
