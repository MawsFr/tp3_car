package question2;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import com.typesafe.config.ConfigFactory;

import acteurs.Constants;
import acteurs.Mapper;
import acteurs.MapperImpl;
import acteurs.Master;
import acteurs.MasterImpl;
import acteurs.Reducer;
import acteurs.ReducerImpl;
import akka.actor.ActorSystem;
import akka.actor.TypedActor;
import akka.actor.TypedProps;
import question1.Main3;

public class MainMasterReducer {
	public static final String FILENAME = "texte.txt";

	public static void main(final String[] args) throws UnsupportedEncodingException {
		Constants.init();
		final ActorSystem system = ActorSystem.create(Constants.MASTER_REDUCERS_SYSTEM,
				ConfigFactory.load(Constants.CONFIG_MASTER_REDUCERS));

		final List<Mapper> mappers = new ArrayList<>();
		final List<Reducer> reducers = new ArrayList<>();

		final Master master = TypedActor.get(system).typedActorOf(
				new TypedProps<>(Master.class, MasterImpl.class));
		final Mapper mapper1 = TypedActor.get(system).typedActorOf(new TypedProps<>(Mapper.class, MapperImpl.class),
				Constants.MAPPER1_ADRESS);
		final Mapper mapper2 = TypedActor.get(system).typedActorOf(new TypedProps<>(Mapper.class, MapperImpl.class),
				Constants.MAPPER2_ADRESS);
		final Mapper mapper3 = TypedActor.get(system).typedActorOf(new TypedProps<>(Mapper.class, MapperImpl.class),
				Constants.MAPPER3_ADRESS);
		final Reducer reducer1 = TypedActor.get(system)
				.typedActorOf(new TypedProps<>(Reducer.class, ReducerImpl.class));
		final Reducer reducer2 = TypedActor.get(system)
				.typedActorOf(new TypedProps<>(Reducer.class, ReducerImpl.class));

		mappers.add(mapper1);
		mappers.add(mapper2);
		mappers.add(mapper3);

		reducers.add(reducer1);
		reducers.add(reducer2);

		master.setReducers(reducers);
		master.setMappers(mappers);

		mapper1.setReducers(reducers);
		mapper2.setReducers(reducers);
		mapper3.setReducers(reducers);

		try {
			master.countWords(Main3.FILENAME);
			System.out.println(master.print());
		} catch (final IOException e) {
			e.printStackTrace();
		}

		system.terminate();

	}


}
