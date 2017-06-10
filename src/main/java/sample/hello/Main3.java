package sample.hello;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import akka.actor.ActorSystem;
import akka.actor.TypedActor;
import akka.actor.TypedProps;

public class Main3 {
	public static final String FILENAME = "texte.txt";
	public static final String SYSTEM_NAME = "MapReduceSystem";
	public static final String MASTER_NAME = "master";
	public static final String MAPPER1_NAME = "master1";
	public static final String MAPPER2_NAME = "master2";
	public static final String REDUCER1_NAME = "reducer1";
	public static final String REDUCER2_NAME = "reducer2";
	public static final String REDUCER3_NAME = "reducter3";

	public static void main(String[] args) {
		ActorSystem system = ActorSystem.create(SYSTEM_NAME);
		
		List<Mapper> mappers = new ArrayList<>();
		List<Reducer> reducers = new ArrayList<>();

		Master master = TypedActor.get(system).typedActorOf(new TypedProps<>(Master.class, MasterImpl.class));
		Mapper mapper1 = TypedActor.get(system).typedActorOf(new TypedProps<>(Mapper.class, MapperImpl.class));
		Mapper mapper2 = TypedActor.get(system).typedActorOf(new TypedProps<>(Mapper.class, MapperImpl.class));
		Reducer reducer1 = TypedActor.get(system).typedActorOf(new TypedProps<>(Reducer.class, ReducerImpl.class));
		Reducer reducer2 = TypedActor.get(system).typedActorOf(new TypedProps<>(Reducer.class, ReducerImpl.class));
		Reducer reducer3 = TypedActor.get(system).typedActorOf(new TypedProps<>(Reducer.class, ReducerImpl.class));
		
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
		} catch (IOException e) {
			e.printStackTrace();
		}

		system.terminate();

	}
}
