package eu.scape_project.watch.rest.resource;

import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import com.sun.jersey.spi.resource.Singleton;
import com.wordnik.swagger.core.Api;

/**
 * {@link MeasurementResource} with XML output.
 * 
 * @author Luis Faria <lfaria@keep.pt>
 * 
 */
@Path("/measurement.xml")
@Api(value = "/measurement", description = "Operations about Measurements")
@Singleton
@Produces({"application/xml"})
public class MeasurementResourceXML extends MeasurementResource {

}
