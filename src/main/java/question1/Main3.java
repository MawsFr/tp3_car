package question1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import acteurs.Mapper;
import acteurs.MapperImpl;
import acteurs.Master;
import acteurs.MasterImpl;
import acteurs.Reducer;
import acteurs.ReducerImpl;
import akka.actor.ActorSystem;
import akka.actor.TypedActor;
import akka.actor.TypedActorExtension;
import akka.actor.TypedProps;

public class Main3 {
	public static final String FILENAME = "texte.txt";
	public static final String SYSTEM_NAME = "MapReduceSystem";
	public static final String MASTER_NAME = "master";
	public static final String MAPPER1_NAME = "master1";
	public static final String MAPPER2_NAME = "master2";
	public static final String REDUCER1_NAME = "reducer1";
	public static final String REDUCER2_NAME = "reducer2";
	public static final String REDUCER3_NAME = "reductecalcalr3";

	public static void main(final String[] args) {
		final ActorSystem system = ActorSystem.create(SYSTEM_NAME);
		TypedActorExtension extension = TypedActor.get(system);
		final List<Mapper> mappers = new ArrayList<>();
		final List<Reducer> reducers = new ArrayList<>();

		final Master master = TypedActor.get(system).typedActorOf(new TypedProps<>(Master.class, MasterImpl.class));
		final Mapper mapper1 = TypedActor.get(system).typedActorOf(new TypedProps<>(Mapper.class, MapperImpl.class));
		final Mapper mapper2 = TypedActor.get(system).typedActorOf(new TypedProps<>(Mapper.class, MapperImpl.class));
		final Reducer reducer1 = TypedActor.get(system).typedActorOf(new TypedProps<>(Reducer.class, ReducerImpl.class));
		final Reducer reducer2 = TypedActor.get(system).typedActorOf(new TypedProps<>(Reducer.class, ReducerImpl.class));
		final Reducer reducer3 = TypedActor.get(system).typedActorOf(new TypedProps<>(Reducer.class, ReducerImpl.class));
		
		mappers.add(mapper1);
		mappers.add(mapper2);

		reducers.add(reducer1);
		reducers.add(reducer2);
		reducers.add(reducer3);

		master.setMappers(mappers);
		master.setReducers(reducers);
		
		mapper1.setReducers(reducers);
		mapper2.setReducers(reducers);
		
		try {
			master.countWords(FILENAME);
			System.out.println(master.print());
		} catch (final IOException e) {
			e.printStackTrace();
		}

		extension.stop(reducer3);
		extension.stop(reducer2);
		extension.stop(reducer1);
		extension.stop(mapper2);
		extension.stop(mapper1);
		extension.stop(master);

		system.terminate();

	}
}
