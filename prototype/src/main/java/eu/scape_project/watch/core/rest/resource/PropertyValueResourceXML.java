package eu.scape_project.watch.core.rest.resource;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.sun.jersey.spi.resource.Singleton;
import com.wordnik.swagger.core.Api;

@Path("/propertyvalue.xml")
@Api(value = "/propertyvalue", description = "Operations about property values")
@Singleton
@Produces({ "application/xml" })
public class PropertyValueResourceXML extends PropertyValueResource {

}