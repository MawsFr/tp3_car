package question2;

import java.io.UnsupportedEncodingException;

import com.typesafe.config.ConfigFactory;

import acteurs.Constants;
import acteurs.Mapper;
import acteurs.MapperImpl;
import akka.actor.ActorSystem;
import akka.actor.Address;
import akka.actor.AddressFromURIString;
import akka.actor.Deploy;
import akka.actor.TypedActor;
import akka.actor.TypedProps;
import akka.remote.RemoteScope;

public class MainMapper {

	public static void main(String[] args) throws UnsupportedEncodingException {
		Constants.init();
		final ActorSystem system = ActorSystem.create(Constants.MAPPER_SYSTEM, ConfigFactory.load(Constants.CONFIG_MAPPERS));
		final Address addr = AddressFromURIString.parse(Constants.ADDRESS_MAPPERS_SYSTEM);
		final Mapper mapper1 = TypedActor.get(system).typedActorOf(
				new TypedProps<>(Mapper.class, MapperImpl.class).withDeploy(new Deploy(new RemoteScope(addr))),
				Constants.MAPPER1_NAME);
		final Mapper mapper2 = TypedActor.get(system).typedActorOf(
				new TypedProps<>(Mapper.class, MapperImpl.class).withDeploy(new Deploy(new RemoteScope(addr))),
				Constants.MAPPER2_NAME);
		final Mapper mapper3 = TypedActor.get(system).typedActorOf(
				new TypedProps<>(Mapper.class, MapperImpl.class).withDeploy(new Deploy(new RemoteScope(addr))),
				Constants.MAPPER3_NAME);
	}

}
