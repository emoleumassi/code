package de.emo.cit.tuberlin;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import de.emo.cit.tuberlin.bootstrap.ThesisConfiguration;
//@Component
@Path("/webservice")
@Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
public class JaxService {
	// implements WebApplicationInitializer {

	@Autowired
	Emo emo;
	@Autowired
	Track track;

	// @Autowired
	// UDDIDao uddiDao;

	// @SuppressWarnings("resource")
	// @Override
	// public void onStartup(ServletContext arg0) throws ServletException {
	// ApplicationContext applicationContext = new
	// AnnotationConfigApplicationContext(
	// ThesisConfiguration.class);
	// AutowireCapableBeanFactory acbFactory = applicationContext
	// .getAutowireCapableBeanFactory();
	// acbFactory.autowireBean(this);
	//
	// }

	@SuppressWarnings("resource")
	@GET
	public Track test() {

		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(
				ThesisConfiguration.class);
		AutowireCapableBeanFactory acbFactory = applicationContext
				.getAutowireCapableBeanFactory();
		acbFactory.autowireBean(this);

		emo.setNom("Mon nom est Emo Leumassi Ferdinand Frederic");
		track.setSinger("je suis le singer new singer");
		track.setTitle("voici le titel");
		track.setEmo(emo);
		return track;
	}

	// @GET
	// @Path("/all")
	// public void uddiTest() {
	// uddiDao.listUDDI();
	// }
}
