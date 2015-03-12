package de.emo.cit.tuberlin;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import de.emo.cit.tuberlin.bootstrap.ThesisConfiguration;

//@Component
@Path("/webservice")
@Produces(MediaType.APPLICATION_JSON)
public class JaxService {

	@Autowired
	Emo emo;
	@Autowired
	Track track;

	@GET
	public Track test() {

		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(
				ThesisConfiguration.class);
		AutowireCapableBeanFactory acbFactory = applicationContext
				.getAutowireCapableBeanFactory();
		acbFactory.autowireBean(this);

		emo.setNom("Mon nom est Emo Leumassi Ferdinand");
		track.setSinger("je suis le singer new singer");
		track.setTitle("voici le titel with bean and autowired");
		track.setEmo(emo);
		return track;
	}

}
