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
    @Path("/login")
    @Template(name = "/protected/loginPage.jsp")
    public Response getStartPage() {
        return Response.ok("secured pages").build();
    }
}
