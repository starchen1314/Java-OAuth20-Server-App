package com.dev3l.ws.helwoho.resource;

import javax.inject.Inject;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.logging.log4j.Logger;

import com.dev3l.helwoho.data.entity.AuthenticationTokenEntity;
import com.dev3l.helwoho.ws.ResourceConstants;
import com.dev3l.ws.helwoho.agent.AuthenticationTokenAgent;
import com.dev3l.ws.helwoho.agent.UserAgent;

@Path(ResourceConstants.ROOT_PATH_AUTHENTICATION_TOKEN)
@Produces(MediaType.APPLICATION_JSON)
public class AuthenticationTokenResource {
	private AuthenticationTokenAgent authenticationTokenAgent;
	private UserAgent userAgent;
	private Logger logger;

	@Deprecated
	public AuthenticationTokenResource() {
	}

	@Inject
	public AuthenticationTokenResource(final AuthenticationTokenAgent authenticationTokenAgent, final UserAgent userAgent,
			final Logger logger) {
		this.authenticationTokenAgent = authenticationTokenAgent;
		this.userAgent = userAgent;
		this.logger = logger;
	}

	@GET
	// curl http://localhost:8080/HelwohoWebServices/token?token={token}
	public Response get(@QueryParam("token") final String token) {
		final AuthenticationTokenEntity authenticationTokenEntity = authenticationTokenAgent.getByToken(token);

		if (authenticationTokenEntity == null) {
			logger.warn("Failed to retrieve AuthenticationToken by token: " + token);
			return Response.status(Status.EXPECTATION_FAILED).build();
		}

		return Response.ok().entity(new GenericEntity<AuthenticationTokenEntity>(authenticationTokenEntity) {}).build();
	}

	@POST
	// curl -X POST http://localhost:8080/HelwohoWebServices/token -d "user=1"
	public Response create(final @FormParam("user") Long userId) {
		if (userAgent.get(userId) == null) {
			logger.warn("Failed to retrieve User by userId: " + userId);
			return Response.status(Status.EXPECTATION_FAILED).build();
		}

		final AuthenticationTokenEntity authenticationTokenEntity = authenticationTokenAgent.create(userId);

		if (authenticationTokenEntity == null) {
			logger.warn("Error occured while creating AuthenticationToken for user: " + userId);
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		}

		return Response.ok().entity(new GenericEntity<AuthenticationTokenEntity>(authenticationTokenEntity) {}).build();
	}
}
