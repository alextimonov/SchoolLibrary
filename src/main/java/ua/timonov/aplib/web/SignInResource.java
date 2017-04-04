package ua.timonov.aplib.web;

import org.glassfish.jersey.server.mvc.Template;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 */
@Path("/security")
@Produces(MediaType.TEXT_HTML)
public class SignInResource {
    @GET
    @Path("/signin")
    @Template(name = "/security/signin.jsp")
    public Response getSignInPage() {
        return Response.ok("signin").build();
    }

    @GET
    @Path("/signin-failure")
    @Template(name = "/security/signinFailure.jsp")
    public Response getSignInFailurePage() {
        return Response.ok("signinFailure").build();
    }
}
