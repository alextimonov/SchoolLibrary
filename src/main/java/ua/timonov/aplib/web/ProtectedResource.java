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
@Path("/protected")
@Produces(MediaType.TEXT_HTML)
public class ProtectedResource {

    @GET
    @Path("/start")
    @Template(name = "/protected/start.jsp")
    public Response getProtectedPage() {
        return Response.ok("Security pages").build();
    }

    @GET
    @Path("/signin")
    @Template(name = "/protected/signin.jsp")
    public Response getSignInPage() {
        return Response.ok("signin").build();
    }

    @GET
    @Path("/signin-failure")
    @Template(name = "/protected/signinFailure.jsp")
    public Response getSignInFailurePage() {
        return Response.ok("signinFailure").build();
    }
}
