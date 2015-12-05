package com.dev3l.ws.helwoho.resource;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.dev3l.helwoho.ws.ResourceConstants;
import com.dev3l.ws.helwoho.agent.UserAgent;
import com.dev3l.ws.helwoho.bean.UserBean;

@Path(ResourceConstants.ROOT_PATH_USER)
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {
	private UserAgent userAgent;

	@Deprecated
	public UserResource() {
	}

	@Inject
	public UserResource(final UserAgent userAgent) {
		this.userAgent = userAgent;
	}

	@GET
	// curl http://localhost:8080/HelwohoWebServices/user
	public Response getAll() {
		return Response.ok().entity(new GenericEntity<List<UserBean>>(userAgent.get()) {}).build();
	}

	@GET
	@Path(ResourceConstants.PATH_ELEMENT_ID)
	// curl http://localhost:8080/HelwohoWebServices/user/{id}
	public Response get(@PathParam("id") final Long id) {
		final UserBean userBean = userAgent.get(id);

		if (userBean == null) {
			return Response.status(Status.BAD_REQUEST).build();
		}

		return Response.ok().entity(new GenericEntity<UserBean>(userBean) {}).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	// curl -X POST http://localhost:8080/HelwohoWebServices/user -H "Content-Type: application/json" -d '{"userName":"user_name","email":"email@email.com","status":"AC","password":"password"}'
	public Response add(final UserBean userBean) {
		//TODO validation checks here before calling add

		if (userAgent.add(userBean) == null) {
			return Response.ok().entity("Could not add user - " + userBean).build();
		}

		return Response.ok().entity(new GenericEntity<UserBean>(userBean) {}).build();
	}

	@PUT
	@Path(ResourceConstants.PATH_ELEMENT_ID)
	@Consumes(MediaType.APPLICATION_JSON)
	// curl -X PUT http://localhost:8080/HelwohoWebServices/user/{id} -H "Content-Type: application/json" -d '{"id":{id},"userName":"user_name","email":"email@email.com","status":"AC","password":"password"}'
	public Response put(final UserBean userBean) {
		//TODO validation checks here before calling update

		return Response.ok().entity(new GenericEntity<Boolean>(userAgent.update(userBean)) {}).build();
	}

	@DELETE
	@Path(ResourceConstants.PATH_ELEMENT_ID)
	// curl -X DELETE http://localhost:8080/HelwohoWebServices/user/{id}
	public Response delete(@PathParam(ResourceConstants.PATH_PARAM_ELEMENT_ID) final Long id) {
		if (!userAgent.delete(id)) {
			return Response.status(Status.BAD_REQUEST).build();
		}

		return Response.ok().build();
	}
}
