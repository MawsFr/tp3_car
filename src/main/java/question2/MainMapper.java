package question2;

import java.io.UnsupportedEncodingException;

import com.typesafe.config.ConfigFactory;

import acteurs.Constants;
import akka.actor.ActorSystem;

public class MainMapper {

	public static void main(String[] args) throws UnsupportedEncodingException {
		Constants.init();
		ActorSystem.create(Constants.MAPPER_SYSTEM, ConfigFactory.load(Constants.CONFIG_MAPPERS));
		
	}

}
