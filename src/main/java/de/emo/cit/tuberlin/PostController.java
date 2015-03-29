package de.emo.cit.tuberlin;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import de.emo.cit.tuberlin.bootstrap.ThesisConfiguration;
import de.emo.cit.tuberlin.help.CheckJsonData;
import de.emo.cit.tuberlin.help.RequestHelp;
import de.emo.cit.tuberlin.model.ThesisRoot;
import de.emo.cit.tuberlin.service.PostService;

/**
 * 
 * @author emoleumassi
 * 
 */
@Path("/webservice")
@Produces(MediaType.APPLICATION_JSON)
public class PostController {
	
	@Autowired
	private PostService postService;

	@SuppressWarnings({ "resource" })
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/send")
	public Response postUDDISLA(ThesisRoot thesisRoot) {

		new CheckJsonData(thesisRoot);

		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(
				ThesisConfiguration.class);
		AutowireCapableBeanFactory acbFactory = applicationContext
				.getAutowireCapableBeanFactory();
		acbFactory.autowireBean(this);

		postService.setUUID(thesisRoot);

		return Response.status(RequestHelp.OK).entity(thesisRoot).build();
	}
}
