package com.dev3l.ws.helwoho.agent;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.dev3l.helwoho.data.entity.AuthenticationTokenEntity;
import com.dev3l.helwoho.data.entity.manager.AuthenticationTokenManager;

@Singleton
public class AuthenticationTokenAgent {
	private final AuthenticationTokenManager authenticationTokenManager;

	@Inject
	public AuthenticationTokenAgent(final AuthenticationTokenManager authenticationTokenManager) {
		this.authenticationTokenManager = authenticationTokenManager;
	}

	public AuthenticationTokenEntity getByToken(final String token) {
		return authenticationTokenManager.getByToken(token);
	}

	public AuthenticationTokenEntity create(final Long userId) {
		return authenticationTokenManager.create(userId);
	}
}
