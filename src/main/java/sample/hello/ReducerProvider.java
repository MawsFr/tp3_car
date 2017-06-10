package sample.hello;

import java.util.List;

public class ReducerProvider {
	public static Reducer partition(String word, List<Reducer> reducers) {
		System.out.println("word" + word);
		System.out.println("reducer" + reducers);
		return reducers.get(word.hashCode() % reducers.size());
	}

}
