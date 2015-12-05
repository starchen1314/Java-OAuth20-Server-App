package com.dev3l.ws.helwoho.agent.action;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.dev3l.helwoho.data.entity.manager.AuthenticationTokenManager;

@Singleton
public class AuthenticationTokenTouchAgent {
	private final AuthenticationTokenManager authenticationTokenManager;

	@Inject
	public AuthenticationTokenTouchAgent() {
		this.authenticationTokenManager = null;
	}

	public boolean touch(final Long authenticationTokenId) {
		return authenticationTokenManager.touchToken(authenticationTokenId) != null;
	}
}
