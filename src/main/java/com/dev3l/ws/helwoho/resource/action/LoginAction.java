package com.dev3l.ws.helwoho.resource.action;

import java.io.FileNotFoundException;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.Logger;

import com.dev3l.helwoho.data.entity.AuthenticationTokenEntity;
import com.dev3l.helwoho.ws.ResourceConstants;
import com.dev3l.ws.helwoho.agent.AuthenticationTokenAgent;
import com.dev3l.ws.helwoho.agent.action.UserLoginAgent;
import com.dev3l.ws.helwoho.bean.UserBean;
import com.dev3l.ws.helwoho.bean.action.UserLoginRequestBean;

@Path(ResourceConstants.ROOT_PATH_USER_ACTION)
@Produces(MediaType.APPLICATION_JSON)
public class LoginAction {
	private AuthenticationTokenAgent authenticationTokenAgent;
	private UserLoginAgent userLoginAgent;
	private Logger logger;

	@Deprecated
	public LoginAction() {
	}

	@Inject
	public LoginAction(final AuthenticationTokenAgent authenticationTokenAgent, final UserLoginAgent userLoginAgent, final Logger logger) {
		this.authenticationTokenAgent = authenticationTokenAgent;
		this.userLoginAgent = userLoginAgent;
		this.logger = logger;
	}

	@POST
	@Path(ResourceConstants.ACTION_PATH_LOGIN)
	@Consumes(MediaType.APPLICATION_JSON)
	// curl -X POST http://localhost:8080/HelwohoWebServices/user/action/login -H "Content-Type: application/json" -d '{"userName":"user_name","password":"password"}'
	public Response login(final UserLoginRequestBean userLoginRequestBean) {
		UserBean userBean = null;

		try {
			userBean = userLoginAgent.canUserLogin(userLoginRequestBean);
		} catch (final FileNotFoundException e) {
			logger.error(e.getMessage(), e);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}

		if (userBean == null) {
			return Response.status(Response.Status.UNAUTHORIZED).build();
		}

		return Response.ok().entity(new GenericEntity<AuthenticationTokenEntity>(authenticationTokenAgent.create(userBean.getId())) {})
				.build();
	}
}
